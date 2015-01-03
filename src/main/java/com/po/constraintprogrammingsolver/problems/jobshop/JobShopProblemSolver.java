package com.po.constraintprogrammingsolver.problems.jobshop;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.po.constraintprogrammingsolver.problems.ProblemSolver;
import org.jacop.constraints.Constraint;
import org.jacop.constraints.Or;
import org.jacop.constraints.PrimitiveConstraint;
import org.jacop.constraints.XplusClteqZ;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.*;

import java.util.Optional;

/**
 * Created by Aleksander on 2014-12-03.
 */
public class JobShopProblemSolver implements ProblemSolver<JobShopData, Multimap<Integer, TaskIntVarWrapper>> {
    @Override
    public Optional<Multimap<Integer, TaskIntVarWrapper>> solveProblem(JobShopData data) {
        int maxTime = data.getJobs().stream()
                .mapToInt(job -> job.getStart() + job.getTasks().stream()
                        .mapToInt(Task::getTime)
                        .sum())
                .sum();

        Store store = new Store();

        Multimap<Integer, TaskIntVarWrapper> taskJob = ArrayListMultimap.create();
        Multimap<Integer, TaskIntVarWrapper> taskMachine = ArrayListMultimap.create();

        for (Job job : data.getJobs()) {
            for (Task task : job.getTasks()) {
                //Variable
                IntVar taskStartVar = new IntVar(store, job.getName() + task.getName(), job.getStart(), maxTime);

                //Constraints
                //Constraint on job
                if (task.getNumber() > 1) {
                    TaskIntVarWrapper previousTaskIntVarWrapper = Iterables.getLast(taskJob.get(job.getNumber()));
                    Constraint constraint = new XplusClteqZ(previousTaskIntVarWrapper.getIntVar(), previousTaskIntVarWrapper.getTask().getTime(), taskStartVar);
                    store.impose(constraint);
                }
                //Constraint on machine
                if (!taskMachine.get(task.getMachine()).isEmpty()) {
                    taskMachine.get(task.getMachine()).stream()
                            .forEach(taskIntVarWrapper -> {
                                PrimitiveConstraint constraint1 = new XplusClteqZ(taskStartVar, task.getTime(), taskIntVarWrapper.getIntVar());
                                PrimitiveConstraint constraint2 = new XplusClteqZ(taskIntVarWrapper.getIntVar(), taskIntVarWrapper.getTask().getTime(), taskStartVar);
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
        Indomain<IntVar> indomain = data.getIndomain();
        SelectChoicePoint<IntVar> select = new InputOrderSelect<>(store, intVars, indomain);

        search.setPrintInfo(false);
        boolean result;
        if (data.getCostFunction().isPresent()) {
            result = search.labeling(store, select, data.getCostFunction().get());
        } else {
            result = search.labeling(store, select);
        }

        if (result) {
            return Optional.of(taskJob);
        } else {
            return Optional.empty();
        }
    }
}
