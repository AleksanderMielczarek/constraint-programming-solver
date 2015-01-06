package com.po.constraintprogrammingsolver.problems.Trucks;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Janek on 2014-12-28.
 */
public class Package {
    private SimpleIntegerProperty ID;
    private SimpleIntegerProperty weight;

    public Package(int ID, int weight) {
        this.ID = new SimpleIntegerProperty(ID);
        this.weight = new SimpleIntegerProperty(weight);
    }

    public int getID() {
        return ID.get();
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public int getWeight() {
        return weight.get();
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }
}
