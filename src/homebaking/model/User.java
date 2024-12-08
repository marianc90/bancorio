package homebaking.model;

import java.util.List;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private List<Cuenta> cuentas;
    private List<Card> cards;

    public User() {}

    public User(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Cuenta> getAccounts() {
        return cuentas;
    }

    public void setAccounts(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isAdmin() {
        return username.equals("admin");
    }

}
