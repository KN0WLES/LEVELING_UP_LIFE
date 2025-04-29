package exceptions;

public class ProductException extends Exception {
    public ProductException(String message) {
        super(message);
    }
    
    public static ProductException notFound() {
        return new ProductException("Producto no encontrado");
    }
    public static ProductException invalidCategory() {
        return new ProductException("Categoría inválida (use C, B o P)");
    }
    public static ProductException outOfStock() {
        return new ProductException("Stock insuficiente");
    }
}