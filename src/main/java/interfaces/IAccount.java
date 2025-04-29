package interfaces;

import model.Account;
import exceptions.AccountException;
import java.util.List;

/**
 * Interfaz para la gestión de cuentas de usuario.
 */
public interface IAccount {
    
    /**
     * Registra una nueva cuenta.
     * @param account Cuenta a registrar.
     * @throws AccountException Si la cuenta ya existe o los datos son inválidos.
     */
    void registerAccount(Account account) throws AccountException;

    /**
     * Valida las credenciales de una cuenta.
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @return Cuenta si las credenciales son válidas, null en caso contrario.
     * @throws AccountException Si ocurre un error en la validación.
     */
    Account login(String username, String password) throws AccountException;

    /**
     * Actualiza el nombre de una cuenta.
     * @param username Nombre de usuario de la cuenta a modificar.
     * @param newName Nuevo nombre.
     * @throws AccountException Si la cuenta no existe o los datos son inválidos.
     */
    void updateName(String username, String newName) throws AccountException;

    /**
     * Actualiza el apellido de una cuenta.
     * @param username Nombre de usuario de la cuenta a modificar.
     * @param newlast Nuevo nombre.
     * @throws AccountException Si la cuenta no existe o los datos son inválidos.
     */
    void updateLast(String username, String newLast) throws AccountException;

    /**
     * Actualiza el teléfono de una cuenta.
     * @param username Nombre de usuario de la cuenta a modificar.
     * @param newPhone Nuevo número de teléfono.
     * @throws AccountException Si la cuenta no existe o los datos son inválidos.
     */
    void updatePhone(String username, String newPhone) throws AccountException;

    /**
     * Actualiza el correo electrónico de una cuenta.
     * @param username Nombre de usuario de la cuenta a modificar.
     * @param newEmail Nuevo correo electrónico.
     * @throws AccountException Si la cuenta no existe o los datos son inválidos.
     */
    void updateEmail(String username, String newEmail) throws AccountException;

    /**
     * Cambia la contraseña de una cuenta.
     * @param username Nombre de usuario de la cuenta.
     * @param currentPassword Contraseña actual.
     * @param newPassword Nueva contraseña.
     * @throws AccountException Si la cuenta no existe o los datos son inválidos.
     */
    void updatePassword(String username, String currentPassword, String newPassword) throws AccountException;

    /**
     * Resetear la contraseña de un usuario.
     * @param username Nombre de usuario.
     * @param newPassword Nueva contraseña.
     * @throws AccountException Si la cuenta no existe o los datos son inválidos.
     */
    void resetUserPassword(String username, String newPassword) throws AccountException;
    
    /**
     * Establece el estado de administrador de una cuenta.
     * @param username Nombre de usuario de la cuenta.
     * @param isAdmin Estado de administrador (true para activar, false para desactivar).
     * @throws AccountException Si la cuenta no existe o los datos son inválidos.
     */
    void setAdminStatus(String username, boolean isAdmin) throws AccountException;
    
    /**
     * Busca una cuenta por nombre de usuario.
     * @param username Nombre de usuario a buscar.
     * @return Cuenta si existe, null en caso contrario.
     * @throws AccountException Si la cuenta no existe o los datos son inválidos.
     */
    Account getByUsername(String username) throws AccountException;

    /**
     * Busca una cuenta por correo electrónico.
     * @param email Correo electrónico a buscar.
     * @return Cuenta si existe, null en caso contrario.
     * @throws AccountException Si la cuenta no existe o los datos son inválidos.
     */
    Account getByEmail(String email) throws AccountException;

    /**
     * Busca una cuenta por id.
     * @param id Correo electrónico a buscar.
     * @return Cuenta si existe, null en caso contrario.
     * @throws AccountException Si la cuenta no existe o los datos son inválidos.
     */
    Account getById(String id) throws AccountException;

    /**
     * Elimina una cuenta.
     * @param username Nombre de usuario de la cuenta a eliminar.
     * @throws AccountException Si la cuenta no existe o los datos son inválidos.
     */
    void deleteAccount(String username) throws AccountException;

    /**
     * Obtiene todas las cuentas registradas.
     * @return Lista de cuentas registradas.
     * @throws AccountException Si ocurre un error al obtener las cuentas.
     */
    List<Account> getAllAccounts() throws AccountException;
}
