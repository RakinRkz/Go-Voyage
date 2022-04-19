package newproject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Properties;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class TicketController implements Initializable {

    public userInfo current_user = new userInfo();

    @FXML
    private Text FromLocation;

    @FXML
    private Text ToLocation;

    @FXML
    private Text comapnyName;

    @FXML
    private Text price;

    @FXML
    private Text ticketNO;

    @FXML
    private Button goBackBtn;

    @FXML
    private Text time;

    @FXML
    void goBackBtnOnAction(ActionEvent event) throws IOException {

        saveToDatabase();
        takeScreenShot(userInfo.current_username + " ticket");
        sendMail();
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    static StringBuilder ticketNo;

    static String getAlphaNumericString() {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        ticketNo = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            ticketNo.append(AlphaNumericString
                    .charAt(index));
        }

        return ticketNo.toString();
    }

    public void showInfo(String c, String s, String e, String d, String p) {
        comapnyName.setText(c);
        FromLocation.setText(s);
        ToLocation.setText(e);
        time.setText(d);
        price.setText(p);
    }

    String payment_ID;
    String user_ID;
    String amount_paid;
    String payment_date;
    int counter=0;
    
    
    public void saveToDatabase() {

        String user = userInfo.current_username;
        String priceNow = price.getText();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        String date = now.toString();

        databaseCon connectNow = new databaseCon();
        Connection conDB = connectNow.getConnection();
        try {
//            System.out.println(userInfo.current_username+ price.getText());

            PreparedStatement stmt = conDB.prepareStatement("insert into mydb.payment(user_name,amount_paid,payment_date) values(?,?,?);");

//            System.out.println(stmt.toString());
            stmt.setString(1, user);
            stmt.setString(2, priceNow);
            stmt.setString(3, date);

            stmt.executeUpdate();

//            System.out.println(stmt.toString());
        } catch (SQLException ex) {
        }
    }
    @FXML
    private VBox ticketBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getAlphaNumericString();
        ticketNO.setText(ticketNo.toString());
        
    }

    void takeScreenShot(String fileName) throws IOException {
        counter++;
        WritableImage image = ticketBox.snapshot(new SnapshotParameters(), null);
        String address = "src\\images\\Tickets\\" + fileName + counter+ ".png";
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(address));
        
    }
    
    public void sendMail() throws IOException {
        
        String recepient = null;
        
        databaseCon connectNow = new databaseCon();
        Connection conDB = connectNow.getConnection();
        
        String getEmail = "SELECT email FROM mydb.user WHERE username = '" + userInfo.current_username + "' ;";
        
        try {
            Statement stmt = conDB.createStatement();
            ResultSet rset = stmt.executeQuery(getEmail);

            while (rset.next()) {
                recepient = rset.getString("email");
            }
        } catch (SQLException e) {
        }
        
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
            
            Multipart emailContent = new MimeMultipart();
            
            
            //Attaching text
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("\n\nThis is your ticket!!!");
            
            
            //Attaching ticket
            MimeBodyPart ticketAttachment = new MimeBodyPart();
            ticketAttachment.attachFile("C:\\Users\\MSI\\Documents\\NetBeansProjects\\NewProject\\src\\images\\Tickets\\"+ userInfo.current_username + " ticket"+ counter +".png");
            
            
            //Attach body parts
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(ticketAttachment);
            
            //Attach multipart to message
            message.setContent(emailContent);
            
            
            
            Transport.send(message);
        } catch (MessagingException ex) {
        }

        //Message message = prepareMessage(session, mailAccount, recepient);
        System.out.println("Message sent Successfully");

    }

}
