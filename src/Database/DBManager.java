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
 * DBManager class handles the core database operations and connection management.
 * It provides methods for establishing and managing database connections,
 * creating tables, and executing SQL statements.
 */
public class DBManager {
    /** Database connection object */
    private Connection conn;
    /** SQL statement object for executing queries */
    private Statement statement;

    /**
     * Constructor initializes the database connection
     */
    public DBManager() {
        establishConnection();
      //  createTable(); 
    }

    /**
     * Shuts down the Derby database engine
     */
    public static void Shutdown() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            System.out.println("Shutting down derby failed");
        }
    }

    /**
     * Gets the database connection, establishing it if necessary
     * @return Connection object for database operations
     */
    public Connection getConnection() {
        if (conn == null) {
            establishConnection();
        } 
        return conn;
    }
    
    /**
     * Establishes a connection to the Derby database
     * Creates the database if it doesn't exist
     */
    public final void establishConnection() {
        if ( conn != null) {
            return;
        }
        
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String dburl = "jdbc:derby:TransportDB;create=true";
            conn = DriverManager.getConnection(dburl);
            statement = conn.createStatement();
            System.out.println("Database Connection Successful");
        } catch (ClassNotFoundException ex) {
            System.out.println("Database driver not found " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Database connection error " + ex.getMessage());
        }
    }

    /**
     * Creates all necessary database tables
     * Creates booking types, users, and bookings tables
     * @throws SQLException if there is an error creating any of the tables
     */
    public void createTable() throws SQLException {
        if (conn == null || statement == null) {
            throw new SQLException("Database connection not initialized");
        }
        
        try {
            // Create tables in correct order due to foreign key dependencies
            Tables.createBookingTypeTable(this);
            insertDefaultBookingTypes();
            Tables.createUsersTable(this);
            Tables.createBookingsTable(this);
        } catch (SQLException e) {
            throw new SQLException("Error creating tables: " + e.getMessage(), e);
        }
    }
            
    /**
     * Executes an SQL update statement
     * @param sql The SQL statement to execute
     * @throws SQLException if there is an error executing the SQL statement
     */
    public void updateDB(String sql) throws SQLException {
        if (statement == null) {
            throw new SQLException("Statement is not initialized");
        }
        statement.executeUpdate(sql);
    }
    
    /**
     * Drops a table if it exists in the database
     * @param tableName Name of the table to drop
     */
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

    /**
     * Inserts default booking types (bus and train) into the booking_type table
     */
    public void insertDefaultBookingTypes() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO booking_type (type) VALUES ('bus')");
            stmt.executeUpdate("INSERT INTO booking_type (type) VALUES ('train')");
        } catch (SQLException e) {
            System.out.println("Might already exist: " + e.getMessage());
        }
    }
     
    /**
     * Closes the database connection and statement
     * Should be called when the database operations are complete
     */
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
