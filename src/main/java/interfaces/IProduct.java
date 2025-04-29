package interfaces;

import model.Product;
import exceptions.ProductException;
import java.util.List;

/**
 * Interfaz para la gestión de productos.
 * Define los métodos necesarios para agregar, consultar, actualizar y eliminar productos,
 * así como para gestionar el inventario y realizar búsquedas por diferentes criterios.
 *
 * @description Funcionalidades principales:
 *                   - Agregar nuevos productos al inventario.
 *                   - Obtener productos por identificador o categoría.
 *                   - Actualizar información de productos existentes.
 *                   - Eliminar productos del inventario.
 *                   - Listar productos disponibles.
 *                   - Realizar compras de productos y actualizar el stock.
 *
 * Ejemplo de uso:
 * <pre>
 *     IProduct productManager = new ProductController(fileHandler);
 *     Product nuevoProducto = new Product("P001", "Jabón de lujo", 15.50, 100, 'H');
 *     productManager.addProduct(nuevoProducto);
 * </pre>
 *
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 */
public interface IProduct {
    
    /**
     * Agrega un nuevo producto.
     *                      El producto debe contener:
     *                          -ID único
     *                          -Nombre
     *                          -Precio
     *                          -Stock
     *                          -Categoría.
     *                      El sistema validará que no exista otro producto con el mismo ID.
     *
     * @param product Producto a agregar.
     * @throws ProductException Si ocurre un error al agregar el producto.
     */
    void addProduct(Product product) throws ProductException;
    
    /**
     * Obtiene un producto por su ID.
     *
     * @param id Identificador del producto.
     * @return El producto correspondiente.
     * @throws ProductException Si el producto no existe.
     */
    Product getProductById(String id) throws ProductException;
    
    /**
     * Actualiza un producto existente.
     *                      Permite modificar:
     *                          -Nombreprecio
     *                          -Descripción
     *                          -Categoría
     *                          -Otras propiedades
     *                      del producto, manteniendo su ID original.
     *
     * @param product Producto con los datos actualizados.
     * @throws ProductException Si el producto no existe o los datos son inválidos.
     */
    void updateProduct(Product product) throws ProductException;
    
    /**
     * Elimina un producto.
     *                      Remueve completamente el producto del inventario. Solo debe ser utilizado
     *                      cuando se descontinúa un producto, ya que la eliminación es permanente.
     *
     * @param id Identificador del producto a eliminar.
     * @throws ProductException Si el producto no existe.
     */
    void deleteProduct(String id) throws ProductException;
    
    /**
     * Obtiene productos por categoría.
     *
     * @param category Categoría de los productos.
     * @return Lista de productos en la categoría.
     * @throws ProductException Si ocurre un error al obtener los productos.
     */
    List<Product> getProductsByCategory(char category) throws ProductException;
    
    /**
     * Obtiene productos disponibles.
     *                      Un producto disponible es aquel que tiene stock mayor a cero.
     * 
     * @return Lista de productos disponibles.
     * @throws ProductException Si ocurre un error al obtener los productos.
     */
    List<Product> getAvailableProducts() throws ProductException;

    /**
     * Compra un producto reduciendo su stock.
     *                      Verifica que haya suficiente stock disponible antes de realizar la compra.
     * 
     * @param id Identificador del producto.
     * @param quantity Cantidad a comprar.
     * @throws ProductException Si el producto no existe o no hay stock suficiente.
     */
    void buyProduct(String id, int quantity) throws ProductException;
    
    /**
     * Actualiza el stock de un producto.
     *                      Permite incrementar o decrementar el stock disponible de un producto.
     * 
     * @param id Identificador del producto.
     * @param quantity Cantidad a agregar (positivo) o quitar (negativo).
     * @throws ProductException Si el producto no existe o el stock resultante es inválido.
     */
    void updateStock(String id, int quantity) throws ProductException;
}