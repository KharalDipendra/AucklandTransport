import Database.DBManager;
import Database.Tables;
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
        DBManager manager = new DBManager();
        /** only run when you want to Delete the previous tables and start fresh (NOT NEEDED FOR NOW) - Dipendra
         * Tables.createUsersTable(manager);
         * Tables.createBookingsTable(manager);
         * Tables.createBookingTypeTable(manager);
         */
        new LaunchWindow().setVisible(true);
    }
}
   
