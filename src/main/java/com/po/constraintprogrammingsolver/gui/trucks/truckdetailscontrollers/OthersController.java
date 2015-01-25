package com.po.constraintprogrammingsolver.gui.trucks.truckdetailscontrollers;

import com.po.constraintprogrammingsolver.problems.trucks.Others;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Janek on 2014-12-28.
 */
public class OthersController implements Initializable {
    @FXML
    private TextField distance;

    @FXML
    private TextField costFuel;

    @FXML
    private Button startBtn;

    private Others othersData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        othersData = new Others();
        handlingTextFields();
    }

    public Others getOthersData() {
        return othersData;
    }

    private void handlingTextFields() {
        distance.textProperty().bindBidirectional(othersData.distanceValueProperty(), new NumberStringConverter());
        costFuel.textProperty().bindBidirectional(othersData.costFuelProperty(), new NumberStringConverter());

//        distance.textProperty().addListener((observable, oldValue, newValue) -> {
//            othersData.setDistanceValue(Double.parseDouble(newValue));
//        });
//
//        costFuel.textProperty().addListener((observable, oldValue, newValue) -> {
//            othersData.setCostFuel(Double.parseDouble(newValue));
//        });
    }
}
