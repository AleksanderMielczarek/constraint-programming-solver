package com.po.constraintprogrammingsolver.controllers;

import com.po.constraintprogrammingsolver.problems.Problem;
import com.po.constraintprogrammingsolver.problems.JobShop.JobShopProblemService;
import com.po.constraintprogrammingsolver.problems.Trucks.TrucksProblemService;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
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
    private ProgressBar computationProgressBar;


    private Map<Problem, Service<String>> problemWorkerMap;

    private Stage stage;

    public ConstraintProgrammingSolverController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        problemWorkerMap = new EnumMap(Problem.class);

        problemWorkerMap.put(Problem.TRUCKS, new TrucksProblemService());
        problemWorkerMap.put(Problem.JOB_SHOP, new JobShopProblemService());

        ExecutorService executor = Executors.newSingleThreadExecutor();
        problemWorkerMap.values().forEach(service -> service.setExecutor(executor));
        problemWorkerMap.values().forEach(service -> service.setOnRunning(event -> computationProgressBar.progressProperty().bind(service.progressProperty())));
//        startTrucksButton.setOnMouseClicked(event -> problemWorkerMap.get(Problem.TRUCKS).restart());
        stage.setOnCloseRequest(event -> executor.shutdown());

    }

}
