package homebaking.model;

import java.util.List;

public class Card {
    private final Long id;
    private String number;
    private String type;
    private double availableBalance;
    private double balance;
    private User owner;
    private List<Movimiento> movimientos;

    public Card(Long id, String number, String type, double availableBalance, double balance, User owner, List<Movimiento> movimientos) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.availableBalance = availableBalance;
        this.balance = balance;
        this.owner = owner;
        this.movimientos = movimientos;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimiento(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
