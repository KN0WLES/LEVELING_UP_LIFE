package view.console;

import interfaces.*;
import model.*;
import exceptions.*;
import controller.*;

import java.util.List;
import java.util.Scanner;

/**
 * Clase que representa el controlador de menú para la gestión de habitaciones.
 * Maneja la interacción del usuario con las funcionalidades de habitaciones,
 * tanto para usuarios normales como administradores.
 * 
 * @description Funcionalidades principales:
 *                   - Mostrar menús diferenciados para usuarios y administradores.
 *                   - Gestionar operaciones CRUD de habitaciones.
 *                   - Manejar reservas y disponibilidad de habitaciones.
 *                   - Controlar estados de mantenimiento y ocupación.
 *                   - Validar entradas del usuario y manejar excepciones.
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 * @see RoomController
 * @see Room
 * @see Account
 */
public class RoomMenuController {
    private RoomController controller;
    //private final Account account;
    private final Scanner scanner;

    public RoomMenuController(Account account) {
        Room prototype = new Room();
        //this.account = account;
        IFile<Room> fileHandler = new FileHandler<>(prototype);
        
        try {
            this.controller = new RoomController(fileHandler);
        } catch (Exception e) {
            System.err.println("Error al inicializar RoomController: " + e.getMessage());
            this.controller = null;
        }
        
        this.scanner = new Scanner(System.in);
    }

    public void showUserMenu() {
        int option;
        do {
            mostrarMensajeCentrado("==== SISTEMA DE GESTIÓN DE HABITACIONES ====");
            System.out.println("1. Ver habitaciones disponibles");
            System.out.println("2. Ver habitaciones por tipo");
            System.out.println("3. Reservar habitación");
            System.out.println("4. Cancelar reserva");
            System.out.println("0. Volver al menú principal");
            
            option = readIntOption("Seleccione una opción: ");
            
            try {
                switch (option) {
                    case 0 -> System.out.println("Volviendo al menú principal...");
                    case 1 -> listarDisponibles();
                    case 2 -> listarPorTipo();
                    case 3 -> reservarHabitacion();
                    case 4 -> cancelarReserva();
                    default -> System.out.println("Opción inválida. Por favor intente de nuevo.");
                }
            } catch (RoomException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }

    public void showAdminMenu() {
        int option;
        do {
            mostrarMensajeCentrado("==== SISTEMA DE GESTIÓN DE HABITACIONES ====");
            System.out.println("1. Ver todas las habitaciones");
            System.out.println("2. Buscar habitación por ID");
            System.out.println("3. Ver habitaciones disponibles");
            System.out.println("4. Ver habitaciones por tipo");
            System.out.println("5. Añadir nueva habitación");
            System.out.println("6. Actualizar habitación");
            System.out.println("7. Eliminar habitación");
            System.out.println("8. Cambiar estado de mantenimiento");
            System.out.println("9. Cambiar estado de ocupación");
            System.out.println("0. Volver al menú principal");
            
            option = readIntOption("Seleccione una opción: ");
            
            try {
                switch (option) {
                    case 0 -> System.out.println("Volviendo al menú principal...");
                    case 1 -> mostrarTodasLasHabitaciones();
                    case 2 -> buscarHabitacion();
                    case 3 -> listarDisponibles();
                    case 4 -> listarPorTipo();
                    case 5 -> agregarHabitacion();
                    case 6 -> actualizarHabitacion();
                    case 7 -> eliminarHabitacion();
                    case 8 -> cambiarEstadoMantenimiento();
                    case 9 -> cambiarEstadoOcupacion();
                    default -> System.out.println("Opción inválida. Por favor intente de nuevo.");
                }
            } catch (RoomException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }

    private void mostrarTodasLasHabitaciones() throws RoomException {
        mostrarMensajeCentrado("==== LISTADO DE TODAS LAS HABITACIONES ====");
        List<Room> habitaciones = controller.getAvailableRooms();
        mostrarListaHabitaciones(habitaciones);
    }

    private void buscarHabitacion() throws RoomException {
        mostrarMensajeCentrado("==== BUSCAR HABITACIÓN POR ID ====");
        System.out.print("ID de la habitación: ");
        String id = scanner.nextLine();
        Room habitacion = controller.getRoomById(id);
        mostrarDetallesHabitacion(habitacion);
    }

    private void agregarHabitacion() throws RoomException {
        mostrarMensajeCentrado("==== AÑADIR NUEVA HABITACIÓN ====");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Tipo (I: Individual, P: Privado, D: Dormitorio, M: Mixto): ");
        char tipo = scanner.nextLine().toUpperCase().charAt(0);
        System.out.print("Precio por hora: ");
        double precioPorHora = Double.parseDouble(scanner.nextLine());

        Room habitacion = new Room(nombre, tipo, precioPorHora);
        controller.addRoom(habitacion);
        System.out.println("Habitación añadida exitosamente.");
        System.out.println("ID asignado: " + habitacion.getId());
    }

    private void actualizarHabitacion() throws RoomException {
        mostrarMensajeCentrado("==== ACTUALIZAR HABITACIÓN ====");
        System.out.print("ID de la habitación a actualizar: ");
        String id = scanner.nextLine();
        
        Room habitacionExistente = controller.getRoomById(id);
        mostrarDetallesHabitacion(habitacionExistente);

        System.out.println("\nIngrese los nuevos datos (presione Enter para mantener el valor actual):");
        
        System.out.print("Nombre [" + habitacionExistente.getNombre() + "]: ");
        String nombre = scanner.nextLine();
        nombre = nombre.isEmpty() ? habitacionExistente.getNombre() : nombre;

        System.out.print("Tipo [" + habitacionExistente.getTipo() + "]: ");
        String tipoStr = scanner.nextLine();
        char tipo = tipoStr.isEmpty() ? habitacionExistente.getTipo() : tipoStr.toUpperCase().charAt(0);

        System.out.print("Precio por hora [" + habitacionExistente.getPrecioPorHora() + "]: ");
        String precioStr = scanner.nextLine();
        double precio = precioStr.isEmpty() ? habitacionExistente.getPrecioPorHora() : Double.parseDouble(precioStr);

        Room habitacionActualizada = new Room(nombre, tipo, precio);
        String roomData = String.format("%s|%s|%c|%s|%b",
            habitacionExistente.getId(), 
            nombre, 
            tipo, 
            String.format(java.util.Locale.US, "%.2f", precio),
            habitacionExistente.isDisponible());
        habitacionActualizada = habitacionActualizada.fromFile(roomData);
        
        controller.updateRoom(habitacionActualizada);
        System.out.println("Habitación actualizada exitosamente");
    }

    private void eliminarHabitacion() throws RoomException {
        mostrarMensajeCentrado("==== ELIMINAR HABITACIÓN ====");
        System.out.print("ID de la habitación a eliminar: ");
        String id = scanner.nextLine();
        
        Room habitacion = controller.getRoomById(id);
        mostrarDetallesHabitacion(habitacion);
        
        System.out.print("¿Está seguro de que desea eliminar esta habitación? (s/n): ");
        String response = scanner.nextLine();
        
        if (response.equalsIgnoreCase("s")) {
            controller.deleteRoom(id);
            System.out.println("Habitación eliminada exitosamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private void listarPorTipo() throws RoomException {
        mostrarMensajeCentrado("==== LISTAR POR TIPO ====");
        System.out.print("Ingrese el tipo (I/P/D/M): ");
        char tipo = scanner.nextLine().toUpperCase().charAt(0);
        List<Room> habitaciones = controller.getRoomsByType(tipo);
        mostrarListaHabitaciones(habitaciones);
    }

    private void listarDisponibles() throws RoomException {
        mostrarMensajeCentrado("==== HABITACIONES DISPONIBLES ====");
        List<Room> habitaciones = controller.getAvailableRooms();
        mostrarListaHabitaciones(habitaciones);
    }

    private void cambiarEstadoMantenimiento() throws RoomException {
        mostrarMensajeCentrado("==== CAMBIAR ESTADO DE MANTENIMIENTO ====");
        System.out.print("ID de la habitación: ");
        String id = scanner.nextLine();
        
        Room habitacion = controller.getRoomById(id);
        mostrarDetallesHabitacion(habitacion);
        
        System.out.print("¿Poner en mantenimiento? (s/n): ");
        String response = scanner.nextLine();
        
        controller.setRoomMaintenance(id, response.equalsIgnoreCase("s"));
        System.out.println("Estado de mantenimiento actualizado exitosamente.");
    }

    private void cambiarEstadoOcupacion() throws RoomException {
        mostrarMensajeCentrado("==== CAMBIAR ESTADO DE OCUPACIÓN ====");
        System.out.print("ID de la habitación: ");
        String id = scanner.nextLine();
        
        Room habitacion = controller.getRoomById(id);
        mostrarDetallesHabitacion(habitacion);
        
        System.out.print("¿Marcar como ocupada? (s/n): ");
        String response = scanner.nextLine();
        
        controller.setRoomOccupied(id, response.equalsIgnoreCase("s"));
        System.out.println("Estado de ocupación actualizado exitosamente.");
    }

    private void reservarHabitacion() throws RoomException {
        mostrarMensajeCentrado("==== RESERVAR HABITACIÓN ====");
        System.out.print("ID de la habitación: ");
        String id = scanner.nextLine();
        
        Room habitacion = controller.getRoomById(id);
        if (habitacion == null) {
            System.out.println("Habitación no encontrada.");
            return;
        }
        
        if (!habitacion.isDisponible()) {
            System.out.println("Esta habitación no está disponible.");
            return;
        }
        
        mostrarDetallesHabitacion(habitacion);
        System.out.print("¿Confirmar reserva? (s/n): ");
        String response = scanner.nextLine();
        
        if (response.equalsIgnoreCase("s")) {
            controller.setRoomOccupied(id, true);
            System.out.println("Habitación reservada exitosamente.");
        } else {
            System.out.println("Reserva cancelada.");
        }
    }

    private void cancelarReserva() throws RoomException {
        mostrarMensajeCentrado("==== CANCELAR RESERVA ====");
        System.out.print("ID de la habitación: ");
        String id = scanner.nextLine();
        
        Room habitacion = controller.getRoomById(id);
        if (habitacion == null) {
            System.out.println("Habitación no encontrada.");
            return;
        }
        
        if (habitacion.isDisponible()) {
            System.out.println("Esta habitación no está reservada.");
            return;
        }
        
        mostrarDetallesHabitacion(habitacion);
        System.out.print("¿Confirmar cancelación de reserva? (s/n): ");
        String response = scanner.nextLine();
        
        if (response.equalsIgnoreCase("s")) {
            controller.setRoomOccupied(id, false);
            System.out.println("Reserva cancelada exitosamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private void mostrarDetallesHabitacion(Room habitacion) {
        System.out.println("\nDetalles de la habitación:");
        System.out.println("ID: " + habitacion.getId());
        System.out.println("Nombre: " + habitacion.getNombre());
        System.out.println("Tipo: " + habitacion.getTipo());
        System.out.println("Precio por hora: $" + habitacion.getPrecioPorHora());
        System.out.println("Estado: " + (habitacion.isDisponible() ? "✅ Disponible" : "❌ Ocupada"));
    }

    private void mostrarListaHabitaciones(List<Room> habitaciones) {
        if (habitaciones.isEmpty()) {
            System.out.println("No se encontraron habitaciones");
            return;
        }

        String format = "| %-36s | %-6s | %-12s | %12s | %-10s |%n";
        int totalWidth = 90;

        System.out.println("+" + "-".repeat(totalWidth) + "+");
        System.out.printf(format, 
            "ID", "NOMBRE", "TIPO", "PRECIO/HORA", "ESTADO");
        System.out.println("+" + "-".repeat(totalWidth) + "+");

        for (Room habitacion : habitaciones) {
            System.out.printf(format,
                habitacion.getId(),
                truncateString(habitacion.getNombre(), 25),
                getTipoHabitacion(habitacion.getTipo()),
                String.format("$%.2f", habitacion.getPrecioPorHora()),
                habitacion.isDisponible() ? "Disponible" : "No Disponible");
        }

        System.out.println("+" + "-".repeat(totalWidth) + "+");
    }

    private String truncateString(String str, int length) {
        if (str == null) return "N/A";
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }

    private String getTipoHabitacion(char tipo) {
        return switch (tipo) {
            case 'I' -> "Individual";
            case 'P' -> "Privada";
            case 'D' -> "Dormitorio";
            case 'M' -> "Mixta";
            default -> "Desconocido";
        };
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

    public void mostrarMensajeCentrado(String mensaje) {
        int longitudMaxima = 73;
        int longitudMensaje = mensaje.length();
        int espaciosIzquierda = (longitudMaxima - longitudMensaje) / 2;
        int espaciosDerecha = longitudMaxima - longitudMensaje - espaciosIzquierda;
        String lineaCentrada = "=".repeat(espaciosIzquierda) + mensaje + "=".repeat(espaciosDerecha);
        System.out.println(lineaCentrada);
    }

    public static void main(String[] args) {
        try {
            RoomMenuController menuController = new RoomMenuController(null);
            Scanner sc = new Scanner(System.in);
            
            System.out.print("¿Iniciar menú como administrador? (s/n): ");
            String input = sc.nextLine().toLowerCase();
            boolean isAdmin = input.equals("s") || input.equals("si");
            
            if (isAdmin) menuController.showAdminMenu();
            else         menuController.showUserMenu();
            
            sc.close();
        } catch (Exception e) {
            System.err.println("Error al inicializar el menú: " + e.getMessage());
        }
    }
}