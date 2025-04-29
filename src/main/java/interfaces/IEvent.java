package interfaces;

import model.Event;
import exceptions.EventException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz para la gestión de eventos.
 */
public interface IEvent {
    /**
     * Crea un nuevo evento.
     * @param evento Evento a crear.
     * @throws EventException Si ocurre un error al crear el evento.
     */
    void crearEvento(Event evento) throws EventException;

    /**
     * Cancela un evento existente.
     * @param eventId Identificador del evento a cancelar.
     * @throws EventException Si el evento no existe.
     */
    void cancelarEvento(String eventId) throws EventException;

    /**
     * Agrega un participante a un evento.
     * @param eventId Identificador del evento.
     * @param accountId Identificador del participante.
     * @return true si el participante fue agregado con éxito.
     * @throws EventException Si el evento no existe o el participante no puede ser agregado.
     */
    boolean agregarParticipante(String eventId, String accountId) throws EventException;

    /**
     * Remueve un participante de un evento.
     * @param eventId Identificador del evento.
     * @param accountId Identificador del participante.
     * @return true si el participante fue removido con éxito.
     * @throws EventException Si el evento no existe o el participante no puede ser removido.
     */
    boolean removerParticipante(String eventId, String accountId) throws EventException;

    /**
     * Busca un evento por su ID.
     * @param eventId Identificador del evento.
     * @return El evento correspondiente.
     * @throws EventException Si el evento no existe.
     */
    Event buscarPorId(String eventId) throws EventException;

    /**
     * Busca eventos por fecha.
     * @param fecha Fecha de los eventos a buscar.
     * @return Lista de eventos en la fecha especificada.
     * @throws EventException Si ocurre un error al obtener los eventos.
     */
    List<Event> buscarPorFecha(LocalDateTime fecha) throws EventException;

    /**
     * Lista los eventos activos.
     * @return Lista de eventos activos.
     * @throws EventException Si ocurre un error al obtener los eventos.
     */
    List<Event> listarEventosActivos() throws EventException;

    /**
     * Lista todos los eventos.
     * @return Lista de todos los eventos.
     * @throws EventException Si ocurre un error al obtener los eventos.
     */
    List<Event> listarTodos() throws EventException;
}
