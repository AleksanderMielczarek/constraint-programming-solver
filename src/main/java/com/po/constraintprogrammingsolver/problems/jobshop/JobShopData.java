package com.po.constraintprogrammingsolver.problems.jobshop;

import org.jacop.core.IntVar;
import org.jacop.core.Var;
import org.jacop.search.Indomain;

import java.util.List;
import java.util.Optional;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopData {
    private final List<Job> jobs;
    private final Indomain<IntVar> indomain;
    private final Optional<Var> costFunction;

    public JobShopData(List<Job> jobs, Indomain<IntVar> indomain, Var costFunction) {
        this.jobs = jobs;
        this.indomain = indomain;
        this.costFunction = Optional.of(costFunction);
    }

    public JobShopData(List<Job> jobs, Indomain<IntVar> indomain) {
        this.jobs = jobs;
        this.indomain = indomain;
        costFunction = Optional.empty();
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public Indomain<IntVar> getIndomain() {
        return indomain;
    }

    public Optional<Var> getCostFunction() {
        return costFunction;
    }
}
