import GUI.*;
import java.sql.*;


/**
 *
 * @author scara
 */
public class Main {
      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new LaunchWindow().setVisible(true);
        
          try {
            Connection conn = DriverManager.getConnection("jdbc:derby:TransportDB;create=true");
            System.out.println("Database connection successful");
            
//            
////            String query = 'INSERT INTO users (name, email, password, role, cardNumber, money)';
//            
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * from users");
//            
//            System.out.println(rs);
//            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
   
