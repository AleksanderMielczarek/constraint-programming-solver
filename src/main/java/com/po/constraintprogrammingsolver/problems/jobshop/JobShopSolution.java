package com.po.constraintprogrammingsolver.problems.jobshop;

import com.po.constraintprogrammingsolver.problems.Parameter;

import java.util.Map;

/**
 * Output in {@link com.po.constraintprogrammingsolver.problems.jobshop.JobShopProblemSolver}
 *
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
public class JobShopSolution {
    private final JobShopData jobShopData;
    private final int cost;
    private final long time;
    private final Map<Parameter, Integer> parameters;

    /**
     * Constructor
     *
     * @param jobShopData input data with calculated task start time
     * @param cost        value of cost function
     * @param time        the search solution
     * @param parameters  value of solution parameters
     */
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
