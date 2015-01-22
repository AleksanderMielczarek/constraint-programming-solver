package com.po.constraintprogrammingsolver.problems.jobshop;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class JobShopSolution {
    private final JobShopData jobShopData;
    private final int cost;
    private final long solverTime;
    private final long totalTime;

    public JobShopSolution(JobShopData jobShopData, int cost, long solverTime, long totalTime) {
        this.jobShopData = jobShopData;
        this.cost = cost;
        this.solverTime = solverTime;
        this.totalTime = totalTime;
    }

    public JobShopData getJobShopData() {
        return jobShopData;
    }

    public int getCost() {
        return cost;
    }

    public long getSolverTime() {
        return solverTime;
    }

    public long getTotalTime() {
        return totalTime;
    }
}
