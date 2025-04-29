package controller;

import interfaces.*;
import model.FaQ;
import exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class FaQController implements IFaQ {
    
    private final IFile<FaQ> fileHandler;
    private final String filePath = "C:/Users/HP/Downloads/VTerminado-4/V1-4/src/main/java/data/faqs.txt";
    private List<FaQ> faqs;

    public FaQController(IFile<FaQ> fileHandler) throws FaQException{
        this.fileHandler = fileHandler;
        try {
            this.fileHandler.createFileIfNotExists(filePath);
            this.faqs = this.fileHandler.loadData(filePath);

            // Verificar si hay FAQs cargadas
            if (this.faqs == null || this.faqs.isEmpty()) {
                // Crear cuenta por defecto
                //Account defaultAdmin = new Account("", "", "", "", "admin", "admin");
                FaQ default1FaQ = new FaQ("Donde se encuentran ubicados","Calle Almagro 11, Madrid - CIF B65739856");
                FaQ default2FaQ = new FaQ("Contactos","Teléfono: 4258813 Whatsapp: 65486014 Correo: info@unicorn.com.bo");
                this.faqs = new ArrayList<>(); // Inicializar la lista si es nula
                this.faqs.add(default1FaQ);
                this.faqs.add(default2FaQ);
                saveChanges();
            }
        } catch (FileException e) {
            this.faqs = new ArrayList<>();
            System.err.println("Error al cargar FAQs: " + e.getMessage());
        }
    }

    private void saveChanges() throws FaQException {
        try {
            fileHandler.saveData(faqs, filePath);
        } catch (FileException e) {
            throw new FaQException("Error al guardar los cambios: " + e.getMessage());
        }
    }

    @Override
    public void addFaq(FaQ faq) throws FaQException {
        // Verificar si ya existe una pregunta similar
        if (faqs.stream().anyMatch(f -> f.getPregunta().equalsIgnoreCase(faq.getPregunta()))) {
            throw new FaQException("Esta pregunta ya existe en el sistema");
        }
        
        faqs.add(faq);
        saveChanges();
    }

    @Override
    public FaQ getFaqById(String id) throws FaQException {
        return faqs.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElseThrow(FaQException::notFound);
    }

    @Override
    public void updateFaq(FaQ faq) throws FaQException {
        // Verificar si el FAQ existe
        FaQ existingFaq = getFaqById(faq.getId());
        
        // Actualizar los campos
        existingFaq.setPregunta(faq.getPregunta());
        existingFaq.setRespuesta(faq.getRespuesta());
        existingFaq.setPendiente(false);
        
        saveChanges();
    }

    public boolean areFaqByPending() throws FaQException {
        return faqs.stream().anyMatch(FaQ::isPendiente);
    }

    public List<FaQ> getFaqByPending() throws FaQException {
        return faqs.stream()
                .filter(FaQ::isPendiente)
                .toList();
    }

    @Override
    public void deleteFaq(String id) throws FaQException {
        // Verificar si el FAQ existe
        FaQ existingFaq = getFaqById(id);
        
        if (!faqs.remove(existingFaq)) {
            throw new FaQException("No se pudo eliminar el FAQ");
        }
        saveChanges();
    }

    @Override
    public List<FaQ> getAllFaqs() throws FaQException {
        return new ArrayList<>(faqs);
    }
}