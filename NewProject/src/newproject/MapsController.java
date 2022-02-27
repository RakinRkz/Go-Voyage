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
    }
    String s = "Railgate Rajshahi";
    @FXML
    void loadPage() {
        engine.load("https://www.google.com/maps");
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
