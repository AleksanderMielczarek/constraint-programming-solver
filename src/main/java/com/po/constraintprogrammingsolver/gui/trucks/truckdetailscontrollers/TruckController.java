package com.po.constraintprogrammingsolver.gui.trucks.truckdetailscontrollers;

import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * @author Aleksander Mielczarek
 * @since 2014-12-28
 */
public interface TruckController<T> {
    public ObservableList<T> getData();
    public void setData(ArrayList<T> initData);
}
