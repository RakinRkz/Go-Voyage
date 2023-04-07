package newproject;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.codec.digest.DigestUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class RegisterPageController implements Initializable {

    public int x = 0;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField fullNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private Label responseMsg;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    boolean validateData() {
        String usernameVerify = "SELECT count(1) FROM mydb.user WHERE username = '" + userNameTextField.getText() + "'";

        if (fullNameTextField.getText().isEmpty() || userNameTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            responseMsg.setTextFill(Color.web("#CF000F"));
            responseMsg.setText("Please enter all valid information!!!");
            return false;
        } else if (!validateMail(emailTextField.getText())) {
            responseMsg.setTextFill(Color.web("#CF000F"));
            responseMsg.setText("Please enter a valid email address");
            return false;
        }

        databaseCon connectNow = new databaseCon();
        Connection conDB = connectNow.getConnection();

        try {
            Statement stmt = conDB.createStatement();
            ResultSet rset = stmt.executeQuery(usernameVerify);

            while (rset.next()) {
                if (rset.getInt(1) == 0) {
                    responseMsg.setTextFill(Color.web("#26A65B"));
                    responseMsg.setText("Registration Successful!!" + " WELCOME ");
                    return true;
                } else {
                    responseMsg.setTextFill(Color.web("#CF000F"));
                    responseMsg.setText("Username already taken!!!");
                    return false;
                }
            }
        } catch (SQLException e) {
        }
        return false;
    }

    @FXML
    void registerButtonOnAction(ActionEvent event) throws MessagingException, IOException {
        String fullname = fullNameTextField.getText();
        String username = userNameTextField.getText();
        String email = emailTextField.getText();
        String password = DigestUtils.sha256Hex(passwordTextField.getText());

//        validateMail(email);
        if (validateData()) {
            databaseCon connectNow = new databaseCon();
            Connection conDB = connectNow.getConnection();

            try {
                PreparedStatement stmt = conDB.prepareStatement("insert into mydb.user(Fullname, username, Email, Password) values(?,?,?,?);");

                stmt.setString(1, fullname);
                stmt.setString(2, username);
                stmt.setString(3, email);
                stmt.setString(4, password);

                int status = stmt.executeUpdate();
                System.out.println(status);
            } catch (SQLException e) {
                System.out.println("Error while connecting to the database.Exception code: " + e);
            }

            Random random = new Random();

            x = random.nextInt(9999) + 1;
            System.out.println(x);
            sendMail(email, x);

            System.out.println(x);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("VerifyEmail.fxml"));
            root = loader.load();

            VerifyEmailController verifyEmailController = loader.getController();
            verifyEmailController.saveCode(x);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    public void sendMail(String recepient, int x) throws IOException {
        Properties properties = new Properties();

        properties.put("mail.debug", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String mailAccount = "sakifahbab74@gmail.com";
        String password = "gswsbkxtzksryaey";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(mailAccount, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Voyagers");
            message.setText(x + "\n\nInsert this code in the application");
            Transport.send(message);
        } catch (MessagingException ex) {
        }

        //Message message = prepareMessage(session, mailAccount, recepient);
        System.out.println("Message sent Successfully");

    }

    @FXML
    void switchToLogin(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("UI.fxml"));
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

    }

    boolean validateMail(String mail) {
        if (mail == null || mail.isEmpty()) {
            return false;
        }
        String emailRegeX = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegeX);

        return pat.matcher(mail).matches();
    }
}
