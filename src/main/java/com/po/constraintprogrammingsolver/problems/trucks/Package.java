package com.po.constraintprogrammingsolver.problems.trucks;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Contains package with own properties.
 */
public class Package {
    private SimpleIntegerProperty ID;
    private SimpleIntegerProperty weight;

    /**
     * Constructs package with ID and weight.
     * @param ID the ID value.
     * @param weight the package weight
     */
    public Package(int ID, int weight) {
        this.ID = new SimpleIntegerProperty(ID);
        this.weight = new SimpleIntegerProperty(weight);
    }

    /**
     * Returns ID value.
     * @return the ID value
     */
    public int getID() {
        return ID.get();
    }

    /**
     * Sets ID value.
     * @param ID the ID value
     */
    public void setID(int ID) {
        this.ID.set(ID);
    }

    /**
     * Returns package weight.
     * @return the pakages weight
     */
    public int getWeight() {
        return weight.get();
    }

    /**
     * Sets package weight.
     * @param weight the package weight
     */
    public void setWeight(int weight) {
        this.weight.set(weight);
    }
}
