package com.po.constraintprogrammingsolver.problems.trucks;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Janek on 2015-01-02.
 */
public class TrucksResult {
    private Map<Integer, Integer> mapPackageID;
    private Map<Integer, Integer> mapVehicleID;

    private ObservableMap<Integer, String> packagesLocations;
    private ObservableMap<Integer, Integer> capacities;

    private SimpleStringProperty wholeCost;

    public TrucksResult() {
        mapPackageID = new HashMap<>();
        mapVehicleID = new HashMap<>();
        packagesLocations = FXCollections.observableHashMap();
        capacities = FXCollections.observableHashMap();

        wholeCost = new SimpleStringProperty();
    }

    public SimpleStringProperty wholeCostProperty() {
        return wholeCost;
    }

    public void setWholeCost(String wholeCost) {
        this.wholeCost.set(wholeCost);
    }

    public String getWholeCost() {
        return wholeCost.get();
    }

    public void setWholeCost(TrucksProblemData trucksProblemData, double costWithSolver) {
        double finalCost;
        double distance = trucksProblemData.getOthersData().getDistanceValue();
        double costFuel = trucksProblemData.getOthersData().getCostFuel();

        finalCost = ((double) costWithSolver * distance / 100.0)*costFuel;
        this.wholeCost.set(Double.toString(finalCost));
    }

    public ObservableMap<Integer, String> getPackagesLocations() {
        return packagesLocations;
    }

    public void setPackagesLocations(ObservableMap<Integer, String> packagesLocations) {
        this.packagesLocations = packagesLocations;
    }

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

    public ObservableMap<Integer, Integer> getCapacities() {
        return capacities;
    }

    public void setCapacities(int[] capacitiesArray) {
        Map<Integer, Integer> tempMapCapacities = new HashMap<>();
        for (int i=0; i<capacitiesArray.length; i++) {
            tempMapCapacities.put(i, capacitiesArray[i]);
        }
        capacities = changeIDInMapCapacities(tempMapCapacities);
    }

    public Map<Integer, Integer> getMapVehicleID() {
        return mapVehicleID;
    }

    public void setMapVehicleID(Map<Integer, Integer> mapVehicleID) {
        this.mapVehicleID = mapVehicleID;
    }

    public Map<Integer, Integer> getMapPackageID() {
        return mapPackageID;
    }

    public void setMapPackageID(Map<Integer, Integer> mapPackageID) {
        this.mapPackageID = mapPackageID;
    }

    private Map<Integer, ArrayList<Integer>> changeIDInPackageLocations(Map<Integer, ArrayList<Integer>> tempMapPackagesLocations) {
        Map<Integer, ArrayList<Integer>> helpMap = new HashMap<>();

        tempMapPackagesLocations.entrySet().forEach(packageLoc -> {
            int newKey = mapVehicleID.get(packageLoc.getKey());
            ArrayList<Integer> newValues = new ArrayList<Integer>();
            packageLoc.getValue().forEach(oldValue -> {
                newValues.add(mapPackageID.get(oldValue));
            });
            helpMap.put(newKey, newValues);
        });

        return helpMap;
    }

    private ObservableMap<Integer, String> valuesSplitToString(Map<Integer, ArrayList<Integer>> tempMapPackagesLocations) {
        ObservableMap<Integer, String> packagesLocations = FXCollections.observableHashMap();
        tempMapPackagesLocations.keySet().forEach(key -> {
            StringBuilder stringBuilder = new StringBuilder();
            tempMapPackagesLocations.get(key).forEach(pack -> {
                stringBuilder.append(pack.toString())
                             .append("; ")
                             .toString();
            });
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
