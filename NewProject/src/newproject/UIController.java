package newproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class UIController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView MainLogo;

    @FXML
    private ImageView passwordLogo;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Label loginMessageLabel;

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        if (usernameTextField.getText().isBlank()) {
            loginMessageLabel.setText("Enter Username!");
        } else if (passwordTextField.getText().isBlank()) {
            loginMessageLabel.setText("Enter Password!");
        } else {
            if (ValidateLogin()) {
                Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
            }
        }

    }

    @FXML
    private ImageView userLogo;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    void changeToRegister(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        //System.out.println("Hello World");
        this.MainLogo = new ImageView(new Image(getClass().getResourceAsStream("/images/ticket.png")));
        userLogo.setImage(new Image(getClass().getResourceAsStream("/images/user.png")));
        passwordLogo.setImage(new Image(getClass().getResourceAsStream("/images/login.png")));

    }

    public boolean ValidateLogin() {
        databaseCon connectNow = new databaseCon();
        Connection conDB = connectNow.getConnection();
        
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        String verifyLogin = "SELECT count(1) FROM new_table WHERE username = '" + username + "' AND PASSWORD= '" + password + "';";

        try {
            Statement stmt = conDB.createStatement();
            ResultSet rset = stmt.executeQuery(verifyLogin);

            while (rset.next()) {
                if (rset.getInt(1) == 1) {
                    loginMessageLabel.setTextFill(Color.web("#26A65B"));
                    loginMessageLabel.setText("Login Successful!!" + " WELCOME ");
                    return true;
                } else {
                    loginMessageLabel.setTextFill(Color.web("#CF000F"));
                    loginMessageLabel.setText("Wrong username or password!!");
                }
            }
        } catch (SQLException e) {
        }
        return false;
    }
}
