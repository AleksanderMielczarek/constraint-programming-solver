package com.po.constraintprogrammingsolver.gui.trucks;

import com.po.constraintprogrammingsolver.gui.trucks.truckdetailscontrollers.OthersController;
import com.po.constraintprogrammingsolver.gui.trucks.truckdetailscontrollers.PackagesController;
import com.po.constraintprogrammingsolver.gui.trucks.truckdetailscontrollers.ResultController;
import com.po.constraintprogrammingsolver.gui.trucks.truckdetailscontrollers.VehiclesController;
import com.po.constraintprogrammingsolver.problems.trucks.InitData;
import com.po.constraintprogrammingsolver.problems.trucks.TrucksProblemData;
import com.po.constraintprogrammingsolver.problems.trucks.TrucksProblemSolver;
import com.po.constraintprogrammingsolver.problems.trucks.TrucksResult;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by Janek on 2014-12-19.
 */

public class TrucksProblemController {
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

    private TrucksResult trucksResult;

    @FXML
    public void initialize() {
        trucksResult = new TrucksResult();
        setInitData();
    }

    public void handlingStartBtn() {
        getDataFromControllers();
        TrucksProblemData trucksProblemData = getDataFromControllers();
        TrucksProblemSolver trucksProblemSolver = new TrucksProblemSolver();
        trucksResult = trucksProblemSolver.solveProblem(trucksProblemData).get();

        initResultPackageLocationsTable();
        initResultVehicleLoadTable();
        initCostText();

        dataAccordion.setExpanded(false);
        resultAccordion.setExpanded(true);
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

        packagesLocations.addListener((MapChangeListener.Change<? extends Integer, ? extends String> change) -> packageLocationTable.setItems(FXCollections.observableArrayList(packagesLocations.entrySet())));

        vehicleColLocation.setCellValueFactory((p) -> new ReadOnlyObjectWrapper<>(p.getValue().getKey()));

        packagesCol.setCellValueFactory((p) -> new ReadOnlyObjectWrapper<>(p.getValue().getValue()));
        packageLocationTable.setItems(FXCollections.observableArrayList(packagesLocations.entrySet()));
    }

    private void initResultVehicleLoadTable() {
        TableView<ObservableMap.Entry<Integer, Integer>> vehiclesLoadTable = resultController.getVehiclesLoadTable();
        ObservableMap<Integer, Integer> capacities = trucksResult.getCapacities();
        TableColumn<ObservableMap.Entry<Integer, Integer>, Integer> vehicleColLoad =
                resultController.getVehicleColLoad();
        TableColumn<ObservableMap.Entry<Integer, Integer>, Integer> loadCol =
                resultController.getLoadCol();

        capacities.addListener((MapChangeListener.Change<? extends Integer, ? extends Integer> change) -> vehiclesLoadTable.setItems(FXCollections.observableArrayList(capacities.entrySet())));

        vehicleColLoad.setCellValueFactory((p) -> new ReadOnlyObjectWrapper<>(p.getValue().getKey()));

        loadCol.setCellValueFactory((p) -> new ReadOnlyObjectWrapper<>(p.getValue().getValue()));
        vehiclesLoadTable.setItems(FXCollections.observableArrayList(capacities.entrySet()));
    }

    private void initCostText() {
        Text costText = resultController.getCostText();
        costText.textProperty().bind(trucksResult.wholeCostProperty());
    }

    private void setInitData() {
        InitData initData = new InitData();
        vehiclesController.setData(initData.getStartVehicles());
        packagesController.setData(initData.getStartPackages());
    }
}
