package homebaking.dao;

import homebaking.exceptions.DAOException;
import homebaking.model.Tarjeta;
import java.util.List;

public interface TarjetaDao {
    void crearTarjeta(Tarjeta aTarjeta) throws DAOException;

    void borrarTarjeta(Long numero, String tipo) throws DAOException;

    void actualizaSaldo(Tarjeta aTarjeta) throws DAOException;

    void actualizaDisponible(Tarjeta aTarjeta) throws DAOException;

    Tarjeta checkTarjeta(Long numero, String tipo) throws DAOException;

    Tarjeta checkTarjeta(Long numero) throws DAOException;

    List<Tarjeta> listaTodasLasTarjetas() throws DAOException;

    List<Tarjeta> listaTarjetasUser(Integer id) throws DAOException;

}
