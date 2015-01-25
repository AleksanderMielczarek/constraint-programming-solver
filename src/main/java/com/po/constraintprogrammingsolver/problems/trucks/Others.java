package com.po.constraintprogrammingsolver.problems.trucks;

import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Janek on 2014-12-28.
 */
public class Others {
    private SimpleDoubleProperty distanceValue;
    private SimpleDoubleProperty costFuel;

    public Others() {
        distanceValue = new SimpleDoubleProperty(50);
        costFuel = new SimpleDoubleProperty(5);
    }

    public Double getDistanceValue() {
        return distanceValue.get();
    }

    public SimpleDoubleProperty distanceValueProperty() {
        return distanceValue;
    }

    public Double getCostFuel() {
        return costFuel.get();
    }

    public SimpleDoubleProperty costFuelProperty() {
        return costFuel;
    }
}
