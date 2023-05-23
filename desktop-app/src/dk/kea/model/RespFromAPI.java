package dk.kea.model;

/**
 * Created by coag on 23-04-2018.
 */
public class RespFromAPI {
    private int status;
    private String statusDescription;

    public RespFromAPI() {
    }

    @Override
    public String toString() {
        return "Status: {" +
                "status=" + status +
                ", statusDescription='" + statusDescription + '\'' +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public RespFromAPI(int status, String statusDescription) {

        this.status = status;
        this.statusDescription = statusDescription;
    }
}
