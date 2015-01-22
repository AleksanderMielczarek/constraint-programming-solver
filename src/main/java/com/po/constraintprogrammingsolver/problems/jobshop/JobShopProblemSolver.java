package com.po.constraintprogrammingsolver.problems.jobshop;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.po.constraintprogrammingsolver.problems.JacopStrategyProblemSolver;
import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import org.jacop.constraints.*;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.DepthFirstSearch;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Aleksander on 2014-12-03.
 */
public class JobShopProblemSolver implements JacopStrategyProblemSolver<JobShopData, JobShopSolution> {
    @Override
    public Optional<JobShopSolution> solveProblem(JobShopData data, JacopStrategyProvider jacopStrategyProvider) {
        int maxTime = data.maxEndTime();

        Store store = new Store();

        Multimap<Integer, TaskIntVarWrapper> taskJob = ArrayListMultimap.create();
        Multimap<Integer, TaskIntVarWrapper> taskMachine = ArrayListMultimap.create();

        data.getJobs().stream()
                .map(job-> job.)

        Constraint diff2 = new Diff2();
        for (Job job : data.getJobs()) {
            for (Task task : job.getTasks()) {
                //Variable
                IntVar taskStartVar = new IntVar(store, job.getStart(), maxTime);

                //Constraints
                //Constraint on job
                if (task.getNumber() > 1) {
                    TaskIntVarWrapper previousTaskIntVarWrapper = Iterables.getLast(taskJob.get(job.getNumber()));
                    Constraint constraint = new XplusClteqZ(previousTaskIntVarWrapper.getIntVar(), previousTaskIntVarWrapper.getTask().getDuration(), taskStartVar);
                    store.impose(constraint);
                }
                //Constraint on machine
                if (!taskMachine.get(task.getMachine()).isEmpty()) {
                    taskMachine.get(task.getMachine()).stream()
                            .forEach(taskIntVarWrapper -> {
                                PrimitiveConstraint constraint1 = new XplusClteqZ(taskStartVar, task.getDuration(), taskIntVarWrapper.getIntVar());
                                PrimitiveConstraint constraint2 = new XplusClteqZ(taskIntVarWrapper.getIntVar(), taskIntVarWrapper.getTask().getDuration(), taskStartVar);
                                Constraint or = new Or(constraint1, constraint2);
                                store.impose(or);
                            });
                }

                //complete maps
                TaskIntVarWrapper varWrapper = new TaskIntVarWrapper(task, taskStartVar);
                taskJob.put(job.getNumber(), varWrapper);
                taskMachine.put(task.getMachine(), varWrapper);
            }
        }

        IntVar[] intVars = taskJob.values().stream()
                .map(TaskIntVarWrapper::getIntVar)
                .toArray(IntVar[]::new);

        Search<IntVar> search = new DepthFirstSearch<>();
        SelectChoicePoint<IntVar> select = jacopStrategyProvider.getSelectChoicePoint(intVars, store);

        search.setPrintInfo(false);
        boolean result;
        result = search.labeling(store, select);

        if (result) {
            int cost = taskJob.asMap().entrySet().stream()
                    .map(Map.Entry::getValue)
                    .map(Iterables::getLast)
                    .map(wrapper -> wrapper.getTask().getDuration() + wrapper.getIntVar().value())
                    .max(Comparator.<Integer>naturalOrder()).get();

            return Optional.of(new JobShopSolution(taskJob, cost));
        } else {
            return Optional.empty();
        }
    }
}
