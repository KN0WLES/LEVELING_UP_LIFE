package view.console;

import controller.*;
import interfaces.*;
import model.*;
import exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que representa el controlador de menú para la gestión de eventos.
 * Maneja la interacción del usuario con los eventos del sistema, permitiendo
 * su creación, gestión y participación.
 * 
 * @description Funcionalidades principales:
 *                   - Gestionar la creación y modificación de eventos.
 *                   - Controlar la participación en eventos.
 *                   - Manejar fechas y capacidad de eventos.
 *                   - Realizar búsquedas y filtrados de eventos.
 *                   - Validar entradas y manejar excepciones.
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 * @see EventController
 * @see Event
 * @see Account
 */
public class EventMenuController {
    private EventController controller;
    private AccountController accountController;
    private final Account account;
    private final Scanner scanner;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public EventMenuController(Account account) throws EventException, AccountException{
        Event prototype = new Event();
        IFile<Event> fileHandler = new FileHandler<>(prototype);

        this.account = account;
        Account accountPrototype = new Account();
        IFile<Account> accountFileHandler = new FileHandler<>(accountPrototype);
        this.accountController = new AccountController(accountFileHandler);
        try {
            this.controller = new EventController(fileHandler);
        } catch (Exception e) {
            System.err.println("Error al inicializar EventController: " + e.getMessage());
            this.controller = null;
        }
        
        this.scanner = new Scanner(System.in);
    }

    public void showAdminMenu() {
        int option;
        do {
            mostrarMensajeCentrado("==== SISTEMA DE GESTION DE EVENTO ====");
            System.out.println("1. Ver todos los Evento");
            System.out.println("2. Ver Eventos Activos");
            System.out.println("3. Buscar Eventos por ID");
            System.out.println("4. Buscar Eventos por Fecha");
            System.out.println("5. Crear Eventos");
            System.out.println("6. Cancelar Eventos");
            System.out.println("7. Gestionar participantes");
            System.out.println("0. Volver al menú principal");
            
            option = readIntOption("Seleccione una opción: ");
            
            try {
                switch (option) {
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    case 1:
                        mostrarTodosLosEventos();
                        break;
                    case 2:
                        mostrarEventosActivos();
                        break;
                    case 3:
                        buscarEventoPorId();
                        break;
                    case 4:
                        buscarEventosPorFecha();
                        break;
                    case 5:
                        crearEvento();
                        break;
                    case 6:
                        cancelarEvento();
                        break;
                    case 7:
                        gestionarParticipantes();
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor intente de nuevo.");
                }
            } catch (EventException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }
    
    public void showUserMenu() {
        int option;
        do {
            mostrarMensajeCentrado("==== SISTEMA DE GESTION DE EVENTOS ====");
            System.out.println("1. Ver cartelera de Eventos");
            System.out.println("2. Registrarse en Evento");
            System.out.println("0. Volver al menú principal");
            option = readIntOption("Seleccione una opción: ");
            
            try {
                switch (option) {
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    case 1:
                        mostrarEventosActivos();
                        break;
                    case 2:
                        registrarseEnEvento();
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor intente de nuevo.");
                }
            } catch (EventException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }

    private void mostrarTodosLosEventos() throws EventException {
        mostrarMensajeCentrado("==== LISTADO DE TODOS LOS EVENTOS ====");
        List<Event> eventos = controller.listarTodos();
        mostrarListaEventos(eventos);
    }

    private void mostrarEventosActivos() throws EventException {
        mostrarMensajeCentrado("==== EVENTOS ACTIVOS ====");
        List<Event> eventos = controller.listarEventosActivos();
        mostrarListaEventos(eventos);
    }

    private void buscarEventoPorId() throws EventException {
        mostrarMensajeCentrado("==== BUSCAR EVENTO POR ID ====");
        System.out.print("ID del evento: ");
        String id = scanner.nextLine();
        Event evento = controller.buscarPorId(id);
        if (evento != null) {
            mostrarDetallesEvento(evento);
        } else {
            System.out.println("Evento no encontrado.");
        }
    }

    private void buscarEventosPorFecha() throws EventException {
        mostrarMensajeCentrado("==== BUSCAR EVENTOS POR FECHA ====");
        LocalDateTime fecha = leerFecha("Ingrese la fecha (dd/MM/yyyy HH:mm): ");
        List<Event> eventos = controller.buscarPorFecha(fecha);
        mostrarListaEventos(eventos);
    }

    private void crearEvento() throws EventException {
        mostrarMensajeCentrado("==== CREAR NUEVO EVENTO ====");
        
        System.out.print("Nombre del evento: ");
        String nombre = scanner.nextLine();
        
        LocalDateTime fechaInicio = leerFecha("Fecha de inicio (dd/MM/yyyy HH:mm): ");
        LocalDateTime fechaFin = leerFecha("Fecha de fin (dd/MM/yyyy HH:mm): ");
        
        System.out.print("Tipo (C: Conferencia, T: Taller, F: Fiesta): ");
        char tipo = scanner.nextLine().toUpperCase().charAt(0);
        
        System.out.print("Capacidad: ");
        int capacidad = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

        Event evento = new Event(nombre, fechaInicio, fechaFin, tipo, capacidad, precio);
        controller.crearEvento(evento);
        System.out.println("Evento creado exitosamente.");
        System.out.println("ID asignado: " + evento.getId());
    }

    private void cancelarEvento() throws EventException {
        mostrarMensajeCentrado("==== CANCELAR EVENTO ====");
        System.out.print("ID del evento a cancelar: ");
        String id = scanner.nextLine();
        
        Event evento = controller.buscarPorId(id);
        if (evento != null) {
            mostrarDetallesEvento(evento);
            System.out.print("¿Está seguro de que desea cancelar este evento? (s/n): ");
            String response = scanner.nextLine();
            
            if (response.equalsIgnoreCase("s")) {
                controller.cancelarEvento(id);
                System.out.println("Evento cancelado exitosamente.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("Evento no encontrado.");
        }
    }

    private void gestionarParticipantes() throws EventException {
        mostrarMensajeCentrado("==== GESTIÓN DE PARTICIPANTES ====");
        System.out.print("ID del evento: ");
        String eventId = scanner.nextLine();
        
        Event evento = controller.buscarPorId(eventId);
        if (evento == null) {
            System.out.println("Evento no encontrado.");
            return;
        }

        mostrarDetallesEvento(evento);
        
        System.out.println("\n1. Agregar participante");
        System.out.println("2. Remover participante");
        System.out.println("0. Volver");
        
        int opcion = readIntOption("Seleccione una opción: ");
        
        switch (opcion) {
            case 1:
                agregarParticipante(evento);
                break;
            case 2:
                removerParticipante(evento);
                break;
            case 0:
                return;
            default:
                System.out.println("Opción inválida");
        }
    }

    private void agregarParticipante(Event evento) throws EventException {
        System.out.print("ID del participante: ");
        String accountId = scanner.nextLine();
        
        if (controller.agregarParticipante(evento.getId(), accountId)) {
            System.out.println("Participante agregado exitosamente.");
        } else {
            System.out.println("No se pudo agregar el participante.");
        }
    }

    private void removerParticipante(Event evento) throws EventException {
        System.out.print("ID del participante: ");
        String accountId = scanner.nextLine();
        
        if (controller.removerParticipante(evento.getId(), accountId)) {
            System.out.println("Participante removido exitosamente.");
        } else {
            System.out.println("No se pudo remover el participante.");
        }
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

    private void mostrarDetallesEvento(Event evento) {
        mostrarMensajeCentrado("Detalles del evento:");
        System.out.println("ID: " + evento.getId());
        System.out.println("Nombre: " + evento.getNombre());
        System.out.println("Tipo: " + getTipoEvento(evento.getTipo()));
        System.out.println("Fecha inicio: " + evento.getFechaInicio().format(formatter));
        System.out.println("Fecha fin: " + evento.getFechaFin().format(formatter));
        System.out.println("Capacidad: " + evento.getParticipantes().size() + "/" + evento.getCapacidad());
        System.out.println("Precio: $" + evento.getPrecio());
        
        mostrarMensajeCentrado("Participantes registrados:");
        if (evento.getParticipantes().isEmpty()) {
            System.out.println("No hay participantes registrados.");
        } else {
            System.out.println("ID\t\t\t\t\tUsername");
            System.out.println("-".repeat(60));
            for (String participantId : evento.getParticipantes()) {
                try {
                    Account account = accountController.getById(participantId);
                    System.out.printf("%s\t%s%n", participantId, account.getUser());
                } catch (Exception e) {
                    System.out.printf("%s\t[Usuario no encontrado]%n", participantId);
                }
            }
        }
    }

    private void mostrarListaEventos(List<Event> eventos) {
        if (eventos.isEmpty()) {
            System.out.println("No se encontraron eventos");
            return;
        }

        String format = "| %-36s | %-20s | %-12s | %-19s | %-8s | %-9s | %-11s |%n";
        int totalWidth = 135;

        System.out.println("+" + "-".repeat(totalWidth) + "+");
        System.out.printf(format, 
            "ID", "NOMBRE", "TIPO", "FECHA", "DURACIÓN", "CAPACIDAD", "ASISTENTES");
        System.out.println("+" + "-".repeat(totalWidth) + "+");

        for (Event evento : eventos) {
            long duracionHoras = java.time.Duration.between(
                evento.getFechaInicio(), 
                evento.getFechaFin()).toHours();
            
            System.out.printf(format,
                evento.getId(),
                truncateString(evento.getNombre(), 25),
                getTipoEvento(evento.getTipo()),
                evento.getFechaInicio().format(formatter),
                duracionHoras + "h",
                evento.getCapacidad(),
                evento.getParticipantes().size());
        }

        System.out.println("+" + "-".repeat(totalWidth) + "+");
    }

    private String truncateString(String str, int length) {
        if (str == null) return "N/A";
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }

    private String getTipoEvento(char tipo) {
        switch (tipo) {
            case 'C': return "Conferencia";
            case 'T': return "Taller";
            case 'F': return "Fiesta";
            default: return "Desconocido";
        }
    }

    public void registrarseEnEvento() {
        mostrarMensajeCentrado("==== REGISTRARSE EN EVENTO ====");
        try {
            System.out.println("Eventos disponibles:");
            List<Event> eventosActivos = controller.listarEventosActivos();
            if (eventosActivos.isEmpty()) {
                System.out.println("No hay eventos activos disponibles.");
                return;
            }
            
            for (int i = 0; i < eventosActivos.size(); i++) {
                Event evento = eventosActivos.get(i);
                System.out.printf("%d. %-20s (Tipo: %-10s Fecha: %-16s Precio: $%.2f Ocupación: %d/%d)%n",
                        i + 1,
                        evento.getNombre(),
                        getTipoEvento(evento.getTipo()),
                        evento.getFechaInicio().format(formatter),
                        evento.getPrecio(),
                        evento.getParticipantes().size(),
                        evento.getCapacidad());
            }
            
            System.out.print("Seleccione el número del evento (0 para cancelar): ");
            int seleccion = readIntOption("") - 1;
            if (seleccion == -1) {
                System.out.println("Operación cancelada.");
                return;
            }
            
            if (seleccion < 0 || seleccion >= eventosActivos.size()) {
                System.out.println("Selección inválida.");
                return;
            }
            
            Event eventoSeleccionado = eventosActivos.get(seleccion);

            System.out.println("ID de usuario: " + account.getId());
            String userId = account.getId();

            if (eventoSeleccionado.getParticipantes().contains(userId)) {
                System.out.println("Ya estás registrado en este evento.");
                return;
            }

            if (eventoSeleccionado.getParticipantes().size() >= eventoSeleccionado.getCapacidad()) {
                System.out.println("El evento ya está lleno.");
                return;
            }

            if (controller.agregarParticipante(eventoSeleccionado.getId(), userId)) {
                System.out.println("¡Registro exitoso en el evento: " + eventoSeleccionado.getNombre() + "!");
            } else {
                System.out.println("No se pudo completar el registro. Intente nuevamente.");
            }
            
        } catch (EventException e) {
            System.out.println("Error: " + e.getMessage());
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
            EventMenuController menuController = new EventMenuController(null);
            Scanner sc = new Scanner(System.in);
            
            System.out.print("¿Iniciar menú como administrador? (s/n): ");
            String input = sc.nextLine().toLowerCase();
            boolean isAdmin = input.equals("s") || input.equals("si");
            
            if (isAdmin) menuController.showAdminMenu();
            else menuController.showUserMenu();
            
            sc.close();
        } catch (AccountException | EventException e) {
            System.err.println("Error al inicializar el menú: " + e.getMessage());
        }
    }
}