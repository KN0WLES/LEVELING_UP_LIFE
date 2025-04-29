package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Clase que representa el modelo de un evento en el sistema.
 * Gestiona información completa de eventos incluyendo datos como nombre, fechas,
 * tipo de evento, capacidad, precio y control de participantes.
 * 
 * @description Funcionalidades principales:
 *                   - Crear eventos con validaciones de fechas, tipo y capacidad.
 *                   - Gestionar la inscripción y eliminación de participantes.
 *                   - Serializar y deserializar eventos para almacenamiento.
 *                   - Mostrar información detallada del evento.
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 * @see Base
 */
public class Event extends Base<Event> {
    private String id;
    private String nombre;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private char tipo; // 'C': Conferencia, 'T': Taller, 'F': Fiesta
    private int capacidad;
    private double precio;
    private Set<String> participantes; // IDs de Account

    /**
     * Constructor vacío para serialización.
     */
    public Event(){}

    /**
     * Constructor principal para crear un evento con todos sus atributos.
     * Realiza validaciones para asegurar que los datos sean correctos.
     *
     * @param nombre Nombre descriptivo del evento
     * @param fechaInicio Fecha y hora de inicio
     * @param fechaFin Fecha y hora de finalización
     * @param tipo Tipo de evento: 'C' (Conferencia), 'T' (Taller), 'F' (Fiesta)
     * @param capacidad Número máximo de participantes
     * @param precio Costo para participar en el evento
     * @throws IllegalArgumentException Si los datos no cumplen las validaciones
     */
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

    /**
     * Obtiene el identificador único del evento.
     * @return ID del evento
     */
    public String getId() { return id; }
    
    /**
     * Obtiene el nombre del evento.
     * @return Nombre del evento
     */
    public String getNombre() { return nombre; }
    
    /**
     * Obtiene la fecha y hora de inicio.
     * @return Fecha de inicio
     */
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    
    /**
     * Obtiene la fecha y hora de finalización.
     * @return Fecha de fin
     */
    public LocalDateTime getFechaFin() { return fechaFin; }
    
    /**
     * Obtiene el tipo de evento.
     * @return Tipo de evento: 'C' (Conferencia), 'T' (Taller), 'F' (Fiesta)
     */
    public char getTipo() { return tipo; }
    
    /**
     * Obtiene la capacidad máxima del evento.
     * @return Capacidad del evento
     */
    public int getCapacidad() { return capacidad; }
    
    /**
     * Obtiene el precio de participación.
     * @return Precio del evento
     */
    public double getPrecio() { return precio; }
    
    /**
     * Obtiene el conjunto de IDs de los participantes.
     * @return Conjunto de IDs de participantes
     */
    public Set<String> getParticipantes() { return participantes; }

    /**
     * Modifica el tipo de evento.
     * 
     * @param tipo Nuevo tipo de evento ('C', 'T' o 'F')
     * @throws IllegalArgumentException Si el tipo no es válido
     */
    public void setTipo(char tipo) {
        if (!"CTF".contains(String.valueOf(tipo)))
            throw new IllegalArgumentException("Tipo inválido");
        this.tipo = tipo;
    }

    /**
     * Modifica la capacidad del evento.
     * 
     * @param capacidad Nueva capacidad del evento
     * @throws IllegalArgumentException Si la capacidad no es positiva
     */
    public void setCapacidad(int capacidad) {
        if (capacidad <= 0)
            throw new IllegalArgumentException("La capacidad debe ser positiva");
        this.capacidad = capacidad;
    }

    /**
     * Añade un participante al evento.
     * 
     * @param accountId ID de la cuenta a añadir
     * @return true si se añadió correctamente, false si ya existía
     * @throws IllegalStateException Si el evento ya está lleno
     */
    public boolean addParticipante(String accountId) {
        if (participantes.size() >= capacidad)
            throw new IllegalStateException("Evento lleno");
        return participantes.add(accountId);
    }

    /**
     * Elimina un participante del evento.
     * 
     * @param accountId ID de la cuenta a eliminar
     * @return true si se eliminó correctamente, false si no existía
     */
    public boolean removeParticipante(String accountId) {
        return participantes.remove(accountId);
    }

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