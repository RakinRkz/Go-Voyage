package com.example.sadwork1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

public class HelloController extends Thread implements Initializable, EventHandler<ActionEvent> {
    @FXML
    private Label info;
    Integer select_count = 0, allowed_seats = 4;
    ArrayList<String> selected_seats = new ArrayList<>();
    @FXML
    ListView<String> selected_seat_view = new ListView<>();
    @FXML
    Button  B1, B2, B3; //these are booked

    @FXML
    protected void selectSeat(ActionEvent e) {
        if(((Node) e.getSource()).getStyle().equals("-fx-background-color:red;"))
        {
            //tell that seat is booked already and return
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "The seat " + ((Node) e.getSource()).getId() + " is already booked!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
                return;
            }
        }

        for (String selected_seat : selected_seats) {
            if(selected_seat.equals(((Node) e.getSource()).getId()))
            {
                selected_seats.remove(selected_seat);
                --select_count;
                selected_seat_view.getItems().clear();
                selected_seat_view.getItems().addAll(selected_seats);
                ((Node) e.getSource()).setStyle(null);

                return;
            }
        }
        select_count = selected_seats.size();
        if(select_count >= allowed_seats)
        {
            // show error and return
            Alert alert2 = new Alert(Alert.AlertType.WARNING,
                    "You cannot book more than " + allowed_seats + " seats!!", ButtonType.OK);
            alert2.showAndWait();
            if (alert2.getResult() == ButtonType.OK) {
                alert2.close();
            }
            return;
        }


        selected_seats.add(((Node) e.getSource()).getId());

//                ((Node) e.getSource()).getId();

//      clearing and updating the list view every time a button is pressed
        selected_seat_view.getItems().clear();
        selected_seat_view.getItems().addAll(selected_seats);

        ((Node) e.getSource()).setStyle("-fx-background-color:green; ");


    }

    // this is must, otherwise Action event will not work
    @Override
    public void handle(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        B1.setStyle("-fx-background-color:red;");
        B2.setStyle("-fx-background-color:red;");
    }
}