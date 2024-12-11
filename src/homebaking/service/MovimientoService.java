package homebaking.service;


import homebaking.dao.MovimientoDao;
import homebaking.exceptions.DAOException;
import homebaking.exceptions.ServiceException;
import homebaking.h2Impl.MovimientoDaoH2Impl;
import homebaking.model.Cuenta;
import homebaking.model.Movimiento;
import homebaking.model.Tarjeta;

import java.util.Date;
import java.util.List;

public class MovimientoService {
    MovimientoDao dao = new MovimientoDaoH2Impl();

    public void crearMovimiento(String descripcion, double monto, String tipo, Cuenta cuentaOrigen, Cuenta cuentaDestino, Tarjeta tarjeta) throws ServiceException {

        try {
            if (tipo.equals("TRANSFERENCIA") || tipo.equals("DEBITO")) {
                CuentaService cs = new CuentaService();
                if (cuentaOrigen != null) {
                    cuentaOrigen.setSaldo(-monto);
                    cs.actualizaSaldo(cuentaOrigen);
                    if (cuentaDestino != null) {
                        cuentaDestino.setSaldo(monto);
                        cs.actualizaSaldo(cuentaDestino);
                    }
                }
            } else if (tipo.equals("CREDITO")) {
                if (cuentaOrigen != null) {
                    CuentaService cs = new CuentaService();
                    cuentaOrigen.setSaldo(monto);
                    cs.actualizaSaldo(cuentaOrigen);
                }
            } else if (tipo.equals("CONSUMO")) {
                if (tarjeta != null) {
                    TarjetaService ts = new TarjetaService();
                    if ((tarjeta.getDisponible() - tarjeta.getSaldo()) < monto) {
                        throw new ServiceException("No hay suficiente límite en la tarjeta");
                    } else {
                        tarjeta.setSaldo(monto);
                        ts.actualizaSaldo(tarjeta);
                    }
                }

            } else if (tipo.equals("PAGO")) {
                if (tarjeta != null) {
                    TarjetaService ts = new TarjetaService();
                    tarjeta.setSaldo(-monto);
                    ts.actualizaSaldo(tarjeta);
                }
            }

            Movimiento aInsertar = new Movimiento(descripcion, monto, tipo, cuentaOrigen, cuentaDestino, tarjeta);
            dao.crearMovimiento(aInsertar);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<Movimiento> listaTodosLosMovimientos() throws ServiceException {
        try {
            return dao.listaTodosLosMovimientos();
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    public void borrarMovimiento(Movimiento aBorrar) throws ServiceException {
        try {
            Long id = aBorrar.getId();
            dao.borrarMovimiento(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void editarMovimiento(Movimiento aEditar) throws ServiceException {
        try {
            dao.editarMovimiento(aEditar);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Movimiento checkMovimiento(Long id) throws ServiceException {
        try {
            return dao.checkMovimiento(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
