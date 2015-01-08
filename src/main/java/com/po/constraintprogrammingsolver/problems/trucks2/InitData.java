package com.po.constraintprogrammingsolver.problems.trucks2;

import java.util.ArrayList;

/**
 * Created by Janek on 2015-01-08.
 */
public class InitData {
    private ArrayList<Truck> startVehicles;
    private ArrayList<Package> startPackages;

    public InitData() {
        startVehicles = new ArrayList<>();
        startPackages = new ArrayList<>();
        insertInitVehicles();
        insertInitPackages();
    }

    private void insertInitVehicles() {
        startVehicles.add(new Truck(1, 10, 3));
        startVehicles.add(new Truck(2, 8, 5));
        startVehicles.add(new Truck(3, 13, 10));
        startVehicles.add(new Truck(4, 15, 12));
        startVehicles.add(new Truck(5, 9, 9));
        startVehicles.add(new Truck(6, 7, 4));
    }

    private void insertInitPackages() {
        startPackages.add(new Package(1, 3));
        startPackages.add(new Package(2, 5));
        startPackages.add(new Package(3, 10));
        startPackages.add(new Package(4, 6));
        startPackages.add(new Package(5, 6));
        startPackages.add(new Package(6, 9));
        startPackages.add(new Package(7, 2));
        startPackages.add(new Package(8, 1));
        startPackages.add(new Package(9, 4));
        startPackages.add(new Package(10, 3));
    }

    public ArrayList<Truck> getStartVehicles() {
        return startVehicles;
    }

    public ArrayList<Package> getStartPackages() {
        return startPackages;
    }
}
