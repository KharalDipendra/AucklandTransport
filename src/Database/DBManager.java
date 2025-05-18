/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GGPC
 */
public class DBManager {
    private Connection conn;
    private Statement statement;

    public DBManager() {
        establishConnection();
        createTable(); // ← Optional

    }

    public Connection getConnection() {
        if (conn == null) {
            establishConnection();
        } 
        return conn;
    }
    
    public void establishConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String dburl = "jdbc:derby:TransportDB;create=true;";
            conn = DriverManager.getConnection(dburl);
            statement = conn.createStatement();
            System.out.println("Database Connection Successful");
        } catch (ClassNotFoundException ex) {
            System.out.println("Database driver not found " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Database connection error " + ex.getMessage());
        }
    }
    
    public void createTable() {
        Tables.createUsersTable(this);
        Tables.createBookingsTable(this);
    }
            
    public void updateDB(String sql) {

        if ( statement == null ) {
            System.out.println("Statement is not initialized");
            return;
        }

        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Update error " + ex.getMessage());
        }
    }
    
     public void dropTableifexist(String tableName) {
        try {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null,null, tableName.toUpperCase(),null);
            if ( tables.next()) {
                String dropSQL = "DROP TABLE " + tableName;
                System.out.println("Dropped Existing Table " + tableName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    
    public void closeConnection() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Error closing statement " + ex.getMessage());
            }
        }
            
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection " + ex.getMessage());
            }
        }
    }
}
