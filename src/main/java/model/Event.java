package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Event extends Base<Event> {
    private String id;
    private String nombre;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private char tipo; // 'C': Conferencia, 'T': Taller, 'F': Fiesta
    private int capacidad;
    private double precio;
    private Set<String> participantes; // IDs de Account

    public Event(){}

    public Event(String nombre, LocalDateTime fechaInicio, LocalDateTime fechaFin, char tipo, int capacidad, double precio) {
        if (!"CTF".contains(String.valueOf(tipo)))
            throw new IllegalArgumentException("Tipo inválido. Use C, T, o F");
        if (fechaInicio.isAfter(fechaFin))
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha fin");
        if (capacidad <= 0)
            throw new IllegalArgumentException("La capacidad debe ser positiva");

        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precio = precio;
        this.participantes = new HashSet<>();
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public LocalDateTime getFechaFin() { return fechaFin; }
    public char getTipo() { return tipo; }
    public int getCapacidad() { return capacidad; }
    public double getPrecio() { return precio; }
    public Set<String> getParticipantes() { return participantes; }

    // --- Setters ---
    public void setTipo(char tipo) {
        if (!"CTF".contains(String.valueOf(tipo)))
            throw new IllegalArgumentException("Tipo inválido");
        this.tipo = tipo;
    }

    public void setCapacidad(int capacidad) {
        if (capacidad <= 0)
            throw new IllegalArgumentException("La capacidad debe ser positiva");
        this.capacidad = capacidad;
    }

    // --- Métodos de Participantes ---
    public boolean addParticipante(String accountId) {
        if (participantes.size() >= capacidad)
            throw new IllegalStateException("Evento lleno");
        return participantes.add(accountId);
    }

    public boolean removeParticipante(String accountId) {
        return participantes.remove(accountId);
    }

    // --- Serialización ---
    @Override
    public String toFile() {
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return String.join("|",
            id, nombre,
            fechaInicio.format(fmt), fechaFin.format(fmt),
            String.valueOf(tipo), String.valueOf(capacidad),
            String.valueOf(precio), String.join(";", participantes)
        );
    }

    @Override
    public Event fromFile(String line) {
        String[] parts = line.split("\\|");
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        Event event = new Event(
            parts[1],
            LocalDateTime.parse(parts[2], fmt),
            LocalDateTime.parse(parts[3], fmt),
            parts[4].charAt(0),
            Integer.parseInt(parts[5]),
            Double.parseDouble(parts[6])
        );
        event.id = parts[0];
        if (parts.length > 7 && !parts[7].isEmpty()) {
            for (String id : parts[7].split(";")) {
                event.participantes.add(id);
            }
        }
        return event;
    }

    @Override
    public String getInfo() {
        return String.format(
            "Evento: %s\nTipo: %s\nFecha: %s a %s\nCapacidad: %d/%d\nPrecio: $%.2f",
            nombre, tipo, fechaInicio, fechaFin, participantes.size(), capacidad, precio
        );
    }
}