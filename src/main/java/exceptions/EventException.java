package exceptions;

public class EventException extends Exception {
    public EventException(String message) {
        super(message);
    }
    
    public static EventException eventoNoEncontrado() {
        return new EventException("Evento no encontrado");
    }
    public static EventException eventoCancelado() {
        return new EventException("El evento está cancelado");
    }
    public static EventException eventoLleno() {
        return new EventException("El evento ha alcanzado su capacidad máxima");
    }
    public static EventException participanteYaRegistrado() {
        return new EventException("El usuario ya está registrado en este evento");
    }
    public static EventException fechaInvalida() {
        return new EventException("La fecha de inicio debe ser anterior a la fecha fin");
    }
}
