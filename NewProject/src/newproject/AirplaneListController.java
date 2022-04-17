/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newproject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AirplaneListController implements Initializable {

    @FXML
    private DatePicker DateSelect;

    @FXML
    private ComboBox<?> FromSelect;

    @FXML
    private ComboBox<?> ToSelect;

    @FXML
    private GridPane planeGrid;

    private List<Airplane> planes() {
        List<Airplane> ls = new ArrayList<>();

        Airplane plane = new Airplane();
        plane.setLogo("/images/BimanBangladesh.png");
        plane.setCompany("Bangladesh Biman");
        plane.setStarTime("12.00");
        plane.setLandTime("13.35");
        plane.setPrice("4375 BDT");
        ls.add(plane);
        
        plane = new Airplane();
        plane.setLogo("/images/us-bangla-airlines.png");
        plane.setCompany("US-Bangla Airlines");
        plane.setStarTime("13.00");
        plane.setLandTime("14.35");
        plane.setPrice("5570 BDT");
        ls.add(plane);
        
        plane = new Airplane();
        plane.setLogo("/images/novoair.png");
        plane.setCompany("Fly Novo Air");
        plane.setStarTime("14.00");
        plane.setLandTime("14.45");
        plane.setPrice("5230 BDT");
        ls.add(plane);

        return ls;
    }

    private List<Airplane> planes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        planes = new ArrayList<>(planes());
        int columns = 0;
        int row = 1;

        try {
            for (int i = 0; i < planes.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("planeViewThumb.fxml"));
                VBox vbox = fxmlloader.load();
                PlaneViewThumbController planeThumbController = fxmlloader.getController();
                planeThumbController.setData(planes.get(i));

                if (columns == 1) {
                    columns = 0;
                    ++row;
                }

                planeGrid.add(vbox, columns++, row);
                GridPane.setMargin(vbox, new Insets(10));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
