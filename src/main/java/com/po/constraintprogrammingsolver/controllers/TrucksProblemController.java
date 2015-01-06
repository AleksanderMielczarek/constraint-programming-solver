package com.po.constraintprogrammingsolver.controllers;

import com.po.constraintprogrammingsolver.controllers.TruckDetailsControllers.OthersController;
import com.po.constraintprogrammingsolver.controllers.TruckDetailsControllers.PackagesController;
import com.po.constraintprogrammingsolver.controllers.TruckDetailsControllers.ResultController;
import com.po.constraintprogrammingsolver.controllers.TruckDetailsControllers.VehiclesController;
import com.po.constraintprogrammingsolver.problems.Trucks.*;
import com.po.constraintprogrammingsolver.problems.Trucks.Package;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Janek on 2014-12-19.
 */

public class TrucksProblemController implements Initializable {
    @FXML
    private TitledPane dataAccordion;

    @FXML
    private TitledPane resultAccordion;

    @FXML
    private VehiclesController vehiclesController;

    @FXML
    private PackagesController packagesController;

    @FXML
    private OthersController othersController;

    @FXML
    private ResultController resultController;

    private Button startBtn;
    private TrucksResult trucksResult;


    public void initialize(URL location, ResourceBundle resources) {
        trucksResult = new TrucksResult();
        startBtn = othersController.getStartBtn();
        handlingStartBtn();
    }

    private void handlingStartBtn() {
        startBtn.setOnAction((ActionEvent e) -> {
            getDataFromControllers();
            TrucksProblemData trucksProblemData = getDataFromControllers();
            TrucksProblemSolver trucksProblemSolver = new TrucksProblemSolver(trucksProblemData);
            trucksResult = trucksProblemSolver.solveProblem();

            initResultPackageLocationsTable();
            initResultVehicleLoadTable();
            initCostText();

            dataAccordion.setExpanded(false);
            resultAccordion.setExpanded(true);
        });
    }

    private TrucksProblemData getDataFromControllers() {
        TrucksProblemData trucksProblemData = new TrucksProblemData();

        trucksProblemData.setTrucksData(new ArrayList<>(vehiclesController.getData()));
        trucksProblemData.setPackagesData(new ArrayList<>(packagesController.getData()));
        trucksProblemData.setOthersData(othersController.getOthersData());

        return trucksProblemData;
    }

    private void initResultPackageLocationsTable() {
        TableView<ObservableMap.Entry<Integer, String>> packageLocationTable = resultController.getPackageLocationTable();
        ObservableMap<Integer, String> packagesLocations = trucksResult.getPackagesLocations();
        TableColumn<ObservableMap.Entry<Integer, String>, Integer> vehicleColLocation =
               resultController.getVehicleColLocation();
        TableColumn<ObservableMap.Entry<Integer, String>, String> packagesCol =
                resultController.getPackagesCol();

        packagesLocations.addListener((MapChangeListener.Change<? extends Integer, ? extends String> change) -> {
            packageLocationTable.setItems(FXCollections.observableArrayList(packagesLocations.entrySet()));
        });

        vehicleColLocation.setCellValueFactory((p) -> {
            return new ReadOnlyObjectWrapper<>(p.getValue().getKey());
        });

        packagesCol.setCellValueFactory((p) -> {
            return new ReadOnlyObjectWrapper<>(p.getValue().getValue());
        });
        packageLocationTable.setItems(FXCollections.observableArrayList(packagesLocations.entrySet()));
    }

    private void initResultVehicleLoadTable() {
        TableView<ObservableMap.Entry<Integer, Integer>> vehiclesLoadTable = resultController.getVehiclesLoadTable();
        ObservableMap<Integer, Integer> capacities = trucksResult.getCapacities();
        TableColumn<ObservableMap.Entry<Integer, Integer>, Integer> vehicleColLoad =
                resultController.getVehicleColLoad();
        TableColumn<ObservableMap.Entry<Integer, Integer>, Integer> loadCol =
                resultController.getLoadCol();

        capacities.addListener((MapChangeListener.Change<? extends Integer, ? extends Integer> change) -> {
            vehiclesLoadTable.setItems(FXCollections.observableArrayList(capacities.entrySet()));
        });

        vehicleColLoad.setCellValueFactory((p) -> {
            return new ReadOnlyObjectWrapper<>(p.getValue().getKey());
        });

        loadCol.setCellValueFactory((p) -> {
            return new ReadOnlyObjectWrapper<>(p.getValue().getValue());
        });
        vehiclesLoadTable.setItems(FXCollections.observableArrayList(capacities.entrySet()));
    }

    private void initCostText() {
        Text costText = resultController.getCostText();
        costText.textProperty().bind(trucksResult.wholeCostProperty());
    }


}

