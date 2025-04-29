package exceptions;

public class FaQException extends Exception {
    public FaQException(String message) {
        super(message);
    }
    
    public static FaQException notFound() {
        return new FaQException("Pregunta no encontrada");
    }
}