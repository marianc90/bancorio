package homebaking.dao;

import homebaking.exceptions.DAOException;
import homebaking.model.Cuenta;
import homebaking.model.Movimiento;

import java.util.List;

public interface MovimientoDao {

    void crearMovimiento(Movimiento aMovimiento) throws DAOException;

    void borrarMovimiento(Long id) throws DAOException;

    void editarMovimiento(Movimiento aMovimiento) throws DAOException;

    Movimiento checkMovimiento(Long id) throws DAOException;

    List<Movimiento> listaTodosLosMovimientos() throws DAOException;


}
