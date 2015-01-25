package com.po.constraintprogrammingsolver.problems.trucks;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Janek on 2014-12-21.
 */
public class Truck {
    private SimpleIntegerProperty ID;
    private SimpleIntegerProperty loading;
    private SimpleIntegerProperty combustion;

    public Truck(Integer ID, Integer loading, Integer combustion) {
        this.ID = new SimpleIntegerProperty(ID);
        this.loading = new SimpleIntegerProperty(loading);
        this.combustion = new SimpleIntegerProperty(combustion);
    }

    public int getID() {
        return ID.get();
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public int getLoading() {
        return loading.get();
    }

    public void setLoading(int loading) {
        this.loading.set(loading);
    }

    public int getCombustion() {
        return combustion.get();
    }

    public void setCombustion(int combustion) {
        this.combustion.set(combustion);
    }
}
