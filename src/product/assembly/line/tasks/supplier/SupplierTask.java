package product.assembly.line.tasks.supplier;

import product.assembly.line.product.Product;
import product.assembly.line.products.factory.ProductsFactory;
import product.assembly.line.warehouse.Warehouse;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class SupplierTask implements Runnable{
    private final int maximalAmountOfMessages;
    private final String name;
    private final ProductsFactory productsFactory;
    private final Warehouse warehouse;
    private final int DURATION_SLEEP_BEFORE_SUPPLYING_IN_SECONDS = 1;
    public SupplierTask(String name, int maximalAmountOfMessages, ProductsFactory productsFactory, Warehouse warehouse){
        this.name = name;
        this.maximalAmountOfMessages = maximalAmountOfMessages;
        this.productsFactory = productsFactory;
        this.warehouse = warehouse;

    }
    public int getMaximalAmountOfMessages() {
        return this.maximalAmountOfMessages;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void run() {
        while(!currentThread().isInterrupted()){
            try{
                SECONDS.sleep(DURATION_SLEEP_BEFORE_SUPPLYING_IN_SECONDS);
                Product product =  productsFactory.create();
                warehouse.putProduct(product, this.getName(), this);
            } catch (InterruptedException interruptedException) {
                currentThread().interrupt();
            }
        }
    }
}
