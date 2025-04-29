package exceptions;

/**
 * Clase que representa excepciones específicas relacionadas con la gestión de reservas.
 * Proporciona mensajes de error predefinidos para situaciones comunes, como reservas no encontradas,
 * fechas inválidas o conflictos de disponibilidad.
 * 
 * @desciption Funcionalidades principales:
 *                   - Lanzar una excepción cuando una reserva no es encontrada.
 *                   - Lanzar una excepción cuando el rango de fechas es inválido.
 *                   - Lanzar una excepción cuando la habitación no está disponible.
 *                   - Lanzar una excepción cuando ya existe una reserva para la misma habitación en el mismo horario.
 *                   - Lanzar una excepción cuando se intenta reservar en una fecha pasada.
 *                   - Lanzar una excepción cuando un usuario no tiene permisos para modificar una reserva.
 * 
 * @autor KNOWLES
 * @version 1.0
 * @since 2025-04-29
 */
public class BookingException extends Exception {
    
    public BookingException(String message) {
        super(message);
    }
    
    public static BookingException notFound() {
        return new BookingException("Reserva no encontrada");
    }
    
    public static BookingException invalidDateRange() {
        return new BookingException("Rango de fechas inválido");
    }
    
    public static BookingException roomNotAvailable() {
        return new BookingException("La habitación no está disponible para las fechas seleccionadas");
    }
    
    public static BookingException duplicateBooking() {
        return new BookingException("Ya existe una reserva para esta habitación en ese horario");
    }
    
    public static BookingException pastDate() {
        return new BookingException("No se pueden realizar reservas en fechas pasadas");
    }
    
    public static BookingException unauthorizedUser() {
        return new BookingException("El usuario no tiene permisos para modificar esta reserva");
    }
}