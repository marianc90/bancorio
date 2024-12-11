package homebaking.service;

import homebaking.dao.CuentaDao;
import homebaking.exceptions.DAOException;
import homebaking.exceptions.ServiceException;
import homebaking.h2Impl.CuentaDaoH2Impl;
import homebaking.model.Cuenta;
import homebaking.model.User;

import java.util.List;

public class CuentaService {

    CuentaDao dao = new CuentaDaoH2Impl();

    public void crearCuenta(String tipo, Integer titular) throws ServiceException {

        try {
            Cuenta aInsertar = new Cuenta(tipo, titular);
            dao.crearCuenta(aInsertar);
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    public List<Cuenta> listaTodasLasCuentas() throws ServiceException {
        try {
            return dao.listaTodasLasCuentas();
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    public List<Cuenta> listaCuentasUser(User u) throws ServiceException {
        try {
            Integer id = u.getId();
            return dao.listaCuentasUser(id);
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    public void borrarCuenta(Cuenta aBorrar) throws ServiceException {
        try {
            Integer numero = aBorrar.getNumero();
            dao.borrarCuenta(numero);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void actualizaSaldo(Cuenta aEditar) throws ServiceException {
        try {
            dao.actualizaSaldo(aEditar);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Cuenta checkCuenta(Integer numero, String tipo) throws ServiceException {
        try {
            return dao.checkCuenta(numero, tipo);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Cuenta checkCuenta(Integer numero) throws ServiceException {
        try {
            return dao.checkCuenta(numero);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
