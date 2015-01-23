package com.po.constraintprogrammingsolver.problems.jobshop;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Multimap;
import com.po.constraintprogrammingsolver.problems.JacopStrategyProblemSolver;
import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import org.jacop.constraints.Constraint;
import org.jacop.constraints.Diff2;
import org.jacop.constraints.XgtY;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.DepthFirstSearch;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;

import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aleksander on 2014-12-03.
 */
public class JobShopProblemSolver implements JacopStrategyProblemSolver<JobShopData, JobShopSolution> {
    @Override
    public Optional<JobShopSolution> solveProblem(JobShopData data, JacopStrategyProvider jacopStrategyProvider) {
        Stopwatch constraintStopwatch = Stopwatch.createStarted();

        Store store = new Store();

        IntVar[] starts = createStartIntVariables(data, store);
        createPrimitiveConstraints(data.tasksOnJobs(), store);
        createPrimitiveConstraints(data.tasksOnMachines(), store);
        createGlobalConstraints(data, store, starts);

        Search<IntVar> search = new DepthFirstSearch<>();
        SelectChoicePoint<IntVar> select = jacopStrategyProvider.getSelectChoicePoint(starts, store);

        /*IntVar cost = new IntVar(store, data.minStartTime(), data.maxEndTime());
        Constraint max = new Max(starts, cost);
        max.impose(store);*/

        long constraintTime = constraintStopwatch.elapsed(TimeUnit.MILLISECONDS);

        Stopwatch solverStopwatch = Stopwatch.createStarted();
        search.setPrintInfo(false);

        boolean result = search.labeling(store, select);
        long solverTime = solverStopwatch.elapsed(TimeUnit.MILLISECONDS);

        if (result) {
            return Optional.of(new JobShopSolution(data, search.getCostValue(), constraintTime, solverTime));
        } else {
            return Optional.empty();
        }
    }

    private void createGlobalConstraints(JobShopData data, Store store, IntVar[] starts) {
        IntVar[] durations = data.tasks().stream()
                .map(Task::getDuration)
                .map(duration -> new IntVar(store, duration, duration))
                .toArray(IntVar[]::new);

        IntVar[] machines = data.tasks().stream()
                .map(Task::getMachineNumber)
                .map(machine -> new IntVar(store, machine, machine))
                .toArray(IntVar[]::new);

        IntVar[] machinesEnd = Collections.nCopies(machines.length, new IntVar(store, 0, 0)).toArray(new IntVar[machines.length]);

        //Constraint diff2 = new Diff2(starts, machines, durations, machinesEnd);
        Constraint diff2 = new Diff2(machines, starts, machinesEnd, durations);
        store.impose(diff2);
    }

    private static <T> void createPrimitiveConstraints(Multimap<T, Task> data, Store store) {
        data.asMap().values().stream()
                .filter(tasks -> tasks.size() > 1)
                .forEach(tasks -> {
                    Iterator<Task> iterator = tasks.iterator();
                    IntVar y = iterator.next().getStartTimeVar().get();
                    while (iterator.hasNext()) {
                        IntVar x = iterator.next().getStartTimeVar().get();
                        Constraint xgty = new XgtY(x, y);
                        store.impose(xgty);
                        y = x;
                    }
                });
    }

    private static IntVar[] createStartIntVariables(JobShopData data, Store store) {
        int maxTime = data.maxEndTime();

        data.tasks().forEach(task-> {
            IntVar var = new IntVar(store, task.getJob().get().getStartTime(), maxTime);
            task.setStartTimeVar(var);
        });

        IntVar[] starts = data.tasks().stream()
                .map(Task::getStartTimeVar)
                .toArray(IntVar[]::new);

        return starts;
    }
}
