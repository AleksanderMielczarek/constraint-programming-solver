package com.po.constraintprogrammingsolver.problems.trucks;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains final, calculating, optimal solution.
 */
public class TrucksResult {
    private Map<Integer, Integer> mapPackageID;
    private Map<Integer, Integer> mapVehicleID;

    private ObservableMap<Integer, String> packagesLocations;
    private ObservableMap<Integer, Integer> capacities;

    private final SimpleStringProperty wholeCost;

    /**
     * Constructs class containing final, calculating, optimal solution.
     */
    public TrucksResult() {
        mapPackageID = new HashMap<>();
        mapVehicleID = new HashMap<>();
        packagesLocations = FXCollections.observableHashMap();
        capacities = FXCollections.observableHashMap();

        wholeCost = new SimpleStringProperty();
    }

    /**
     * Returns final cost property.
     * @return the final cost property
     */
    public SimpleStringProperty wholeCostProperty() {
        return wholeCost;
    }

    /**
     * Calculate and set final cost.
     * @param trucksProblemData the data contains all trucks, packages and parameters to solver
     * @param costWithSolver the resultant cost with solver
     */
    public void setWholeCost(TrucksProblemData trucksProblemData, double costWithSolver) {
        double finalCost;
        double distance = trucksProblemData.getOthersData().getDistanceValue();
        double costFuel = trucksProblemData.getOthersData().getCostFuel();

        finalCost = (costWithSolver * distance / 100.0)*costFuel;
        this.wholeCost.set(Double.toString(finalCost));
    }

    /**
     * Returns map with packages and theirs location.
     * @return the map with packages and theirs location
     */
    public ObservableMap<Integer, String> getPackagesLocations() {
        return packagesLocations;
    }

    /**
     * Sets final packages location.
     * @param packagesLocationsArray the array from solver containing packages location
     */
    public void setPackageLocations(int[] packagesLocationsArray) {
        Map<Integer, ArrayList<Integer>> tempMapPackagesLocations = new HashMap<>();
        for (int i = 0; i < packagesLocationsArray.length; i++) {
            if (tempMapPackagesLocations.containsKey(packagesLocationsArray[i])) {
                tempMapPackagesLocations.get(packagesLocationsArray[i]).add(i);
            } else {
                tempMapPackagesLocations.put(packagesLocationsArray[i], new ArrayList<>(Arrays.asList(new Integer[]{i})));
            }
        }
        tempMapPackagesLocations = changeIDInPackageLocations(tempMapPackagesLocations);
        packagesLocations = valuesSplitToString(tempMapPackagesLocations);
    }

    /**
     * Returns map with trucks and theirs capacity.
     * @return the map with trucks and theirs capacity
     */
    public ObservableMap<Integer, Integer> getCapacities() {
        return capacities;
    }

    /**
     * Sets final trucks capacity.
     * @param capacitiesArray the array from solver containing trucks capacity
     */
    public void setCapacities(int[] capacitiesArray) {
        Map<Integer, Integer> tempMapCapacities = new HashMap<>();
        for (int i=0; i<capacitiesArray.length; i++) {
            tempMapCapacities.put(i, capacitiesArray[i]);
        }
        capacities = changeIDInMapCapacities(tempMapCapacities);
    }

    /**
     * Sets map with vehicle numbers in solver and theirs ID value.
     * @param mapVehicleID the map with vehicles number in solver and its ID value
     */
    public void setMapVehicleID(Map<Integer, Integer> mapVehicleID) {
        this.mapVehicleID = mapVehicleID;
    }

    /**
     * Sets map with package numbers in solver and theirs ID value.
     * @param mapPackageID the map with package numbers in solver and theirs ID value
     */
    public void setMapPackageID(Map<Integer, Integer> mapPackageID) {
        this.mapPackageID = mapPackageID;
    }

    private Map<Integer, ArrayList<Integer>> changeIDInPackageLocations(Map<Integer, ArrayList<Integer>> tempMapPackagesLocations) {
        Map<Integer, ArrayList<Integer>> helpMap = new HashMap<>();

        tempMapPackagesLocations.entrySet().forEach(packageLoc -> {
            int newKey = mapVehicleID.get(packageLoc.getKey());
            ArrayList<Integer> newValues = new ArrayList<>();
            packageLoc.getValue().forEach(oldValue -> newValues.add(mapPackageID.get(oldValue)));
            helpMap.put(newKey, newValues);
        });

        return helpMap;
    }

    private ObservableMap<Integer, String> valuesSplitToString(Map<Integer, ArrayList<Integer>> tempMapPackagesLocations) {
        ObservableMap<Integer, String> packagesLocations = FXCollections.observableHashMap();
        tempMapPackagesLocations.keySet().forEach(key -> {
            StringBuilder stringBuilder = new StringBuilder();
            tempMapPackagesLocations.get(key).forEach(pack -> stringBuilder.append(pack.toString())
                         .append("; "));
            String packagesString = stringBuilder.toString();
            packagesLocations.put(key, packagesString);
        });
        return packagesLocations;
    }

    private ObservableMap<Integer, Integer> changeIDInMapCapacities(Map<Integer, Integer> tempMapCapacities) {
        ObservableMap<Integer, Integer> capacities = FXCollections.observableHashMap();

        tempMapCapacities.entrySet().forEach(capacity -> {
            int newKey = mapVehicleID.get(capacity.getKey());
            capacities.put(newKey, capacity.getValue());
        });

        return capacities;
    }
}
