package com.po.constraintprogrammingsolver.controllers;

import com.po.constraintprogrammingsolver.models.ControllerProvider;
import com.po.constraintprogrammingsolver.models.Problem;
import com.po.constraintprogrammingsolver.models.ServiceProvider;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Aleksander on 2014-12-01.
 */
public class ConstraintProgrammingSolverController {
    @FXML
    private ProgressBar computationProgressBar;
    @FXML
    private Label timeLabel;
    @FXML
    private Label errorLabel;

    @FXML
    private TabPane problemsTabPane;

    @FXML
    private Button startButton;

    @FXML
    private JobShopProblemController jobShopProblemController;

    @FXML
    private ResourceBundle resources;

    private final Stage stage;

    public ConstraintProgrammingSolverController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        //one executor for all tasks
        ExecutorService executor = Executors.newSingleThreadExecutor();

        //register problem here
        ControllerProvider controllerProvider = new ControllerProvider();
        controllerProvider.registerProblemController(Problem.JOB_SHOP, jobShopProblemController);

        //set properties
        controllerProvider.getControllers().values().stream()
                .forEach(controller -> {
                    controller.setTimeProperty(timeLabel.textProperty());
                    controller.setErrorProperty(errorLabel.textProperty());
                    controller.setProgressProperty(computationProgressBar.progressProperty());
                });

        //get services from controllers
        ServiceProvider serviceProvider = new ServiceProvider();
        controllerProvider.getControllers().entrySet().stream()
                .forEach(entry -> serviceProvider.registerProblemService(entry.getKey(), entry.getValue().getProblemService()));
        serviceProvider.getProblems().values().stream()
                .forEach(service -> service.setExecutor(executor));

        //bind selected tab with service provider
        problemsTabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                serviceProvider.problemProperty().bind(new SimpleObjectProperty<>(Problem.valueOfId(newValue.getId()))));
        serviceProvider.setProblem(Problem.valueOfId(problemsTabPane.getSelectionModel().getSelectedItem().getId()));

        startButton.setOnMouseClicked(event -> serviceProvider.getProblemService().restart());

        stage.setOnCloseRequest(event -> executor.shutdown());
    }
}
