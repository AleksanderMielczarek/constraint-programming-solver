package com.po.constraintprogrammingsolver.problems.trucks;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Contains truck with own properties
 */
public class Truck {
    private SimpleIntegerProperty ID;
    private SimpleIntegerProperty loading;
    private SimpleIntegerProperty combustion;

    /**
     * Constructs truck with ID, loading and consumption.
     * @param ID the truck ID value
     * @param loading the truck loading value
     * @param combustion the truck consumption value
     */
    public Truck(Integer ID, Integer loading, Integer combustion) {
        this.ID = new SimpleIntegerProperty(ID);
        this.loading = new SimpleIntegerProperty(loading);
        this.combustion = new SimpleIntegerProperty(combustion);
    }

    /**
     * Returns truck ID value.
     * @return the truck ID value
     */
    public int getID() {
        return ID.get();
    }

    /**
     * Sets truck ID value.
     * @param ID the truck ID value
     */
    public void setID(int ID) {
        this.ID.set(ID);
    }

    /**
     * Returns truck loading.
     * @return the truck loading
     */
    public int getLoading() {
        return loading.get();
    }

    /**
     * Sets truck loading.
     * @param loading the truck loading
     */
    public void setLoading(int loading) {
        this.loading.set(loading);
    }

    /**
     * Returns truck consumption.
     * @return the truck consumption
     */
    public int getCombustion() {
        return combustion.get();
    }

    /**
     * Sets truck consumption.
     * @param combustion the truck consumption
     */
    public void setCombustion(int combustion) {
        this.combustion.set(combustion);
    }
}
