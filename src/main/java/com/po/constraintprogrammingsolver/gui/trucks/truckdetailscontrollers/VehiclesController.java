package com.po.constraintprogrammingsolver.gui.trucks.truckdetailscontrollers;

import com.po.constraintprogrammingsolver.problems.trucks.Truck;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Janek on 2014-12-28.
 */
public class VehiclesController implements Initializable, TruckController<Truck> {
    @FXML
    private TableColumn<Truck, Integer> trucksIDCol;

    @FXML
    private TableColumn<Truck, Integer> trucksLoadingCol;

    @FXML
    private TableColumn<Truck, Integer> trucksCombustionCol;

    @FXML
    private TableView<Truck> trucksTable;

    @FXML
    private Button addTruckBtn;

    @FXML
    private Button deleteTruckBtn;

    @FXML
    private TextField truckID;

    @FXML
    private TextField truckLoading;

    @FXML
    private TextField truckCombustion;

    private ObservableList<Truck> trucksData;
    private IntegerProperty truckIndexInTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trucksData = FXCollections.observableArrayList();
        truckIndexInTable = new SimpleIntegerProperty();
        adjustColumns();
        handlingAddTruckBtn();
        handlingDeleteTruckBtn();
        trucksTable.setItems(trucksData);
    }

    @Override
    public ObservableList<Truck> getData() {
        return trucksData;
    }

    @Override
    public void setData(ArrayList<Truck> initData) {
        trucksData.addAll(initData);
    }

    private void adjustColumns() {
        adjustViewTrucksTableColumns();
        adjustPropertiesTrucksTableColumns();
    }

    private void handlingAddTruckBtn() {
        addTruckBtn.setOnAction((ActionEvent e) -> {
            trucksData.add(new Truck(
                    Integer.parseInt(truckID.getText()),
                    Integer.parseInt(truckLoading.getText()),
                    Integer.parseInt(truckCombustion.getText())
            ));
            truckID.clear();
            truckLoading.clear();
            truckCombustion.clear();
        });
    }

    private void adjustViewTrucksTableColumns() {
        trucksIDCol.prefWidthProperty().bind(trucksTable.widthProperty().multiply(0.2));
        trucksLoadingCol.prefWidthProperty().bind(trucksTable.widthProperty().multiply(0.39));
        trucksCombustionCol.prefWidthProperty().bind(trucksTable.widthProperty().multiply(0.4));
    }

    private void adjustPropertiesTrucksTableColumns() {
        trucksIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        trucksLoadingCol.setCellValueFactory(new PropertyValueFactory<>("loading"));
        trucksLoadingCol.setCellFactory(TextFieldTableCell.<Truck, Integer>forTableColumn(new IntegerStringConverter()));
        trucksLoadingCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Truck, Integer> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setLoading(t.getNewValue());
                });
        trucksCombustionCol.setCellValueFactory(new PropertyValueFactory<>("combustion"));
        trucksCombustionCol.setCellFactory(TextFieldTableCell.<Truck, Integer>forTableColumn(new IntegerStringConverter()));
        trucksCombustionCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Truck, Integer> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setCombustion(t.getNewValue());

                });
    }

    private void handlingDeleteTruckBtn() {
        trucksTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Truck>() {
            public void changed(ObservableValue<? extends Truck> observable, Truck oldvalue, Truck newValue) {
                setTruckIndexInTable(trucksData.indexOf(newValue));
            }
        });

        deleteTruckBtn.setOnAction(event -> {
            trucksData.remove(getTruckIndexInTable());
            trucksTable.getSelectionModel().clearSelection();
        });
    }

    public int getTruckIndexInTable() {
        return truckIndexInTable.get();
    }

    public void setTruckIndexInTable(int truckIndexInTable) {
        this.truckIndexInTable.set(truckIndexInTable);
    }

}
