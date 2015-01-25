package com.po.constraintprogrammingsolver.problems.trucks;

import java.util.ArrayList;

/**
 * Created by Janek on 2014-12-22.
 */
public class TrucksProblemData {
    private ArrayList<Truck> trucksData;
    private ArrayList<Package> packagesData;
    private Others othersData;

    public TrucksProblemData() {
        trucksData = new ArrayList<>();
        packagesData = new ArrayList<>();
        othersData = new Others();
    }

    public ArrayList<Truck> getTrucksData() {
        return trucksData;
    }

    public void setTrucksData(ArrayList<Truck> trucksData) {
        this.trucksData = trucksData;
    }

    public ArrayList<Package> getPackagesData() {
        return packagesData;
    }

    public void setPackagesData(ArrayList<Package> packagesData) {
        this.packagesData = packagesData;
    }

    public Others getOthersData() {
        return othersData;
    }

    public void setOthersData(Others othersData) {
        this.othersData = othersData;
    }
}
