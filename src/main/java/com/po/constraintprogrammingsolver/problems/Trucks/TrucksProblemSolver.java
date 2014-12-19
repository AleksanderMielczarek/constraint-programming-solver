package com.po.constraintprogrammingsolver.problems.Trucks;

import com.po.constraintprogrammingsolver.problems.ProblemSolver;
import org.jacop.constraints.Constraint;
import org.jacop.constraints.Sum;
import org.jacop.constraints.XmulYeqZ;
import org.jacop.constraints.binpacking.Binpacking;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.*;

import java.util.Arrays;

/**
 * Created by Aleksander on 2014-12-03.
 */
public class TrucksProblemSolver implements ProblemSolver<String> {
    @Override
    public String solveProblem() {
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
