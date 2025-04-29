package exceptions;

/**
 * Clase que representa excepciones específicas relacionadas con la gestión de cuentas de usuario.
 * Proporciona mensajes de error predefinidos para situaciones comunes, como cuentas no encontradas,
 * usuarios duplicados o credenciales inválidas.
 * 
 * @description Funcionalidades principales:
 *                   - Lanzar una excepción cuando una cuenta no es encontrada.
 *                   - Lanzar una excepción cuando un usuario ya existe.
 *                   - Lanzar una excepción cuando las credenciales son inválidas.
 *                   - Lanzar una excepción cuando se intenta modificar/eliminar una cuenta de administrador.
 * 
 * @autor KNOWLES
 * @version 1.0
 * @since 2025-04-29
 */
public class AccountException extends Exception {
    public AccountException(String message) {
        super(message);
    }
    
    public static AccountException userNotFound() {
        return new AccountException("Cuenta no encontrada");
    }
    public static AccountException duplicateUser() {
        return new AccountException("El usuario ya existe");
    }
    public static AccountException invalidCredentials() {
        return new AccountException("Usuario o contraseña incorrectos");
    }
    public static AccountException adminRestriction() {
        return new AccountException("No se puede modificar/eliminar una cuenta admin");
    }
}