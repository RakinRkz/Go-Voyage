package newproject;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

public class RegisterPageController implements Initializable {

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField fullNameTextField;

    @FXML
    private PasswordField passwordTextField;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registerButtonOnAction(ActionEvent event) {
        String fullName = fullNameTextField.getText();
        String email = emailTextField.getText();
        String password = DigestUtils.sha256Hex(passwordTextField.getText());
        
        databaseCon connectNow = new databaseCon();
        Connection conDB = connectNow.getConnection();
        
        try{
            PreparedStatement stmt = conDB.prepareStatement("insert into UserDetails(Full_Name, email, password) values(?,?,?);");
            
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, password);
            
            int status = stmt.executeUpdate();
        }
        catch (SQLException e) {
                System.out.println("Error while connecting to the database.Exception code: " + e);
        } 
    }
    
    @FXML
    void switchToLogin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
