package interfaces;

import model.Product;
import exceptions.ProductException;
import java.util.List;

/**
 * Interfaz para la gestión de productos.
 */
public interface IProduct {
    
    /**
     * Agrega un nuevo producto.
     * @param product Producto a agregar.
     * @throws ProductException Si ocurre un error al agregar el producto.
     */
    void addProduct(Product product) throws ProductException;
    
    /**
     * Obtiene un producto por su ID.
     * @param id Identificador del producto.
     * @return El producto correspondiente.
     * @throws ProductException Si el producto no existe.
     */
    Product getProductById(String id) throws ProductException;
    
    /**
     * Actualiza un producto existente.
     * @param product Producto con los datos actualizados.
     * @throws ProductException Si el producto no existe o los datos son inválidos.
     */
    void updateProduct(Product product) throws ProductException;
    
    /**
     * Elimina un producto.
     * @param id Identificador del producto a eliminar.
     * @throws ProductException Si el producto no existe.
     */
    void deleteProduct(String id) throws ProductException;
    
    /**
     * Obtiene productos por categoría.
     * @param category Categoría de los productos.
     * @return Lista de productos en la categoría.
     * @throws ProductException Si ocurre un error al obtener los productos.
     */
    List<Product> getProductsByCategory(char category) throws ProductException;
    
    /**
     * Obtiene productos disponibles.
     * @return Lista de productos disponibles.
     * @throws ProductException Si ocurre un error al obtener los productos.
     */
    List<Product> getAvailableProducts() throws ProductException;

    /**
     * Compra un producto reduciendo su stock.
     * @param id Identificador del producto.
     * @param quantity Cantidad a comprar.
     * @throws ProductException Si el producto no existe o no hay stock suficiente.
     */
    void buyProduct(String id, int quantity) throws ProductException;
    
    /**
     * Actualiza el stock de un producto.
     * @param id Identificador del producto.
     * @param quantity Cantidad a agregar (positivo) o quitar (negativo).
     * @throws ProductException Si el producto no existe o el stock resultante es inválido.
     */
    void updateStock(String id, int quantity) throws ProductException;
}