/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import org.apache.derby.iapi.sql.PreparedStatement;

/**
 *
 * @author Pammi
 */
public class AdminDB {
    
    private Connection conn;
    private final DBManager manager;

    public AdminDB(DBManager manager) {
        this.manager = manager;
    }

    public Connection getConnection() {
        return conn;
    }
  
    //view all users method
    public void viewallusers() throws SQLException {
        String SQL = "SELECT * FROM USERS";
        Statement stmt = conn.createStatement(); 
        ResultSet rs = stmt.executeQuery(SQL);
        
        while (rs.next()) {
            System.out.println("EMAIL: " + rs.getString("email") +
                               ", NAME: " + rs.getString("username"));
                    
        }
        rs.close();
        stmt.close();
    }
    
    //attach action listener to method
    
    //view all bookings
    public void viewallbookings() throws SQLException {
        String SQL = "SELECT * FROM BOOKINGS";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        
        while (rs.next()) {
            System.out.println("BookingID: " + rs.getString("bookingID") + 
                    ",Username : " + rs.getString("username") + 
                    ",Email: " + rs.getString("email") + 
                    ",DateBooked:  " + rs.getString("dateBooked") + 
                    ",DepartureDate: " + rs.getString("departureDate") +
                    ",Destination: " + rs.getString("destination") + 
                    ",Price: " + rs.getString("price") + 
                    ", ServiceType: " + rs.getString("serviceType"));
                        }
        
            rs.close();
            stmt.close();
    }
    


    
    

}
