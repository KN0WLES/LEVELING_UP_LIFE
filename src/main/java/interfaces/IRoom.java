package interfaces;

import model.Room;
import exceptions.RoomException;
import java.util.List;

/**
 * Interfaz para la gestión de habitaciones.
 */
public interface IRoom {
    /**
     * Agrega una nueva habitación.
     * @param room Habitación a agregar.
     * @throws RoomException Si ocurre un error al agregar la habitación.
     */
    void addRoom(Room room) throws RoomException;

    /**
     * Obtiene una habitación por su ID.
     * @param id Identificador de la habitación.
     * @return La habitación correspondiente.
     * @throws RoomException Si la habitación no existe.
     */
    Room getRoomById(String id) throws RoomException;

    /**
     * Actualiza una habitación existente.
     * @param room Habitación con los datos actualizados.
     * @throws RoomException Si la habitación no existe o los datos son inválidos.
     */
    void updateRoom(Room room) throws RoomException;

    /**
     * Elimina una habitación.
     * @param id Identificador de la habitación a eliminar.
     * @throws RoomException Si la habitación no existe.
     */
    void deleteRoom(String id) throws RoomException;

    /**
     * Obtiene habitaciones por tipo.
     * @param type Tipo de habitación.
     * @return Lista de habitaciones del tipo especificado.
     * @throws RoomException Si ocurre un error al obtener las habitaciones.
     */
    List<Room> getRoomsByType(char type) throws RoomException;

    /**
     * Obtiene habitaciones disponibles.
     * @return Lista de habitaciones disponibles.
     * @throws RoomException Si ocurre un error al obtener las habitaciones.
     */
    List<Room> getAvailableRooms() throws RoomException;

    /**
     * Establece el estado de mantenimiento de una habitación.
     * @param roomId Identificador de la habitación.
     * @param inMaintenance true si la habitación está en mantenimiento, false en caso contrario.
     * @throws RoomException Si la habitación no existe.
     */
    void setRoomMaintenance(String roomId, boolean inMaintenance) throws RoomException;

    /**
     * Establece el estado de ocupación de una habitación.
     * @param roomId Identificador de la habitación.
     * @param occupied true si la habitación está ocupada, false en caso contrario.
     * @throws RoomException Si la habitación no existe.
     */
    void setRoomOccupied(String roomId, boolean occupied) throws RoomException;
}