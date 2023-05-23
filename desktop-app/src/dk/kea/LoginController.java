package dk.kea;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dk.kea.model.Passenger;
import dk.kea.model.RespFromAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginController {

    @FXML
    private TextField inputEmail;
    @FXML
    private PasswordField inputPass;

    public void loginBtn(ActionEvent actionEvent) {
        String email = inputEmail.getText();
        String pass = inputPass.getText();

        System.out.println("email: " + email + "\npass: " + pass);

        try {
            URL url = new URL(MyUtil.API_URL +
                    "&action=login" +
                    "&email=" + email +
                    "&pass=" + pass);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream resp = con.getInputStream();
            byte[] data = new byte[1024];
            resp.read(data);

            System.out.println("-----Resp------");

            String respString = new String(data).trim();

            System.out.println(respString);

            JsonArray respJsonArray = new JsonParser().parse(respString.trim()).getAsJsonArray();

            System.out.println("---GSON---");
            System.out.println(respJsonArray.toString());

            Gson g = new Gson();
            JsonElement element0 = respJsonArray.get(0);
            RespFromAPI rfa = g.fromJson(element0, RespFromAPI.class);
            System.out.println(rfa);

            JsonElement element1 = respJsonArray.get(1);
            Passenger passenger = g.fromJson(element1, Passenger.class);
            System.out.println(passenger);

            //load new scene
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/find_flight.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("FlyBook :: Find Flight");
            Scene scene = new Scene(root1, 640,400);
            scene.setUserData(passenger);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
