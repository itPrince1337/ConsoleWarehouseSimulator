package product.assembly.line.tasks.packer;

import product.assembly.line.product.Product;
import product.assembly.line.tasks.supplier.SupplierException;
import product.assembly.line.warehouse.Warehouse;

import java.util.Optional;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class PackerTask implements Runnable{
    private final String name;
    private final int minimalAmountOfProducts;
    private final Warehouse warehouse;
    private final int DURATION_SLEEP_BETWEEN_PACKING_IN_SECONDS = 1;
    public PackerTask(String name, int minimalAmountOfProducts, Warehouse warehouse){
        this.name = name;
        this.minimalAmountOfProducts = minimalAmountOfProducts;
        this.warehouse = warehouse;
    }

    public String getName() {
        return this.name;
    }

    public int getMinimalAmountOfProducts() {
        return this.minimalAmountOfProducts;
    }

    @Override
    public void run() {
        while(!currentThread().isInterrupted()){
            try{
                SECONDS.sleep(DURATION_SLEEP_BETWEEN_PACKING_IN_SECONDS);
                Optional<Product> product = this.warehouse.takeProduct(this.getName(),this);
                product.orElseThrow(SupplierException::new);
            }catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }
    }
}
