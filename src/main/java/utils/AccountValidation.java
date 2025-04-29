package utils;

import java.util.regex.Pattern;

/**
 * Valida datos de cuentas (email, teléfono, contraseña, etc.).
 */
public class AccountValidation {

    private AccountValidation(){} // Clase de utilidad

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d).{8,}$";

    public static boolean validateUsername(String username) {
        return username != null && username.matches("^[a-zA-Z0-9]{4,}$");
    }

    public static boolean validateEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean validatePhone(String phone) {
        return phone != null && phone.matches("^\\d{7,15}$");
    }

    public static boolean validatePasswordStrength(String password) {
        return password != null && Pattern.matches(PASSWORD_REGEX, password);
    }
}
