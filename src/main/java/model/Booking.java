package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Booking extends Base<Booking> {
    private String id;
    private String accountId;
    private String roomId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private double total;

    public Booking(){}

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

    // --- Getters ---
    public String getId() { return id; }
    public String getAccountId() { return accountId; }
    public String getRoomId() { return roomId; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public LocalDateTime getFechaFin() { return fechaFin; }
    public double getTotal() { return total; }
    public int getHoras() {
        return (int) java.time.Duration.between(fechaInicio, fechaFin).toHours();
    }
        
    public void setFechaInicio(LocalDateTime fechaInicio) {
        if (fechaInicio == null)
            throw new IllegalArgumentException("La fecha de inicio no puede estar vacía");
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaInicio.plusHours(getHoras());
    }

    public void setTotal(double total) {
        if (total < 0)
            throw new IllegalArgumentException("El total no puede ser negativo");
        this.total = total;
    }

    public void setHoras(int horas) {
        if (horas <= 0)
            throw new IllegalArgumentException("La cantidad de horas debe ser mayor a cero");
        this.fechaFin = this.fechaInicio.plusHours(horas);
        this.total = horas * (this.total / getHoras());
    }
    


    // --- Serialización ---
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
        
        // Parse the dates first
        LocalDateTime inicio = LocalDateTime.parse(parts[3], fmt);
        LocalDateTime fin = LocalDateTime.parse(parts[4], fmt);
        
        // Calculate hours between dates
        int horas = (int) java.time.Duration.between(inicio, fin).toHours();
        double total = Double.parseDouble(parts[5]);
        double precioPorHora = total / horas;

        // Create booking with calculated hours and price
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