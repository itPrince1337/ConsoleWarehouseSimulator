package product.assembly.line.warehouse;

import product.assembly.line.product.Product;
import product.assembly.line.tasks.packer.PackerTask;
import product.assembly.line.tasks.supplier.SupplierTask;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

public final class Warehouse {
    private final int amountOfProducts;
    private final Queue<Product> products;
    private final String TEMPLATE_MESSAGE_WAS_ADDED = "Message with name: '%s' was added in worker with name: '%s'\n";
    private final String TEMPLATE_MESSAGE_WAS_DELETED = "Message with name: '%s' was deleted in worker with name: '%s'\n";

    public Warehouse(int amountOfProducts){
        this.amountOfProducts = amountOfProducts;
        this.products = new ArrayDeque<>(amountOfProducts);
    }

    public synchronized void putProduct(Product product, String workerName, SupplierTask supplierTask){
        try{
            while(this.isShouldPut(supplierTask)){
                wait();
            }
            this.products.add(product);
            out.printf(TEMPLATE_MESSAGE_WAS_ADDED, product, workerName);
            notifyAll();
        } catch (InterruptedException interruptedException){
            currentThread().interrupt();
        }
    }

    public synchronized Optional<Product> takeProduct(String workerName, PackerTask packerTask){
        try {
            while(this.isShouldTake(packerTask)) {
                wait();
            }
            Product product = this.products.poll();
            if (product != null) {
                out.printf(TEMPLATE_MESSAGE_WAS_DELETED, product, workerName);
            }
            notifyAll();
            return ofNullable(product);
        } catch (InterruptedException interruptedException) {
            currentThread().interrupt();
        }
        return empty();
    }

    private boolean isShouldTake(PackerTask packerTask){
        return this.products.isEmpty() ||
                this.products.size() < packerTask.getMinimalAmountOfProducts();
    }

    private boolean isShouldPut(SupplierTask supplierTask){
        return this.products.size() >= this.amountOfProducts
                || this.products.size() >= supplierTask.getMaximalAmountOfMessages();
    }
}