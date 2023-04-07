package newproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ChooseTransportController implements Initializable {

    @FXML
    private AnchorPane anchor;
    
    @FXML
    private Button busBtn;

    @FXML
    private Button planeBtn;

    @FXML
    private Button trainBtn;

    @FXML
    void busBtnOnAction(ActionEvent event) throws IOException {
        String type = "bus";
        userInfo.vType = "bus";
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowResults.fxml"));
        Parent root  = loader.load();
        
        ShowResultsController showResultsController = loader.getController();
        showResultsController.saveVehicleType(type);
        
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void planeBtnOnAction(ActionEvent event) throws IOException {
        userInfo.vType = "airplane";
        String type = "airplane";
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AirplaneList.fxml"));
        Parent root  = loader.load();
        
        AirplaneListController airplaneListController = loader.getController();
        airplaneListController.saveVehicleType(type);
        
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void trainBtnOnAction(ActionEvent event) throws IOException {
        String type = "train";
        userInfo.vType = "train";
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowResults.fxml"));
        Parent root  = loader.load();
        
        ShowResultsController showResultsController = loader.getController();
        showResultsController.saveVehicleType(type);
        
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
