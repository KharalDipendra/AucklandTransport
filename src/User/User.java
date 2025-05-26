package User;

/**
 * Represents a user in the Auckland Transport app.
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String cardNumber;
    private double balance;
    private String memberType;  // 'Admin' or 'Member'

    /**
     * Constructor with role. Balance defaults to 0.0, cardNumber defaults to null.
     * USER_ROLE should be either "Admin" or "Member".
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0.0;
        this.memberType = "Member";
    }

    // --- ID ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    // --- Balance ---
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // --- User Role ---
    public String getUserRole() {
        return memberType;
    }

    public void setUserRole(String USER_ROLE) {
        this.memberType = USER_ROLE;
    }

    // --- Member Type alias ---
    /**
     * Alias for getting the memberType (USER_ROLE).
     */
    public String getMemberType() {
        return memberType;
    }

    /**
     * Alias for setting the memberType (USER_ROLE).
     */
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
}
