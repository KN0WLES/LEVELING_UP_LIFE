package interfaces;

import model.Event;
import exceptions.EventException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz para la gestión de eventos.
 * Define los métodos necesarios para crear, obtener, actualizar y eliminar preguntas frecuentes,
 * así como para gestionar el estado de las preguntas pendientes de revisión.
 * 
 * @description Funcionalidades principales:
 *                          - Agregar nuevas preguntas frecuentes.
 *                          - Obtener preguntas frecuentes por ID.
 *                          - Listar preguntas pendientes de revisión.
 *                          - Verificar existencia de preguntas pendientes.
 *                          - Actualizar preguntas existentes.
 *                          - Eliminar preguntas.
 *                          - Listar todas las preguntas registradas.
 * Ejemplo de uso:
 * <pre>
 *     IEvent eventManager = new EventController(fileHandler);
 *     Event nuevoEvento = new Event("E001", "Workshop Java", LocalDateTime.now());
 *     eventManager.crearEvento(nuevoEvento);
 * </pre>
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 */
public interface IEvent {
    /**
     * Crea un nuevo evento.
     * El evento debe contener toda la información requerida como:
     *                        -Título
     *                        -Fecha
     *                        -Hora
     *                        -Descripción
     *                        -Capacidad máxima.
     * El sistema validará que no exista otro evento
     * con el mismo ID y que los datos cumplan con las validaciones establecidas.
     *
     * @param evento Evento a crear.
     * @throws EventException Si ocurre un error al crear el evento.
     */
    void crearEvento(Event evento) throws EventException;

    /**
     * Cancela un evento existente.
     * La cancelación implica cambiar el estado del evento a "cancelado" y notificar
     * a todos los participantes registrados. No se elimina el evento del sistema.
     *
     * @param eventId Identificador del evento a cancelar.
     * @throws EventException Si el evento no existe.
     */
    void cancelarEvento(String eventId) throws EventException;

    /**
     * Agrega un participante a un evento.
     * Se verifica que el evento tenga capacidad disponible y que el participante
     * no esté ya registrado en el mismo evento.
     *
     * @param eventId Identificador del evento.
     * @param accountId Identificador del participante.
     * @return true si el participante fue agregado con éxito.
     * @throws EventException Si el evento no existe o el participante no puede ser agregado.
     */
    boolean agregarParticipante(String eventId, String accountId) throws EventException;

    /**
     * Remueve un participante de un evento.
     * Un participante puede ser removido antes de que el evento comience.
     *
     * @param eventId Identificador del evento.
     * @param accountId Identificador del participante.
     * @return true si el participante fue removido con éxito.
     * @throws EventException Si el evento no existe o el participante no puede ser removido.
     */
    boolean removerParticipante(String eventId, String accountId) throws EventException;

    /**
     * Busca un evento por su ID.
     *
     * @param eventId Identificador del evento.
     * @return El evento correspondiente.
     * @throws EventException Si el evento no existe.
     */
    Event buscarPorId(String eventId) throws EventException;

    /**
     * Busca eventos por fecha.
     * Retorna todos los eventos programados para la fecha especificada,
     * independientemente de su estado (activo, cancelado).
     *
     * @param fecha Fecha de los eventos a buscar.
     * @return Lista de eventos en la fecha especificada.
     * @throws EventException Si ocurre un error al obtener los eventos.
     */
    List<Event> buscarPorFecha(LocalDateTime fecha) throws EventException;

    /**
     * Lista los eventos activos.
     * Un evento activo es aquel que no ha sido cancelado y cuya fecha
     * es igual o posterior a la fecha actual.
     *
     * @return Lista de eventos activos.
     * @throws EventException Si ocurre un error al obtener los eventos.
     */
    List<Event> listarEventosActivos() throws EventException;

    /**
     * Lista todos los eventos.
     * Incluye eventos:
     *               -Activos
     *               -Pasados
     *               -Cancelados
     *
     * @return Lista de todos los eventos.
     * @throws EventException Si ocurre un error al obtener los eventos.
     */
    List<Event> listarTodos() throws EventException;
}
