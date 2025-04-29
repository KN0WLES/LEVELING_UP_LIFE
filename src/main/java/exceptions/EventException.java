package exceptions;

/**
 * Clase que representa excepciones específicas relacionadas con la gestión de eventos.
 * Proporciona mensajes de error predefinidos para situaciones comunes, como eventos no encontrados,
 * eventos cancelados, eventos llenos y usuarios ya registrados en un evento.
 * 
 * @description Funcionalidades principales:
 *                   - Lanzar una excepción cuando un evento no es encontrado.
 *                   - Lanzar una excepción cuando un evento está cancelado.
 *                   - Lanzar una excepción cuando un evento ha alcanzado su capacidad máxima.
 *                   - Lanzar una excepción cuando un usuario ya está registrado en un evento.
 *                   - Lanzar una excepción cuando la fecha de inicio es posterior a la fecha fin.
 *  
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 */
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
