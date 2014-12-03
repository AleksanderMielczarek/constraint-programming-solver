package com.po.constraintprogrammingsolver;

import com.po.constraintprogrammingsolver.problems.KnapsackProblemService;
import com.po.constraintprogrammingsolver.problems.Problem;
import com.po.constraintprogrammingsolver.problems.TestProblemService;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2014-12-01.
 */
public class ConstraintProgrammingSolverController implements Initializable {
    @FXML
    private TextArea resultTextArea;
    @FXML
    private ProgressBar computationProgressBar;

    @FXML
    private Button startKnapsackButton;
    @FXML
    private Button startTestButton;

    private Map<Problem, Service<String>> problemWorkerMap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        problemWorkerMap = new EnumMap(Problem.class);

        problemWorkerMap.put(Problem.KNAPSACK, new KnapsackProblemService());
        problemWorkerMap.put(Problem.TEST, new TestProblemService());

        startKnapsackButton.setOnMouseClicked(event -> startService(Problem.KNAPSACK));
        startTestButton.setOnMouseClicked(event -> startService(Problem.TEST));
    }

    private void startService(Problem problem) {
        Service<String> service = problemWorkerMap.get(problem);

        resultTextArea.textProperty().bind(service.valueProperty());
        computationProgressBar.progressProperty().bind(service.progressProperty());

        service.restart();
    }
}
