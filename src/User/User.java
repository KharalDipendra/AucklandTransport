/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

/**
 *
 * @author GGPC
 */
public class User {

    private String email;
    private String name;
    private String password;
    private Double card_money;
    private String cardNumber;

    public User(String name,String email,String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.card_money = 0.0;
        this.cardNumber = "";
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
      return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
       return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(Double card_money) {
        this.card_money = card_money;
    }

    public double getBalance() {
        return this.card_money;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public boolean hasCard() {
        return cardNumber != null && !cardNumber.isEmpty() && !cardNumber.equals("NULL");
    }

    
    
}
