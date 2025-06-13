/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pammi
 */
public class AdminDB {
    
    private Connection conn;
    private final DBManager manager;

    public AdminDB(DBManager manager) {
        this.manager = manager;
        this.conn = manager.getConnection();
    }

    public Connection getConnection() {
        return conn;
    }
  
    //view all users method
    public ResultSet viewallusers() throws SQLException {
        String SQL = "SELECT * FROM USERS";
        if (conn == null || conn.isClosed()) {
            conn = manager.getConnection();
        }
        Statement stmt = conn.createStatement(); 
        return stmt.executeQuery(SQL);
    }
    
    //attach action listener to method
    
    //view all bookings
    public ResultSet viewallbookings() throws SQLException {
        String SQL = "SELECT * FROM BOOKINGS";
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(SQL);
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
            return false;
        }
    }

    /**
     * Remove booking by bookingID.
     */
    public boolean deleteBooking(String bookingID) {
        String sql = "DELETE FROM BOOKINGS WHERE bookingID = ?";
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bookingID);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

}
