package com.po.constraintprogrammingsolver;

import com.po.constraintprogrammingsolver.component.ChartComponentController;
import com.po.constraintprogrammingsolver.component.TextAreaComponentController;
import com.po.constraintprogrammingsolver.problems.Problem;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2014-12-01.
 */
public class ConstraintProgrammingSolverController implements Initializable {
    @FXML
    private BorderPane solverBorderPane;
    @FXML
    private ProgressBar computationProgressBar;

    @FXML
    private Button startKnapsackButton;
    @FXML
    private Button startJobShopButton;

    @FXML
    private ChartComponentController chartComponentController;

    @FXML
    private TextAreaComponentController textAreaComponentController;

    private Map<Problem, Service<String>> problemWorkerMap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        problemWorkerMap = new EnumMap(Problem.class);
//
//        problemWorkerMap.put(Problem.KNAPSACK, new KnapsackProblemService());
//        problemWorkerMap.put(Problem.JOB_SHOP, new TestProblemService());
//
//        Executor executor = Executors.newSingleThreadExecutor();
//        problemWorkerMap.values().forEach(service -> service.setExecutor(executor));
//        problemWorkerMap.values().forEach(service -> service.setOnRunning(event -> computationProgressBar.progressProperty().bind(service.progressProperty())));
//        problemWorkerMap.values().forEach(service -> service.setOnSucceeded(event -> resultTextArea.textProperty().bind(service.valueProperty())));
//
//        startKnapsackButton.setOnMouseClicked(event -> problemWorkerMap.get(Problem.KNAPSACK).restart());
//        startTestButton.setOnMouseClicked(event -> problemWorkerMap.get(Problem.TEST).restart());

        startKnapsackButton.setOnMouseClicked(event -> solverBorderPane.setCenter(startJobShopButton));
        startJobShopButton.setOnMouseClicked(event -> solverBorderPane.setCenter(chartComponentController));
        textAreaComponentController.test();
    }

}
