package com.po.constraintprogrammingsolver.controllers.truckdetailscontrollers2;

import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by Janek on 2014-12-28.
 */
public interface TruckController<T> {
    public ObservableList<T> getData();
    public void setData(ArrayList<T> initData);
}
