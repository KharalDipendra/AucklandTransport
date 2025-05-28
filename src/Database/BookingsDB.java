package Database;

import User.Booking;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * BookingsDB class handles all database operations related to transport bookings.
 */
public class BookingsDB {

    private final DBManager manager;

    public BookingsDB(DBManager manager) {
        this.manager = manager;
    }

    /**
     * Inserts a new booking and returns the generated bookingId.
     */
    public int addBooking(
            String name,
            String email,
            LocalDate dateBooked,
            LocalDate departureDate,
            String serviceType
    ) throws SQLException {
        String sql = "INSERT INTO BOOKINGS ("
                + "name, email, dateBooked, departureDate, serviceType) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql,
                new String[]{"BOOKINGID"}
        )) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setDate(3, Date.valueOf(dateBooked));
            ps.setDate(4, Date.valueOf(departureDate));
            ps.setString(5, serviceType);

            int count = ps.executeUpdate();
            if (count != 1) {
                throw new SQLException("Expected 1 row insert, got " + count);
            }

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys != null && keys.next()) {
                    return keys.getInt(1);
                } else {
                    throw new SQLException("No generated key returned");
                }
            }
        }
    }

    /**
     * Retrieves all bookings for a given user email.
     */
    public List<Booking> getBookingsForUser(String email) throws SQLException {
        String sql = "SELECT bookingId, name, email, dateBooked, departureDate, serviceType "
                + "FROM BOOKINGS WHERE email = ? ORDER BY departureDate";
        List<Booking> list = new ArrayList<>();
        try (Connection conn = manager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Booking b = new Booking();
                    b.setBookingId(rs.getInt("bookingId"));
                    b.setName(rs.getString("name"));
                    b.setEmail(rs.getString("email"));
                    b.setDateBooked(rs.getDate("dateBooked").toLocalDate());
                    b.setDepartureDate(rs.getDate("departureDate").toLocalDate());
                    b.setServiceType(rs.getString("serviceType"));
                    list.add(b);
                }
            }
        }
        return list;
    }

    // TODO: add updateBooking/removeBooking methods as needed
}
