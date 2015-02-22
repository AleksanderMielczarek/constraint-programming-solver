package com.po.constraintprogrammingsolver.problems.jobshop;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Iterables;
import com.po.constraintprogrammingsolver.problems.JacopStrategyProblemSolver;
import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import org.jacop.constraints.*;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.DepthFirstSearch;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Aleksander Mielczarek
 * @since 2014-12-03
 */
public class JobShopProblemSolver implements JacopStrategyProblemSolver<JobShopData, JobShopSolution> {
    @Override
    public Optional<JobShopSolution> solveProblem(JobShopData data, JacopStrategyProvider jacopStrategyProvider) {
        Store store = new Store();

        int totalEndTime = data.totalEndTime();
        int minEndTime = data.minEndTime();

        IntVar[] starts = createStartIntVariables(data, store, totalEndTime);
        createPrimitiveConstraintsOnJobs(data, store);
        createGlobalConstraints(data, store, starts);
        IntVar cost = createCostFunction(data, store, totalEndTime, minEndTime);

        Search<IntVar> search = new DepthFirstSearch<>();
        SelectChoicePoint<IntVar> select = jacopStrategyProvider.getSelectChoicePoint(starts, store);

        search.setPrintInfo(false);

        Stopwatch solverStopwatch = Stopwatch.createStarted();
        boolean result = search.labeling(store, select, cost);
        long solverTime = solverStopwatch.elapsed(TimeUnit.MILLISECONDS);

        if (result) {
            Map<Parameter, Integer> parameters = new EnumMap<>(Parameter.class);
            parameters.put(Parameter.BACKTRACKS, search.getBacktracks());
            parameters.put(Parameter.DECISIONS, search.getDecisions());
            parameters.put(Parameter.MAXIMUM_DEPTH, search.getMaximumDepth());
            parameters.put(Parameter.NODES, search.getNodes());
            parameters.put(Parameter.WRONG_DECISIONS, search.getWrongDecisions());

            return Optional.of(new JobShopSolution(data, search.getCostValue(), solverTime, parameters));
        }
        return Optional.empty();
    }

    private static IntVar createCostFunction(JobShopData data, Store store, int totalEndTime, int minEndTime) {
        IntVar cost = new IntVar(store, minEndTime, totalEndTime);
        IntVar[] ends = data.tasksOnJobs().asMap().values().stream()
                .map(Iterables::getLast)
                .map(task -> {
                    IntVar end = new IntVar(store, minEndTime, totalEndTime);
                    Constraint xPlusCeqZ = new XplusCeqZ(task.getStartTimeVar().get(), task.getDuration(), end);
                    store.impose(xPlusCeqZ);
                    return end;
                })
                .toArray(IntVar[]::new);

        Constraint max = new Max(ends, cost);
        max.impose(store);
        return cost;
    }

    private static void createGlobalConstraints(JobShopData data, Store store, IntVar[] starts) {
        IntVar[] durations = data.tasks().stream()
                .map(Task::getDuration)
                .map(duration -> new IntVar(store, duration, duration))
                .toArray(IntVar[]::new);

        IntVar[] machines = data.tasks().stream()
                .map(Task::getMachineNumber)
                .map(number -> number * 2)
                .map(machine -> new IntVar(store, machine, machine))
                .toArray(IntVar[]::new);

        IntVar[] machinesEnd = Collections.nCopies(machines.length, new IntVar(store, 1, 1)).toArray(new IntVar[machines.length]);

        Constraint diff2 = new Diff2(starts, machines, durations, machinesEnd);
        store.impose(diff2);
    }

    private static void createPrimitiveConstraintsOnJobs(JobShopData data, Store store) {
        data.tasksOnJobs().asMap().values().stream()
                .filter(tasks -> tasks.size() > 1)
                .forEach(tasks -> {
                    Iterator<Task> iterator = tasks.iterator();
                    Task previous = iterator.next();
                    while (iterator.hasNext()) {
                        Task actual = iterator.next();
                        Constraint xPlusClteqZ = new XplusClteqZ(previous.getStartTimeVar().get(), previous.getDuration(), actual.getStartTimeVar().get());
                        store.impose(xPlusClteqZ);
                        previous = actual;
                    }
                });
    }

    private static IntVar[] createStartIntVariables(JobShopData data, Store store, int totalEndTime) {
        data.tasks().forEach(task -> {
            IntVar var = new IntVar(store, task.getJob().get().getStartTime(), totalEndTime);
            task.setStartTimeVar(var);
        });

        return data.tasks().stream()
                .map(Task::getStartTimeVar)
                .map(Optional::get)
                .toArray(IntVar[]::new);
    }
}
