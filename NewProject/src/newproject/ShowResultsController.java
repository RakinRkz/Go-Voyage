package newproject;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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

public class ShowResultsController implements Initializable {

    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
    @FXML
    private ImageView backBtn;
    
    @FXML
    private ComboBox ChildCountBox;
    
    @FXML
    private ImageView exitBtn;
    
     @FXML
    private ImageView homeBtn;
    
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
    private ImageView MainLogo;

    @FXML
    private Button searchButton;
    
    @FXML
    private GridPane trainGrid;

     @FXML
    private ImageView setBtn;

    
    
    private final ObservableList<TrainInfo> trainInfoList = FXCollections.observableArrayList();
    private final ObservableList<String> RouteList = FXCollections.observableArrayList("Rajshahi","Dhaka","Chittagong","Rangpur");
    private final ObservableList<String> ChairList = FXCollections.observableArrayList("AC_B","AC_S","snigdha","S_CHAIR");
    private final ObservableList<String> busList = FXCollections.observableArrayList("ac","non-ac");
    
    private String vType;
    public void saveVehicleType(String type){
        vType = type;
    }
    public void addInfo(){
        fromBox.setItems(RouteList);
        toBox.setItems(RouteList);
        if(vType.equals("train")){
            classBox.setItems(ChairList);
        }
        else{
            classBox.setItems(busList);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //TODO
    }
    
    
    public void findButtonAction(ActionEvent e) throws IOException{
        
        List<TrainInfo> trains= new ArrayList<>();
        trains.clear();
        databaseCon dbConnection = new databaseCon();
        Connection conDBNow = dbConnection.getConnection();
      
        System.out.println(datePickerBox.getValue());
        
        String trainViewQuery = "SELECT company_name, start, end ,fare, class,type, time, total_seat,date FROM vehicle where start= '" + 
                fromBox.getValue().toString() + "' AND end= '"+ toBox.getValue().toString() +"' AND date = '"+ datePickerBox.getValue().toString() 
                +"'  AND type = '"+ vType+"' AND class ='"+ classBox.getValue().toString() +"'";
        
        try{
            Statement stmt= conDBNow.createStatement();
            ResultSet rset = stmt.executeQuery(trainViewQuery);
            
            
            
            while(rset.next()){
                
                String queryTrainID = rset.getString("company_name");
                String queryFrom = rset.getString("start");
                String qeuryTo = rset.getString("end");
                Integer queryPrice = rset.getInt("fare");
                String schedule = rset.getString("time"); 
                Integer total_seats = rset.getInt("total_seat");
                String queryClass = rset.getString("class");
                

                TrainInfo train = new TrainInfo();
                train.setFrom(queryFrom);
                train.setTo(qeuryTo);
                train.setTrainName(queryTrainID);
                train.setPrice(queryPrice);
                train.setCapacity(total_seats);
                train.setSchedule(schedule);
                train.setClass(queryClass);
                trains.add(train);
                
            }
            
            int columns= 0 ;
            int rows=1;
               
            for(int i=0;i<trains.size();i++){
                System.out.println("Data from database");
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("busViewThumb.fxml"));
                VBox vbox = fxmlloader.load();
                BusViewThumbController busViewThumbController = fxmlloader.getController();
                busViewThumbController.setData(trains.get(i));
                if (columns == 1) {
                    columns = 0;
                    ++rows;
                }
                
                trainGrid.add(vbox,columns++,rows);
                GridPane.setMargin(vbox, new Insets(6));
            }  
        }
        catch(SQLException event){
        }
    }
    @FXML
    void viewSeatBtnOnAction(ActionEvent event) throws IOException {
        
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
    void setBtnOnAction(MouseEvent event) {
        addInfo();
    }
}
