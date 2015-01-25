package com.po.constraintprogrammingsolver.gui.main.controller;

import com.po.constraintprogrammingsolver.Context;
import com.po.constraintprogrammingsolver.gui.jobshop.controller.JobShopProblemController;
import com.po.constraintprogrammingsolver.gui.main.model.ConstraintProgrammingSolverModel;
import com.po.constraintprogrammingsolver.gui.main.util.ControllerProvider;
import com.po.constraintprogrammingsolver.gui.main.util.Problem;
import com.po.constraintprogrammingsolver.gui.main.util.ServiceProvider;
import com.po.constraintprogrammingsolver.gui.trucks.TrucksProblemController;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

/**
 * Created by Aleksander on 2014-12-01.
 */
public class ConstraintProgrammingSolverController {
    @FXML
    private TabPane tabPaneProblems;

    @FXML
    private JobShopProblemController jobShopProblemController;

    @FXML
    private TrucksProblemController trucksProblemController;

    private final ConstraintProgrammingSolverModel model = new ConstraintProgrammingSolverModel();
    private final ServiceProvider serviceProvider = new ServiceProvider(model);
    private final ControllerProvider controllerProvider = new ControllerProvider();

    @FXML
    public void initialize() {
        //register problem here
        controllerProvider.registerProblemController(Problem.JOB_SHOP, jobShopProblemController);

        //get services from controllers and register
        controllerProvider.getControllers().entrySet().stream()
                .forEach(entry -> serviceProvider.registerProblemService(entry.getKey(), entry.getValue().getService()));

        //set selected tab in model
        tabPaneProblems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> model.setProblem(Problem.valueOfId(newValue.getId())));
        model.setProblem(Problem.valueOfId(tabPaneProblems.getSelectionModel().getSelectedItem().getId()));


        Context.INSTANCE.getStage().setOnCloseRequest(event -> Context.INSTANCE.getExecutor().shutdown());
    }

    @FXML
    private void startButtonOnMouseClicked() {
        if (model.getProblem().equals(Problem.TRUCKS)) {
            trucksProblemController.handlingStartBtn();
            return;
        }
        serviceProvider.getProblemService().restart();
    }
}
