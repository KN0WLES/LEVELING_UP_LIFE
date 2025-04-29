package view.console;

import interfaces.*;
import model.*;
import exceptions.*;
import controller.*;

import java.util.List;
import java.util.Scanner;
import java.io.Console;

public class AccountMenuController {
    private final AccountController accountController;
    private final Scanner scanner;
    private Account currentLoggedAccount;
    private final Console console;

    public AccountMenuController(Account account) throws AccountException {
        Account prototype = new Account();
        IFile<Account> fileHandler = new FileHandler<>(prototype);
        this.accountController = new AccountController(fileHandler);
        this.scanner = new Scanner(System.in);
        this.console = System.console();
        this.currentLoggedAccount = null;
    }

    // Method to get current logged account
    public Account getCurrentLoggedAccount() {
        return currentLoggedAccount;
    }

    // Method for login that returns the Account object
    public Account login(String username, String password) throws AccountException {
        // Intentar login directamente con la contraseña proporcionada
        currentLoggedAccount = accountController.login(username, password);
        
        // Limpiar pantalla después del login exitoso
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        // Mostrar mensaje de bienvenida y menú principal
        System.out.println("\n==== SISTEMA DE GESTIÓN LEVELING UP LIFE ====");
        System.out.println("Usuario actual: " + username + " modo: " + 
            (currentLoggedAccount.isAdmin() ? "Admin" : "Client"));
        
        return currentLoggedAccount;
    }

    // Method to handle user registration and return the created account
    public Account registerAccount(String name, String lastName, String phone, String email, String username, String password) throws AccountException {
        Account newAccount = new Account(name, lastName, phone, email, username, password);
        accountController.registerAccount(newAccount);
        return newAccount;
    }

    public void showUserMenu() {
        int option;
        do {
            System.out.println("\n==== MENÚ DE USUARIO ====");
            System.out.println("1. Actualizar información de cuenta");
            System.out.println("2. Cambiar contraseña");
            System.out.println("0. Volver al menú principal");

            option = readIntOption("Seleccione una opción: ");

            try {
                switch (option) {
                    case 1 -> updateAccountMenu();
                    case 2 -> changePasswordMenu();
                    case 0 -> {
                    }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }

    public void showAdminMenu() {
        int option;
        do {
            System.out.println("\n==== MENÚ DE ADMINISTRACIÓN DE CUENTAS ====");
            System.out.println("1. Ver todas las cuentas");
            System.out.println("2. Buscar cuenta por usuario");
            System.out.println("3. Buscar cuenta por email");
            System.out.println("4. Modificar estado de administrador");
            System.out.println("5. Eliminar cuenta");
            System.out.println("6. Actualizar datos de cuenta"); // Nueva opción
            System.out.println("0. Volver al menú principal");

            option = readIntOption("Seleccione una opción: ");

            try {
                switch (option) {
                    case 1 -> showAllAccountsMenu();
                    case 2 -> findAccountByUsernameMenu();
                    case 3 -> findAccountByEmailMenu();
                    case 4 -> modifyAdminStatusMenu();
                    case 5 -> deleteAccountMenu();
                    case 6 -> updateAnyAccountMenu(); // Nuevo método
                    case 0 -> {
                    }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }

    // Nuevo método para actualizar cualquier cuenta
    private void updateAnyAccountMenu() {
        System.out.println("\n==== ACTUALIZAR DATOS DE CUENTA ====");
        System.out.print("Ingrese el nombre de usuario de la cuenta a modificar: ");
        String username = scanner.nextLine();
        
        try {
            Account account = accountController.getByUsername(username);
            if (account == null) {
                System.out.println("No se encontró la cuenta con el nombre de usuario: " + username);
                return;
            }

            System.out.println("\n==== ACTUALIZAR INFORMACIÓN DE CUENTA ====");
            System.out.println("1. Actualizar nombre");
            System.out.println("2. Actualizar apellido");
            System.out.println("3. Actualizar teléfono");
            System.out.println("4. Actualizar correo electrónico");
            System.out.println("5. Actualizar contraseña");
            System.out.println("0. Volver");
            
            int option = readIntOption("Seleccione una opción: ");
            
            switch (option) {
                case 1 -> {
                    System.out.print("Nuevo nombre: ");
                    String newName = scanner.nextLine();
                    accountController.updateName(username, newName);
                    System.out.println("Nombre actualizado exitosamente.");
                }
                case 2 -> {
                    System.out.print("Nuevo apellido: ");
                    String newLast = scanner.nextLine();
                    accountController.updateLast(username, newLast);
                    System.out.println("Apellido actualizado exitosamente.");
                }
                case 3 -> {
                    System.out.print("Nuevo teléfono: ");
                    String newPhone = scanner.nextLine();
                    accountController.updatePhone(username, newPhone);
                    System.out.println("Teléfono actualizado exitosamente.");
                }
                case 4 -> {
                    System.out.print("Nuevo correo electrónico: ");
                    String newEmail = scanner.nextLine();
                    accountController.updateEmail(username, newEmail);
                    System.out.println("Correo electrónico actualizado exitosamente.");
                }
                case 5 -> {
                    System.out.print("Nueva contraseña: ");
                    String newPassword = scanner.nextLine();
                    // No necesitamos la contraseña actual porque es un admin
                    accountController.resetUserPassword(username, newPassword);
                    System.out.println("Contraseña actualizada exitosamente.");
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        } catch (AccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateAccountMenu() {
        System.out.println("\n==== ACTUALIZAR INFORMACIÓN DE CUENTA ====");
        System.out.println("1. Actualizar nombre");
        System.out.println("2. Actualizar apellido");
        System.out.println("3. Actualizar teléfono");
        System.out.println("4. Actualizar correo electrónico");
        System.out.println("0. Volver");
        
        int option = readIntOption("Seleccione una opción: ");
        
        try {
            switch (option) {
                case 1 -> {
                    System.out.print("Nuevo nombre: ");
                    String newName = scanner.nextLine();
                    accountController.updateName(currentLoggedAccount.getUser(), newName);
                    System.out.println("Nombre actualizado exitosamente.");
                }
                case 2 -> {
                    System.out.print("Nuevo nombre: ");
                    String newLast = scanner.nextLine();
                    accountController.updateLast(currentLoggedAccount.getUser(), newLast);
                    System.out.println("Nombre actualizado exitosamente.");
                }
                case 3 -> {
                    System.out.print("Nuevo teléfono: ");
                    String newPhone = scanner.nextLine();
                    accountController.updatePhone(currentLoggedAccount.getUser(), newPhone);
                    System.out.println("Teléfono actualizado exitosamente.");
                }
                case 4 -> {
                    System.out.print("Nuevo correo electrónico: ");
                    String newEmail = scanner.nextLine();
                    accountController.updateEmail(currentLoggedAccount.getUser(), newEmail);
                    System.out.println("Correo electrónico actualizado exitosamente.");
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        } catch (AccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void changePasswordMenu() {
        System.out.println("\n==== CAMBIAR CONTRASEÑA ====");
        
        String currentPassword;
        String newPassword;
        
        if (console != null) {
            // Usando console para ocultar la contraseña
            currentPassword = new String(console.readPassword("Contraseña actual: "));
            newPassword = new String(console.readPassword("Nueva contraseña: "));
        } else {
            // Fallback para entornos sin console (como algunos IDEs)
            System.out.print("Contraseña actual: ");
            currentPassword = scanner.nextLine();
            System.out.print("Nueva contraseña: ");
            newPassword = scanner.nextLine();
        }
        
        try {
            accountController.updatePassword(currentLoggedAccount.getUser(), currentPassword, newPassword);
            System.out.println("Contraseña actualizada exitosamente.");
        } catch (AccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showAllAccountsMenu() {
        System.out.println("\n==== LISTADO DE TODAS LAS CUENTAS ====");
        try {
            List<Account> accounts = accountController.getAllAccounts();
            if (accounts.isEmpty()) {
                System.out.println("No hay cuentas registradas.");
                return;
            }

            String format = "| %-36s | %-10s | %-36s | %-25s | %-15s | %-6s |%n";
            int totalWidth = 145;

            // Header
            System.out.println("+" + "-".repeat(totalWidth) + "+");
            System.out.printf(format, 
                "ID","USUARIO", "NOMBRE", "EMAIL", "TELÉFONO", "ADMIN");
            System.out.println("+" + "-".repeat(totalWidth) + "+");

            // Content
            for (Account account : accounts) {
                System.out.printf(format,
                    account.getId(),
                    truncateString(account.getUser(), 20),
                    truncateString(account.getFullName(), 25),
                    truncateString(account.getEmail(), 25),
                    account.getPhone(),
                    account.isAdmin() ? "Sí" : "No");
            }

            // Footer
            System.out.println("+" + "-".repeat(totalWidth) + "+");

        } catch (AccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String truncateString(String str, int length) {
        if (str == null) return "N/A";
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }

    private void findAccountByUsernameMenu() {
        System.out.println("\n==== BUSCAR CUENTA POR NOMBRE DE USUARIO ====");
        System.out.print("Nombre de usuario: ");
        String username = scanner.nextLine();
        
        try {
            Account account = accountController.getByUsername(username);
            if (account == null) {
                System.out.println("No se encontró la cuenta con el nombre de usuario: " + username);
                return;
            }
            displayAccountDetails(account);
        } catch (AccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findAccountByEmailMenu() {
        System.out.println("\n==== BUSCAR CUENTA POR EMAIL ====");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        try {
            Account account = accountController.getByEmail(email);
            if (account == null) {
                System.out.println("No se encontró la cuenta con el email: " + email);
                return;
            }
            displayAccountDetails(account);
        } catch (AccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void modifyAdminStatusMenu() {
        System.out.println("\n==== MODIFICAR ESTADO DE ADMINISTRADOR ====");
        System.out.print("Nombre de usuario: ");
        String username = scanner.nextLine();
        
        try {
            Account account = accountController.getByUsername(username);
            if (account == null) {
                System.out.println("No se encontró la cuenta con el nombre de usuario: " + username);
                return;
            }
            
            System.out.println("Estado actual: " + (account.isAdmin() ? "Administrador" : "Usuario normal"));
            System.out.print("¿Desea cambiar el estado? (s/n): ");
            String response = scanner.nextLine();
            
            if (response.equalsIgnoreCase("s")) {
                boolean newStatus = !account.isAdmin();
                accountController.setAdminStatus(username, newStatus);
                System.out.println("Estado cambiado a: " + (newStatus ? "Administrador" : "Usuario normal"));
            }
        } catch (AccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteAccountMenu() {
        System.out.println("\n==== ELIMINAR CUENTA ====");
        System.out.print("Nombre de usuario: ");
        String username = scanner.nextLine();
        
        try {
            Account account = accountController.getByUsername(username);
            if (account == null) {
                System.out.println("No se encontró la cuenta con el nombre de usuario: " + username);
                return;
            }
            
            if (account.isAdmin()) {
                System.out.println("ADVERTENCIA: Está a punto de eliminar una cuenta de administrador.");
            }

            System.out.print("¿Está seguro de que desea eliminar esta cuenta? (s/n): ");
            String response = scanner.nextLine();
            
            if (response.equalsIgnoreCase("s")) {
                accountController.deleteAccount(username);
                System.out.println("Cuenta eliminada exitosamente.");
            }
        } catch (AccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void displayAccountDetails(Account account) {
        System.out.println("\nDetalles de la cuenta:");
        System.out.println("Usuario: " + account.getUser());
        System.out.println("Nombre: " + account.getNombre());
        System.out.println("Apellido: " + account.getApellido());
        System.out.println("Email: " + account.getEmail());
        System.out.println("Teléfono: " + account.getPhone());
        System.out.println("Admin: " + (account.isAdmin() ? "Sí" : "No"));
    }

    public void logout() {
        currentLoggedAccount = null;
        System.out.println("Sesión cerrada exitosamente.");
    }

    public void showMyAccount(Account account) {
        System.out.println("\n==== MI CUENTA ====");
        displayAccountDetails(account);
        showUserMenu();
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
            AccountMenuController menuController = new AccountMenuController(null);
            Scanner sc = new Scanner(System.in);

            System.out.print("¿Iniciar menú como administrador? (s/n): ");
            String input = sc.nextLine().toLowerCase();
            boolean isAdmin = input.equals("s") || input.equals("si");

            if (isAdmin) menuController.showAdminMenu();
            else        menuController.showUserMenu();
            sc.close();
        } catch (AccountException e) {
            System.err.println("Error al inicializar el menú: " + e.getMessage());
        }
    }
}