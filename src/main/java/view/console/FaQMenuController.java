package view.console;

import interfaces.*;
import model.*;
import exceptions.*;
import controller.*;

import java.util.*;

public class FaQMenuController {
    private FaQController faqController;
    private final Account account;
    private final Scanner scanner;

    public FaQMenuController(Account account) {
        FaQ prototype = new FaQ(); // Usa el constructor por defecto o el apropiado
        this.account = account;
        IFile<FaQ> fileHandler = new FileHandler<>(prototype);
        
        try {
            this.faqController = new FaQController(fileHandler);
        } catch (FaQException e) {
            System.err.println("Error al inicializar FaQController: " + e.getMessage());
            this.faqController = null; // O maneja la inicialización de otra manera
        }
        
        this.scanner = new Scanner(System.in);
    }

    public void showUserMenu() {
        int option;
        do {
            System.out.println("\n==== SISTEMA DE GESTIÓN DE PREGUNTAS FRECUENTES ====");
            System.out.println("1. Ver todas las preguntas frecuentes");
            System.out.println("2. Buscar pregunta por ID");
            System.out.println("3. Hacer una pregunta");
            System.out.println("0. Volver al menú principal");
            
            option = readIntOption("Seleccione una opción: ");
            
            try {
                switch (option) {
                    case 0 -> System.out.println("Volviendo al menú principal...");
                    case 1 -> showAllFaqsMenu();
                    case 2 -> findFaqByIdMenu();
                    case 3 -> hacerPreguntaMenu();
                    default -> System.out.println("Opción inválida. Por favor intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }

    public void showAdminMenu() {
        int option = -1;
        do {
            try {
                if (faqController.areFaqByPending()) {
                    System.out.println("\n¡ATENCIÓN! Hay preguntas pendientes de respuesta.");
                    System.out.println("Debe responderlas antes de continuar.");
                    responderPreguntasPendientes();
                    continue;
              
                }
            } catch (FaQException e) {
                System.out.println("Error al verificar preguntas pendientes: " + e.getMessage());
                continue;
            }

            System.out.println("\n==== SISTEMA DE GESTIÓN DE PREGUNTAS FRECUENTES - ADMINISTRADOR ====");
            System.out.println("1. Ver todas las preguntas frecuentes");
            System.out.println("2. Buscar pregunta por ID");
            System.out.println("3. Añadir nueva pregunta frecuente");
            System.out.println("4. Actualizar pregunta frecuente");
            System.out.println("5. Eliminar pregunta frecuente");
            System.out.println("0. Volver al menú principal");
            
            option = readIntOption("Seleccione una opción: ");
            
            try {
                switch (option) {
                    case 0 -> System.out.println("Volviendo al menú principal...");
                    case 1 -> showAllFaqsMenu();
                    case 2 -> findFaqByIdMenu();
                    case 3 -> addFaqMenu();
                    case 4 -> updateFaqMenu();
                    case 5 -> deleteFaqMenu();
                    default -> System.out.println("Opción inválida. Por favor intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }

    private void showAllFaqsMenu() {
        System.out.println("\n==== LISTADO DE TODAS LAS PREGUNTAS FRECUENTES ====");
        try {
            List<FaQ> faqs = faqController.getAllFaqs();
            if (faqs.isEmpty()) {
                System.out.println("No hay preguntas frecuentes registradas.");
                return;
            }

            for (FaQ faq : faqs) {
                displayFaqDetails(faq);
                System.out.println("=".repeat(80)); // Separator between FAQs
            }

        } catch (FaQException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findFaqByIdMenu() {
        System.out.println("\n==== BUSCAR PREGUNTA POR ID ====");
        System.out.print("ID de la pregunta: ");
        String id = scanner.nextLine();
        
        try {
            FaQ faq = faqController.getFaqById(id);
            displayFaqDetails(faq);
        } catch (FaQException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addFaqMenu() {
        System.out.println("\n==== AÑADIR NUEVA PREGUNTA FRECUENTE ====");
        System.out.print("Pregunta: ");
        String pregunta = scanner.nextLine();
        System.out.print("Respuesta: ");
        String respuesta = scanner.nextLine();
        
        try {
            // Ajusta según el constructor real de FaQ
            FaQ newFaq = new FaQ(pregunta, respuesta);
            faqController.addFaq(newFaq);
            System.out.println("Pregunta frecuente añadida exitosamente.");
            System.out.println("ID asignado: " + newFaq.getId());
        } catch (FaQException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateFaqMenu() {
        System.out.println("\n==== ACTUALIZAR PREGUNTA FRECUENTE ====");
        System.out.print("ID de la pregunta a actualizar: ");
        String id = scanner.nextLine();
        
        try {
            FaQ faq = faqController.getFaqById(id);
            displayFaqDetails(faq);
            
            System.out.println("\nActualizar información:");
            System.out.print("Nueva pregunta (deje en blanco para mantener la actual): ");
            String newPregunta = scanner.nextLine();
            if (!newPregunta.isEmpty()) {
                faq.setPregunta(newPregunta);
            }
            
            System.out.print("Nueva respuesta (deje en blanco para mantener la actual): ");
            String newRespuesta = scanner.nextLine();
            if (!newRespuesta.isEmpty()) {
                faq.setRespuesta(newRespuesta);
            }
            
            faqController.updateFaq(faq);
            System.out.println("Pregunta frecuente actualizada exitosamente.");
        } catch (FaQException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void hacerPreguntaMenu() {
        System.out.println("\n==== HACER UNA PREGUNTA ====");
        System.out.print("Escriba su pregunta: ");
        String pregunta = scanner.nextLine();
        
        try {
            // Create FAQ with default pending response
            FaQ newFaq = new FaQ(pregunta, "Solicitud pendiente de respuesta");
            newFaq.setPendiente(true); // Mark as pending
            faqController.addFaq(newFaq);
            System.out.println("Pregunta enviada exitosamente. Un administrador la responderá pronto.");
            System.out.println("ID de seguimiento: " + newFaq.getId());
        } catch (FaQException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void responderPreguntasPendientes() {
        try {
            List<FaQ> pendientes = faqController.getFaqByPending();
            for (FaQ faq : pendientes) {
                System.out.println("\n==== PREGUNTA PENDIENTE ====");
                displayFaqDetails(faq);
                
                System.out.print("Escriba la respuesta: ");
                String respuesta = scanner.nextLine();
                
                if (!respuesta.trim().isEmpty()) {
                    faq.setRespuesta(respuesta);
                    faq.setPendiente(false);  // Toggle pending status
                    faqController.updateFaq(faq);
                    System.out.println("Respuesta guardada exitosamente.");
                }
            }
        } catch (FaQException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void displayFaqDetails(FaQ faq) {
        int width = 80;
        String border = "+" + "-".repeat(width - 2) + "+";
        
        System.out.println(border);
        System.out.printf("| %-" + (width-4) + "s |%n", "ID: " + faq.getId());
        System.out.println(border);
        
        System.out.println("| PREGUNTA:");
        String[] preguntaLines = wordWrap(faq.getPregunta(), width - 4);
        for (String line : preguntaLines) {
            System.out.printf("| %-" + (width-4) + "s |%n", line);
        }
        
        System.out.println(border);
        System.out.println("| RESPUESTA:");
        String[] respuestaLines = wordWrap(faq.getRespuesta(), width - 4);
        for (String line : respuestaLines) {
            System.out.printf("| %-" + (width-4) + "s |%n", line);
        }
        
        System.out.println(border);
        System.out.printf("| %-" + (width-4) + "s |%n", 
            "Estado: " + (faq.isPendiente() ? "Pendiente" : "Respondida"));
        System.out.println(border);
    }

    private String[] wordWrap(String text, int width) {
        if (text == null) return new String[]{"N/A"};
        
        String[] words = text.split("\\s+");
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (currentLine.length() + word.length() + 1 <= width) {
                if (currentLine.length() > 0) currentLine.append(" ");
                currentLine.append(word);
            } else {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            }
        }
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }
        
        return lines.toArray(new String[0]);
    }

    private void deleteFaqMenu() {
        System.out.println("\n==== ELIMINAR PREGUNTA FRECUENTE ====");
        System.out.print("ID de la pregunta a eliminar: ");
        String id = scanner.nextLine();
        
        try {
            FaQ faq = faqController.getFaqById(id);
            displayFaqDetails(faq);
            
            System.out.print("¿Está seguro de que desea eliminar esta pregunta frecuente? (s/n): ");
            String response = scanner.nextLine();
            
            if (response.equalsIgnoreCase("s")) {
                faqController.deleteFaq(id);
                System.out.println("Pregunta frecuente eliminada exitosamente.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } catch (FaQException e) {
            System.out.println("Error: " + e.getMessage());
        }
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

    public static void main(String[] args) {
        try {
            FaQMenuController menuController = new FaQMenuController(null);
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