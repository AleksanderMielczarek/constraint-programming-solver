package com.po.constraintprogrammingsolver.gui.trucks.truckdetailscontrollers;

import com.po.constraintprogrammingsolver.problems.trucks.Package;
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
 * @author Aleksander Mielczarek
 * @since 2014-12-28
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
    private Button deletePackageBtn;

    @FXML
    private TextField packageID;

    @FXML
    private TextField packageWeight;

    private ObservableList<Package> trucksData;
    private IntegerProperty truckIndexInTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trucksData = FXCollections.observableArrayList();
        truckIndexInTable = new SimpleIntegerProperty();
        adjustColumns();
        handlingAddPackageBtn();
        handlingDeletePackageBtn();
        packagesTable.setItems(trucksData);
    }

    @Override
    public ObservableList<Package> getData() {
        return trucksData;
    }

    @Override
    public void setData(ArrayList<Package> initData) {
        trucksData.addAll(initData);
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
                (TableColumn.CellEditEvent<Package, Integer> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setWeight(t.getNewValue()));
    }

    private void handlingDeletePackageBtn() {
        packagesTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Package>() {
            public void changed(ObservableValue<? extends Package> observable, Package oldValue, Package newValue) {
                setTruckIndexInTable(trucksData.indexOf(newValue));
            }
        });

        deletePackageBtn.setOnAction(event -> {
            trucksData.remove(getTruckIndexInTable());
            packagesTable.getSelectionModel().clearSelection();
        });
    }

    public int getTruckIndexInTable() {
        return truckIndexInTable.get();
    }

    public IntegerProperty truckIndexInTableProperty() {
        return truckIndexInTable;
    }

    void setTruckIndexInTable(int truckIndexInTable) {
        this.truckIndexInTable.set(truckIndexInTable);
    }

}
