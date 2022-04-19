package newproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AirplaneListController implements Initializable {
    
    @FXML
    private ImageView backBtn;
    
    
    @FXML
    private ImageView homeBtn;
    
    @FXML
    private DatePicker DateSelect;

    @FXML
    private ComboBox FromSelect;

    @FXML
    private ComboBox ToSelect;

    @FXML
    private VBox dashboard;
    
    @FXML
    private Button findBtn;
    

    @FXML
    private GridPane planeGrid;
    
    private String vType;
    
    
    private final ObservableList<String> RouteList = FXCollections.observableArrayList("Rajshahi","Dhaka","Chittagong","Sylhet");
//    private final ObservableList<String> ChairList = FXCollections.observableArrayList("AC_B","AC_S","Singdha","S_CHAIR");
    
    public void saveVehicleType(String type){
        vType = type;
    }
    
    public void addInfo(){
        FromSelect.setItems(RouteList);
        ToSelect.setItems(RouteList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        planes = new ArrayList<>(planes());
        addInfo();
    }

    @FXML
    private ImageView exitBtn;

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
    
     @FXML
    void findBtnOnAction(ActionEvent event) {
        List<Airplane> planes = new ArrayList<>();

        databaseCon dbConnection = new databaseCon();
        Connection conDBNow = dbConnection.getConnection();
        
         System.out.println("Connection Successful");

        String planeViewQuery = "SELECT company_name, start, end ,fare, class, time , total_seat FROM vehicle where start= '"
                + FromSelect.getValue().toString() + "' AND end= '" + ToSelect.getValue().toString() + "' AND type = '"+ vType +"' "
                + "AND date = '"+DateSelect.getValue().toString() +"'";

        int columns = 0;
        int row = 1;

        try { 
            Statement stmt = conDBNow.createStatement();
            ResultSet rset = stmt.executeQuery(planeViewQuery);
            
            System.out.println("Executing query....");

            while (rset.next()) {
                String queryCompany = rset.getString("company_name");
                String queryStartTime = rset.getString("start");
                String queryEndTime = rset.getString("end");
                String queryPrice = rset.getString("fare");

                Airplane plane = new Airplane();
                plane.setCompany(queryCompany);
                switch (queryCompany) {
                    case "US-Bangla":
                        plane.setLogo("/images/us-bangla-airlines.png");
                        break;
                    case "Biman BD":
                        plane.setLogo("/images/BimanBangladesh.png");
                        break;
                    case "Novo Air":
                        plane.setLogo("/images/novoair.png");
                        break;
                    default:
                        break;
                }
                plane.setStarTime(queryStartTime);
                plane.setLandTime(queryEndTime);
                plane.setPrice(queryPrice);
                planes.add(plane);
            }
            
            System.out.println("Query successfully executed!");
            for (int i = 0; i < planes.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("planeViewThumb.fxml"));
                VBox vbox = fxmlloader.load();
                PlaneViewThumbController planeThumbController = fxmlloader.getController();
                planeThumbController.setData(planes.get(i));

                if (columns == 1) {
                    columns = 0;
                    ++row;
                }

                planeGrid.add(vbox, columns++, row);
                GridPane.setMargin(vbox, new Insets(10));
            }
            System.out.println("Successfully printed!");
        } catch (IOException e) {
        } catch (SQLException ex) {
            Logger.getLogger(AirplaneListController.class.getName()).log(Level.SEVERE, null, ex);
        }
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


}
