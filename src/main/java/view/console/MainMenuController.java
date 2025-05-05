package view.console;

import exceptions.*;
import model.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Clase principal que controla el menú principal del sistema.
 * Coordina la navegación entre los diferentes módulos y gestiona
 * la sesión del usuario actual.
 * 
 * @description Funcionalidades principales:
 *                   - Gestionar el inicio de sesión y registro de usuarios.
 *                   - Coordinar la navegación entre módulos del sistema.
 *                   - Mostrar menús específicos según el rol del usuario.
 *                   - Mantener el estado de la sesión actual.
 *                   - Inicializar y coordinar los controladores secundarios.
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 * @see AccountMenuController
 * @see BookingMenuController
 * @see EventMenuController
 * @see Account
 */
public class MainMenuController {
    private AccountMenuController accountMenu;
    private BookingMenuController bookingMenu;
    private EventMenuController eventMenu;
    private FaQMenuController faqMenu;
    private ProductMenuController productMenu;
    private RoomMenuController roomMenu;
    private Scanner scanner;
    private Account currentAccount;
    private Logo logo;

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
            this.logo = new Logo();
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
            this.logo = new Logo();
        } catch (AccountException | BookingException | EventException | RoomException e) {
            System.err.println("Error initializing menus: " + e.getMessage());
        }
    }

    public void start() {
        int option = -1;
        logo.loadingEffect();
        logo.mostrarLogo();
        logo.details();
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

    public void go() {
        int option = -1;
        logo.loadingEffect();
        //logo.mostrarLogo();
        logo.details();
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
        mostrarMensajeCentrado("=");
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
        mostrarMensajeCentrado(" MENU PRINCIPAL ");
        if (currentAccount.isAdmin()){
            System.out.println("1. Mi Cuenta");
            System.out.println("2. Gestión de Cuentas");
            System.out.println("3. Gestión de Reservas");
            System.out.println("4. Gestión de Eventos");
            System.out.println("5. Gestión de Salas");
            System.out.println("6. Gestión de Productos");
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
                this.accountMenu = new AccountMenuController(currentAccount);
                this.bookingMenu = new BookingMenuController(currentAccount);
                this.eventMenu = new EventMenuController(currentAccount);
                this.faqMenu = new FaQMenuController(currentAccount);
                this.productMenu = new ProductMenuController(currentAccount);
                this.roomMenu = new RoomMenuController(currentAccount);
            }
        } catch (AccountException | EventException | BookingException | RoomException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void register() {
        mostrarMensajeCentrado("REGISTRO DE NUEVO USUARIO");

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
            mostrarMensajeCentrado("¡Cuenta registrada exitosamente!");
        } catch (AccountException e) {
            System.out.println("Error registering account: " + e.getMessage());
        }
    }

    private void logout() {
        currentAccount = null;
        mostrarMensajeCentrado("Sesión cerrada exitosamente");
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
        MainMenuController mainMenu = new MainMenuController();
        mainMenu.start();
    }
}

/**
 * Clase que gestiona la presentación del logo y animación inicial.
 * Proporciona efectos visuales y animaciones para mejorar la experiencia
 * de usuario al iniciar el sistema.
 * 
 * @description Funcionalidades principales:
 *                   - Mostrar el logo animado del sistema.
 *                   - Proporcionar efectos visuales de carga.
 *                   - Gestionar colores y estilos de texto en consola.
 *                   - Controlar tiempos de animación.
 *                   - Manejar excepciones de interrupción.
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 */
class Logo {
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";
    public static final String RED = "\u001B[31m";
    public static final String STAR = "\u2605";
    private static final String VERSION = "V1.4.0";
    private static final String BUILD_DATE = "29/04/2025";

    public void mostrarLogo() {
        String[] logo = {
            YELLOW + "╔═════════════════════════════════════════════════════════════════════════╗" + RESET,
            YELLOW + "║                                                                         ║" + RESET,
            YELLOW + "║" + CYAN + "  ██╗      ███████╗ ██╗   ██╗ ███████╗ ██╗      ██╗ ███╗   ██╗  ██████╗  " + YELLOW + "║" + RESET,
            YELLOW + "║" + CYAN + "  ██║      ██╔════╝ ██║   ██║ ██╔════╝ ██║      ██║ ████╗  ██║ ██╔════╝  " + YELLOW + "║" + RESET,
            YELLOW + "║" + CYAN + "  ██║      █████╗   ██║   ██║ █████╗   ██║      ██║ ██╔██╗ ██║ ██║  ███╗ " + YELLOW + "║" + RESET,
            YELLOW + "║" + CYAN + "  ██║      ██╔══╝   ╚██╗ ██╔╝ ██╔══╝   ██║      ██║ ██║╚██╗██║ ██║   ██║ " + YELLOW + "║" + RESET,
            YELLOW + "║" + CYAN + "  ███████╗ ███████╗  ╚████╔╝  ███████╗ ███████╗ ██║ ██║ ╚████║ ╚██████╔╝ " + YELLOW + "║" + RESET,
            YELLOW + "║" + CYAN + "  ╚══════╝ ╚══════╝   ╚═══╝   ╚══════╝ ╚══════╝ ╚═╝ ╚═╝  ╚═══╝  ╚═════╝  " + YELLOW + "║" + RESET,
            YELLOW + "║" + "                                                                         " + YELLOW + "║" + RESET,
            YELLOW + "║" + GREEN + "                           ██╗   ██╗ ██████╗                             " + YELLOW + "║" + RESET,
            YELLOW + "║" + GREEN + "                           ██║   ██║ ██╔══██╗                            " + YELLOW + "║" + RESET,
            YELLOW + "║" + GREEN + "                           ██║   ██║ ██████╔╝                            " + YELLOW + "║" + RESET,
            YELLOW + "║" + GREEN + "                           ██║   ██║ ██╔═══╝                             " + YELLOW + "║" + RESET,
            YELLOW + "║" + GREEN + "                           ╚██████╔╝ ██║                                 " + YELLOW + "║" + RESET,
            YELLOW + "║" + GREEN + "                            ╚═════╝  ╚═╝                                 " + YELLOW + "║" + RESET,
            YELLOW + "║" + "                                                                         " + YELLOW + "║" + RESET,
            YELLOW + "║" + PURPLE + "                    ██╗      ██╗ ███████╗ ███████╗                       " + YELLOW + "║" + RESET,
            YELLOW + "║" + PURPLE + "                    ██║      ██║ ██╔════╝ ██╔════╝                       " + YELLOW + "║" + RESET,
            YELLOW + "║" + PURPLE + "                    ██║      ██║ █████╗   █████╗                         " + YELLOW + "║" + RESET,
            YELLOW + "║" + PURPLE + "                    ██║      ██║ ██╔══╝   ██╔══╝                         " + YELLOW + "║" + RESET,
            YELLOW + "║" + PURPLE + "                    ███████╗ ██║ ██║      ███████╗                       " + YELLOW + "║" + RESET,
            YELLOW + "║" + PURPLE + "                    ╚══════╝ ╚═╝ ╚═╝      ╚══════╝                       " + YELLOW + "║" + RESET,
            YELLOW + "║" + "                                                                         " + YELLOW + "║" + RESET,
            YELLOW + "╚═════════════════════════════════════════════════════════════════════════╝" + RESET,
            "",
        };
        
        for (String line : logo) {
            System.out.println(line);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void details(){
        String[] logo = {
            CYAN + "                          "+STAR+" ¡BIENVENIDO! "+STAR + RESET,
            PURPLE + "                    Version: " + VERSION + " - Build: " + BUILD_DATE + RESET,
            PURPLE + "                         Copyright © 2025 KNOWLES" + RESET
        };
        
        for (String line : logo) {
            System.out.println(line);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void loadingEffect() {
        System.out.println(RED + "Iniciando sistema..." + RESET);
        String loading = "█";
        
        System.out.print("Cargando: [          ] 0%");
        
        for (int i = 0; i <= 100; i += 10) {
            try {
                Thread.sleep(500);
                System.out.print("\rCargando: [");
                for (int j = 0; j < i/10; j++) {
                    System.out.print(GREEN + loading + RESET);
                }
                for (int j = 0; j < 10 - i/10; j++) {
                    System.out.print(" ");
                }
                System.out.print("] " + i + "%");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("\n" + GREEN + "¡Sistema cargado con éxito!" + RESET);
        System.out.println();
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
