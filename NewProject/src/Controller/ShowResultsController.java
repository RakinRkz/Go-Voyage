package Controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import Utility.TrainInfo;
import Utility.databaseCon;

public class ShowResultsController implements Initializable {

    @FXML
    private ComboBox ChildCountBox;
    
    @FXML
    private ComboBox classBox;
    
    @FXML
    private DatePicker datePickerBox;

    @FXML
    private ComboBox fromBox;
    
    @FXML
    private ComboBox toBox;

    @FXML
    private ComboBox passengerCountBox;
    

    

    @FXML
    private TableView<TrainInfo> TrainTableView;
    
    @FXML
    private TableColumn<TrainInfo, String> FromColumn;

    @FXML
    private TableColumn<TrainInfo, String> ToColumn;

    @FXML
    private TableColumn<TrainInfo, Integer> capacityColumn;

    @FXML
    private TableColumn<TrainInfo, String> classColumn;

    @FXML
    private TableColumn<TrainInfo, String> dateColumn;
    @FXML
    private TableColumn<TrainInfo, String> stationColumn;
    
    @FXML
    private TableColumn<TrainInfo, Integer> trainIDColumn;

     @FXML
    private ImageView MainLogo;

    @FXML
    private Button searchButton;

    

    
    
    private final ObservableList<TrainInfo> trainInfoList = FXCollections.observableArrayList();
    private final ObservableList<String> RouteList = FXCollections.observableArrayList("Rajshahi","Dhaka","Chittagong","Rangpur");
    private final ObservableList<String> ChairList = FXCollections.observableArrayList("AC_B","AC_S","Singdha","S_CHAIR");
    
    
    
    public void addInfo(){
        fromBox.setItems(RouteList);
        toBox.setItems(RouteList);
        classBox.setItems(ChairList);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        addInfo();
        fromBox.getSelectionModel().select(0);
        
        //System.out.println("Hello World");
        TrainTableView.setPlaceholder(new Label(""));
        //this.MainLogo= new ImageView(new Image(getClass().getResourceAsStream("/images/ticket.png")));
        //System.out.println("Connection successful");
        
        
        
    }
    
    public void findButtonAction(ActionEvent e){
        databaseCon dbConnection = new databaseCon();
        Connection conDBNow = dbConnection.getConnection();
      
        
        
        
        String trainViewQuery = "SELECT TrainID, Start, End, Classes, Capacity, Time, Station FROM vehicle_info where Start= '" + fromBox.getValue().toString() + "' ";
        
        try{
            Statement stmt= conDBNow.createStatement();
            ResultSet rset = stmt.executeQuery(trainViewQuery);
            
            
            
            while(rset.next()){
                
                Integer queryTrainID = rset.getInt("TrainID");
                String queryFrom = rset.getString("Start");
                String qeuryTo = rset.getString("End");
                String queryClass = rset.getString("Classes");
                Integer queryCapactiy = rset.getInt("Capacity"); 
                String queryDate = rset.getString("Time");
                String queryStation =  rset.getString("Station");
                
                trainInfoList.add(new TrainInfo(queryTrainID,queryFrom,qeuryTo,queryClass,queryCapactiy,queryDate,queryStation));
                
            }
            
            trainIDColumn.setCellValueFactory(new PropertyValueFactory<>("TrainID"));
            FromColumn.setCellValueFactory(new PropertyValueFactory<>("From"));
            ToColumn.setCellValueFactory(new PropertyValueFactory<>("To"));
            classColumn.setCellValueFactory(new PropertyValueFactory<>("Chair"));
            capacityColumn.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
            stationColumn.setCellValueFactory(new PropertyValueFactory<>("Station"));
            
            
            
            TrainTableView.setItems(trainInfoList);
        }
        catch(SQLException event){
        }
    }
    
    
}
