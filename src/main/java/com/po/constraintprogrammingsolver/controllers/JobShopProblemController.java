package com.po.constraintprogrammingsolver.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * Created by Aleksander on 2014-12-19.
 */
public class JobShopProblemController {
    @FXML
    private Button startJobShopButton;
    @FXML
    private TextArea solutionTextArea;

    @FXML
    public void initialize() {

    }

    public Button getStartJobShopButton() {
        return startJobShopButton;
    }

    public TextArea getSolutionTextArea() {
        return solutionTextArea;
    }
}
