package User;
import java.time.LocalDate;

public class Booking {
    private int id;
    private int userId;
    private String bookingType;
    private LocalDate datebook;
    private String departure;

    public Booking(int userId, String bookingType, String departure) {
        this.userId = userId;
        this.bookingType = bookingType;
        this.departure = departure;
        this.datebook = LocalDate.now();
    }

    public int getId() { return id;}
    public void setId(int id) { this.id = id;}

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getBookingType() { return bookingType; }
    public void setBookingType(String bookingType) { this.bookingType = bookingType; }
    public void setDateBooked(LocalDate datebook) { this.datebook = datebook; }
    public LocalDate getDateBooked() { return datebook; }

    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }
}

