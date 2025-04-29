package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Clase que representa el modelo de una reserva.
 * Contiene todos los campos necesarios para gestionar información de reservas, como el usuario, la habitación,
 * las fechas de inicio y fin, y el costo total.
 * 
 * @description Funcionalidades principales:
 *                   - Crear una nueva reserva con validaciones de horas y precio.
 *                   - Calcular automáticamente la fecha de fin y el costo total de la reserva.
 *                   - Serializar y deserializar reservas para almacenamiento en archivos.
 *                   - Obtener información detallada de la reserva.
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 * @see Base
 */
public class Booking extends Base<Booking> {
    private String id;
    private String accountId;
    private String roomId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private double total;

    /**
     * Constructor vacío necesario para la deserialización.
     */
    public Booking(){}

    /**
     * Constructor que crea una nueva reserva con validación de datos.
     * Calcula automáticamente la fecha de fin basada en la duración y el costo total.
     * 
     * @param accountId Identificador único del usuario que realiza la reserva
     * @param roomId Identificador único de la habitación reservada
     * @param fechaInicio Fecha y hora de inicio de la reserva
     * @param horas Duración de la reserva en horas (debe ser positivo)
     * @param precioPorHora Precio por hora de la habitación (debe ser positivo)
     * @throws IllegalArgumentException Si las horas o el precio por hora son valores no positivos
     */
    public Booking(String accountId, String roomId, LocalDateTime fechaInicio, int horas, double precioPorHora) {
        if (horas <= 0)
            throw new IllegalArgumentException("Las horas deben ser positivas");
        if (precioPorHora <= 0)
            throw new IllegalArgumentException("El precio debe ser positivo");

        this.id = UUID.randomUUID().toString();
        this.accountId = accountId;
        this.roomId = roomId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaInicio.plusHours(horas);
        this.total = precioPorHora * horas;
    }

    /**
     * Obtiene el identificador único de la reserva.
     * 
     * @return El UUID de la reserva como String
     */
    public String getId() { return id; }
    
    /**
     * Obtiene el identificador del usuario que realizó la reserva.
     * 
     * @return El UUID del usuario como String
     */
    public String getAccountId() { return accountId; }
    
    /**
     * Obtiene el identificador de la habitación reservada.
     * 
     * @return El UUID de la habitación como String
     */
    public String getRoomId() { return roomId; }
    
    /**
     * Obtiene la fecha y hora de inicio de la reserva.
     * 
     * @return Objeto LocalDateTime con la fecha y hora de inicio
     */
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    
    /**
     * Obtiene la fecha y hora de fin de la reserva.
     * 
     * @return Objeto LocalDateTime con la fecha y hora de fin
     */
    public LocalDateTime getFechaFin() { return fechaFin; }
    
    /**
     * Obtiene el costo total de la reserva.
     * 
     * @return Valor decimal con el costo total
     */
    public double getTotal() { return total; }
    
    /**
     * Calcula la duración de la reserva en horas.
     * 
     * @return Número entero de horas entre la fecha de inicio y fin
     */
    public int getHoras() {
        return (int) java.time.Duration.between(fechaInicio, fechaFin).toHours();
    }
    
    /**
     * Establece una nueva fecha y hora de inicio para la reserva.
     * Recalcula automáticamente la fecha de fin manteniendo la misma duración.
     * 
     * @param fechaInicio Nueva fecha y hora de inicio
     * @throws IllegalArgumentException Si la fecha de inicio es null
     */    
    public void setFechaInicio(LocalDateTime fechaInicio) {
        if (fechaInicio == null)
            throw new IllegalArgumentException("La fecha de inicio no puede estar vacía");
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaInicio.plusHours(getHoras());
    }

    /**
     * Establece un nuevo costo total para la reserva.
     * 
     * @param total Nuevo costo total (debe ser no negativo)
     * @throws IllegalArgumentException Si el total es negativo
     */
    public void setTotal(double total) {
        if (total < 0)
            throw new IllegalArgumentException("El total no puede ser negativo");
        this.total = total;
    }

    /**
     * Establece una nueva duración para la reserva en horas.
     * Recalcula automáticamente la fecha de fin y el costo total manteniendo el mismo precio por hora.
     * 
     * @param horas Nueva duración en horas (debe ser positiva)
     * @throws IllegalArgumentException Si las horas son cero o negativas
     */
    public void setHoras(int horas) {
        if (horas <= 0)
            throw new IllegalArgumentException("La cantidad de horas debe ser mayor a cero");
        this.fechaFin = this.fechaInicio.plusHours(horas);
        this.total = horas * (this.total / getHoras());
    }

    @Override
    public String toFile() {
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return String.join("|",
            id, accountId, roomId,
            fechaInicio.format(fmt), fechaFin.format(fmt),
            String.valueOf(total)
        );
    }

    @Override
    public Booking fromFile(String line) {
        String[] parts = line.split("\\|");
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        
        LocalDateTime inicio = LocalDateTime.parse(parts[3], fmt);
        LocalDateTime fin = LocalDateTime.parse(parts[4], fmt);
        
        int horas = (int) java.time.Duration.between(inicio, fin).toHours();
        double total = Double.parseDouble(parts[5]);
        double precioPorHora = total / horas;

        Booking booking = new Booking(
            parts[1], parts[2],
            inicio,
            horas, precioPorHora
        );
        booking.id = parts[0];
        return booking;
    }

    @Override
    public String getInfo() {
        return String.format(
            "Reserva #%s\nUsuario: %s\nHabitación: %s\nTotal: $%.2f",
            id, accountId, roomId, total
        );
    }
}