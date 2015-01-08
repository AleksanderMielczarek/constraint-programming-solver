package com.po.constraintprogrammingsolver.problems.jobshop;

import com.google.common.collect.Multimap;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class JobShopSolution {
    private final Multimap<Integer, TaskIntVarWrapper> solution;
    private final int cost;

    public JobShopSolution(Multimap<Integer, TaskIntVarWrapper> solution, int cost) {
        this.solution = solution;
        this.cost = cost;
    }

    public Multimap<Integer, TaskIntVarWrapper> getSolution() {
        return solution;
    }

    public int getCost() {
        return cost;
    }
}
