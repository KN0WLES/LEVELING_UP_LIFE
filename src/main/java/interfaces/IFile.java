package interfaces;

import exceptions.FileException;
import java.util.List;

/**
 * Interfaz genérica para la gestión de archivos.
 * @param <T> Tipo de datos a manejar en el archivo.
 */
public interface IFile<T> {
    
    /**
     * Guarda una lista de datos en un archivo.
     * @param data Lista de datos a guardar.
     * @param filePath Ruta del archivo.
     * @throws FileException Si ocurre un error al guardar los datos.
     */
    void saveData(List<T> data, String filePath) throws FileException;
    
    /**
     * Carga una lista de datos desde un archivo.
     * @param filePath Ruta del archivo.
     * @return Lista de datos cargados.
     * @throws FileException Si ocurre un error al leer el archivo.
     */
    List<T> loadData(String filePath) throws FileException;
    
    /**
     * Agrega un dato al final del archivo.
     * @param data Dato a agregar.
     * @param filePath Ruta del archivo.
     * @throws FileException Si ocurre un error al escribir en el archivo.
     */
    void appendData(T data, String filePath) throws FileException;
    
    /**
     * Verifica si un archivo existe.
     * @param filePath Ruta del archivo.
     * @return true si el archivo existe, false en caso contrario.
     */
    boolean fileExists(String filePath);
    
    /**
     * Crea un archivo si no existe.
     * @param filePath Ruta del archivo.
     * @throws FileException Si ocurre un error al crear el archivo.
     */
    void createFileIfNotExists(String filePath) throws FileException;
}