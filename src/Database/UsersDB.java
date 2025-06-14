package Database;

import User.User;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * UsersDB handles CRUD operations for the USERS table.
 */
public class UsersDB {

    private final DBManager manager;
    private static final Logger logger = Logger.getLogger(UsersDB.class.getName());

    // creates a new database manager
    public UsersDB() {
        this.manager = new DBManager();
    }

    // uses a shared database manager
    public UsersDB(DBManager manager) {
        this.manager = manager;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Add a new user. memberType defaults to 'Member'; cardNumber and topUp remain NULL.
     */
    public boolean addUser(User user) {
        String sql = "INSERT INTO USERS (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Fetch user by email, populating all fields.
     */
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM USERS WHERE email = ?";
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                    user.setMemberType(rs.getString("memberType"));
                    user.setCardNumber(rs.getString("cardNumber"));
                    user.setBalance(rs.getDouble("topUp"));
                    return user;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Update all user fields by email.
     */
    public boolean updateUser(User user) {
        String sql = "UPDATE USERS SET name = ?, password = ?, memberType = ?, cardNumber = ?, topUp = ? WHERE email = ?";
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getMemberType());
            ps.setString(4, user.getCardNumber());
            ps.setDouble(5, user.getBalance());
            ps.setString(6, user.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating user: " + ex.getMessage(), ex);
            return false;
        }
    }

    /**
     * Remove user by email.
     */
    public boolean deleteUser(String email) {
        String sql = "DELETE FROM USERS WHERE email = ?";
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * List all users.
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USERS";
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                user.setMemberType(rs.getString("memberType"));
                user.setCardNumber(rs.getString("cardNumber"));
                user.setBalance(rs.getDouble("topUp"));
                users.add(user);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return users;
    }

    /**
     * Update only the topUp (balance) field.
     */
    public boolean updateUserBalance(String email, double newBalance) {
        String sql = "UPDATE USERS SET topUp = ? WHERE email = ?";
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Update only the cardNumber field.
     */
    public boolean updateCardNumber(String email, String cardNumber) {
        String sql = "UPDATE USERS SET cardNumber = ? WHERE email = ?";
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cardNumber);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Returns the User if the given name/password pair is valid, or null if no matching record exists.
     * Username check is case-insensitive.
     */
    public User authenticate(String name, String password) {
        String sql = "SELECT * FROM USERS WHERE UPPER(name) = UPPER(?) AND password = ?";
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // build a User object from the row
                    User u = new User(
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                    u.setMemberType(rs.getString("memberType"));
                    u.setCardNumber(rs.getString("cardNumber"));
                    u.setBalance(rs.getDouble("topUp"));
                    return u;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // updates a users role
    public boolean updateUserRole(String email, String role_name) {
        String sql = "UPDATE USERS SET MEMBERTYPE = ? WHERE email = ?";
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, role_name);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Checks if a user with the given email exists in the database.
     */
    public boolean checkStatus(String email) {
        String sql = "SELECT COUNT(*) FROM USERS WHERE email = ?";
        try (Connection conn = manager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error checking user status", ex);
        }
        return false;
    }
        
//    public boolean getCardBalance(String email) {
//        String sql = "SELECT topUp FROM USERS WHERE email = ?";
//        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, userEmail);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    double balance = rs.getDouble("topUp");
//                    // … use balance …
//                }
//            }
//        }}

    /**
     * Update only the user's email.
     * This will also update the email in all of the user's bookings.
     */
    public boolean updateUserEmail(String oldEmail, String newEmail) {
        String updateUserSQL = "UPDATE USERS SET email = ? WHERE email = ?";
        String updateBookingsSQL = "UPDATE BOOKINGS SET email = ? WHERE email = ?";
        Connection conn = null;
        try {
            conn = manager.getConnection();
            conn.setAutoCommit(false); // Start transaction

            try (PreparedStatement psUser = conn.prepareStatement(updateUserSQL)) {
                psUser.setString(1, newEmail);
                psUser.setString(2, oldEmail);
                psUser.executeUpdate();
            }

            try (PreparedStatement psBooking = conn.prepareStatement(updateBookingsSQL)) {
                psBooking.setString(1, newEmail);
                psBooking.setString(2, oldEmail);
                psBooking.executeUpdate();
            }

            conn.commit(); // Commit transaction
            return true;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating user email", ex);
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback on error
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error rolling back transaction", e);
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error closing connection", e);
                }
            }
        }
    }

    /**
     * Update only the user's password.
     */
    public boolean updateUserPassword(String email, String newPassword) {
        String sql = "UPDATE USERS SET password = ? WHERE email = ?";
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating user password", ex);
            return false;
        }
    }
}
