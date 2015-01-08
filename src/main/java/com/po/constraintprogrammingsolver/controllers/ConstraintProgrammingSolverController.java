package com.po.constraintprogrammingsolver.controllers;

import com.po.constraintprogrammingsolver.problems.ProblemService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    private JobShopProblemController jobShopProblemController;

    private final Stage stage;
//    private final ProblemService<String> jobShopProblemService;

    public ConstraintProgrammingSolverController(Stage stage) {
        this.stage = stage;
//        jobShopProblemService = new ProblemService<>(new JobShopProblemSolver());
    }

    @FXML
    public void initialize() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

//        jobShopProblemService.setOnSucceeded(event -> {
//            jobShopProblemController.getSolutionTextArea().textProperty().bind(jobShopProblemService.valueProperty().get().solutionProperty());
//            timeLabel.textProperty().bind(jobShopProblemService.valueProperty().get().timeProperty());
//        });
//        jobShopProblemService.setOnRunning(event -> computationProgressBar.progressProperty().bind(jobShopProblemService.progressProperty()));
//
//        jobShopProblemController.getStartJobShopButton().setOnMouseClicked(event -> jobShopProblemService.restart());

        stage.setOnCloseRequest(event -> executor.shutdown());
    }
}
