package newproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AdminUIController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView MainLogo;

    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView passwordLogo;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ImageView userLogo;

    @FXML
    private TextField usernameTextField;

    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginButtonOnAction(ActionEvent event) throws IOException {
        if (usernameTextField.getText().isBlank()) {
            loginMessageLabel.setText("Enter Username!");
        } else if (passwordTextField.getText().isBlank()) {
            loginMessageLabel.setText("Enter Password!");
        } else {
            if (ValidateLogin()) {
                userInfo.current_username = usernameTextField.getText();
                Parent root = FXMLLoader.load(getClass().getResource("AdminOptions.fxml"));
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

    public boolean ValidateLogin() {
        databaseCon connectNow = new databaseCon();
        Connection conDB = connectNow.getConnection();

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        
        AdminInfo.companyName = username;
//        String hashedPassword = DigestUtils.sha256Hex(password);

        String verifyLogin = "SELECT count(1) FROM mydb.company WHERE company_name = '" + username + "' AND PASSWORD= '" + password + "';";

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
