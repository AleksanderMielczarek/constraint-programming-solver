package com.po.constraintprogrammingsolver.problems.jobshop;

import com.po.constraintprogrammingsolver.problems.jobshop.factories.JacopProvider;

import java.util.List;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopData {
    private final List<Job> jobs;
    private final JacopProvider jacopProvider;

    public JobShopData(List<Job> jobs, JacopProvider jacopProvider) {
        this.jobs = jobs;
        this.jacopProvider = jacopProvider;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public JacopProvider getJacopProvider() {
        return jacopProvider;
    }
}
