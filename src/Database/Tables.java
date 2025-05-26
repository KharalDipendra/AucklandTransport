package Database;

import java.sql.SQLException;

/**
 * Utility class for managing application tables in the Derby database.
 */
public class Tables {
    // SQL definitions
    private static final String CREATE_USERS_TABLE =
        "CREATE TABLE USERS ("
      + "name VARCHAR(100) PRIMARY KEY, "
      + "email VARCHAR(150) UNIQUE NOT NULL, "
      + "password VARCHAR(100) NOT NULL, "
      + "memberType VARCHAR(10) DEFAULT 'Member', "
      + "cardNumber VARCHAR(20), "
      + "topUp DECIMAL(10,2)"
      + ")";

    private static final String CREATE_BOOKINGS_TABLE =
        "CREATE TABLE BOOKINGS ("
      + "bookingId INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
      + "name VARCHAR(100) NOT NULL, "
      + "email VARCHAR(150) NOT NULL, "
      + "dateBooked DATE NOT NULL, "
      + "departureDate DATE NOT NULL, "
      + "serviceType VARCHAR(10) NOT NULL, "
      + "FOREIGN KEY (name) REFERENCES USERS(name), "
      + "FOREIGN KEY (email) REFERENCES USERS(email)"
      + ")";

    private static final String CREATE_BOOKING_TYPE =
        "CREATE TABLE booking_type ("
      + "typeId INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
      + "typeName VARCHAR(20) NOT NULL"
      + ")";

    /**
     * Creates the USERS table in the database.
     * Drops existing table if present.
     */
    public static void createUsersTable(DBManager manager) throws SQLException {
        try {
            manager.updateDB("DROP TABLE USERS");
        } catch (SQLException e) {
            // Table doesn't exist
        }
        manager.updateDB(CREATE_USERS_TABLE);
    }
    
    /**
     * Creates the BOOKINGS table in the database.
     * Drops existing table if present.
     */
    public static void createBookingsTable(DBManager manager) throws SQLException {
        try {
            manager.updateDB("DROP TABLE BOOKINGS");
        } catch (SQLException e) {
            // Table doesn't exist
        }
        manager.updateDB(CREATE_BOOKINGS_TABLE);
    }

    /**
     * Creates the booking_type table in the database.
     * Drops existing table if present.
     */
    public static void createBookingTypeTable(DBManager manager) throws SQLException {
        try {
            manager.updateDB("DROP TABLE booking_type");
        } catch (SQLException e) {
            // Table doesn't exist
        }
        manager.updateDB(CREATE_BOOKING_TYPE);
    }

    /**
     * Drops all application tables, in order that satisfies FK constraints.
     */
    public static void dropTables(DBManager manager) throws SQLException {
        // Drop bookings first to avoid FK issues
        try {
            manager.updateDB("DROP TABLE BOOKINGS");
        } catch (SQLException e) {
            // ignore
        }
        try {
            manager.updateDB("DROP TABLE booking_type");
        } catch (SQLException e) {
            // ignore
        }
        try {
            manager.updateDB("DROP TABLE USERS");
        } catch (SQLException e) {
            // ignore
        }
    }

    /**
     * Initialize all tables: drop then recreate.
     */
    public static void initializeTables(DBManager manager) throws SQLException {
        dropTables(manager);
        createUsersTable(manager);
        createBookingsTable(manager);
        createBookingTypeTable(manager);
    }
}
