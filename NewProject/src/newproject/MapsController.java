package newproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MapsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField searchTextField;

    @FXML
    private WebView webView;

    @FXML
    private Button load;

    @FXML
    private Button loadBtn;

    @FXML
    private Button proceed_payment_btn;

    private WebEngine engine;

    private String startLocation;

    String company;
    String endLocation;
    String deptTime;
    String fare;

    void showLocation(String companyName, String start, String end, String dept, String price) {
        company = companyName;
        startLocation = start;
        endLocation = end;
        deptTime = dept;
        fare = price;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    @FXML
    void loadLocation(String url) {
        engine = webView.getEngine();
        engine.load(url);
    }

    @FXML
    void zoomIn(ActionEvent event) {
        webView.setZoom(1.25);
    }

    @FXML
    void zoomOut(ActionEvent event) {
        webView.setZoom(.75);
    }

    @FXML
    void loadButtonOnAction(ActionEvent event) {
        databaseCon dbConnection = new databaseCon();
        Connection conDBNow = dbConnection.getConnection();
        if (userInfo.vType.equals("train")) {
            String query = "SELECT url from mydb.train_station_location WHERE location = '" + startLocation + "' ";
            System.out.println(startLocation);
            try {
                Statement stmt = conDBNow.createStatement();
                ResultSet rset = stmt.executeQuery(query);
                while (rset.next()) {
                    String queryUrl = rset.getString("url");
                    System.out.println(queryUrl);
                    loadLocation(queryUrl);

                }
            } catch (SQLException ex) {

            }
        } else if (userInfo.vType.equals("bus")) {
            String query = "SELECT url from mydb.bus_station_location WHERE location = '" + startLocation + "' ";
            System.out.println(startLocation);
            try {
                Statement stmt = conDBNow.createStatement();
                ResultSet rset = stmt.executeQuery(query);
                while (rset.next()) {
                    String queryUrl = rset.getString("url");
                    System.out.println(queryUrl);
                    loadLocation(queryUrl);

                }
            } catch (SQLException ex) {

            }
        } else if (userInfo.vType.equals("airplane")) {
            String query = "SELECT url from mydb.airport_location WHERE location = '" + startLocation + "' ";
            System.out.println(startLocation);
            try {
                Statement stmt = conDBNow.createStatement();
                ResultSet rset = stmt.executeQuery(query);
                while (rset.next()) {
                    String queryUrl = rset.getString("url");
                    System.out.println(queryUrl);
                    loadLocation(queryUrl);

                }
            } catch (SQLException ex) {

            }
        }
    }

    @FXML
    void proceed_payment_ButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("payment.fxml"));
        root = loader.load();
        PaymentController paymentController = loader.getController();
        paymentController.saveInfo(company, startLocation, endLocation, deptTime, fare);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

}
