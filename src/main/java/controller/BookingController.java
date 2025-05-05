package controller;

import interfaces.*;
import model.*;
import exceptions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que actúa como controlador para la gestión de reservas.
 * Proporciona la lógica de negocio necesaria para crear, actualizar, cancelar y consultar reservas.
 * También gestiona la disponibilidad de habitaciones y verifica conflictos entre reservas.
 * Los datos de las reservas se almacenan y recuperan desde un archivo utilizando un manejador de archivos genérico.
 * 
 * @description Funcionalidades principales:
 *                   - Crear una nueva reserva.
 *                   - Actualizar una reserva existente.
 *                   - Cancelar una reserva.
 *                   - Consultar reservas por usuario, habitación o rango de fechas.
 *                   - Verificar la disponibilidad de habitaciones.
 *                   - Listar reservas actuales, futuras o todas las reservas.
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 * @see IBooking
 * @see Booking
 * @see IFile
 * @see FileException
 * @see BookingException
 * @see RoomController
 */
public class BookingController implements IBooking {
    
    private final IFile<Booking> fileHandler;
    private final RoomController roomController;
    private final String filePath = "src/main/java/data/bookings.txt";
    private List<Booking> bookings;

    public BookingController(IFile<Booking> fileHandler, RoomController roomController) {
        this.fileHandler = fileHandler;
        this.roomController = roomController;
        try {
            this.fileHandler.createFileIfNotExists(filePath);
            try {
                this.bookings = this.fileHandler.loadData(filePath);
            } catch (IllegalArgumentException e) {
                System.err.println("Error en el formato de los datos: " + e.getMessage());
                this.bookings = new ArrayList<>();
            }
        } catch (FileException e) {
            this.bookings = new ArrayList<>();
            System.err.println("Error al cargar reservas: " + e.getMessage());
        }
    }

    private void saveChanges() throws BookingException {
        try {
            fileHandler.saveData(bookings, filePath);
        } catch (FileException e) {
            throw new BookingException("Error al guardar los cambios: " + e.getMessage());
        }
    }

    @Override
    public void createBooking(Booking booking) throws BookingException, 
    RoomException {
        createBooking(booking.getAccountId(), booking.getRoomId(), booking.getFechaInicio(), booking.getHoras());
    }

    public Booking createBooking(String accountId, String roomId, LocalDateTime fechaInicio, int horas) 
            throws BookingException, RoomException {
        Room room = roomController.getRoomById(roomId);
        if (!room.isDisponible()) throw BookingException.roomNotAvailable();

        LocalDateTime fechaFin = fechaInicio.plusHours(horas);
        boolean overlap = bookings.stream()
                .filter(b -> b.getRoomId().equals(roomId))
                .anyMatch(b ->
                    (fechaInicio.isBefore(b.getFechaFin()) && fechaFin.isAfter(b.getFechaInicio()))
                );
        if (overlap) throw BookingException.duplicateBooking();

        Booking booking = new Booking(accountId, roomId, fechaInicio, horas, room.getPrecioPorHora());
        bookings.add(booking);
        if (fechaInicio.isBefore(LocalDateTime.now()) && fechaFin.isAfter(LocalDateTime.now())) {
            roomController.setRoomOccupied(roomId, true);
        }
        saveChanges();
        return booking;
    }

    @Override
    public void updateBooking(Booking booking) throws BookingException {
        Booking existingBooking = getBookingById(booking.getId());
        if (existingBooking == null) throw BookingException.notFound();
        existingBooking.setFechaInicio(booking.getFechaInicio());
        existingBooking.setHoras(booking.getHoras());
        existingBooking.setTotal(booking.getTotal());
        saveChanges();
    }

    @Override
public void cancelBooking(String bookingId) throws BookingException, RoomException {
        Booking booking = getBookingById(bookingId);
        if (booking == null) throw BookingException.notFound();
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(booking.getFechaInicio()) && now.isBefore(booking.getFechaFin())) {
            roomController.setRoomOccupied(booking.getRoomId(), false);
        }
        bookings.removeIf(b -> b.getId().equals(bookingId));
        saveChanges();
    }

    @Override
    public Booking getBookingById(String id) throws BookingException {
        return bookings.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(BookingException::notFound);
    }

    @Override
    public List<Booking> getBookingsByUser(String accountId) throws BookingException {
        return bookings.stream()
                .filter(b -> b.getAccountId().equals(accountId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> getBookingsByRoom(String roomId) throws BookingException {
        return bookings.stream()
                .filter(b -> b.getRoomId().equals(roomId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isRoomAvailable(String roomId, LocalDateTime startDate, LocalDateTime endDate) throws BookingException {
        return bookings.stream()
                .filter(b -> b.getRoomId().equals(roomId))
                .noneMatch(b -> 
                    (startDate.isBefore(b.getFechaFin()) && endDate.isAfter(b.getFechaInicio()))
                );
    }

    @Override
    public List<Booking> getBookingsByDateRange(LocalDateTime startDate, LocalDateTime endDate) throws BookingException {
        return bookings.stream()
                .filter(b ->
                    (b.getFechaInicio().isAfter(startDate) || b.getFechaInicio().isEqual(startDate)) &&
                    (b.getFechaFin().isBefore(endDate) || b.getFechaFin().isEqual(endDate))
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> getCurrentBookings() throws BookingException {
        LocalDateTime now = LocalDateTime.now();
        return bookings.stream()
                .filter(b -> b.getFechaInicio().isBefore(now) && b.getFechaFin().isAfter(now))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> getFutureBookings() {
        LocalDateTime now = LocalDateTime.now();
        return bookings.stream()
                .filter(b -> b.getFechaInicio().isAfter(now))
                .collect(Collectors.toList());
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }
}
