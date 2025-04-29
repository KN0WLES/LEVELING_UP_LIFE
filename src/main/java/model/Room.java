package model;

import java.util.UUID;

public class Room extends Base<Room> {
    private String id;
    private String nombre;
    private char tipo; // 'I': Individual, 'P': Privado, 'D': Dormitorio, 'M': Mixto
    private double precioPorHora;
    private boolean disponible;

    public Room(){}
    
    public Room(String nombre, char tipo, double precioPorHora) {
        if (!"IPDM".contains(String.valueOf(tipo)))
            throw new IllegalArgumentException("Tipo inválido. Use I, P, D, o M");
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioPorHora = precioPorHora;
        this.disponible = true;
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public char getTipo() { return tipo; }
    public double getPrecioPorHora() { return precioPorHora; }
    public boolean isDisponible() { return disponible; }
    

    // --- Setters ---
    public void setTipo(char tipo) {
        if (!"IPDM".contains(String.valueOf(tipo)))
            throw new IllegalArgumentException("Tipo inválido");
        this.tipo = tipo;
    }

    public void setPrecioPorHora(double precioPorHora) {
        if (precioPorHora < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        this.precioPorHora = precioPorHora;
    }

    public void setDisponible(boolean disponible) { 
        this.disponible = disponible; 
    }

    // --- Serialización ---
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