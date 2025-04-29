package controller;

import interfaces.*;
import model.Event;
import exceptions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que actúa como controlador para la gestión de eventos.
 * Proporciona la lógica de negocio necesaria para crear, cancelar, buscar, listar y gestionar participantes de eventos.
 * Los datos de los eventos se almacenan y recuperan desde un archivo utilizando un manejador de archivos genérico.
 * 
  * @description Funcionalidades principales:
 *                   - Crear un nuevo evento.
 *                   - Cancelar un evento existente.
 *                   - Agregar o remover participantes de un evento.
 *                   - Buscar eventos por ID o por fecha.
 *                   - Listar todos los eventos o solo los eventos activos.
 * 
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 * @see IEvent
 * @see Event
 * @see IFile
 * @see FileException
 * @see EventException
 */
public class EventController implements IEvent {
    
    private final IFile<Event> fileHandler;
    private final String filePath = "/src/main/java/data/events.txt";
    private List<Event> events;

    public EventController(IFile<Event> fileHandler) {
        this.fileHandler = fileHandler;
        try {
            this.fileHandler.createFileIfNotExists(filePath);
            this.events = this.fileHandler.loadData(filePath);
        } catch (FileException e) {
            this.events = new ArrayList<>();
            System.err.println("Error al cargar eventos: " + e.getMessage());
        }
    }

    private void saveChanges() throws EventException {
        try {
            fileHandler.saveData(events, filePath);
        } catch (FileException e) {
            throw new EventException("Error al guardar los cambios: " + e.getMessage());
        }
    }

    @Override
    public void crearEvento(Event evento) throws EventException {
        events.add(evento);
        saveChanges();
    }

    @Override
    public void cancelarEvento(String eventId) throws EventException {
        Event evento = buscarPorId(eventId);
        
        if (evento == null) throw EventException.eventoNoEncontrado();
        
        //! Como no hay un método para cancelar el evento, se elimina de la lista.

        events.removeIf(e -> e.getId().equals(eventId));
        saveChanges();
    }

    @Override
    public boolean agregarParticipante(String eventId, String accountId) throws EventException {
        Event evento = buscarPorId(eventId);
        
        if (evento == null) throw EventException.eventoNoEncontrado();
        
        if (evento.getParticipantes().size() >= evento.getCapacidad()) throw EventException.eventoLleno();
        
        if (evento.getParticipantes().contains(accountId)) throw EventException.participanteYaRegistrado();
        
        boolean result = evento.addParticipante(accountId);
        saveChanges();
        return result;
    }

    @Override
    public boolean removerParticipante(String eventId, String accountId) throws EventException {
        Event evento = buscarPorId(eventId);
        
        if (evento == null) throw EventException.eventoNoEncontrado();
        
        if (!evento.getParticipantes().contains(accountId)) {
            throw new EventException("El participante no está registrado en este evento");
        }
        
        boolean result = evento.removeParticipante(accountId);
        saveChanges();
        return result;
    }

    @Override
    public Event buscarPorId(String eventId) throws EventException {
        return events.stream()
                .filter(e -> e.getId().equals(eventId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Event> buscarPorFecha(LocalDateTime fecha) throws EventException {
        LocalDateTime inicioDia = fecha.toLocalDate().atStartOfDay();
        LocalDateTime finDia = fecha.toLocalDate().atTime(23, 59, 59);
        
        return events.stream()
                .filter(e -> {
                    return (e.getFechaInicio().isEqual(inicioDia) || e.getFechaInicio().isAfter(inicioDia)) &&
                           (e.getFechaInicio().isEqual(finDia) || e.getFechaInicio().isBefore(finDia));
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> listarEventosActivos() throws EventException {
        LocalDateTime ahora = LocalDateTime.now();    
        return events.stream()
                .filter(e -> e.getFechaFin().isAfter(ahora))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> listarTodos() throws EventException {
        return new ArrayList<>(events);
    }
}