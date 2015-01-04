package com.po.constraintprogrammingsolver.problems.jobshop;

import com.google.common.collect.Multimap;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class JobShopSolution {
    private final Multimap<Integer, TaskIntVarWrapper> solution;

    public JobShopSolution(Multimap<Integer, TaskIntVarWrapper> solution) {
        this.solution = solution;
    }

    public Multimap<Integer, TaskIntVarWrapper> getSolution() {
        return solution;
    }
}
