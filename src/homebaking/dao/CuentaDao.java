package homebaking.dao;

import homebaking.exceptions.DAOException;
import homebaking.exceptions.ObjectoDuplicadoException;
import homebaking.model.Cuenta;

import java.util.List;

public interface CuentaDao {

    void crearCuenta(Cuenta aCuenta) throws DAOException;

    void borrarCuenta(Integer numero) throws DAOException;

    void actualizaSaldo(Cuenta aCuenta) throws DAOException;

    Cuenta checkCuenta(Integer numero, String tipo) throws DAOException;

    Cuenta checkCuenta(Integer numero) throws DAOException;

    List<Cuenta> listaTodasLasCuentas() throws DAOException;

    List<Cuenta> listaCuentasUser(Integer id) throws DAOException;

}