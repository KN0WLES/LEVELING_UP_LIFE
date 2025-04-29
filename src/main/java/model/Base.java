package model;

import java.io.Serializable;

/**
 * Clase base para serialización de modelos a archivos.
 * @param <T> tipo de modelo a serializar
 */
public abstract class Base<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public abstract String toFile();         // Serializar a String
    public abstract T fromFile(String line); // Deserializar
    public abstract String getInfo();        // Info legible
}