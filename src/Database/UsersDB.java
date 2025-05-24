package Database;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import User.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UsersDB class handles all database operations related to user management.
 * This includes creating, reading, updating, and deleting user records,
 * as well as managing user balances and card information.
 */
public class UsersDB {
    private final DBManager manager;
    
    /**
     * Constructor initializes the database manager for handling database connections
     */
    public UsersDB() {
        this.manager = new DBManager();
    }
    
    /**
     * Adds a new user to the database
     * @param user The User object containing user information to be added
     * @return true if the user was successfully added, false otherwise
     */
    public boolean addUser(User user) {
        String sql = "INSERT INTO USERS (username, email, password, cardNumber, topUp) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = manager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getCardNumber());
            pstmt.setDouble(5, user.getBalance());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Retrieves a user from the database by their email address
     * @param email The email address of the user to find
     * @return User object if found, null if no user exists with the given email
     */
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM USERS WHERE email = ?";
        try (Connection conn = manager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                User user = new User(
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password")
                );
                user.setCardNumber(rs.getString("cardNumber"));
                user.setBalance(rs.getDouble("topUp"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Updates an existing user's information in the database
     * @param user The User object containing updated information
     * @param manager The database manager instance
     * @return true if the update was successful, false otherwise
     */
    public static boolean updateUser(User user, DBManager manager) {
        String sql = "UPDATE USERS SET username = ?, password = ?, cardNumber = ?, topUp = ? WHERE email = ?";
        try (Connection conn = manager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getCardNumber());
            pstmt.setDouble(4, user.getBalance());
            pstmt.setString(5, user.getEmail());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Deletes a user from the database
     * @param email The email address of the user to delete
     * @return true if the user was successfully deleted, false otherwise
     */
    public boolean deleteUser(String email) {
        String sql = "DELETE FROM USERS WHERE email = ?";
        try (Connection conn = manager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Retrieves all users from the database
     * @return List of all User objects in the database
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USERS";
        try (Connection conn = manager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                User user = new User(
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password")
                );
                user.setCardNumber(rs.getString("cardNumber"));
                user.setBalance(rs.getDouble("topUp"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    /**
     * Updates a user's balance in the database
     * @param email The email address of the user to update
     * @param newBalance The new balance amount
     * @return true if the balance was successfully updated, false otherwise
     */
    public boolean updateUserBalance(String email, double newBalance) {
        String sql = "UPDATE USERS SET topUp = ? WHERE email = ?";
        try (Connection conn = manager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, email);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Updates a user's card number in the database
     * @param email The email address of the user to update
     * @param cardNumber The new card number
     * @return true if the card number was successfully updated, false otherwise
     */
    public boolean updateCardNumber(String email, String cardNumber) {
        String sql = "UPDATE USERS SET cardNumber = ? WHERE email = ?";
        try (Connection conn = manager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cardNumber);
            pstmt.setString(2, email);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
