package model;

import java.io.Serializable;

/**
 * Clase base para serialización de modelos a archivos.
 * Proporciona la infraestructura común para todos los modelos que necesitan
 * persistencia en el sistema.
 * 
 * @description Funcionalidades principales:
 *                   - Serializar objetos a formato de texto para almacenamiento.
 *                   - Deserializar texto a objetos del modelo.
 *                   - Generar información legible para presentación.
 * 
 * @param <T> Tipo de modelo que extiende esta clase base
 * @author KNOWLES
 * @version 1.0
 * @since 2025-04-29
 */
public abstract class Base<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Serializa el objeto a una representación en formato texto.
     * 
     * @return Cadena que representa el estado del objeto para almacenamiento
     */
    public abstract String toFile();

    /**
     * Deserializa una cadena de texto para reconstruir un objeto del modelo.
     * 
     * @param line Cadena que contiene los datos serializados
     * @return Objeto del tipo T reconstruido a partir de los datos
     */
    public abstract T fromFile(String line);

    /**
     * Genera una representación legible del objeto para presentación.
     * 
     * @return Cadena con información formateada del objeto
     */
    public abstract String getInfo();
}