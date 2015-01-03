package com.po.constraintprogrammingsolver.problems.jobshop2;

import org.jacop.core.IntVar;

/**
 * Created by Aleksander on 2015-01-01.
 */
public class TaskIntVarWrapper {
    private final Task task;
    private final IntVar intVar;

    public TaskIntVarWrapper(Task task, IntVar intVar) {
        this.task = task;
        this.intVar = intVar;
    }

    public Task getTask() {
        return task;
    }

    public IntVar getIntVar() {
        return intVar;
    }
}
