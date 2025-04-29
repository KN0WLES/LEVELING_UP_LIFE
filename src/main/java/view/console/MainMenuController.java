package view.console;

import exceptions.*;
import model.*;
import java.util.Scanner;

public class MainMenuController {
    private AccountMenuController accountMenu;
    private BookingMenuController bookingMenu;
    private EventMenuController eventMenu;
    private FaQMenuController faqMenu;
    private ProductMenuController productMenu;
    private RoomMenuController roomMenu;
    private Scanner scanner;
    private Account currentAccount;

    public MainMenuController() {
        try {
            this.currentAccount = null;
            this.accountMenu = new AccountMenuController(currentAccount);
            this.bookingMenu = new BookingMenuController(currentAccount);
            this.eventMenu = new EventMenuController(currentAccount);
            this.faqMenu = new FaQMenuController(currentAccount);
            this.productMenu = new ProductMenuController(currentAccount);
            this.roomMenu = new RoomMenuController(currentAccount);
            this.scanner = new Scanner(System.in);
        } catch (AccountException | BookingException | EventException | RoomException e) {
            System.err.println("Error initializing menus: " + e.getMessage());
        }
    }

    public MainMenuController(Account account) {
        try {
            this.currentAccount = account;
            this.accountMenu = new AccountMenuController(currentAccount);
            this.bookingMenu = new BookingMenuController(currentAccount);
            this.eventMenu = new EventMenuController(currentAccount);
            this.faqMenu = new FaQMenuController(currentAccount);
            this.productMenu = new ProductMenuController(currentAccount);
            this.roomMenu = new RoomMenuController(currentAccount);
            this.scanner = new Scanner(System.in);
        } catch (AccountException | BookingException | EventException | RoomException e) {
            System.err.println("Error initializing menus: " + e.getMessage());
        }
    }

    public void start() {
        int option = -1;
        do {
            if (currentAccount == null) {
                showLoginMenu();
            } else {
                showMainMenu();
                option = readIntOption("Seleccione una opción: ");
                handleMainMenuOption(option);
            }
        } while (option != 0);
    }

    private void showLoginMenu() {
        System.out.println("\n==== SISTEMA DE GESTIÓN HOTELERA ====");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("0. Salir");

        int option = readIntOption("Seleccione una opción: ");
        switch (option) {
            case 1 -> login();
            case 2 -> register();
            case 0 -> System.exit(0);
            default -> System.out.println("Opción inválida");
        }
    }

    private void showMainMenu() {
        System.out.println("\n==== SISTEMA DE GESTIÓN LEVELING UP LIFE ====");
        System.out.println("Usuario actual: " + currentAccount.getUser() + " modo: " + (currentAccount.isAdmin() ? "Admin" : "Client"));
        if (currentAccount.isAdmin()){
            System.out.println("1. Mi Cuenta");
            System.out.println("2. Gestión de Cuentas");
            System.out.println("3. Gestión de Reservas");
            System.out.println("4. Gestión de Eventos");
            System.out.println("5. Gestión de Salas");
            System.out.println("6. Gestión de Productos"); // Reportes y estadisticas
            System.out.println("7. Preguntas Frecuentes");
            System.out.println("8. Cerrar sesión");
            System.out.println("0. Salir");
        } else {
            System.out.println("1. Mi Cuenta");
            System.out.println("2. Mis Reservas");
            System.out.println("3. Eventos Disponibles");
            System.out.println("4. Salas Disponibles");
            System.out.println("5. Productos Ofrecidos");
            System.out.println("6. Ayuda y soporte");
            System.out.println("7. Cerrar sesión");
            System.out.println("0. Salir");
        }
    }

    private void handleMainMenuOption(int option) {
        try {
            if (currentAccount.isAdmin()) {
                switch (option) {
                    case 1 -> accountMenu.showMyAccount(currentAccount);
                    case 2 -> accountMenu.showAdminMenu();
                    case 3 -> {
                        bookingMenu.showAdminMenu();
                    }
                    case 4 -> {
                        eventMenu.showAdminMenu();
                    }
                    case 5 -> {
                        roomMenu.showAdminMenu();
                    }
                    case 6 -> {
                        productMenu.showAdminMenu();
                    }
                    case 7 -> {
                        faqMenu.showAdminMenu();
                    }
                    case 8 -> logout();
                    case 0 -> {
                        System.out.println("¡Gracias por usar el sistema!");
                        System.exit(0);
                    }
                    default -> System.out.println("Opción inválida");
                }
            } else {
                switch (option) {
                    case 1 -> accountMenu.showMyAccount(currentAccount);
                    case 2 -> {
                        bookingMenu.showUserMenu();
                    }
                    case 3 -> {
                        eventMenu.showUserMenu();
                    }
                    case 4 -> {
                        roomMenu.showUserMenu();
                    }
                    case 5 -> {
                        productMenu.showUserMenu();
                    }
                    case 6 -> {
                        faqMenu.showUserMenu();;
                    }
                    case 7 -> logout();
                    case 0 -> {
                        System.out.println("¡Gracias por usar el sistema!");
                        System.exit(0);
                    }
                    default -> System.out.println("Opción inválida");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void login() {
        System.out.print("Usuario: ");
        String username = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        try {
            currentAccount = accountMenu.login(username, password);
            if (currentAccount != null) {
                // Update all menu controllers with the new account
                this.accountMenu = new AccountMenuController(currentAccount);
                this.bookingMenu = new BookingMenuController(currentAccount);
                this.eventMenu = new EventMenuController(currentAccount);
                this.faqMenu = new FaQMenuController(currentAccount);
                this.productMenu = new ProductMenuController(currentAccount);
                this.roomMenu = new RoomMenuController(currentAccount);
                System.out.println("¡Bienvenido, " + currentAccount.getUser() + "!");
            }
        } catch (AccountException | EventException | BookingException | RoomException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void register() {
        System.out.println("\n----- CREAR CUENTA -----");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Usuario: ");
        String user = scanner.nextLine();

        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        try {
            accountMenu.registerAccount(nombre, apellido, phone, email, user, password);
            System.out.println("Account registered successfully!");
        } catch (AccountException e) {
            System.out.println("Error registering account: " + e.getMessage());
        }
    }

    private void logout() {
        currentAccount = null;
        System.out.println("Sesión cerrada exitosamente");
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
        MainMenuController mainMenu = new MainMenuController();
        mainMenu.start();
    }
}