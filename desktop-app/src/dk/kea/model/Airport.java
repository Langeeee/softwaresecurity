package dk.kea.model;

/**
 * Created by coag on 23-04-2018.
 */
public class Airport {
    private int id;
    private String airport;
    private String code;

    public Airport() {
    }

    public Airport(int id, String airport, String code) {
        this.id = id;
        this.airport = airport;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return airport + "(" + code + ")";
    }
}
