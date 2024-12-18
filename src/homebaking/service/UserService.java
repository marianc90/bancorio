package homebaking.service;

import homebaking.dao.UserDao;
import homebaking.exceptions.DAOException;
import homebaking.exceptions.ServiceException;
import homebaking.h2Impl.UserDaoH2Impl;
import homebaking.model.User;

import java.util.List;

public class UserService {

    UserDao dao = new UserDaoH2Impl();

    public void crearUser(User aInsertar) throws ServiceException {

        try {
            dao.crearUser(aInsertar);
        } catch (DAOException e) {
           throw new ServiceException();
        }
    }

    public List<User> listaTodosLosUsers() throws ServiceException {
        try {
           return dao.listaTodosLosUsers();
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    public void borrarUser(User aBorrar) throws ServiceException {
        try {
            String username = aBorrar.getUsername();
            dao.borraUser(username);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void actualizaUser(User aEditar) throws ServiceException {
        try {
            dao.actualizaUser(aEditar);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public User checkUser(String email, String password) throws ServiceException {
        try {
            return dao.checkUser(email, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public User getUser(Integer id) throws ServiceException {
        try {
            return dao.getUser(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
