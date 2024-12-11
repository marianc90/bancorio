package homebaking.model;

import homebaking.exceptions.ServiceException;
import homebaking.service.UserService;

import java.util.List;
import java.util.UUID;

public class Tarjeta {
    private Long numero;
    private String tipo;
    private double disponible;
    private double saldo;
    private User titular;
    private List<Movimiento> movimientos;

    public Tarjeta(Long numero, String tipo, double disponible, double saldo, User titular, List<Movimiento> movimientos) {
        this.numero = numero;
        this.tipo = tipo;
        this.disponible = disponible;
        this.saldo = saldo;
        this.titular = titular;
        this.movimientos = movimientos;
    }

    public Tarjeta(Integer titular, String tipo, double disponible) throws ServiceException {
        this.tipo = tipo;
        String prefix = tipo.equalsIgnoreCase("Mastercard") ? "5375" : tipo.equalsIgnoreCase("Visa") ? "4239" : "";
        this.numero = Long.parseLong(prefix + Math.abs(UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 12).hashCode()));
        this.disponible = disponible;
        this.saldo = 0;
        UserService s = new UserService();
        this.titular = s.getUser(titular);
    }

    public Tarjeta(String tipo, Integer titular, Long numero, double disponible, double saldo) throws ServiceException {
        this.tipo = tipo;
        UserService s = new UserService();
        this.titular = s.getUser(titular);
        this.numero = numero;
        this.disponible = disponible;
        this.saldo = saldo;
    }

    public Tarjeta(String tipo, User titular, Long numero, double disponible, double saldo) {
        this.tipo = tipo;
        this.titular = titular;
        this.numero = numero;
        this.disponible = disponible;
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public double getDisponible() {
        return disponible;
    }

    public void setDisponible(double disponible) {
        this.disponible = disponible;
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

    public void setMovimiento(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
