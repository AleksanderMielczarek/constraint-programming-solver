package com.po.constraintprogrammingsolver.problems.Trucks;

import com.google.common.base.Stopwatch;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.jacop.constraints.Constraint;
import org.jacop.constraints.Sum;
import org.jacop.constraints.XmulYeqZ;
import org.jacop.constraints.binpacking.Binpacking;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aleksander on 2014-12-03.
 */
public class TrucksProblemService extends Service<String> {
    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                Stopwatch stopwatch = Stopwatch.createStarted();
                String result = solve();

                StringBuilder stringBuilder = new StringBuilder("Total time [ms]: ")
                        .append(stopwatch.elapsed(TimeUnit.MILLISECONDS))
                        .append(System.getProperty("line.separator"))
                        .append(result);
                updateProgress(1,1);
                return stringBuilder.toString();
            }
        };
    }

    private String solve() {
        Store store = new Store();
        int[] packagesWeight = {3, 4, 7, 5, 6, 4, 2, 5};
        IntVar[] packagesLocation = new IntVar[packagesWeight.length];

        int TrucksNr = 6;
        for (int i = 0; i < packagesWeight.length; i++) {
            packagesLocation[i] = new IntVar(store, "packagesLocation" + i, 0, TrucksNr - 1);
        }

        int maxCapacity = 10;
        IntVar capacities[] = new IntVar[TrucksNr];
        for (int i = 0; i < TrucksNr; i++) {
            capacities[i] = new IntVar(store, "capacities" + i, 0, maxCapacity);
        }

        Constraint binPacking = new Binpacking(packagesLocation, capacities, packagesWeight);
        store.impose(binPacking);
        boolean Result = store.consistency();

        //--COST FUNCTION

        IntVar[] capacitySquare = new IntVar[TrucksNr];
        for (int i = 0; i < TrucksNr; i++) {
            capacitySquare[i] = new IntVar(store, "z" + i, 0, maxCapacity * maxCapacity);
            store.impose(new XmulYeqZ(capacities[i], capacities[i], capacitySquare[i]));
        }

        IntVar cost = new IntVar(store, "costSum", 0, (maxCapacity * maxCapacity) * TrucksNr);
        store.impose(new Sum(capacitySquare, cost));

        //------------------

        Search<IntVar> label = new DepthFirstSearch<IntVar>();
        SelectChoicePoint<IntVar> select = new SimpleSelect<IntVar>(packagesLocation, null, new IndomainMin<IntVar>());
        label.setAssignSolution(true);
        label.setPrintInfo(true);
        Result = label.labeling(store, select, cost);
        StringBuilder stringBuilder = new StringBuilder();

        if (Result) {
            stringBuilder.append("---Resolved---")
                    .append(System.getProperty("line.separator"))
                    .append(Arrays.asList(packagesLocation))
                    .append(System.getProperty("line.separator"))
                    .append(Arrays.asList(capacities));
        } else
            stringBuilder.append("---Not resolved---");

        return stringBuilder.toString();
    }
}
