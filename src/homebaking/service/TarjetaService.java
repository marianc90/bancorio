package homebaking.service;

import homebaking.dao.TarjetaDao;
import homebaking.exceptions.DAOException;
import homebaking.exceptions.ServiceException;
import homebaking.h2Impl.TarjetaDaoH2Impl;
import homebaking.model.Tarjeta;

import java.util.List;

public class TarjetaService {

    TarjetaDao dao = new TarjetaDaoH2Impl();

    public void crearTarjeta(Integer titular, String tipo, double disponible) throws ServiceException {

        try {
            Tarjeta aInsertar = new Tarjeta(titular, tipo, disponible);
            dao.crearTarjeta(aInsertar);
        } catch (DAOException e) {
            throw new ServiceException("Error al crear la tarjeta", e);
        } catch (NullPointerException e) {
            throw new ServiceException("El titular no existe", e);
        }
    }

    public List<Tarjeta> listaTodasLasTarjetas() throws ServiceException {
        try {
            return dao.listaTodasLasTarjetas();
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    public void borrarTarjeta(Tarjeta aBorrar) throws ServiceException {
        try {
            Long numero = aBorrar.getNumero();
            String tipo = aBorrar.getTipo();
            dao.borrarTarjeta(numero, tipo);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void actualizaSaldo(Tarjeta aEditar) throws ServiceException {
        try {
            dao.actualizaSaldo(aEditar);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void actualizaDisponible(Tarjeta aEditar) throws ServiceException {
        try {
            dao.actualizaDisponible(aEditar);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Tarjeta checkTarjeta(Long numero, String tipo) throws ServiceException {
        try {
            return dao.checkTarjeta(numero, tipo);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Tarjeta checkTarjeta(Long numero) throws ServiceException {
        try {
            return dao.checkTarjeta(numero);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
