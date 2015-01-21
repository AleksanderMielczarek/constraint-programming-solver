package com.po.constraintprogrammingsolver.problems.jobshop;

import java.util.List;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopData {
    private final List<Job> jobs;

    public JobShopData(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
