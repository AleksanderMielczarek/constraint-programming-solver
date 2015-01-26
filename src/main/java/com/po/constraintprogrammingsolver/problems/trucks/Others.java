package com.po.constraintprogrammingsolver.problems.trucks;

import javafx.beans.property.SimpleDoubleProperty;

/**
 * Containts solver parameters existing in "Others" window.
 */
public class Others {
    private SimpleDoubleProperty distanceValue;
    private SimpleDoubleProperty costFuel;

    /**
     * Constructs others data class with default parameters
     */
    public Others() {
        distanceValue = new SimpleDoubleProperty(50);
        costFuel = new SimpleDoubleProperty(5);
    }

    /**
     * Returns distance value between start and finish point.
     * @return the distance value between start and finish point
     */
    public Double getDistanceValue() {
        return distanceValue.get();
    }

    /**
     * Get distance property.
     * @return the distance property
     */
    public SimpleDoubleProperty distanceValueProperty() {
        return distanceValue;
    }

    /**
     * Get cost of fuel value.
     * @return the cost of fuel value
     */
    public Double getCostFuel() {
        return costFuel.get();
    }

    /**
     * Get cost of fuel property
     * @return the cost of fuel property
     */
    public SimpleDoubleProperty costFuelProperty() {
        return costFuel;
    }
}
