package homebaking.model;

import homebaking.exceptions.ServiceException;
import homebaking.service.UserService;
import java.util.List;
import java.util.UUID;

public class Cuenta {
    private Integer numero;
    private String tipo;
    private double saldo;
    private User titular;
    private List<Movimiento> movimientos;

    public Cuenta(String tipo, Integer titular) throws ServiceException {
        this.numero = Math.abs(UUID.randomUUID().hashCode());
        this.tipo = tipo;
        this.saldo = 0;
        UserService s = new UserService();
        this.titular = s.getUser(titular);
    }

    public Cuenta(String tipo, Integer titular, Integer numero, double saldo) throws ServiceException {
        this.tipo = tipo;
        UserService s = new UserService();
        this.titular = s.getUser(titular);
        this.numero = numero;
        this.saldo = saldo;
    }

    public Cuenta(String tipo, User titular, Integer numero, double saldo) {
        this.tipo = tipo;
        this.titular = titular;
        this.numero = numero;
        this.saldo = saldo;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double importe) {
        this.saldo += importe;
    }

    public User getTitular() {
        return titular;
    }

    public void setTitular(User titular) {
        this.titular = titular;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}

