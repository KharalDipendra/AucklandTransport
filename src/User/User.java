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

    // --- Name ---
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // --- Email ---
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // --- Password ---
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // --- Card Number ---
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // --- Balance (topUp) ---
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // --- Member Type ---
    public String getMemberType() {
        return memberType;
    }
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    // --- Member Since ---
    public LocalDate getMemberSince() {
        return memberSince;
    }
    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    // --- Discount Type ---
    public String getDiscountType() {
        return discountType;
    }
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    @Override
    public String toString() {
        return String.format("User[name=%s, email=%s, memberType=%s, since=%s, discount=%s]", 
            name, email, memberType, memberSince, discountType);
    }
}
