package model;

import java.util.UUID;

public class Product extends Base<Product> {
    private String id;
    private String nombre;
    private char categoria; // 'C': Comida, 'B': Bebida, 'P': Postre
    private double precio;
    private int stock;

    public Product(){}
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

    // --- Getters ---
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public char getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    // --- Setters ---
    public void setCategoria(char categoria) {
        if (!"CBP".contains(String.valueOf(categoria)))
            throw new IllegalArgumentException("Categoría inválida");
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        if (precio <= 0)
            throw new IllegalArgumentException("El precio debe ser positivo");
        this.precio = precio;
    }

    public void setStock(int stock) {
        if (stock < 0)
            throw new IllegalArgumentException("El stock no puede ser negativo");
        this.stock = stock;
    }

    // --- Serialización ---
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