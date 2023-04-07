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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class PurchasehistoryController implements Initializable {
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button backButton;
    
    @FXML
    private TableColumn<PaymentInfo, String> amountPaidColumn;


    @FXML
    private TableColumn<PaymentInfo, String> paymentDateColumn;

    @FXML
    public TableColumn<PaymentInfo, Integer> paymentIDColumn;

    @FXML
    private TableColumn<PaymentInfo, String> userIDColumn;
    
    
    @FXML
    private TableView<PaymentInfo> previousTableView;
    
    String userID;
    

    @FXML
    void backButtonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    
    private final ObservableList<PaymentInfo> paymentList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //TODO
        paymentList.clear();
        previousTableView.getItems().clear();
        databaseCon dbConnection = new databaseCon();
        Connection conDBNow = dbConnection.getConnection();
        
        System.out.println(userInfo.current_username);
        String paymentViewQuery = "SELECT amount_paid, payment_date FROM mydb.payment where user_name= '" + userInfo.current_username + "' ";
        
        try{
            Statement stmt= conDBNow.createStatement();
            ResultSet rset = stmt.executeQuery(paymentViewQuery);
            
            
            rset.next();
            while(rset.next()){
                
//                int queryPaymentID = rset.getInt("payment_Id");
//                String queryUserName = rset.getString("user_name"); 
                String queryAmountPaid = rset.getString("amount_paid");
                String queryPaymentDate =  rset.getString("payment_date");
//                System.out.println(queryPaymentID);
                
                paymentList.add(new PaymentInfo(101,queryAmountPaid, queryPaymentDate));
                
                
                paymentIDColumn.setCellValueFactory(new PropertyValueFactory<>("payment_Id"));
//                userIDColumn.setCellValueFactory(new PropertyValueFactory<>("user_name"));
                
                amountPaidColumn.setCellValueFactory(new PropertyValueFactory<>("amount_paid"));
                paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("payment_date"));
                
                previousTableView.setItems(paymentList);
            }
        }
        catch(SQLException ex){
        }
    }
    
    

}
