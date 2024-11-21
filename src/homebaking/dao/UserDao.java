package homebaking.dao;

import homebaking.exceptions.DAOException;
import homebaking.exceptions.ObjectoDuplicadoException;
import homebaking.model.User;

import java.util.List;

public interface UserDao {

    void crearUser(User aUser) throws DAOException;

    void borraUser(String username) throws DAOException;

    void actualizaUser(User aUser) throws DAOException;

    User muestraUser(String username) throws DAOException;

    List<User> listaTodosLosUsers() throws DAOException;

}