package Database;
import User.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BookingsDB class handles all database operations related to transport bookings.
 * This includes creating new bookings, retrieving booking information,
 * and managing booking types.
 */
public class BookingsDB {
    /** Database manager instance for handling database operations */
    private final DBManager manager;

    /**
     * Constructor initializes the database manager
     * @param manager DBManager instance for database operations
     */
    public BookingsDB(DBManager manager) {
        this.manager = manager;
    }

    /**
     * Adds a new booking to the database
     * @param booking The Booking object containing booking information
     * @return true if the booking was successfully added, false otherwise
     */
    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO BOOKINGS (userid, booking_type_id, dateBooked, departure) VALUES (?, ?, ?, ?)";
        try (Connection conn = manager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Get booking type id
            int bookingTypeId = getBookingTypeId(booking.getBookingType());
            if (bookingTypeId == -1) return false;

            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, bookingTypeId);
            pstmt.setDate(3, Date.valueOf(booking.getDateBooked()));
            pstmt.setString(4, booking.getDeparture());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(BookingsDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Retrieves the ID of a booking type from the database
     * @param type The booking type (e.g., 'bus' or 'train')
     * @return The ID of the booking type, or -1 if not found
     */
    private int getBookingTypeId(String type) {
        String sql = "SELECT id FROM booking_type WHERE type = ?";
        try (Connection conn = manager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Retrieves all bookings from the database
     * @return List of all Booking objects in the database
     */
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.*, bt.type FROM BOOKINGS b JOIN booking_type bt ON b.booking_type_id = bt.id";

        try (Connection conn = manager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("userid"),
                    rs.getString("type"),
                    rs.getString("departure")
                );
                booking.setId(rs.getInt("id"));
                booking.setDateBooked(rs.getDate("dateBooked").toLocalDate());
                bookings.add(booking);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookings;
    }
}


