package com.po.constraintprogrammingsolver.problems.trucks;

import java.util.ArrayList;

/**
 * Contains all data required to solve trucks problem.
 */
public class TrucksProblemData {
    private ArrayList<Truck> trucksData;
    private ArrayList<Package> packagesData;
    private Others othersData;

    /**
     * Constructs class with all data required to solve trucks problem.
     */
    public TrucksProblemData() {
        trucksData = new ArrayList<>();
        packagesData = new ArrayList<>();
        othersData = new Others();
    }

    /**
     * Returns all trucks introduced by user.
     * @return the list with all trucks
     */
    public ArrayList<Truck> getTrucksData() {
        return trucksData;
    }

    /**
     * Sets list of trucks.
     * @param trucksData list of trucks
     */
    public void setTrucksData(ArrayList<Truck> trucksData) {
        this.trucksData = trucksData;
    }

    /**
     * Returns all packages introduced by user.
     * @return the list with all packages
     */
    public ArrayList<Package> getPackagesData() {
        return packagesData;
    }

    /**
     * Sets list of packages.
     * @param packagesData the list of packages
     */
    public void setPackagesData(ArrayList<Package> packagesData) {
        this.packagesData = packagesData;
    }

    /**
     * Returns others solver parameters.
     * @return the others solver parameters
     */
    public Others getOthersData() {
        return othersData;
    }

    /**
     * Sets others solver parameters
     * @param othersData the others solver parameters
     */
    public void setOthersData(Others othersData) {
        this.othersData = othersData;
    }
}
