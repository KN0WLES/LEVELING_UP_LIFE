package view.console;

import interfaces.*;
import model.*;

import exceptions.*;
import controller.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class BookingMenuController {
    private final BookingController controller;
    private final RoomController roomController;
    private final AccountController accountController;
    private final Account account;
    private final Scanner scanner;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public BookingMenuController(Account account) throws BookingException, RoomException, AccountException {
        Room roomPrototype = new Room();
        IFile<Room> roomFileHandler = new FileHandler<>(roomPrototype);
        this.roomController = new RoomController(roomFileHandler);

        this.account = account;
        Account accountPrototype = new Account();
        IFile<Account> accountFileHandler = new FileHandler<>(accountPrototype);
        this.accountController = new AccountController(accountFileHandler);

        Booking bookingPrototype = new Booking();
        IFile<Booking> bookingFileHandler = new FileHandler<>(bookingPrototype);
        this.controller = new BookingController(bookingFileHandler, roomController);

        this.scanner = new Scanner(System.in);
    }

    public void showUserMenu() {
        int option;
        do {
            System.out.println("\n==== MENÚ DE USUARIO ====");
            System.out.println("1. Ver reservas actuales");
            System.out.println("2. Ver facturas");
            System.out.println("3. Crear nueva reserva");
            System.out.println("4. Cancelar reserva");
            System.out.println("0. Volver al menú principal");
    
            option = readIntOption("Seleccione una opción: ");
    
            try {
                switch (option) {
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    case 1:
                        mostrarReservasActuales();
                        break;
                    case 2:
                        mostrarReservasFuturas();
                        break;
                    case 3:
                        crearReserva();
                        break;
                    case 4:
                        cancelarReserva();
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor intente de nuevo.");
                }
            } catch (BookingException | RoomException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }
    
    public void showAdminMenu() {
        int option;
        do {
            System.out.println("\n==== MENÚ DE ADMINISTRADOR ====");
            System.out.println("1. Ver todas las reservas");
            System.out.println("2. Ver reservas actuales");
            System.out.println("3. Ver reservas futuras");
            System.out.println("4. Buscar reserva por ID");
            System.out.println("5. Buscar reservas por usuario");
            System.out.println("6. Buscar reservas por habitación");
            System.out.println("7. Crear nueva reserva");
            System.out.println("8. Cancelar reserva");
            System.out.println("9. Buscar reservas por rango de fechas");
            System.out.println("0. Volver al menú principal");
            option = readIntOption("Seleccione una opción: ");
            try {
                switch (option) {
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    case 1:
                        mostrarTodasLasReservas();
                        break;
                    case 2:
                        mostrarReservasActuales();
                        break;
                    case 3:
                        mostrarReservasFuturas();
                        break;
                    case 4:
                        buscarReservaPorId();
                        break;
                    case 5:
                        buscarReservasPorUsuario();
                        break;
                    case 6:
                        buscarReservasPorHabitacion();
                        break;
                    case 7:
                        crearReserva();
                        break;
                    case 8:
                        cancelarReserva();
                        break;
                    case 9:
                        buscarReservasPorRangoFechas();
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor intente de nuevo.");
                }
            } catch (BookingException | RoomException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }

    private void mostrarTodasLasReservas() {
        System.out.println("\n==== LISTADO DE TODAS LAS RESERVAS ====");
        List<Booking> reservas = controller.getAllBookings();
        mostrarListaReservas(reservas);
    }

    private void mostrarReservasActuales() throws BookingException {
        System.out.println("\n==== RESERVAS ACTUALES ====");
        List<Booking> reservas = controller.getCurrentBookings();
        mostrarListaReservas(reservas);
    }

    private void mostrarReservasFuturas() {
        System.out.println("\n==== RESERVAS FUTURAS ====");
        List<Booking> reservas = controller.getFutureBookings();
        mostrarListaReservas(reservas);
    }

    private void buscarReservaPorId() throws BookingException {
        System.out.println("\n==== BUSCAR RESERVA POR ID ====");
        System.out.print("ID de la reserva: ");
        String id = scanner.nextLine();
        Booking reserva = controller.getBookingById(id);
        mostrarDetallesReserva(reserva);
    }

    private void buscarReservasPorUsuario() throws BookingException {
        System.out.println("\n==== BUSCAR RESERVAS POR USUARIO ====");
        System.out.print("ID del usuario: ");
        String userId = scanner.nextLine();
        List<Booking> reservas = controller.getBookingsByUser(userId);
        mostrarListaReservas(reservas);
    }

    private void buscarReservasPorHabitacion() throws BookingException {
        System.out.println("\n==== BUSCAR RESERVAS POR HABITACIÓN ====");
        System.out.print("ID de la habitación: ");
        String roomId = scanner.nextLine();
        List<Booking> reservas = controller.getBookingsByRoom(roomId);
        mostrarListaReservas(reservas);
    }

    private void crearReserva() throws BookingException, RoomException {
        System.out.println("\n==== CREAR NUEVA RESERVA ====");
        
        System.out.println("ID del usuario: " + account.getId());
        
        System.out.print("ID de la habitación: ");
        String roomId = scanner.nextLine();
        
        LocalDateTime fechaInicio = leerFecha("Fecha de inicio (dd/MM/yyyy HH:mm): ");
        System.out.print("Número de horas: ");
        int horas = Integer.parseInt(scanner.nextLine());

        Booking reserva = controller.createBooking(account.getId(), roomId, fechaInicio, horas);
        System.out.println("Reserva creada exitosamente.");
        System.out.println("ID asignado: " + reserva.getId());
        mostrarDetallesReserva(reserva);
    }

    private void cancelarReserva() throws BookingException, RoomException {
        System.out.println("\n==== CANCELAR RESERVA ====");
        System.out.print("ID de la reserva a cancelar: ");
        String id = scanner.nextLine();
        
        Booking reserva = controller.getBookingById(id);
        mostrarDetallesReserva(reserva);
        
        System.out.print("¿Está seguro de que desea cancelar esta reserva? (s/n): ");
        String response = scanner.nextLine();
        
        if (response.equalsIgnoreCase("s")) {
            controller.cancelBooking(id);
            System.out.println("Reserva cancelada exitosamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private void buscarReservasPorRangoFechas() throws BookingException {
        System.out.println("\n==== BUSCAR RESERVAS POR RANGO DE FECHAS ====");
        LocalDateTime fechaInicio = leerFecha("Fecha de inicio (dd/MM/yyyy HH:mm): ");
        LocalDateTime fechaFin = leerFecha("Fecha de fin (dd/MM/yyyy HH:mm): ");
        
        List<Booking> reservas = controller.getBookingsByDateRange(fechaInicio, fechaFin);
        mostrarListaReservas(reservas);
    }

    private LocalDateTime leerFecha(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String fechaStr = scanner.nextLine();
                return LocalDateTime.parse(fechaStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Use dd/MM/yyyy HH:mm");
            }
        }
    }

    private void mostrarDetallesReserva(Booking reserva) {
        System.out.println("\nDetalles de la reserva:");
        System.out.println("ID: " + reserva.getId());
        try {
            Account usuario = accountController.getById(reserva.getAccountId());
            Room habitacion = roomController.getRoomById(reserva.getRoomId());
            System.out.println("Usuario: " + usuario.getUser() + " (" + reserva.getAccountId() + ")");
            System.out.println("Habitación: " + habitacion.getNombre() + " (" + reserva.getRoomId() + ")");
        } catch (AccountException | RoomException e) {
            System.out.println("Usuario: " + reserva.getAccountId());
            System.out.println("Habitación: " + reserva.getRoomId());
        }
        System.out.println("Fecha inicio: " + reserva.getFechaInicio().format(formatter));
        System.out.println("Fecha fin: " + reserva.getFechaFin().format(formatter));
        System.out.println("Horas: " + reserva.getHoras());
        System.out.println("Total: $" + reserva.getTotal());
    }

    private void mostrarListaReservas(List<Booking> reservas) {
        if (reservas.isEmpty()) {
            System.out.println("No se encontraron reservas");
            return;
        }

        String format = "| %-36s | %-18s | %-18s | %-19s | %-19s | %10s |%n";
        int totalWidth = 137;

        // Header
        System.out.println("+" + "-".repeat(totalWidth) + "+");
        System.out.printf(format, 
            "ID", "USUARIO", "HABITACIÓN", "FECHA INICIO", "FECHA FIN", "TOTAL");
        System.out.println("+" + "-".repeat(totalWidth) + "+");

        // Content
        for (Booking reserva : reservas) {
            String nombreUsuario = "N/A";
            String nombreHabitacion = "N/A";
            try {
                Account usuario = accountController.getById(reserva.getAccountId());
                Room habitacion = roomController.getRoomById(reserva.getRoomId());
                nombreUsuario = usuario.getUser();
                nombreHabitacion = habitacion.getNombre();
            } catch (AccountException | RoomException ignored) {}

            System.out.printf(format,
                reserva.getId(),
                truncateString(nombreUsuario, 20),
                truncateString(nombreHabitacion, 20),
                reserva.getFechaInicio().format(formatter),
                reserva.getFechaFin().format(formatter),
                String.format("$%.2f", reserva.getTotal()));
        }

        // Footer
        System.out.println("+" + "-".repeat(totalWidth) + "+");
    }

    private String truncateString(String str, int length) {
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
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
            BookingMenuController menuController = new BookingMenuController(null);
            Scanner sc = new Scanner(System.in);
            
            System.out.print("¿Iniciar menú como administrador? (s/n): ");
            String input = sc.nextLine().toLowerCase();
            boolean isAdmin = input.equals("s") || input.equals("si");

            if (isAdmin) menuController.showAdminMenu();
            else        menuController.showUserMenu();
            sc.close();
        } catch (AccountException | BookingException | RoomException e) {
            System.err.println("Error al inicializar el menú: " + e.getMessage());
        }
    }
}