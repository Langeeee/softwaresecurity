package dk.kea.model;

/**
 * Created by coag on 23-04-2018.
 */
public class Flight {
    private int id;
    private String number;
    private int airport_from;
    private int airport_to;
    private String date_time_depart;
    private String date_time_arriv;
    private int airplane;
    private String gate;

    public Flight() {
    }

    @Override
    public String toString() {
        return number + " | Departure: "  +date_time_depart + " | ETA: " + date_time_arriv + " | Gate: " + gate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAirport_from() {
        return airport_from;
    }

    public void setAirport_from(int airport_from) {
        this.airport_from = airport_from;
    }

    public int getAirport_to() {
        return airport_to;
    }

    public void setAirport_to(int airport_to) {
        this.airport_to = airport_to;
    }

    public String getDate_time_depart() {
        return date_time_depart;
    }

    public void setDate_time_depart(String date_time_depart) {
        this.date_time_depart = date_time_depart;
    }

    public String getDate_time_arriv() {
        return date_time_arriv;
    }

    public void setDate_time_arriv(String date_time_arriv) {
        this.date_time_arriv = date_time_arriv;
    }

    public int getAirplane() {
        return airplane;
    }

    public void setAirplane(int airplane) {
        this.airplane = airplane;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public Flight(int id, String number, int airport_from, int airport_to, String date_time_depart, String date_time_arriv, int airplane, String gate) {
        this.id = id;
        this.number = number;
        this.airport_from = airport_from;
        this.airport_to = airport_to;
        this.date_time_depart = date_time_depart;
        this.date_time_arriv = date_time_arriv;
        this.airplane = airplane;
        this.gate = gate;

    }
}
