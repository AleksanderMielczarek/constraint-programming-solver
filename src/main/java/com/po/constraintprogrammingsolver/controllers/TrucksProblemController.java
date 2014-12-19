package com.po.constraintprogrammingsolver.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Janek on 2014-12-19.
 */

public class TrucksProblemController implements Initializable{
    @FXML
    private TitledPane accordionInTab;

    @FXML
    private Accordion accord;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        accord.setExpandedPane(accordionInTab);
    }
}

