package com.po.constraintprogrammingsolver;

import com.po.constraintprogrammingsolver.problems.Problem;
import com.po.constraintprogrammingsolver.problems.TestProblemService;
import com.po.constraintprogrammingsolver.problems.TrucksProblemService;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Aleksander on 2014-12-01.
 */
public class ConstraintProgrammingSolverController {
    @FXML
    private BorderPane solverBorderPane;
    @FXML
    private ProgressBar computationProgressBar;

    @FXML
    private Button startTrucksButton;
    @FXML
    private Button startJobShopButton;

    @FXML
    private TextArea resultTextArea;

    private Map<Problem, Service<String>> problemWorkerMap;

    private Stage stage;

    public ConstraintProgrammingSolverController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        problemWorkerMap = new EnumMap(Problem.class);

        problemWorkerMap.put(Problem.TRUCKS, new TrucksProblemService());
        problemWorkerMap.put(Problem.JOB_SHOP, new TestProblemService());

        ExecutorService executor = Executors.newSingleThreadExecutor();
        problemWorkerMap.values().forEach(service -> service.setExecutor(executor));
        problemWorkerMap.values().forEach(service -> service.setOnRunning(event -> computationProgressBar.progressProperty().bind(service.progressProperty())));
        problemWorkerMap.values().forEach(service -> service.setOnSucceeded(event -> resultTextArea.textProperty().bind(service.valueProperty())));

        startTrucksButton.setOnMouseClicked(event -> problemWorkerMap.get(Problem.TRUCKS).restart());
        stage.setOnCloseRequest(event -> executor.shutdown());

    }

}
