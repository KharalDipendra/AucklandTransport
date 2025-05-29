package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBManager handles the core database connection and SQL execution.
 * Responsibilities:
 *  - Establish and manage a single Connection & Statement
 *  - Execute SQL updates (DDL/DML)
 *  - Cleanly close resources
 */
public class DBManager {
    private static final String JDBC_URL = "jdbc:derby:TransportDB;create=true";
    private Connection conn;
    private Statement stmt;
    
    /**
     * Initialize and open the database connection.
     */
    public DBManager() {
        establishConnection();
    }
    
    /**
     * Establishes the JDBC connection and creates a Statement.
     * Throws a RuntimeException on failure to avoid silent errors.
     */
    private void establishConnection() {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    return;
                }
            } catch (SQLException e) {
                // Connection is invalid, continue to establish new one
            }
        }
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(JDBC_URL);
            stmt = conn.createStatement();
            System.out.println("Database connected");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to initialize DB: " + e.getMessage(), e);
        }
    }
    
    /**
     * Returns the active Connection. Guaranteed non-null after construction.
     */
    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                establishConnection();
            }
        } catch (SQLException e) {
            establishConnection();
        }
        return conn;
    }
    
    /**
     * Executes a SQL update/DDL statement.
     * @param sql the SQL string (e.g. CREATE, INSERT, UPDATE, ALTER)
     */
    public void executeUpdate(String sql) {
        try {
            if (conn == null || conn.isClosed()) {
                establishConnection();
            }
            if (stmt == null || stmt.isClosed()) {
                stmt = conn.createStatement();
            }
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("SQL execution failed: " + sql, e);
        }
    }
    
    /**
     * Closes the Statement and Connection. Safe to call multiple times.
     */
    public void close() {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing Statement: " + e.getMessage());
            } finally {
                stmt = null;
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing Connection: " + e.getMessage());
            } finally {
                conn = null;
            }
        }
    }
}