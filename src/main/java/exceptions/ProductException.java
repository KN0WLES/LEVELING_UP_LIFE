package exceptions;

/**
 * Clase que representa excepciones específicas relacionadas con la gestión de productos.
 * Proporciona mensajes de error predefinidos para situaciones comunes, como productos no encontrados,
 * categorías inválidas o falta de stock.
 * 
 * @description Funcionalidades principales:
 *                   - Lanzar una excepción cuando un producto no es encontrado.
 *                   - Lanzar una excepción cuando la categoría de un producto es inválida.
 *                   - Lanzar una excepción cuando no hay suficiente stock de un producto.
 *  
 * @autor KNOWLES
 * @version 1.0
 * @since 2025-04-29
 */
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