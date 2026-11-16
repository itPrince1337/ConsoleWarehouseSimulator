package product.assembly.line;

import product.assembly.line.products.factory.ProductsFactory;
import product.assembly.line.tasks.packer.PackerTask;
import product.assembly.line.tasks.supplier.SupplierTask;
import product.assembly.line.warehouse.Warehouse;
import static java.lang.Thread.currentThread;
import static java.util.Arrays.stream;

public class Runner {
    private static final int AMOUNT_OF_PRODUCTS = 15;
    static void main() {
        ProductsFactory productsFactory = new ProductsFactory();
        Warehouse warehouse = new Warehouse(AMOUNT_OF_PRODUCTS);
        Thread firstSupplierTask = new Thread(new SupplierTask("FIRST_SUPPLIER",AMOUNT_OF_PRODUCTS, productsFactory,
                warehouse));
        Thread secondSupplierTask = new Thread(new SupplierTask("SECOND_SUPPLIER",5,  productsFactory,
                warehouse));
        Thread thirdSupplierTask = new Thread(new SupplierTask("THIRD_SUPPLIER",10,  productsFactory,
                warehouse));

        Thread firstPackerTask = new Thread(new PackerTask("FIRST_PACKER", AMOUNT_OF_PRODUCTS, warehouse));
        Thread secondPackerTask = new Thread(new PackerTask("SECOND_PACKER", 10, warehouse));
        Thread thirdPackerTask = new Thread(new PackerTask("THIRD_PACKER", 5, warehouse));
        startThreads(firstPackerTask, secondPackerTask, thirdPackerTask, firstSupplierTask, secondSupplierTask, thirdSupplierTask);
        joinThreads(firstPackerTask, secondPackerTask, thirdPackerTask, firstSupplierTask, secondSupplierTask, thirdSupplierTask);
    }
    private static void startThreads(Thread... threads){
        stream(threads).forEach(Thread::start);
    }
    private static void joinThreads(Thread... threads){
        stream(threads).forEach(thread -> {
            try{
                thread.join();
            } catch (InterruptedException interruptedException) {
                currentThread().interrupt();
            }
        });
    }
}

