package interfaces;

import model.Booking;
import exceptions.BookingException;
import exceptions.RoomException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz para la gestión de reservas de habitaciones.
 * Define los metodos necesarios para crear, obtener, cancelar y actualizar rerservas
 * así como para verificar la disponibilidad de habitaciones en diferentes fechas.
 * 
 * @description Funcionalidades principales:
 *                   - Creación de nuevas reservas.
 *                   - Cancelación de reservas existentes.
 *                   - Actualización de información de reservas.
 *                   - Consulta de reservas por ID, usuario, habitación o rango de fechas.
 *                   - Verificación de disponibilidad de habitaciones.
 *                   - Obtención de reservas actuales y futuras.
 * 
 * Ejemplo de uso:
 * <pre>
 *     IBooking bookingManager = new BookingController(roomManager, fileHandler);
 *     bookingManager.createBooking(new Booking("user1", "room101", startDate, endDate));
 * </pre>
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 */
public interface IBooking {
    
    /**
     * Crea una nueva reserva.
     *                       Verifica la disponibilidad de la habitación en las fechas solicitadas
     *                       y actualiza el estado de ocupación correspondiente.
     * 
     * @param booking Reserva a crear.
     * @throws BookingException Si la reserva ya existe o los datos son inválidos.
     */
    void createBooking(Booking booking) throws BookingException, RoomException;

    /**
     * Obtiene una reserva por su ID.
     * 
     * @param id Identificador de la reserva.
     * @return La reserva correspondiente.
     * @throws BookingException Si la reserva no existe.
     */
    Booking getBookingById(String id) throws BookingException;

    /**
     * Cancela una reserva existente.
     *                      Actualiza el estado de la habitación si corresponde.
     * 
     * @param id Identificador de la reserva a cancelar.
     * @throws BookingException Si la reserva no existe o no se puede cancelar.
     */
    void cancelBooking(String id) throws BookingException, RoomException;

    /**
     * Actualiza una reserva existente.
     *                       Modifica los datos de la reserva manteniendo el mismo ID.
     * 
     * @param booking Reserva con los datos actualizados.
     * @throws BookingException Si la reserva no existe o los datos son inválidos.
     */
    void updateBooking(Booking booking) throws BookingException;

    /**
     * Obtiene todas las reservas de un usuario.
     * 
     * @param accountId ID del usuario.
     * @return Lista de reservas del usuario.
     * @throws BookingException Si ocurre un error al obtener las reservas.
     */
    List<Booking> getBookingsByUser(String accountId) throws BookingException;

    /**
     * Obtiene todas las reservas de una habitación.
     *                      Incluye reservas:
     *                          -Pasadas
     *                          -Actuales
     *                          -Futuras
     * 
     * @param roomId ID de la habitación.
     * @return Lista de reservas de la habitación.
     * @throws BookingException Si ocurre un error al obtener las reservas.
     */
    List<Booking> getBookingsByRoom(String roomId) throws BookingException;

    /**
     * Verifica si una habitación está disponible en un rango de fechas.
     *                      Comprueba que no existan reservas que se solapen con el período especificado
     *                      y que la habitación no esté en mantenimiento.
     * 
     * @param roomId ID de la habitación.
     * @param startDate Fecha de inicio.
     * @param endDate Fecha de fin.
     * @return true si la habitación está disponible, false en caso contrario.
     * @throws BookingException Si ocurre un error al verificar la disponibilidad.
     */
    boolean isRoomAvailable(String roomId, LocalDateTime startDate, LocalDateTime endDate) throws BookingException;

    /**
     * Obtiene reservas en un rango de fechas.
     *                      Devuelve todas las reservas que tengan alguna superposición con el período especificado.
     * 
     * @param startDate Fecha de inicio.
     * @param endDate Fecha de fin.
     * @return Lista de reservas en el rango especificado.
     * @throws BookingException Si ocurre un error al obtener las reservas.
     */
    List<Booking> getBookingsByDateRange(LocalDateTime startDate, LocalDateTime endDate) throws BookingException;

    /**
     * Obtiene las reservas actuales (en curso).
     *                      Devuelve las reservas cuya fecha de inicio es anterior al momento actual
     *                      y cuya fecha de finalización es posterior al momento actual.
     * 
     * @return Lista de reservas en curso.
     * @throws BookingException Si ocurre un error al obtener las reservas.
     */
    List<Booking> getCurrentBookings() throws BookingException;

    /**
     * Obtiene las reservas futuras.
     *                      Devuelve las reservas cuya fecha de inicio es posterior al momento actual.
     * 
     * @return Lista de reservas futuras.
     * @throws BookingException Si ocurre un error al obtener las reservas.
     */
    List<Booking> getFutureBookings() throws BookingException;
}