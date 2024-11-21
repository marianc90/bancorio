package homebaking.model;

import java.util.List;

public class Account {
    private final Long id;
    private String number;
    private String type;
    private double balance;
    private User owner;
    private List<Transaction> transactions;

    public Account(Long id, String number, String type, double balance, User owner, List<Transaction> transactions) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.owner = owner;
        this.transactions = transactions;
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

