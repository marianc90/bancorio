package homebaking.model;

import homebaking.exceptions.ServiceException;
import homebaking.service.CuentaService;
import homebaking.service.TarjetaService;
import java.util.Date;
import java.util.Random;

public class Movimiento {
    private Long id;
    private Date fecha;
    private String descripcion;
    private double monto;
    private String tipo; // Tipo de movimiento (e.g., "TRANSFERENCIA", "DEBITO")
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private Tarjeta tarjeta;

    public Movimiento(Long id, Date fecha, String descripcion, double monto, String tipo, Cuenta cuentaOrigen, Cuenta cuentaDestino, Tarjeta tarjeta) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
        this.tipo = tipo;
        if (cuentaOrigen != null) {
            this.cuentaOrigen = cuentaOrigen;
        }
        if (cuentaDestino != null) {
            this.cuentaDestino = cuentaDestino;
        }
        if (tarjeta != null) {
            this.tarjeta = tarjeta;
        }
    }

    public Movimiento(Long id, Date fecha, String descripcion, double monto, String tipo, Integer cuentaOrigen, Integer cuentaDestino, Long tarjeta) throws ServiceException {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
        this.tipo = tipo;
        if (cuentaOrigen != null) {
            CuentaService cuentaService = new CuentaService();
            this.cuentaOrigen = cuentaService.checkCuenta(cuentaOrigen);;
            if (cuentaDestino != null) {
                this.cuentaDestino = cuentaService.checkCuenta(cuentaDestino);
            }        }
        if (tarjeta != null) {
            TarjetaService tarjetaService = new TarjetaService();
            this.tarjeta = tarjetaService.checkTarjeta(tarjeta);
        }
    }

    public Movimiento(String descripcion, double monto, String tipo, Cuenta cuentaOrigen, Cuenta cuentaDestino, Tarjeta tarjeta) {
        this.id = Math.abs(new Random().nextLong());
        this.fecha = new Date();
        this.descripcion = descripcion;
        this.monto = monto;
        this.tipo = tipo;
        if (cuentaOrigen != null) {
            this.cuentaOrigen = cuentaOrigen;
        }
        if (cuentaDestino != null) {
            this.cuentaDestino = cuentaDestino;
        }
        if (tarjeta != null) {
            this.tarjeta = tarjeta;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
}