package homebaking.dao;

import homebaking.exceptions.DAOException;
import homebaking.model.User;
import java.util.List;

public interface UserDao {

    void crearUser(User aUser) throws DAOException;

    void borraUser(String username) throws DAOException;

    void actualizaUser(User aUser) throws DAOException;

    User checkUser(String email, String password) throws DAOException;

    User getUser(Integer id) throws DAOException;

    List<User> listaTodosLosUsers() throws DAOException;

}