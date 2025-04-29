package model;

import java.util.UUID;

public class FaQ extends Base<FaQ> {
    private String id;
    private String pregunta;
    private String respuesta;
    private boolean pendiente;

    public FaQ(){}
    public FaQ(String pregunta, String respuesta) {
        if (pregunta == null || pregunta.trim().isEmpty())
            throw new IllegalArgumentException("La pregunta no puede estar vacía");
        if (respuesta == null || respuesta.trim().isEmpty())
            throw new IllegalArgumentException("La respuesta no puede estar vacía");

        this.id = UUID.randomUUID().toString();
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.pendiente = false;
    }
    
    public FaQ(String pregunta) {
        if (pregunta == null || pregunta.trim().isEmpty()){
            throw new IllegalArgumentException("La pregunta no puede estar vacía");
        }
        this.respuesta = "Solicitud pendiente de respuesta";
        this.pendiente = true;
        this.id = UUID.randomUUID().toString();
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getPregunta() { return pregunta; }
    public String getRespuesta() { return respuesta; }
    public boolean isPendiente() { return pendiente; }

    // --- Setters ---
    public void setPregunta(String pregunta) {
        if (pregunta == null || pregunta.trim().isEmpty())
            throw new IllegalArgumentException("La pregunta no puede estar vacía");
        this.pregunta = pregunta;
    }

    public void setRespuesta(String respuesta) {
        if (respuesta == null || respuesta.trim().isEmpty())
            throw new IllegalArgumentException("La respuesta no puede estar vacía");
        this.respuesta = respuesta;
    }

    public void setPendiente(boolean estado) { this.pendiente = estado; }

    // --- Serialización ---
    @Override
    public String toFile() {
        return String.join("|", id, pregunta, respuesta, String.valueOf(pendiente));
    }

    @Override
    public FaQ fromFile(String line) {
        String[] parts = line.split("\\|");
        FaQ faq = new FaQ(parts[1], parts[2]);
        faq.id = parts[0];
        faq.pendiente = Boolean.parseBoolean(parts[3]);
        return faq;
    }

    @Override
    public String getInfo() {
        return String.format("P: %s\nR: %s", pregunta, respuesta);
    }
}