package Database;

/**
 * Utility class for creating and initializing application tables.
 * Uses DBManager.executeUpdate(...) which wraps SQLExceptions in RuntimeExceptions.
 */
public final class Tables {
    private Tables() { /* static only */ }

    private static final String CREATE_USERS_TABLE =
        "CREATE TABLE USERS (" +
        "name VARCHAR(100) PRIMARY KEY, " +
        "email VARCHAR(150) UNIQUE NOT NULL, " +
        "password VARCHAR(100) NOT NULL, " +
        "memberType VARCHAR(10) DEFAULT 'Member', " +
        "cardNumber VARCHAR(20), " +
        "topUp DECIMAL(10,2), " +
        "memberSince DATE NOT NULL DEFAULT CURRENT_DATE, " +
        "discountType VARCHAR(20) DEFAULT 'Standard'" +
        ")";

    private static final String CREATE_BOOKING_TYPE_TABLE =
        "CREATE TABLE booking_type (" +
        "typeId INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
        "typeName VARCHAR(20) NOT NULL" +
        ")";

    private static final String CREATE_BOOKINGS_TABLE =
        "CREATE TABLE BOOKINGS (" +
        "bookingId INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
        "name VARCHAR(100) NOT NULL, " +
        "email VARCHAR(150) NOT NULL, " +
        "dateBooked DATE NOT NULL, " +
        "departureDate DATE NOT NULL, " +
        "serviceType VARCHAR(10) NOT NULL, " +
        "FOREIGN KEY (name) REFERENCES USERS(name), " +
        "FOREIGN KEY (email) REFERENCES USERS(email)" +
        ")";

    /**
     * Drops a table if it exists by name; ignores errors if absent.
     */
    private static void dropIfExists(DBManager manager, String tableName) {
        try {
            manager.executeUpdate("DROP TABLE " + tableName);
            System.out.println("Dropped existing table: " + tableName);
        } catch (RuntimeException e) {
            // ignore if table doesn't exist
        }
    }

    /**
     * Drops all application tables (in correct order) and recreates them.
     */
    public static void makeTable(DBManager manager) {
        // Drop order to satisfy FK constraints
        dropIfExists(manager, "BOOKINGS");
        dropIfExists(manager, "booking_type");
        dropIfExists(manager, "USERS");

        // Create tables
        manager.executeUpdate(CREATE_USERS_TABLE);
        manager.executeUpdate(CREATE_BOOKING_TYPE_TABLE);
        manager.executeUpdate(CREATE_BOOKINGS_TABLE);

        System.out.println("All tables initialized");
    }
}
