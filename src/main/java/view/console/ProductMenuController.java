package view.console;

import interfaces.*;
import model.*;
import exceptions.*;
import controller.*;

import java.util.List;
import java.util.Scanner;

public class ProductMenuController {
    private ProductController controller;
    private final Account account;
    private final Scanner scanner;

    public ProductMenuController(Account account) {
        Product prototype = new Product();
        this.account = account;
        IFile<Product> fileHandler = new FileHandler<>(prototype);
        
        try {
            this.controller = new ProductController(fileHandler);
        } catch (Exception e) {
            System.err.println("Error al inicializar ProductController: " + e.getMessage());
            this.controller = null;
        }
        
        this.scanner = new Scanner(System.in);
    }

    public void showAdminMenu(){
        int option;
        do {
            System.out.println("\n==== SISTEMA DE GESTIÓN DE PRODUCTOS ====");
            System.out.println("1. Ver todos los productos");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Ver productos disponibles");
            System.out.println("4. Ver productos por categoría");
            System.out.println("5. Añadir nuevo producto");
            System.out.println("6. Actualizar producto");
            System.out.println("7. Eliminar producto");
            System.out.println("8. Gestionar stock del producto");
            System.out.println("0. Volver al menú principal");
            
            option = readIntOption("Seleccione una opción: ");
            
            try {
                switch (option) {
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    case 1:
                        mostrarTodosLosProductos();
                        break;
                    case 2:
                        buscarProducto();
                        break;
                    case 3:
                        listarDisponibles();
                        break;
                    case 4:
                        listarPorCategoria();
                        break;
                    case 5:
                        agregarProducto();
                        break;
                    case 6:
                        actualizarProducto();
                        break;
                    case 7:
                        eliminarProducto();
                        break;
                    case 8:
                        gestionarStock();
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor intente de nuevo.");
                }
            } catch (ProductException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }

    private void gestionarStock() throws ProductException {
        System.out.println("\n=== GESTIONAR STOCK ===");
        mostrarTodosLosProductos();
        System.out.print("Ingrese el ID del producto: ");
        String id = scanner.nextLine();
        
        System.out.println("1. Agregar stock");
        System.out.println("2. Reducir stock");
        int opcion = readIntOption("Seleccione una opción: ");
        
        System.out.print("Ingrese la cantidad: ");
        int cantidad = readIntOption("");
        
        try {
            if (opcion == 1) {
                controller.updateStock(id, cantidad);
                System.out.println("Stock aumentado exitosamente!");
            } else if (opcion == 2) {
                controller.updateStock(id, -cantidad);
                System.out.println("Stock reducido exitosamente!");
            } else {
                System.out.println("Opción inválida");
            }
        } catch (ProductException e) {
            System.out.println("Error al actualizar stock: " + e.getMessage());
        }
    }
    
    public void showUserMenu() {
        int option;
        do {
            System.out.println("\n==== PRODUCTOS OFRECIDOS ====");
            System.out.println("1. Ver productos disponibles");
            System.out.println("2. Ver productos por categoría");
            System.out.println("3. Comprar producto");
            System.out.println("0. Volver al menú principal");
            
            option = readIntOption("Seleccione una opción: ");
            
            try {
                switch (option) {
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    case 1:
                        listarDisponibles();
                        break;
                    case 2:
                        listarPorCategoria();
                        break;
                    case 3:
                        comprarProducto();
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor intente de nuevo.");
                }
            } catch (ProductException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }

    private void comprarProducto() throws ProductException {
        System.out.println("\n=== COMPRAR PRODUCTO ===");
        listarDisponibles();
        System.out.print("Ingrese el ID del producto a comprar: ");
        String id = scanner.nextLine();
        
        System.out.print("Ingrese la cantidad a comprar: ");
        int cantidad = readIntOption("");
        
        try {
            controller.buyProduct(id, cantidad);
            System.out.println("Compra realizada exitosamente!");
        } catch (ProductException e) {
            System.out.println("No se pudo completar la compra: " + e.getMessage());
        }
    }

    private void mostrarTodosLosProductos() throws ProductException {
        System.out.println("\n==== LISTADO DE TODOS LOS PRODUCTOS ====");
        List<Product> productos = controller.getAvailableProducts();
        mostrarListaProductos(productos);
    }

    private int readIntOption(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }

    private void agregarProducto() throws ProductException {
        System.out.println("\n=== AGREGAR PRODUCTO ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Categoría (C/B/P): ");
        char categoria = scanner.nextLine().toUpperCase().charAt(0);
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        Product producto = new Product(nombre, categoria, precio, stock);
        controller.addProduct(producto);
        System.out.println("Producto agregado exitosamente");
    }

    private void buscarProducto() throws ProductException {
        System.out.println("\n=== BUSCAR PRODUCTO ===");
        System.out.print("Ingrese el ID del producto: ");
        String id = scanner.nextLine();
        Product producto = controller.getProductById(id);
        mostrarProducto(producto);
    }

    private void actualizarProducto() throws ProductException {
        System.out.println("\n=== ACTUALIZAR PRODUCTO ===");
        System.out.print("Ingrese el ID del producto a actualizar: ");
        String id = scanner.nextLine();
        
        Product productoExistente = controller.getProductById(id);
        System.out.println("Producto actual:");
        mostrarProducto(productoExistente);

        System.out.println("\nIngrese los nuevos datos (presione Enter para mantener el valor actual):");
        
        System.out.print("Nombre [" + productoExistente.getNombre() + "]: ");
        String nombre = scanner.nextLine();
        nombre = nombre.isEmpty() ? productoExistente.getNombre() : nombre;

        char categoria = productoExistente.getCategoria();
        boolean categoriaValida = false;
        while (!categoriaValida) {
            System.out.print("Categoría (C:Comida/B:Bebida/P:Producto) [" + productoExistente.getCategoria() + "]: ");
            String catStr = scanner.nextLine();
            if (catStr.isEmpty()) {
                categoriaValida = true;
            } else {
                char nuevaCategoria = catStr.toUpperCase().charAt(0);
                if (nuevaCategoria == 'C' || nuevaCategoria == 'B' || nuevaCategoria == 'P') {
                    categoria = nuevaCategoria;
                    categoriaValida = true;
                } else {
                    System.out.println("Categoría inválida. Use C, B o P.");
                }
            }
        }

        double precio = productoExistente.getPrecio();
        while (true) {
            try {
                System.out.print("Precio [" + productoExistente.getPrecio() + "]: ");
                String precioStr = scanner.nextLine();
                if (!precioStr.isEmpty()) {
                    precio = Double.parseDouble(precioStr);
                    if (precio < 0) {
                        System.out.println("El precio no puede ser negativo.");
                        continue;
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }

        int stock = productoExistente.getStock();
        while (true) {
            try {
                System.out.print("Stock [" + productoExistente.getStock() + "]: ");
                String stockStr = scanner.nextLine();
                if (!stockStr.isEmpty()) {
                    stock = Integer.parseInt(stockStr);
                    if (stock < 0) {
                        System.out.println("El stock no puede ser negativo.");
                        continue;
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número entero válido.");
            }
        }

        String productData = String.format("%s|%s|%c|%s|%d", 
            productoExistente.getId(), 
            nombre, 
            categoria, 
            String.format("%.2f", precio).replace(',', '.'), // Replace comma with period
            stock);
        Product productoActualizado = new Product().fromFile(productData);
        
        controller.updateProduct(productoActualizado);
        System.out.println("Producto actualizado exitosamente");
    }  

    private void eliminarProducto() throws ProductException {
        System.out.println("\n=== ELIMINAR PRODUCTO ===");
        System.out.print("Ingrese el ID del producto a eliminar: ");
        String id = scanner.nextLine();
        controller.deleteProduct(id);
        System.out.println("Producto eliminado exitosamente");
    }

    private void listarPorCategoria() throws ProductException {
        System.out.println("\n=== LISTAR POR CATEGORÍA ===");
        System.out.print("Ingrese la categoría (C/B/P): ");
        char categoria = scanner.nextLine().toUpperCase().charAt(0);
        List<Product> productos = controller.getProductsByCategory(categoria);
        mostrarListaProductos(productos);
    }

    private void listarDisponibles() throws ProductException {
        System.out.println("\n=== PRODUCTOS DISPONIBLES ===");
        List<Product> productos = controller.getAvailableProducts();
        mostrarListaProductos(productos);
    }

    private void mostrarProducto(Product producto) {
        System.out.println("\nDetalles del producto:");
        System.out.println("ID: " + producto.getId());
        System.out.println("Nombre: " + producto.getNombre());
        System.out.println("Categoría: " + producto.getCategoria());
        System.out.println("Precio: " + producto.getPrecio());
        System.out.println("Stock: " + producto.getStock());
    }

    private void mostrarListaProductos(List<Product> productos) {
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos");
            return;
        }

        // Define column widths
        String format = "| %-36s | %-30s | %-10s | %8s | %6s |%n";
        int totalWidth = 104;

        // Print header
        System.out.println("+" + "-".repeat(totalWidth) + "+");
        System.out.printf(format, "ID", "NOMBRE", "CATEGORÍA", "PRECIO", "STOCK");
        System.out.println("+" + "-".repeat(totalWidth) + "+");

        // Print products
        for (Product producto : productos) {
            System.out.printf(format,
                    producto.getId(),
                    truncateString(producto.getNombre(), 30),
                    getCategoryName(producto.getCategoria()),
                    String.format("$%.2f", producto.getPrecio()),
                    producto.getStock());
        }

        // Print footer
        System.out.println("+" + "-".repeat(totalWidth) + "+");
    }

    private String truncateString(String str, int length) {
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }

    private String getCategoryName(char categoria) {
        return switch (categoria) {
            case 'C' -> "Comida";
            case 'B' -> "Bebida";
            case 'P' -> "Postre";
            default -> "N/A";
        };
    }

    public static void main(String[] args) {
        try {
            ProductMenuController menuController = new ProductMenuController(null);
            Scanner sc = new Scanner(System.in);
            
            System.out.print("¿Iniciar menú como administrador? (s/n): ");
            String input = sc.nextLine().toLowerCase();
            boolean isAdmin = input.equals("s") || input.equals("si");
            
            if (isAdmin) {
                menuController.showAdminMenu();
            } else {
                menuController.showUserMenu();
            }
            
            sc.close();
        } catch (Exception e) {
            System.err.println("Error al inicializar el menú: " + e.getMessage());
        }
    }
}