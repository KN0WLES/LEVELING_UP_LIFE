package model;

import utils.PasswordUtil;
import utils.AccountValidation;
import java.util.UUID;

/**
 * Clase que representa el modelo de una cuenta de usuario.
 * Contiene todos los campos necesarios para gestionar información de usuarios, como nombre, email, teléfono,
 * nombre de usuario, contraseña y rol (usuario o administrador).
 * 
 * @description Funcionalidades principales:
 *                   - Validar datos al crear o actualizar una cuenta (email, teléfono, usuario, contraseña).
 *                   - Generar automáticamente un identificador único (UUID) para cada cuenta.
 *                   - Hashing seguro de contraseñas utilizando `PasswordUtil`.
 *                   - Verificar contraseñas ingresadas contra las almacenadas.
 *                   - Gestionar roles de administrador.
 *                   - Serializar y deserializar cuentas para almacenamiento en archivos.
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 * @see PasswordUtil
 * @see AccountValidation
 * @see Base
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

    /**
     * Constructor vacío necesario para la deserialización.
     */
    public Account(){}

    /**
     * Constructor que crea una nueva cuenta con validación de datos.
     * 
     * @param nombre Nombre del usuario
     * @param apellido Apellido del usuario
     * @param phone Número de teléfono (debe cumplir con el formato válido)
     * @param email Correo electrónico (debe cumplir con el formato válido)
     * @param user Nombre de usuario (debe tener mínimo 4 caracteres alfanuméricos)
     * @param plainPassword Contraseña en texto plano que será hasheada
     * @throws IllegalArgumentException Si alguno de los datos no cumple con las validaciones
     */
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

    /**
     * Obtiene el identificador único de la cuenta.
     * 
     * @return El UUID de la cuenta como String
     */
    public String getId() { return id; }
    
    /**
     * Obtiene el nombre del usuario.
     * 
     * @return El nombre del usuario
     */
    public String getNombre() { return nombre; }
    
    /**
     * Obtiene el apellido del usuario.
     * 
     * @return El apellido del usuario
     */
    public String getApellido() { return apellido; }
    
    /**
     * Obtiene el nombre completo del usuario.
     * 
     * @return El nombre y apellido concatenados
     */
    public String getFullName() { return nombre + " " + apellido; }
    
    /**
     * Obtiene el número de teléfono del usuario.
     * 
     * @return El número de teléfono
     */
    public String getPhone() { return phone; }
    
    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return El correo electrónico
     */
    public String getEmail() { return email; }
    
    /**
     * Obtiene el nombre de usuario.
     * 
     * @return El nombre de usuario
     */
    public String getUser() { return user; }
    
    /**
     * Comprueba si la cuenta tiene permisos de administrador.
     * 
     * @return true si la cuenta es de administrador, false en caso contrario
     */
    public boolean isAdmin() { return isAdmin; }

    /**
     * Establece un nuevo número de teléfono con validación.
     * 
     * @param phone El nuevo número de teléfono
     * @throws IllegalArgumentException Si el teléfono no cumple con el formato válido
     */
    public void setPhone(String phone) {
        if (!AccountValidation.validatePhone(phone))
            throw new IllegalArgumentException("Teléfono inválido");
        this.phone = phone;
    }

    /**
     * Establece un nuevo nombre de usuario con validación.
     * 
     * @param user El nuevo nombre de usuario
     * @throws IllegalArgumentException Si el usuario no cumple con el formato válido
     */
    public void setUser(String user) {
        if (!AccountValidation.validateUsername(user))
            throw new IllegalArgumentException("Usuario inválido");
        this.user = user;
    }

    /**
     * Establece una nueva contraseña con validación y hashing.
     * 
     * @param newPassword La nueva contraseña en texto plano
     * @throws IllegalArgumentException Si la contraseña no cumple con los requisitos de seguridad
     */
    public void setPassword(String newPassword) {
        if (!AccountValidation.validatePasswordStrength(newPassword))
            throw new IllegalArgumentException("La contraseña debe tener 8+ caracteres, 1 mayúscula y 1 número");
        this.hashedPassword = PasswordUtil.hashPassword(newPassword);
    }

    /**
     * Establece el rol de administrador para la cuenta.
     * 
     * @param isAdmin true para dar permisos de administrador, false para quitarlos
     */
    public void setAdmin(boolean isAdmin) { 
        this.isAdmin = isAdmin; 
    }

    /**
     * Establece un nuevo nombre para el usuario.
     * 
     * @param nombre El nuevo nombre
     */
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    /**
     * Establece un nuevo apellido para el usuario.
     * 
     * @param apellido El nuevo apellido
     */
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    /**
     * Establece un nuevo correo electrónico para el usuario.
     * 
     * @param email El nuevo correo electrónico
     */
    public void setEmail(String email) { this.email = email; }
    
    /**
     * Verifica si la contraseña proporcionada coincide con la almacenada.
     * 
     * @param inputPassword La contraseña en texto plano a verificar
     * @return true si la contraseña es correcta, false en caso contrario
     */
    public boolean verifyPassword(String inputPassword) {
        return PasswordUtil.verifyPassword(inputPassword, hashedPassword);
    }

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
        account.hashedPassword = parts[6];
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