package com.po.constraintprogrammingsolver.problems.trucks;

import com.po.constraintprogrammingsolver.problems.ProblemSolver;
import org.apache.commons.lang3.ArrayUtils;
import org.jacop.constraints.*;
import org.jacop.constraints.binpacking.Binpacking;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Class solving trucks problem.
 */
public class TrucksProblemSolver implements ProblemSolver<TrucksProblemData, TrucksResult> {
    private int[] packagesWeight = {3, 5, 1, 2};
    private int[] trucksLoading = {10, 10, 10, 10};
    private int[] trucksCombustion = {10, 4, 6, 1};
    private TrucksResult trucksResult;
    private TrucksProblemData trucksProblemData;

    /**
     * Solves problem with data in trucksProblemData.
     * @param trucksProblemData the data contains all trucks, packages and parameters to solver
     * @return the result of calculating optimal solution
     */
    @Override
    public Optional<TrucksResult> solveProblem(TrucksProblemData trucksProblemData) {
        //constructor
        this.trucksProblemData = trucksProblemData;
        initPackagesWeight();
        initTrucksLoadingAndCombustion();
        trucksResult = new TrucksResult();
        setIDMap();


        Store store = new Store();

        IntVar[] packagesLocation = new IntVar[packagesWeight.length];

        int TrucksNr = trucksLoading.length;
        for (int i = 0; i < packagesWeight.length; i++) {
            packagesLocation[i] = new IntVar(store, "packagesLocation" + i, 0, TrucksNr - 1);
        }

        IntVar capacities[] = new IntVar[TrucksNr];
        for (int i = 0; i < TrucksNr; i++) {
            capacities[i] = new IntVar(store, "capacities" + i, 0, trucksLoading[i]);
        }

        Constraint binPacking = new Binpacking(packagesLocation, capacities, packagesWeight);
        store.impose(binPacking);
        boolean Result;

////        --COST FUNCTION

        IntVar truckWeight = new IntVar(store, "truckWeight", (int) Constants.WeightTruck, (int) Constants.WeightTruck);
        store.impose(new XeqC(truckWeight,(int) Constants.WeightTruck));

        IntVar[] allSingleCost = new IntVar[TrucksNr];
        IntVar[] newCapacities = new IntVar[TrucksNr];
        IntVar[] singleCost = new IntVar[TrucksNr];
        IntVar[] bracket = new IntVar[TrucksNr];
        IntVar[] divideEquation = new IntVar[TrucksNr];
        IntVar[] trucksCombustionSolver = new IntVar[TrucksNr];
        IntVar[] ifTruckUsed = new IntVar[TrucksNr];
        int multiplier = 1000;

        for (int i = 0; i < TrucksNr; i++) {
            newCapacities[i] = new IntVar(store, "newCapacities" + i, 0, multiplier*trucksLoading[i]);
            store.impose(new XmulCeqZ(capacities[i], multiplier, newCapacities[i]));

            divideEquation[i] = new IntVar(store, "divideEquation" + i, 0, multiplier*(int) Math.ceil(trucksLoading[i]/ Constants.WeightTruck));
            store.impose(new XdivYeqZ(newCapacities[i], truckWeight, divideEquation[i]));

            bracket[i] = new IntVar(store, "bracket" + i, multiplier, multiplier+(multiplier*(int) Math.ceil(trucksLoading[i]/ Constants.WeightTruck)));
            store.impose(new XplusCeqZ(divideEquation[i], multiplier, bracket[i]));

            trucksCombustionSolver[i] = new IntVar(store, "trucksCombustionSolver" + i, trucksCombustion[i], trucksCombustion[i]);
            store.impose(new XeqC(trucksCombustionSolver[i], trucksCombustion[i]));

            ifTruckUsed[i] = new IntVar(store, "ifTruckUsed" + i, 0, 1);
            store.impose( new IfThenElse(new XgtC(capacities[i], 0), new XeqC(ifTruckUsed[i], 1), new XeqC(ifTruckUsed[i], 0)));

            allSingleCost[i] = new IntVar(store, "allSingleCost" + i, trucksCombustion[i], trucksCombustion[i]*(multiplier+multiplier*(int) Math.ceil(trucksLoading[i]/ Constants.WeightTruck)));
            store.impose(new XmulYeqZ(trucksCombustionSolver[i], bracket[i], allSingleCost[i]));

            singleCost[i] = new IntVar(store, "singleCost" + i,0, trucksCombustion[i]*(multiplier+multiplier*(int) Math.ceil(trucksLoading[i]/ Constants.WeightTruck)));
            store.impose(new XmulYeqZ(allSingleCost[i], ifTruckUsed[i], singleCost[i]));

        }
        int[] maxSumOfCost = new int[TrucksNr];
        for (int i=0; i<TrucksNr; i++) {
            maxSumOfCost[i] = trucksCombustion[i]*(multiplier+multiplier*(int) Math.ceil(trucksLoading[i]/ Constants.WeightTruck));
        }
        IntVar cost = new IntVar(store, "costSum", 0, IntStream.of(maxSumOfCost).sum());
        store.impose(new Sum(singleCost, cost));

//        ------------------------------------------------------

        Search<IntVar> label = new DepthFirstSearch<>();
        SelectChoicePoint<IntVar> select = new SimpleSelect<>(packagesLocation, null, new IndomainMin<>());
        label.setAssignSolution(true);
        label.setPrintInfo(true);
        Result = label.labeling(store, select, cost);
//        StringBuilder stringBuilder = new StringBuilder();
//
//        if (Result) {
//            stringBuilder.append("---Resolved---")
//                    .append(System.getProperty("line.separator"))
//                    .append(Arrays.asList(packagesLocation))
//                    .append(System.getProperty("line.separator"))
//                    .append(Arrays.asList(capacities));
//        } else
//            stringBuilder.append("---Not resolved---");
//        System.out.println(stringBuilder.toString());
        if(Result) {
            trucksResult.setPackageLocations(changeIntVarToInt(packagesLocation));
            trucksResult.setCapacities(changeIntVarToInt(capacities));
            trucksResult.setWholeCost(trucksProblemData, (double) cost.value() / multiplier);
            return Optional.of(trucksResult);
        }

        return Optional.empty();
    }

    private void initPackagesWeight() {
        int packagesNr = trucksProblemData.getPackagesData().size();

        ArrayList<Integer> tempPackagesWeight = new ArrayList<>();
        trucksProblemData.getPackagesData().forEach(x -> tempPackagesWeight.add(x.getWeight()));
        packagesWeight = ArrayUtils.toPrimitive(tempPackagesWeight.toArray(new Integer[packagesNr]));
    }

    private void initTrucksLoadingAndCombustion() {
        int trucksNr = trucksProblemData.getTrucksData().size();

        ArrayList<Integer> tempTrucksLoading = new ArrayList<>();
        ArrayList<Integer> tempTrucksCombustion = new ArrayList<>();

        trucksProblemData.getTrucksData().forEach(x -> {
            tempTrucksLoading.add(x.getLoading());
            tempTrucksCombustion.add(x.getCombustion());
        });
        trucksCombustion = ArrayUtils.toPrimitive(tempTrucksCombustion.toArray(new Integer[trucksNr]));
        trucksLoading = ArrayUtils.toPrimitive(tempTrucksLoading.toArray(new Integer[trucksNr]));
    }

    private void setIDMap() {
        Map<Integer, Integer> mapPackagesID = new HashMap<>();
        ArrayList<Package> packages = trucksProblemData.getPackagesData();
        packages.forEach(x -> mapPackagesID.put(packages.indexOf(x), x.getID()));
        trucksResult.setMapPackageID(mapPackagesID);

        Map<Integer, Integer> mapVehicleID = new HashMap<>();
        ArrayList<Truck> trucks = trucksProblemData.getTrucksData();
        trucks.forEach(x -> mapVehicleID.put(trucks.indexOf(x), x.getID()));
        trucksResult.setMapVehicleID(mapVehicleID);
    }

    private int[] changeIntVarToInt(IntVar[] intVarArray) {
        int[] resultArray = new int[intVarArray.length];
        for (int i = 0; i < intVarArray.length; i++) {
            resultArray[i] = intVarArray[i].value();
        }
        return resultArray;
    }

}
