package newproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MapsController implements Initializable {

    @FXML
    private TextField searchTextField;

    @FXML
    private WebView webView;
    
    @FXML
    private Button load;
    
    private WebEngine engine;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        engine = webView.getEngine();
        loadPage();
//        url = this.getClass().getResource("map1.html");
//        engine.load(url.toString());
    }
//    String s = "Railgate Rajshahi";
    @FXML
    void loadPage() {
        engine.load("https://www.google.com/maps/dir/23.9489053,90.3790392/Gazipur+Chowrasta+Bus+Stand,+Dhaka+-+Mymensingh+Highway,+Gazipur/@23.9684188,90.3627113,14z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3755db7b214cf35b:0xe9268bbc62f8efa0!2m2!1d90.381628!2d23.9899118");
//        engine.load( getClass().getResource("/map1.html").toString() );
    }
    @FXML
    void zoomIn(ActionEvent event) {
        webView.setZoom(1.25);
    }

    @FXML
    void zoomOut(ActionEvent event) {
        webView.setZoom(.75);
    }    
    
}
