package exceptions;

public class BookingException extends Exception {
    // Constructor básico
    public BookingException(String message) {
        super(message);
    }
    
    // Factory methods para errores comunes
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