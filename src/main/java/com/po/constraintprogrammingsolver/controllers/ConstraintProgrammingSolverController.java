package com.po.constraintprogrammingsolver.controllers;

import com.po.constraintprogrammingsolver.models.ControllerProvider;
import com.po.constraintprogrammingsolver.models.Problem;
import com.po.constraintprogrammingsolver.models.ServiceProvider;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
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
    private JobShopProblemController jobShopProblemController;

    @FXML
    private TrucksProblemController trucksProblemController;

    @FXML
    private ResourceBundle resources;

    private final Stage stage;

    private final ServiceProvider serviceProvider;
    private final ControllerProvider controllerProvider;
    private final ExecutorService executorService;

    public ConstraintProgrammingSolverController(Stage stage) {
        this.stage = stage;

        controllerProvider = new ControllerProvider();
        serviceProvider = new ServiceProvider();
        executorService = Executors.newSingleThreadExecutor();
    }

    @FXML
    public void initialize() {
        //register problem here
        controllerProvider.registerProblemController(Problem.JOB_SHOP, jobShopProblemController);

        //set properties
        controllerProvider.getControllers().values().stream()
                .forEach(controller -> {
                    controller.setTimeProperty(timeLabel.textProperty());
                    controller.setErrorProperty(errorLabel.textProperty());
                    controller.setProgressProperty(computationProgressBar.progressProperty());
                });

        //get services from controllers
        controllerProvider.getControllers().entrySet().stream()
                .forEach(entry -> serviceProvider.registerProblemService(entry.getKey(), entry.getValue().getProblemService()));
        serviceProvider.getProblems().values().stream()
                .forEach(service -> service.setExecutor(executorService));

        //bind selected tab with service provider
        problemsTabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                serviceProvider.problemProperty().bind(new SimpleObjectProperty<>(Problem.valueOfId(newValue.getId()))));
        serviceProvider.setProblem(Problem.valueOfId(problemsTabPane.getSelectionModel().getSelectedItem().getId()));

        stage.setOnCloseRequest(event -> executorService.shutdown());
    }

    @FXML
    private void startButtonOnMouseClicked() {
        if (serviceProvider.getProblem().equals(Problem.TRUCKS)) {
            trucksProblemController.handlingStartBtn();
            return;
        }
        serviceProvider.getProblemService().restart();
    }
}
