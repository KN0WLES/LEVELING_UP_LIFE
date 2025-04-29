package exceptions;

public class FileException extends Exception {
    public FileException(String message) {
        super(message);
    }
    
    public static FileException readError() {
        return new FileException("Error al leer el archivo");
    }
    public static FileException writeError() {
        return new FileException("Error al escribir en el archivo");
    }
}