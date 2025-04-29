package model;

import java.util.UUID;
/**
 * Clase que representa el modelo de una habitación en el sistema.
 * Gestiona información completa de habitaciones incluyendo nombre, tipo,
 * precio por hora y estado de disponibilidad.
 * 
 * @description Funcionalidades principales:
 *                   - Crear habitaciones con validaciones de tipo y precio.
 *                   - Gestionar el estado de disponibilidad para reservas.
 *                   - Serializar y deserializar habitaciones para almacenamiento.
 *                   - Mostrar información detallada de la habitación.
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 * @see Base
 */

public class Room extends Base<Room> {
    private String id;
    private String nombre;
    private char tipo; 
    private double precioPorHora;
    private boolean disponible;

    /**
     * Constructor vacío para serialización.
     */
    public Room(){}
    
    /**
     * Constructor principal para crear una habitación con todos sus atributos.
     * Realiza validaciones para asegurar que los datos sean correctos.
     *
     * @param nombre Nombre descriptivo de la habitación
     * @param tipo Tipo de habitación: 'I' (Individual), 'P' (Privado), 'D' (Dormitorio), 'M' (Mixto)
     * @param precioPorHora Precio por hora de uso de la habitación
     * @throws IllegalArgumentException Si los datos no cumplen las validaciones
     */
    public Room(String nombre, char tipo, double precioPorHora) {
        if (!"IPDM".contains(String.valueOf(tipo)))
            throw new IllegalArgumentException("Tipo inválido. Use I, P, D, o M");
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioPorHora = precioPorHora;
        this.disponible = true;
    }

    /**
     * Obtiene el identificador único de la habitación.
     * @return ID de la habitación
     */
    public String getId() { return id; }
    
    /**
     * Obtiene el nombre de la habitación.
     * @return Nombre de la habitación
     */
    public String getNombre() { return nombre; }
    
    /**
     * Obtiene el tipo de la habitación.
     * @return Tipo: 'I' (Individual), 'P' (Privado), 'D' (Dormitorio), 'M' (Mixto)
     */
    public char getTipo() { return tipo; }
    
    /**
     * Obtiene el precio por hora de uso.
     * @return Precio por hora
     */
    public double getPrecioPorHora() { return precioPorHora; }
    
    /**
     * Verifica si la habitación está disponible para reservar.
     * @return true si está disponible, false si está ocupada
     */
    public boolean isDisponible() { return disponible; }
    
    /**
     * Modifica el tipo de habitación.
     * 
     * @param tipo Nuevo tipo de habitación ('I', 'P', 'D' o 'M')
     * @throws IllegalArgumentException Si el tipo no es válido
     */
    public void setTipo(char tipo) {
        if (!"IPDM".contains(String.valueOf(tipo)))
            throw new IllegalArgumentException("Tipo inválido");
        this.tipo = tipo;
    }

    /**
     * Modifica el precio por hora de uso.
     * 
     * @param precioPorHora Nuevo precio por hora
     * @throws IllegalArgumentException Si el precio es negativo
     */
    public void setPrecioPorHora(double precioPorHora) {
        if (precioPorHora < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        this.precioPorHora = precioPorHora;
    }

    /**
     * Modifica el estado de disponibilidad de la habitación.
     * 
     * @param disponible Nuevo estado: true para disponible, false para ocupada
     */
    public void setDisponible(boolean disponible) { 
        this.disponible = disponible; 
    }

    @Override
    public String toFile() {
        return String.join("|", 
            id, nombre, String.valueOf(tipo), 
            String.valueOf(precioPorHora), String.valueOf(disponible)
        );
    }

    @Override
    public Room fromFile(String line) {
        String[] parts = line.split("\\|");
        Room room = new Room(
            parts[1], parts[2].charAt(0), Double.parseDouble(parts[3])
        );
        room.id = parts[0];
        room.disponible = Boolean.parseBoolean(parts[4]);
        return room;
    }

    @Override
    public String getInfo() {
        return String.format(
            "Habitación: %s\nTipo: %s\nPrecio/h: $%.2f\nEstado: %s",
            nombre, tipo, precioPorHora, disponible ? "✅ Disponible" : "❌ Ocupado"
        );
    }
}