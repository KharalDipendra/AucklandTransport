package User;

import java.time.LocalDate;

/**
 * Represents a user in the Auckland Transport app.
 * Reflects the USERS table schema:
 * - name (PK)
 * - email
 * - password
 * - memberType (Admin/Member)
 * - cardNumber
 * - topUp (balance)
 * - memberSince (date account created)
 * - discountType (Standard, Student, Gold Card, Child)
 */
public class User {
    private String name;
    private String email;
    private String password;
    private String cardNumber;
    private double balance;
    private String memberType;    // 'Admin' or 'Member'
    private LocalDate memberSince;    // date when account was created
    private String discountType;   // 'Standard', 'Student Discount', 'Gold Card', 'Child'

    /**
     * Constructor for a new member.
     * memberSince defaults to today, discountType to 'Standard', and balance to 0.0.
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cardNumber = null;
        this.balance = 0.0;
        this.memberType = "Member";
        this.memberSince = LocalDate.now();
        this.discountType = "Standard";
    }

    // gets the users name
    public String getName() {
        return name;
    }
    // sets the users name
    public void setName(String name) {
        this.name = name;
    }

    // gets the users email
    public String getEmail() {
        return email;
    }
    // sets the users email
    public void setEmail(String email) {
        this.email = email;
    }

    // gets the users password
    public String getPassword() {
        return password;
    }
    // sets the users password
    public void setPassword(String password) {
        this.password = password;
    }

    // gets the users card number
    public String getCardNumber() {
        return cardNumber;
    }
    // sets the users card number
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // gets the users balance
    public double getBalance() {
        return balance;
    }
    // sets the users balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // gets the users member type
    public String getMemberType() {
        return memberType;
    }
    // sets the users member type
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    // gets the date the user joined
    public LocalDate getMemberSince() {
        return memberSince;
    }
    // sets the date the user joined
    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    // gets the users discount type
    public String getDiscountType() {
        return discountType;
    }
    // sets the users discount type
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    @Override
    public String toString() {
        return String.format("User[name=%s, email=%s, memberType=%s, since=%s, discount=%s]", 
            name, email, memberType, memberSince, discountType);
    }
}
