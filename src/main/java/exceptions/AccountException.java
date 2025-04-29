package exceptions;

public class AccountException extends Exception {
    // Básico
    public AccountException(String message) {
        super(message);
    }
    
    // Factory methods para errores comunes
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