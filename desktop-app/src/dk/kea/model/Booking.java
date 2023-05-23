package dk.kea.model;

/**
 * Created by coag on 23-04-2018.
 */
public class Booking {
    private int id;
    private int passenger;
    private int flight;
    private String status;

    public Booking() {
    }

    public Booking(int id, int passenger, int flight, String status) {
        this.id = id;
        this.passenger = passenger;
        this.flight = flight;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    public int getFlight() {
        return flight;
    }

    public void setFlight(int flight) {
        this.flight = flight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", passenger=" + passenger +
                ", flight=" + flight +
                ", status='" + status + '\'' +
                '}';
    }
}
