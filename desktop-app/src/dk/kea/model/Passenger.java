package dk.kea.model;

/**
 * Created by coag on 23-04-2018.
 */
public class Passenger {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String travel_document_no;
    private String password;
    private String token;

    public Passenger() {
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", travel_document_no='" + travel_document_no + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTravel_document_no() {
        return travel_document_no;
    }

    public void setTravel_document_no(String travel_document_no) {
        this.travel_document_no = travel_document_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Passenger(int id, String name, String email, String phone, String travel_document_no, String password, String token) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.travel_document_no = travel_document_no;
        this.password = password;
        this.token = token;
    }
}
