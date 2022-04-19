/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newproject;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BusViewThumbController implements Initializable {

     @FXML
    private Text DeptTime;

    @FXML
    private Text FromStation;

    @FXML
    private Text Price;

    @FXML
    private Text ToStation;

    @FXML
    private Text TrainClass;

    @FXML
    private Text TrainName;

    @FXML
    private Button ViewSeatsBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(TrainInfo train){
        TrainName.setText(train.getTrainName());
        TrainClass.setText(train.getChair());
        FromStation.setText(train.getFrom());
        ToStation.setText(train.getTo());
        DeptTime.setText(train.getSchedule());
        Price.setText(train.getPrice().toString());        
    }
    
    @FXML
    void viewSeatBtnOnAction(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("seatSelectionView.fxml"));
        Parent root = loader.load();
        
        seatSelectionController selectionController = loader.getController();
        selectionController.saveLocation(TrainName.getText(),FromStation.getText(),ToStation.getText(),DeptTime.getText(),Price.getText());
        
         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

}
