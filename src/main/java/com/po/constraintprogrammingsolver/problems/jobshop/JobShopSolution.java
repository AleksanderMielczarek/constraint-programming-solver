package com.po.constraintprogrammingsolver.problems.jobshop;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class JobShopSolution {
    private final JobShopData jobShopData;
    private final int cost;
    private final int backtracks;
    private final int decisions;
    private final int maximumDepth;
    private final int nodes;
    private final int wrongDecisions;
    private final long constraintTime;
    private final long solverTime;
    private final long totalTime;

    public JobShopSolution(JobShopData jobShopData, int cost, int backtracks, int decisions, int maximumDepth, int nodes, int wrongDecisions, long constraintTime, long solverTime) {
        this.jobShopData = jobShopData;
        this.cost = cost;
        this.backtracks = backtracks;
        this.decisions = decisions;
        this.maximumDepth = maximumDepth;
        this.nodes = nodes;
        this.wrongDecisions = wrongDecisions;
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

    public int getBacktracks() {
        return backtracks;
    }

    public int getDecisions() {
        return decisions;
    }

    public int getMaximumDepth() {
        return maximumDepth;
    }

    public int getNodes() {
        return nodes;
    }

    public int getWrongDecisions() {
        return wrongDecisions;
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
