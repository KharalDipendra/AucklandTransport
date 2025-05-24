import Database.DBManager;
import GUI.*;
import java.sql.SQLException;

/* 
* @author Pammi
*/
public class Main {
    
      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        new LaunchWindow().setVisible(true);
        initializeDatabase();

    }

    public static void initializeDatabase() throws SQLException {
        DBManager manager = new DBManager();
        manager.getConnection();
        manager.createTable(); // This will create all necessary tables
        System.out.println("Database initialized successfully!");
    }
}
   
