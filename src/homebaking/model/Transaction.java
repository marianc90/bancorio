package homebaking.model;

import java.util.Date;

public class Transaction {
    private final Long id;
    private String type; // Débito o Crédito
    private double amount;
    private Date date;
    private Account account;

    public Transaction(Long id, String type, double amount, Date date, Account account) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
