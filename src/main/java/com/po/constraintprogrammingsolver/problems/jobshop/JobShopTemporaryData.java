package com.po.constraintprogrammingsolver.problems.jobshop;

import com.google.common.collect.Multimap;
import org.jacop.core.Store;

import java.util.List;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class JobShopTemporaryData {
    private final Store store;
    private final Multimap<Integer, TaskIntVarWrapper> taskJob;
    private final Multimap<Integer, TaskIntVarWrapper> taskMachine;
    private final List<Job> jobs;

    public JobShopTemporaryData(Store store, Multimap<Integer, TaskIntVarWrapper> taskJob, Multimap<Integer, TaskIntVarWrapper> taskMachine, List<Job> jobs) {
        this.store = store;
        this.taskJob = taskJob;
        this.taskMachine = taskMachine;
        this.jobs = jobs;
    }

    public Store getStore() {
        return store;
    }

    public Multimap<Integer, TaskIntVarWrapper> getTaskJob() {
        return taskJob;
    }

    public Multimap<Integer, TaskIntVarWrapper> getTaskMachine() {
        return taskMachine;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
