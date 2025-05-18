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
/**
 *
 * @author GGPC
 */
public class UsersDB {

    private final DBManager manager;


    public UsersDB(DBManager manager) {
        this.manager = manager;
    }

    //add user
    public boolean addUser(User user) {
        Connection conn = manager.getConnection();
        if ( conn == null ){
            System.out.println("NO connection");
            return false;
        }
        String sql ="INSERT INTO USERS (NAME, EMAIL, PASSWORD, CARD_MONEY, CARD_NUMBER) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement  ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setDouble(4, user.getBalance());
            ps.setString(5, user.getCardNumber());

            int rowsinserted =ps.executeUpdate();
            return rowsinserted > 0;

        } catch (SQLException e) {
            System.out.println("Error adding user: " +e.getMessage());
            return false;
        }
    }


    //get user by email
    public User getuserbyemail(String email) {
        Connection conn = manager.getConnection();
        if ( conn == null ) {
            System.out.println("No db connection available");
            return null;
        }

        String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
        try ( PreparedStatement ps  = conn.prepareStatement(sql)) {
            ps.setString(1,email);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                            rs.getString("NAME"),
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD")
                    );
                    user.setBalance(rs.getDouble("CARD_MONEY"));
                    user.setCardNumber(rs.getString("CARD_NUMBER"));
                    return user;
                }
                } catch (SQLException ex) {
                System.out.println("Error finding user " + ex.getMessage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;


    }
}
