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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PlaneViewThumbController implements Initializable {

    @FXML
    private ImageView CompanyLogo;

    @FXML
    private Label CompanyTextField;

    @FXML
    private Text End;

    @FXML
    private Text Start;

    @FXML
    private Text TikcetPrice;

    @FXML
    private Button selectBtn;
    
    @FXML
    private Text deptTime;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void setData(Airplane plane){
        Image image = new Image(getClass().getResourceAsStream(plane.getLogo()));
        CompanyLogo.setImage(image);
        
        CompanyTextField.setText(plane.getCompany());
        Start.setText(plane.getStar());
        End.setText(plane.getLand());
        TikcetPrice.setText(plane.getPrice());
        deptTime.setText(plane.dept_time);
        userInfo.payment_price = plane.getPrice();
    }
    
    @FXML
    void selectBtnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Maps.fxml"));
        Parent root = loader.load();
        
        MapsController mapsController = loader.getController();
        mapsController.showLocation(CompanyTextField.getText(),Start.getText(),End.getText(),"9.15 AM",TikcetPrice.getText());
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    
}
