package com.po.constraintprogrammingsolver.problems.jobshop;

import java.util.Map;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class JobShopSolution {
    private final JobShopData jobShopData;
    private final int cost;
    private final long time;
    private final Map<Parameter, Integer> parameters;

    public JobShopSolution(JobShopData jobShopData, int cost, long time, Map<Parameter, Integer> parameters) {
        this.jobShopData = jobShopData;
        this.cost = cost;
        this.time = time;
        this.parameters = parameters;
    }

    public JobShopData getJobShopData() {
        return jobShopData;
    }

    public int getCost() {
        return cost;
    }

    public long getTime() {
        return time;
    }

    public Map<Parameter, Integer> getParameters() {
        return parameters;
    }
}
