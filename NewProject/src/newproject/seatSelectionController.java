package newproject;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class seatSelectionController extends Thread implements Initializable, EventHandler<ActionEvent> {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Label info;
    Integer select_count = 0, allowed_seats = 4;
    ArrayList<String> selected_seats = new ArrayList<>();
    @FXML
    ListView<String> selected_seat_view = new ListView<>();
    @FXML
    Button  B1, B2, B3; //these are booked
    
    
    @FXML
    private ImageView backBtn;

    @FXML
    private Button confirmButton;

    @FXML
    private ImageView exitBtn;

    @FXML
    private ImageView homeBtn;

    @FXML
    protected void selectSeat(ActionEvent e) {
        if(((Node) e.getSource()).getStyle().equals("-fx-background-color:red;"))
        {
            //tell that seat is booked already and return
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "The seat " + ((Node) e.getSource()).getId() + " is already booked!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
                return;
            }
        }

        for (String selected_seat : selected_seats) {
            if(selected_seat.equals(((Node) e.getSource()).getId()))
            {
                selected_seats.remove(selected_seat);
                --select_count;
                selected_seat_view.getItems().clear();
                selected_seat_view.getItems().addAll(selected_seats);
                ((Node) e.getSource()).setStyle(null);

                return;
            }
        }
        select_count = selected_seats.size();
        if(select_count >= allowed_seats)
        {
            // show error and return
            Alert alert2 = new Alert(Alert.AlertType.WARNING,
                    "You cannot book more than " + allowed_seats + " seats!!", ButtonType.OK);
            alert2.showAndWait();
            if (alert2.getResult() == ButtonType.OK) {
                alert2.close();
            }
            return;
        }


        selected_seats.add(((Node) e.getSource()).getId());

//                ((Node) e.getSource()).getId();

//      clearing and updating the list view every time a button is pressed
        selected_seat_view.getItems().clear();
        selected_seat_view.getItems().addAll(selected_seats);

        ((Node) e.getSource()).setStyle("-fx-background-color:green; ");


    }

    // this is must, otherwise Action event will not work
    @Override
    public void handle(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        B1.setStyle("-fx-background-color:red;");
        B2.setStyle("-fx-background-color:red;");
    }
    
    @FXML
    void confirmButtonOnAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Maps.fxml"));
        root = loader.load();
        MapsController mapsController = loader.getController();
        mapsController.showLocation(company_Name,stationLocation,end_location,dept_time,total_price);
        
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    String company_Name;
    String stationLocation;
    String end_location;
    String dept_time;
    String total_price;
    public void saveLocation(String companyName,String startStation,String endStation,String deptTime,String price){
        //System.out.println(startStation);
        stationLocation = startStation;
        company_Name = companyName;
        end_location = endStation;
        dept_time= deptTime;
        total_price = price;
    }
    
     @FXML
    void backBtnOnAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChooseTransport.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    @FXML
    void exitBtnOnAction(MouseEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void homeBtnOnAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}