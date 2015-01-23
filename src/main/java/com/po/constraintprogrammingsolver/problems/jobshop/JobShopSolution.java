package com.po.constraintprogrammingsolver.problems.jobshop;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class JobShopSolution {
    private final JobShopData jobShopData;
    private final int cost;
    private final long constraintTime;
    private final long solverTime;
    private final long totalTime;

    public JobShopSolution(JobShopData jobShopData, int cost, long constraintTime, long solverTime) {
        this.jobShopData = jobShopData;
        this.cost = cost;
        this.constraintTime = constraintTime;
        this.solverTime = solverTime;
        this.totalTime = constraintTime + solverTime;
    }

    public JobShopData getJobShopData() {
        return jobShopData;
    }

    public int getCost() {
        return cost;
    }

    public long getConstraintTime() {
        return constraintTime;
    }

    public long getSolverTime() {
        return solverTime;
    }

    public long getTotalTime() {
        return totalTime;
    }
}
