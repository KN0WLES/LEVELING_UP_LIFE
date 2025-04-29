package model;

import java.util.UUID;

/**
 * Clase que representa el modelo de un producto en el sistema.
 * Gestiona información completa de productos incluyendo nombre, categoría,
 * precio y control de inventario.
 * 
 * @description Funcionalidades principales:
 *                   - Crear productos con validaciones de categoría, precio y stock.
 *                   - Gestionar el inventario de productos disponibles.
 *                   - Serializar y deserializar productos para almacenamiento.
 *                   - Mostrar información detallada del producto.
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 * @see Base
 */
public class Product extends Base<Product> {
    private String id;
    private String nombre;
    private char categoria;
    private double precio;
    private int stock;

    /**
     * Constructor vacío para serialización.
     */
    public Product(){}
    
    /**
     * Constructor principal para crear un producto con todos sus atributos.
     * Realiza validaciones para asegurar que los datos sean correctos.
     *
     * @param nombre Nombre descriptivo del producto
     * @param categoria Tipo de producto: 'C' (Comida), 'B' (Bebida), 'P' (Postre)
     * @param precio Precio de venta del producto
     * @param stock Cantidad disponible en inventario
     * @throws IllegalArgumentException Si los datos no cumplen las validaciones
     */
    public Product(String nombre, char categoria, double precio, int stock) {
        if (!"CBP".contains(String.valueOf(categoria)))
            throw new IllegalArgumentException("Categoría inválida. Use C, B, o P");
        if (precio <= 0)
            throw new IllegalArgumentException("El precio debe ser positivo");
        if (stock < 0)
            throw new IllegalArgumentException("El stock no puede ser negativo");

        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Obtiene el identificador único del producto.
     * @return ID del producto
     */
    public String getId() { return id; }
    
    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto
     */
    public String getNombre() { return nombre; }
    
    /**
     * Obtiene la categoría del producto.
     * @return Categoría: 'C' (Comida), 'B' (Bebida), 'P' (Postre)
     */
    public char getCategoria() { return categoria; }
    
    /**
     * Obtiene el precio del producto.
     * @return Precio de venta
     */
    public double getPrecio() { return precio; }
    
    /**
     * Obtiene la cantidad disponible en inventario.
     * @return Cantidad en stock
     */
    public int getStock() { return stock; }

    /**
     * Modifica la categoría del producto.
     * 
     * @param categoria Nueva categoría ('C', 'B' o 'P')
     * @throws IllegalArgumentException Si la categoría no es válida
     */
    public void setCategoria(char categoria) {
        if (!"CBP".contains(String.valueOf(categoria)))
            throw new IllegalArgumentException("Categoría inválida");
        this.categoria = categoria;
    }

    /**
     * Modifica el precio del producto.
     * 
     * @param precio Nuevo precio de venta
     * @throws IllegalArgumentException Si el precio no es positivo
     */
    public void setPrecio(double precio) {
        if (precio <= 0)
            throw new IllegalArgumentException("El precio debe ser positivo");
        this.precio = precio;
    }

    /**
     * Modifica la cantidad en inventario.
     * 
     * @param stock Nueva cantidad en stock
     * @throws IllegalArgumentException Si el stock es negativo
     */
    public void setStock(int stock) {
        if (stock < 0)
            throw new IllegalArgumentException("El stock no puede ser negativo");
        this.stock = stock;
    }

    @Override
    public String toFile() {
        return String.join("|", 
            id, nombre, String.valueOf(categoria), 
            String.valueOf(precio), String.valueOf(stock)
        );
    }

    @Override
    public Product fromFile(String line) {
        String[] parts = line.split("\\|");
        Product product = new Product(
            parts[1], parts[2].charAt(0), 
            Double.parseDouble(parts[3]), Integer.parseInt(parts[4])
        );
        product.id = parts[0];
        return product;
    }

    @Override
    public String getInfo() {
        return String.format(
            "Producto: %s\nCategoría: %s\nPrecio: $%.2f\nStock: %d",
            nombre, categoria, precio, stock
        );
    }
}