package newproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class TransportQueryController implements Initializable {

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField destinationTextField;

    @FXML
    private TextField startLocationTextField;

    @FXML
    private Button executeBtn;
    
    @FXML
    private TextField fareTextField;
    

    String action;

    public void saveAction(String ac) {
        action = ac;
    }

    @FXML
    void executeBtnOnAction(ActionEvent event) {
         if(action.equals("add")){
             String query = "insert into vehicle(vehicle,company_name,start,end) ";
         }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
