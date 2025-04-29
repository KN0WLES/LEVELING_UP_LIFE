package interfaces;

import model.FaQ;
import exceptions.FaQException;
import java.util.List;

/**
 * Interfaz para la gestión de preguntas frecuentes (FAQ).
 */
public interface IFaQ {
    
    /**
     * Agrega una nueva pregunta frecuente.
     * @param faq Pregunta frecuente a agregar.
     * @throws FaQException Si ocurre un error al agregar la FAQ.
     */
    void addFaq(FaQ faq) throws FaQException;
    
    /**
     * Obtiene una pregunta frecuente por su ID.
     * @param id Identificador de la FAQ.
     * @return La pregunta frecuente correspondiente.
     * @throws FaQException Si la FAQ no existe.
     */
    FaQ getFaqById(String id) throws FaQException;

    /**
     * Obtiene una lista de preguntas pendientes.
     * @return La pregunta frecuente correspondiente.
     * @throws FaQException Si la FAQ no existe.
     */
    List<FaQ> getFaqByPending() throws FaQException;

    /**
     * Verificca que no existan preguntas frecuentes pendientes.
     * @return true si tenemos FaQ pendientes, false si no.
     * @throws FaQException si tenemos un error al verificar pending questions
     */
    boolean areFaqByPending() throws FaQException;

    /**
     * Actualiza una pregunta frecuente existente.
     * @param faq Pregunta frecuente con los datos actualizados.
     * @throws FaQException Si la FAQ no existe o los datos son inválidos.
     */
    void updateFaq(FaQ faq) throws FaQException;
    
    /**
     * Elimina una pregunta frecuente.
     * @param id Identificador de la FAQ a eliminar.
     * @throws FaQException Si la FAQ no existe.
     */
    void deleteFaq(String id) throws FaQException;
    
    /**
     * Obtiene todas las preguntas frecuentes registradas.
     * @return Lista de preguntas frecuentes.
     * @throws FaQException Si ocurre un error al obtener las FAQ.
     */
    List<FaQ> getAllFaqs() throws FaQException;
}