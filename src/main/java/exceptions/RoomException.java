package exceptions;

public class RoomException extends Exception {
    public RoomException(String message) {
        super(message);
    }
    
    public static RoomException notFound() {
        return new RoomException("Habitación no encontrada");
    }
    public static RoomException invalidType() {
        return new RoomException("Tipo de habitación inválido (use I, P, D o M)");
    }
    public static RoomException isOccupied() {
        return new RoomException("La habitación está ocupada");
    }
}