package com.po.constraintprogrammingsolver.controllers.truckdetailscontrollers2;

import com.po.constraintprogrammingsolver.problems.trucks2.Package;
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
 * Created by Janek on 2014-12-28.
 */
public class PackagesController implements Initializable, TruckController<Package> {
    @FXML
    private TableColumn<Package, Integer> packageIDCol;

    @FXML
    private TableColumn<Package, Integer> packageWeightCol;

    @FXML
    private TableView<Package> packagesTable;

    @FXML
    private Button addPackageBtn;

    @FXML
    private TextField packageID;

    @FXML
    private TextField packageWeight;

    private ObservableList<Package> trucksData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trucksData = FXCollections.observableArrayList();
        adjustColumns();
        handlingAddPackageBtn();
        packagesTable.setItems(trucksData);
    }

    @Override
    public ObservableList<Package> getData() {
        return trucksData;
    }

    private void adjustColumns() {
        adjustViewTrucksTableColumns();
        adjustPropertiesTrucksTableColumns();
    }

    private void handlingAddPackageBtn() {
        addPackageBtn.setOnAction((ActionEvent e) -> {
            trucksData.add(new Package(
                    Integer.parseInt(packageID.getText()),
                    Integer.parseInt(packageWeight.getText())
            ));
            packageID.clear();
            packageWeight.clear();
        });
    }

    private void adjustViewTrucksTableColumns() {
        packageIDCol.prefWidthProperty().bind(packagesTable.widthProperty().multiply(0.33));
        packageWeightCol.prefWidthProperty().bind(packagesTable.widthProperty().multiply(0.66));
    }

    private void adjustPropertiesTrucksTableColumns() {
        packageIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        packageWeightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        packageWeightCol.setCellFactory(TextFieldTableCell.<Package, Integer>forTableColumn(new IntegerStringConverter()));
        packageWeightCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Package, Integer> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setWeight(t.getNewValue());

                });
    }

}
