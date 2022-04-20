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
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */

public class PaymentController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button cancel_Btn;
    

    @FXML
    private Button confirm_btn;

    @FXML
    private Label priceLabel;
    

    @FXML
    void cancel_payment_onClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        root = loader.load();
        
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    String company;
    String startLocation;
    String end_location;
    String dept_time;
    String total_price;
    
    public void saveInfo(String c,String s,String e,String d,String p){
        company = c;
        startLocation = s;
        end_location = e;
        dept_time = d;
        total_price = p;
    }
    @FXML
    void confirm_payment_onClick(ActionEvent event) throws IOException {
        
        //Handle payment
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ticket.fxml"));
        root = loader.load();
        
        TicketController ticketController = loader.getController();
        ticketController.showInfo(company,startLocation,end_location,dept_time,total_price);
        
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(userInfo.payment_price);
        priceLabel.setText(userInfo.payment_price);
    }    
    
}
