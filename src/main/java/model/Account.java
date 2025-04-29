package model;

import utils.PasswordUtil;
import utils.AccountValidation;
import java.util.UUID;

/**
 * Modelo de usuario con todos los campos requeridos.
 * Incluye: nombre, email, teléfono, user, password y rol.
 */
public class Account extends Base<Account> {
    private String id;
    private String nombre;
    private String apellido;
    private String phone;
    private String email;
    private String user;
    private String hashedPassword;
    private boolean isAdmin;

    public Account(){}

    public Account(String nombre, String apellido, String phone, String email, String user, String plainPassword) {
        if (!AccountValidation.validateEmail(email)) 
            throw new IllegalArgumentException("Email inválido");
        if (!AccountValidation.validatePhone(phone)) 
            throw new IllegalArgumentException("Teléfono inválido");
        if (!AccountValidation.validateUsername(user)) 
            throw new IllegalArgumentException("Usuario inválido (mínimo 4 caracteres alfanuméricos)");
        
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.apellido = apellido;
        this.phone = phone;
        this.email = email;
        this.user = user;
        this.hashedPassword = PasswordUtil.hashPassword(plainPassword);
        this.isAdmin = false;
    }

    // --- Getters (Acceso controlado) ---
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getFullName() { return nombre + " " + apellido; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getUser() { return user; }
    public boolean isAdmin() { return isAdmin; }

    // --- Setters (Validados) ---
    public void setPhone(String phone) {
        if (!AccountValidation.validatePhone(phone))
            throw new IllegalArgumentException("Teléfono inválido");
        this.phone = phone;
    }

    public void setUser(String user) {
        if (!AccountValidation.validateUsername(user))
            throw new IllegalArgumentException("Usuario inválido");
        this.user = user;
    }

    public void setPassword(String newPassword) {
        if (!AccountValidation.validatePasswordStrength(newPassword))
            throw new IllegalArgumentException("La contraseña debe tener 8+ caracteres, 1 mayúscula y 1 número");
        this.hashedPassword = PasswordUtil.hashPassword(newPassword);
    }

    public void setAdmin(boolean isAdmin) { 
        this.isAdmin = isAdmin; 
    }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEmail(String email) { this.email = email; }

    // --- Métodos Clave ---
    public boolean verifyPassword(String inputPassword) {
        return PasswordUtil.verifyPassword(inputPassword, hashedPassword);
    }

    // --- Serialización ---
    @Override
    public String toFile() {
        return String.join("|", 
            id, nombre, apellido, phone, email, user, 
            hashedPassword, String.valueOf(isAdmin)
        );
    }

    @Override
    public Account fromFile(String line) {
        String[] parts = line.split("\\|");
        Account account = new Account(
            parts[1], parts[2], parts[3], parts[4], parts[5], "dummy"
        );
        account.id = parts[0];
        account.hashedPassword = parts[6]; // Conserva el hash original
        account.isAdmin = Boolean.parseBoolean(parts[7]);
        return account;
    }

    @Override
    public String getInfo() {
        return String.format(
            "Usuario: %s (%s)\nTeléfono: %s\nRol: %s", 
            getFullName(), user, phone, isAdmin ? "Admin" : "Usuario"
        );
    }
}