package dk.kea;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dk.kea.model.Airport;
import dk.kea.model.Flight;
import dk.kea.model.Passenger;
import dk.kea.model.RespFromAPI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by coag on 23-04-2018.
 */
public class FindFlightController {
    @FXML
    private TextField inputFrom;
    @FXML
    private TextField inputTo;
    @FXML
    private DatePicker inputDate;

    @FXML
    private VBox vBoxFlights;

    @FXML
    private Label labelStatus;

    private Airport selectedFrom;
    private Airport selectedTo;
    private ToggleGroup group;

    public void findFrom(KeyEvent keyEvent) {
        findAirports(inputFrom, true);
    }

    public void findTo(KeyEvent keyEvent) {
        findAirports(inputTo, false);
    }

    public void findFlights(ActionEvent actionEvent) {
        int fromAirportId = selectedFrom.getId();
        int toAirportId = selectedTo.getId();
        LocalDate date = inputDate.getValue();
        String dateString =
                date.getYear() + "-" +
                        (("" + date.getDayOfMonth()).length() == 1 ? "0" + date.getDayOfMonth() : date.getDayOfMonth()) + "-" +
                        (("" + date.getMonthValue()).length() == 1 ? "0" + date.getMonthValue() : date.getMonthValue());
        System.out.println("------FIND---");

        System.out.println("from: " + fromAirportId);
        System.out.println("to: " + toAirportId);
        System.out.println("date: " + dateString);

        try {
            URL url = new URL(MyUtil.API_URL +
                    "&action=get" +
                    "&items=flights" +
                    "&date=" + dateString +
                    "&from=" + fromAirportId +
                    "&to=" + toAirportId
            );
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream resp = con.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(resp);
            Scanner sc = new Scanner(resp);
            String data = sc.nextLine();

            //byte[] data = new byte[1024];
            //resp.read(data);

            System.out.println("-----Resp------ " + url);

            String respString = new String(data).trim();

            System.out.println(respString);

            JsonArray respJsonArray = new JsonParser().parse(respString.trim()).getAsJsonArray();

            System.out.println("---GSON---");
            System.out.println(respJsonArray.toString());

            Gson g = new Gson();
            JsonElement element0 = respJsonArray.get(0);
            RespFromAPI rfa = g.fromJson(element0, RespFromAPI.class);
            System.out.println(rfa);
            labelStatus.setText(rfa.toString());

            Iterator<JsonElement> it = respJsonArray.iterator();
            it.next(); // first element is the status code
            List<Flight> airports = new ArrayList<>();
            while (it.hasNext()) {
                JsonElement element = it.next();
                Flight flight = g.fromJson(element, Flight.class);
                airports.add(flight);
                System.out.println(flight);


            }
            if (!airports.isEmpty()) {
                group = new ToggleGroup();

                vBoxFlights.getChildren().remove(0);
                for (Flight f : airports) {
                    RadioButton rbtn = new RadioButton(f.toString());
                    rbtn.setToggleGroup(group);
                    rbtn.setUserData(f);
                    vBoxFlights.getChildren().add(rbtn);
                }

            }


            System.out.println(airports);


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void findAirports(TextField textFieldToSuggestCompletion, boolean isFrom) {
        String srcWord = textFieldToSuggestCompletion.getText();
        if (srcWord.length() >= 2 && srcWord.length() <= 8) {
            System.out.println(srcWord);

            try {
                URL url = new URL(MyUtil.API_URL +
                        "&action=get" +
                        "&items=airport" +
                        "&search=" + srcWord);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                InputStream resp = con.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(resp);
                Scanner sc = new Scanner(resp);
                String data = sc.nextLine();

                //byte[] data = new byte[1024];
                //resp.read(data);

                System.out.println("-----Resp------ " + url);

                String respString = new String(data).trim();

                System.out.println(respString);

                JsonArray respJsonArray = new JsonParser().parse(respString.trim()).getAsJsonArray();

                System.out.println("---GSON---");
                System.out.println(respJsonArray.toString());

                Gson g = new Gson();
                JsonElement element0 = respJsonArray.get(0);
                RespFromAPI rfa = g.fromJson(element0, RespFromAPI.class);
                System.out.println(rfa);
                labelStatus.setText(rfa.toString());

                Iterator<JsonElement> it = respJsonArray.iterator();
                it.next(); // first element is the status code
                List<Airport> airports = new ArrayList<Airport>();
                while (it.hasNext()) {
                    JsonElement element = it.next();
                    Airport airport = g.fromJson(element, Airport.class);
                    airports.add(airport);
                    System.out.println(airport);
                }

                AutoCompletionBinding<Airport> bind = TextFields.bindAutoCompletion(textFieldToSuggestCompletion, airports);
                bind.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<Airport>>() {
                    @Override
                    public void handle(AutoCompletionBinding.AutoCompletionEvent<Airport> event) {
                        if (isFrom) {
                            selectedFrom = event.getCompletion();
                        } else {
                            selectedTo = event.getCompletion();
                        }
                    }
                });


            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }

    }


    public void bookFlight(ActionEvent actionEvent) {
        Flight f = (Flight) group.getSelectedToggle().getUserData();
        System.out.println("---book select------");
        System.out.println(f);

        Passenger passenger = (Passenger) vBoxFlights.getScene().getUserData();

        try {
            URL url = new URL(MyUtil.API_URL +
                    "&action=book" +
                    "&passenger=" + passenger.getId() +
                    "&token=" + passenger.getToken() +
                    "&flight=" + f.getId()
            );
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream resp = con.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(resp);
            Scanner sc = new Scanner(resp);
            String data = sc.nextLine();

            //byte[] data = new byte[1024];
            //resp.read(data);

            System.out.println("-----Resp------ " + url);

            String respString = new String(data).trim();

            System.out.println(respString);

            JsonArray respJsonArray = new JsonParser().parse(respString.trim()).getAsJsonArray();

            System.out.println("---GSON---");
            System.out.println(respJsonArray.toString());

            Gson g = new Gson();
            JsonElement element0 = respJsonArray.get(0);
            RespFromAPI rfa = g.fromJson(element0, RespFromAPI.class);
            System.out.println(rfa);
            labelStatus.setText(rfa.toString());


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
