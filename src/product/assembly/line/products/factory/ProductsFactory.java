package product.assembly.line.products.factory;

import product.assembly.line.product.Product;
import java.util.concurrent.atomic.AtomicInteger;
import static java.lang.String.format;

public class ProductsFactory{
    private final String TEMPLATE_MESSAGE_CREATED = "Message %d";
    private final AtomicInteger nextIndexOfProduct = new AtomicInteger(1);

    public Product create() {
        int id = nextIndexOfProduct.getAndIncrement();
        return new Product(format(TEMPLATE_MESSAGE_CREATED, id));
    }
}
