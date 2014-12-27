package com.po.constraintprogrammingsolver.controllers;

import com.po.constraintprogrammingsolver.problems.Trucks.Truck;
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
import java.util.ResourceBundle;

/**
 * Created by Janek on 2014-12-19.
 */

public class TrucksProblemController implements Initializable {

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
    private TextField TruckID;

    @FXML
    private TextField TruckLoading;

    @FXML
    private TextField TruckCombustion;

    private ObservableList<Truck> trucksData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adjustColumns();
        handlingAddTruckBtn();
        trucksData = FXCollections.observableArrayList();
        trucksTable.setItems(trucksData);

    }

    private void adjustColumns() {
        adjustViewTrucksTableColumns();
        adjustPropertiesTrucksTableColumns();
    }

    private void handlingAddTruckBtn() {
        addTruckBtn.setOnAction((ActionEvent e) -> {
            trucksData.add(new Truck(
                    Integer.parseInt(TruckID.getText()),
                    Integer.parseInt(TruckLoading.getText()),
                    Integer.parseInt(TruckCombustion.getText())
            ));
            TruckID.clear();
            TruckLoading.clear();
            TruckCombustion.clear();
        });
    }

    private void adjustViewTrucksTableColumns() {
        trucksIDCol.prefWidthProperty().bind(trucksTable.widthProperty().multiply(0.2));
        trucksLoadingCol.prefWidthProperty().bind(trucksTable.widthProperty().multiply(0.4));
        trucksCombustionCol.prefWidthProperty().bind(trucksTable.widthProperty().multiply(0.4));
    }

    private void adjustPropertiesTrucksTableColumns() {
        trucksIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        trucksLoadingCol.setCellValueFactory(new PropertyValueFactory<>("loading"));
        trucksLoadingCol.setCellFactory(TextFieldTableCell.<Truck, Integer>forTableColumn(new IntegerStringConverter()));
        trucksLoadingCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Truck, Integer> t) -> {
                    ((Truck) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setLoading(t.getNewValue());

                });
        trucksCombustionCol.setCellValueFactory(new PropertyValueFactory<>("combustion"));
        trucksCombustionCol.setCellFactory(TextFieldTableCell.<Truck, Integer>forTableColumn(new IntegerStringConverter()));
        trucksCombustionCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Truck, Integer> t) -> {
                    ((Truck) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setCombustion(t.getNewValue());

                });
    }


}

