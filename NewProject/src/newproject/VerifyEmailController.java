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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class VerifyEmailController implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField codeTextField;

    @FXML
    private Button resendBtn;

    @FXML
    private Label responseMessage;

    @FXML
    private Button submitBtn;
    
    public int code;
    public void saveCode(int x){
        code=x;
    }
    @FXML
    void cancelBtnOnAction(ActionEvent event) {

    }

    @FXML
    void resendBtnOnAction(ActionEvent event) {

    }

    boolean validateCode() {
        String cd = Integer.toString(code);
        return codeTextField.getText().equals(cd);
    }
    @FXML
    void submitBtnOnAction(ActionEvent event) throws IOException {
        System.out.println(code);
        if (validateCode()) {
            Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        } else {
            responseMessage.setTextFill(Color.web("#CF000F"));
            responseMessage.setText("Wrong Code. Please try again!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
