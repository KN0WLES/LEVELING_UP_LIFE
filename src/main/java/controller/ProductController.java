package controller;

import interfaces.IProduct;
import interfaces.IFile;
import model.Product;
import exceptions.ProductException;
import exceptions.FileException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductController implements IProduct {
    
    private final IFile<Product> fileHandler;
    private final String filePath = "C:/Users/HP/Downloads/VTerminado-4/V1-4/src/main/java/data/products.txt";
    private List<Product> products;

    public ProductController(IFile<Product> fileHandler) throws ProductException {
        this.fileHandler = fileHandler;
        try {
            this.fileHandler.createFileIfNotExists(filePath);
            this.products = this.fileHandler.loadData(filePath);
            if(this.products == null || this.products.isEmpty()){
                this.products = new ArrayList<>();
                createProducts();
                saveChanges();
            }
        } catch (FileException e) {
            this.products = new ArrayList<>();
            System.err.println("Error al cargar productos: " + e.getMessage());
        }
    }

    private void saveChanges() throws ProductException {
        try {
            fileHandler.saveData(products, filePath);
        } catch (FileException e) {
            throw new ProductException("Error al guardar los cambios: " + e.getMessage());
        }
    }

    @Override
    public void addProduct(Product product) throws ProductException {
        // Verificar si ya existe un producto con el mismo nombre
        if (products.stream().anyMatch(p -> p.getNombre().equalsIgnoreCase(product.getNombre()))) {
            throw new ProductException("Ya existe un producto con este nombre");
        }
        
        products.add(product);
        saveChanges();
    }

    @Override
    public Product getProductById(String id) throws ProductException {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(ProductException::notFound);
    }

    @Override
    public void updateProduct(Product product) throws ProductException {
        // Buscar el producto para asegurar que existe
        Product existingProduct = getProductById(product.getId());
        
        // Verificar si el nuevo nombre ya está en uso por otro producto
        if (!existingProduct.getNombre().equals(product.getNombre()) &&
            products.stream()
                .filter(p -> !p.getId().equals(product.getId()))
                .anyMatch(p -> p.getNombre().equalsIgnoreCase(product.getNombre()))) {
            throw new ProductException("Ya existe un producto con este nombre");
        }
        
        // La forma más fácil de actualizar es reemplazar el producto en la lista
        products.removeIf(p -> p.getId().equals(product.getId()));
        products.add(product);
        
        saveChanges();
    }

    @Override
    public void deleteProduct(String id) throws ProductException {
        // Verificar que el producto exista
        Product product = getProductById(id);
        
        if (!products.remove(product)) {
            throw new ProductException("No se pudo eliminar el producto");
        }
        saveChanges();
    }

    @Override
    public List<Product> getProductsByCategory(char category) throws ProductException {
        if (!"CBP".contains(String.valueOf(category))) {
            throw ProductException.invalidCategory();
        }
        
        return products.stream()
                .filter(p -> p.getCategoria() == category)
                .collect(Collectors.toList());
    }

    @Override
    public void buyProduct(String id, int quantity) throws ProductException {
        if (quantity <= 0) {
            throw new ProductException("La cantidad a comprar debe ser positiva");
        }
    
        Product product = getProductById(id);
        if (product.getStock() < quantity) {
            throw ProductException.outOfStock();
        }
        product.setStock(product.getStock() - quantity);
        saveChanges();
    }

    @Override
    public void updateStock(String id, int quantity) throws ProductException {
        Product product = getProductById(id);
        int newStock = product.getStock() - quantity;
    
        if (newStock < 0) {
        throw new ProductException("No se puede tener stock negativo");
        }
        product.setStock(newStock);
        saveChanges();
    }

    @Override
    public List<Product> getAvailableProducts() throws ProductException {
        return products.stream()
                .filter(p -> p.getStock() > 0)
                .collect(Collectors.toList());
    }

    private void createProducts() {
        // Comidas principales (C)
        products.add(new Product("Hamburguesa Clásica", 'C', 25.0, 50));
        products.add(new Product("Hamburguesa Especial con Bacon", 'C', 32.0, 45));
        products.add(new Product("Club Sandwich", 'C', 28.0, 40));
        products.add(new Product("Sandwich Vegetal", 'C', 22.0, 30));
        products.add(new Product("Pizza Margarita", 'C', 35.0, 25));
        products.add(new Product("Pizza 4 Quesos", 'C', 40.0, 25));
        products.add(new Product("Pasta Carbonara", 'C', 30.0, 35));
        products.add(new Product("Pasta Boloñesa", 'C', 28.0, 35));
        products.add(new Product("Ensalada César", 'C', 24.0, 30));
        products.add(new Product("Ensalada Mediterránea", 'C', 22.0, 30));
        products.add(new Product("Tacos de Pollo (3 uds)", 'C', 26.0, 40));
        products.add(new Product("Nachos con Guacamole", 'C', 20.0, 45));

        // Bebidas (B)
        products.add(new Product("Café Espresso", 'B', 5.0, 100));
        products.add(new Product("Café Americano", 'B', 6.0, 100));
        products.add(new Product("Cappuccino", 'B', 8.0, 80));
        products.add(new Product("Latte", 'B', 8.0, 80));
        products.add(new Product("Chocolate Caliente", 'B', 7.0, 60));
        products.add(new Product("Té Verde", 'B', 5.0, 70));
        products.add(new Product("Té Negro", 'B', 5.0, 70));
        products.add(new Product("Smoothie de Frutas", 'B', 12.0, 40));
        products.add(new Product("Limonada Natural", 'B', 8.0, 50));
        products.add(new Product("Agua Mineral", 'B', 4.0, 150));
        products.add(new Product("Refresco Cola", 'B', 6.0, 100));
        products.add(new Product("Cerveza Nacional", 'B', 10.0, 80));
        products.add(new Product("Cerveza Importada", 'B', 15.0, 60));
        products.add(new Product("Vino Tinto Copa", 'B', 12.0, 40));

        // Postres (P)
        products.add(new Product("Tiramisú", 'P', 15.0, 30));
        products.add(new Product("Cheesecake", 'P', 14.0, 35));
        products.add(new Product("Brownie con Helado", 'P', 16.0, 40));
        products.add(new Product("Tarta de Manzana", 'P', 13.0, 25));
        products.add(new Product("Helado (3 bolas)", 'P', 12.0, 50));
        products.add(new Product("Crepe de Nutella", 'P', 14.0, 45));
        products.add(new Product("Flan Casero", 'P', 10.0, 30));
        products.add(new Product("Mousse de Chocolate", 'P', 12.0, 35));
        products.add(new Product("Frutas del Bosque", 'P', 11.0, 25));
        products.add(new Product("Galletas Artesanales", 'P', 8.0, 60));
    }
}