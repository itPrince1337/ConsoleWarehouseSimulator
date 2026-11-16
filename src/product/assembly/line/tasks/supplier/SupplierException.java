package product.assembly.line.tasks.supplier;

public class SupplierException extends RuntimeException {
    public SupplierException() {
        super();
    }
    public SupplierException(String message){
        super(message);
    }
    public SupplierException(String message, Throwable cause) {
        super(message, cause);
    }
}
