package Database;

import User.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;


/**
 * UsersDB handles CRUD operations for the USERS table.
 */
public class UsersDB {
    private final DBManager manager;
    private static final Logger logger = Logger.getLogger(UsersDB.class.getName());

    public UsersDB() {
        this.manager = new DBManager();
    }

    /**
     * Add a new user. memberType defaults to 'Member'; cardNumber and topUp remain NULL.
     */
    public boolean addUser(User user) {
        String sql = "INSERT INTO USERS (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = manager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
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
        try (Connection conn = manager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
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
        try (Connection conn = manager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getMemberType());
            ps.setString(4, user.getCardNumber());
            ps.setDouble(5, user.getBalance());
            ps.setString(6, user.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Remove user by email.
     */
    public boolean deleteUser(String email) {
        String sql = "DELETE FROM USERS WHERE email = ?";
        try (Connection conn = manager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
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
        try (Connection conn = manager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
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
        try (Connection conn = manager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
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
        try (Connection conn = manager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cardNumber);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
/**
 * Returns the User if the given name/password pair is valid,
 * or null if no matching record exists.
 */
public User authenticate(String name, String password) {
    String sql = "SELECT * FROM USERS WHERE name = ? AND password = ?";
    try (Connection conn = manager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

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

    /**
 * Retrieves all users from the USERS table.
 * @return List of User objects (empty if none)
 */
    
}
