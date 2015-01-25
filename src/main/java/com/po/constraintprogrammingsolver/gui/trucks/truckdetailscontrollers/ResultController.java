package com.po.constraintprogrammingsolver.gui.trucks.truckdetailscontrollers;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Janek on 2015-01-02.
 */
public class ResultController implements Initializable {
    @FXML
    private TableView<ObservableMap.Entry<Integer, String>> packageLocationTable;

    @FXML
    private TableColumn<ObservableMap.Entry<Integer, String>, Integer> vehicleColLocation;

    @FXML
    private TableColumn<ObservableMap.Entry<Integer, String>, String> packagesCol;

    @FXML
    private TableView<ObservableMap.Entry<Integer, Integer>> vehiclesLoadTable;

    @FXML
    private TableColumn<ObservableMap.Entry<Integer, Integer>, Integer> vehicleColLoad;

    @FXML
    private TableColumn<ObservableMap.Entry<Integer, Integer>, Integer> loadCol;

    @FXML
    private Text costText;

    public Text getCostText() {
        return costText;
    }

    public void setCostText(Text costText) {
        this.costText = costText;
    }

    public TableView<ObservableMap.Entry<Integer, Integer>> getVehiclesLoadTable() {
        return vehiclesLoadTable;
    }

    public void setVehiclesLoadTable(TableView<ObservableMap.Entry<Integer, Integer>> vehiclesLoadTable) {
        this.vehiclesLoadTable = vehiclesLoadTable;
    }

    public TableColumn<ObservableMap.Entry<Integer, Integer>, Integer> getVehicleColLoad() {
        return vehicleColLoad;
    }

    public void setVehicleColLoad(TableColumn<ObservableMap.Entry<Integer, Integer>, Integer> vehicleColLoad) {
        this.vehicleColLoad = vehicleColLoad;
    }

    public TableColumn<ObservableMap.Entry<Integer, Integer>, Integer> getLoadCol() {
        return loadCol;
    }

    public void setLoadCol(TableColumn<ObservableMap.Entry<Integer, Integer>, Integer> loadCol) {
        this.loadCol = loadCol;
    }

    public TableView<ObservableMap.Entry<Integer, String>> getPackageLocationTable() {
        return packageLocationTable;
    }

    public void setPackageLocationTable(TableView<ObservableMap.Entry<Integer, String>> packageLocationTable) {
        this.packageLocationTable = packageLocationTable;
    }

    public TableColumn<ObservableMap.Entry<Integer, String>, Integer> getVehicleColLocation() {
        return vehicleColLocation;
    }

    public void setVehicleColLocation(TableColumn<ObservableMap.Entry<Integer, String>, Integer> vehicleColLocation) {
        this.vehicleColLocation = vehicleColLocation;
    }

    public TableColumn<ObservableMap.Entry<Integer, String>, String> getPackagesCol() {
        return packagesCol;
    }

    public void setPackagesCol(TableColumn<ObservableMap.Entry<Integer, String>, String> packagesCol) {
        this.packagesCol = packagesCol;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adjustColumns();
    }

    private void adjustColumns() {
        adjustTableLocation();
        adjustTableLoading();
    }

    private void adjustTableLocation() {
        vehicleColLocation.prefWidthProperty().bind(packageLocationTable.widthProperty().multiply(0.49));
        packagesCol.prefWidthProperty().bind(packageLocationTable.widthProperty().multiply(0.49));
    }

    private void adjustTableLoading() {
        vehicleColLoad.prefWidthProperty().bind(vehiclesLoadTable.widthProperty().multiply(0.49));
        loadCol.prefWidthProperty().bind(vehiclesLoadTable.widthProperty().multiply(0.49));
    }
}
