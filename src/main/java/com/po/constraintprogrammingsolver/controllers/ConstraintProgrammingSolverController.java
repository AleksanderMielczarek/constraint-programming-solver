package com.po.constraintprogrammingsolver.controllers;

import com.po.constraintprogrammingsolver.models.ProblemService;
import com.po.constraintprogrammingsolver.models.ServiceProvider;
import com.po.constraintprogrammingsolver.problems.JobShop.JobShopProblemSolver;
import com.po.constraintprogrammingsolver.problems.Problem;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

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
    private TabPane problemsTabPane;

    @FXML
    private Button startButton;

    @FXML
    private JobShopProblemController jobShopController;

    private final Stage stage;
    private final ProblemService<String> jobShopService;
    private final ServiceProvider provider;

    public ConstraintProgrammingSolverController(Stage stage) {
        this.stage = stage;
        jobShopService = new ProblemService<>(new JobShopProblemSolver());
        provider = new ServiceProvider();
    }

    @FXML
    public void initialize() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        problemsTabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                provider.problemProperty().bind(new SimpleObjectProperty<>(Problem.valueOfId(newValue.getId()))));

        //job shop
        jobShopService.setOnSucceeded(event -> {
            //jobShopController.solutionProperty().bind(jobShopService.valueProperty().get().solutionProperty());
            timeLabel.textProperty().bind(jobShopService.valueProperty().get().timeProperty());
        });
        jobShopService.setOnRunning(event -> computationProgressBar.progressProperty().bind(jobShopService.progressProperty()));
        provider.registerService(Problem.JOB_SHOP, jobShopService);

        startButton.setOnMouseClicked(event -> provider.getService().restart());

        stage.setOnCloseRequest(event -> executor.shutdown());
    }
}
