package dk.kea.model;

/**
 * Created by coag on 23-04-2018.
 */
public class Airplane {
    private int id;
    private String model;
    private int seats;
    private String airline;

    public Airplane(int id, String model, int seats, String airline) {
        this.id = id;
        this.model = model;
        this.seats = seats;
        this.airline = airline;
    }

    public Airplane() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", seats=" + seats +
                ", airline='" + airline + '\'' +
                '}';
    }
}
