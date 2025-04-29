package controller;

import interfaces.*;
import model.Room;
import exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomController implements IRoom {
    
    private final IFile<Room> fileHandler;
    private final String filePath = "C:/Users/HP/Downloads/VTerminado-4/V1-4/src/main/java/data/rooms.txt";
    private List<Room> rooms;

    public RoomController(IFile<Room> fileHandler)throws RoomException {
        this.fileHandler = fileHandler;
        try {
            this.fileHandler.createFileIfNotExists(filePath);
            this.rooms = this.fileHandler.loadData(filePath);

            // Verificar si hay salas cargadas
            if (this.rooms == null || this.rooms.isEmpty()) {
                this.rooms = new ArrayList<>(); // Inicializar la lista si es nula
                createDefaultRooms();
                saveChanges();
            }
        } catch (FileException e) {
            this.rooms = new ArrayList<>();
            System.err.println("Error al cargar habitaciones: " + e.getMessage());
        }
    }

    private void saveChanges() throws RoomException {
        try {
            fileHandler.saveData(rooms, filePath);
        } catch (FileException e) {
            throw new RoomException("Error al guardar los cambios: " + e.getMessage());
        }
    }

    @Override
    public void addRoom(Room room) throws RoomException {
        // Verificar si ya existe una habitación con el mismo nombre
        if (rooms.stream().anyMatch(r -> r.getNombre().equalsIgnoreCase(room.getNombre()))) {
            throw new RoomException("Ya existe una habitación con este nombre");
        }
        
        rooms.add(room);
        saveChanges();
    }

    @Override
    public Room getRoomById(String id) throws RoomException {
        return rooms.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(RoomException::notFound);
    }

    @Override
    public void updateRoom(Room room) throws RoomException {
        // Buscar la habitación para asegurar que existe
        Room existingRoom = getRoomById(room.getId());
        
        // Verificar si el nuevo nombre ya está en uso por otra habitación
        if (!existingRoom.getNombre().equals(room.getNombre()) &&
            rooms.stream()
                .filter(r -> !r.getId().equals(room.getId()))
                .anyMatch(r -> r.getNombre().equalsIgnoreCase(room.getNombre()))) {
            throw new RoomException("Ya existe una habitación con este nombre");
        }
        
        // La forma más fácil de actualizar es reemplazar la habitación en la lista
        rooms.removeIf(r -> r.getId().equals(room.getId()));
        rooms.add(room);
        
        saveChanges();
    }

    @Override
    public void deleteRoom(String id) throws RoomException {
        // Verificar que la habitación exista
        Room room = getRoomById(id);
        
        // Verificar si la habitación está ocupada
        if (!room.isDisponible()) {
            throw RoomException.isOccupied();
        }
        
        rooms.removeIf(r -> r.getId().equals(id));
        saveChanges();
    }

    @Override
    public List<Room> getRoomsByType(char type) throws RoomException {
        if (!"IPDM".contains(String.valueOf(type))) {
            throw RoomException.invalidType();
        }
        
        return rooms.stream()
                .filter(r -> r.getTipo() == type)
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> getAvailableRooms() throws RoomException {
        return rooms.stream()
                .filter(Room::isDisponible)
                .collect(Collectors.toList());
    }

    @Override
    public void setRoomMaintenance(String roomId, boolean inMaintenance) throws RoomException {
        Room room = getRoomById(roomId);
        
        // Si está en mantenimiento, no está disponible
        room.setDisponible(!inMaintenance);
        saveChanges();
    }

    @Override
    public void setRoomOccupied(String roomId, boolean occupied) throws RoomException {
        Room room = getRoomById(roomId);
        
        // Actualizar disponibilidad (inversa de ocupado)
        room.setDisponible(!occupied);
        saveChanges();
    }

    private void createDefaultRooms() throws RoomException {
        // Salas individuales (I) - 50 salas distribuidas en 5 pisos
        for (int piso = 1; piso <= 5; piso++) {
            for (int sala = 1; sala <= 10; sala++) {
                String id = String.format("I%d%02d", piso, sala);
                rooms.add(new Room(id, 'I', 20.0 + (piso * 2)));
            }
        }
        
        // Salas privadas (P) - 20 salas distribuidas en 4 pisos
        for (int piso = 1; piso <= 4; piso++) {
            for (int sala = 1; sala <= 5; sala++) {
                String id = String.format("P%d%02d", piso, sala);
                rooms.add(new Room(id, 'P', 35.0 + (piso * 5)));
            }
        }
        
        // Dormitorios (D) - 30 dormitorios distribuidos en 6 pisos
        for (int piso = 1; piso <= 6; piso++) {
            for (int sala = 1; sala <= 5; sala++) {
                String id = String.format("D%d%02d", piso, sala);
                rooms.add(new Room(id, 'D', 45.0 + (piso * 3)));
            }
        }
        
        // Salas mixtas (M) - 15 salas distribuidas en 3 pisos
        for (int piso = 1; piso <= 3; piso++) {
            for (int sala = 1; sala <= 5; sala++) {
                String id = String.format("M%d%02d", piso, sala);
                rooms.add(new Room(id, 'M', 55.0 + (piso * 4)));

            }
        }
    }
}