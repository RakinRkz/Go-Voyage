package newproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class PlaneViewThumbController implements Initializable {

    @FXML
    private ImageView CompanyLogo;

    @FXML
    private Label CompanyTextField;

    @FXML
    private Text EndTime;

    @FXML
    private Text StartTime;

    @FXML
    private Text TikcetPrice;

    @FXML
    private Button selectBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void setData(Airplane plane){
        Image image = new Image(getClass().getResourceAsStream(plane.getLogo()));
        CompanyLogo.setImage(image);
        
        CompanyTextField.setText(plane.getCompany());
        StartTime.setText(plane.getStarTime());
        EndTime.setText(plane.getLandTime());
        TikcetPrice.setText(plane.getPrice());
        
    }
    
}
