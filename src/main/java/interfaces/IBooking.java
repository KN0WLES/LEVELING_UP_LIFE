package interfaces;

import model.Booking;
import exceptions.BookingException;
import exceptions.RoomException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz para la gestión de reservas de habitaciones.
 */
public interface IBooking {
    
    /**
     * Crea una nueva reserva.
     * @param booking Reserva a crear.
     * @throws BookingException Si ocurre un error al crear la reserva.
     */
    void createBooking(Booking booking) throws BookingException, RoomException;

    /**
     * Obtiene una reserva por su ID.
     * @param id Identificador de la reserva.
     * @return La reserva correspondiente.
     * @throws BookingException Si la reserva no existe.
     */
    Booking getBookingById(String id) throws BookingException;

    /**
     * Cancela una reserva existente.
     * @param id Identificador de la reserva a cancelar.
     * @throws BookingException Si la reserva no existe o no se puede cancelar.
     */
    void cancelBooking(String id) throws BookingException, RoomException;

    /**
     * Actualiza una reserva existente.
     * @param booking Reserva con los datos actualizados.
     * @throws BookingException Si la reserva no existe o los datos son inválidos.
     */
    void updateBooking(Booking booking) throws BookingException;

    /**
     * Obtiene todas las reservas de un usuario.
     * @param accountId ID del usuario.
     * @return Lista de reservas del usuario.
     * @throws BookingException Si ocurre un error al obtener las reservas.
     */
    List<Booking> getBookingsByUser(String accountId) throws BookingException;

    /**
     * Obtiene todas las reservas de una habitación.
     * @param roomId ID de la habitación.
     * @return Lista de reservas de la habitación.
     * @throws BookingException Si ocurre un error al obtener las reservas.
     */
    List<Booking> getBookingsByRoom(String roomId) throws BookingException;

    /**
     * Verifica si una habitación está disponible en un rango de fechas.
     * @param roomId ID de la habitación.
     * @param startDate Fecha de inicio.
     * @param endDate Fecha de fin.
     * @return true si la habitación está disponible, false en caso contrario.
     * @throws BookingException Si ocurre un error al verificar la disponibilidad.
     */
    boolean isRoomAvailable(String roomId, LocalDateTime startDate, LocalDateTime endDate) throws BookingException;

    /**
     * Obtiene reservas en un rango de fechas.
     * @param startDate Fecha de inicio.
     * @param endDate Fecha de fin.
     * @return Lista de reservas en el rango especificado.
     * @throws BookingException Si ocurre un error al obtener las reservas.
     */
    List<Booking> getBookingsByDateRange(LocalDateTime startDate, LocalDateTime endDate) throws BookingException;

    /**
     * Obtiene las reservas actuales (en curso).
     * @return Lista de reservas en curso.
     * @throws BookingException Si ocurre un error al obtener las reservas.
     */
    List<Booking> getCurrentBookings() throws BookingException;

    /**
     * Obtiene las reservas futuras.
     * @return Lista de reservas futuras.
     * @throws BookingException Si ocurre un error al obtener las reservas.
     */
    List<Booking> getFutureBookings() throws BookingException;
}