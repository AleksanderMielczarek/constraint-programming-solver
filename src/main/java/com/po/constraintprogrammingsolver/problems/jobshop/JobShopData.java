package com.po.constraintprogrammingsolver.problems.jobshop;

import com.po.constraintprogrammingsolver.problems.jobshop.factories.JacopProvider;
import org.jacop.core.Var;

import java.util.List;
import java.util.Optional;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopData {
    private final List<Job> jobs;
    private final JacopProvider jacopProvider;
    private final Optional<Var> costFunction;

    public JobShopData(List<Job> jobs, JacopProvider jacopProvider, Var costFunction) {
        this.jobs = jobs;
        this.jacopProvider = jacopProvider;
        this.costFunction = Optional.of(costFunction);
    }

    public JobShopData(List<Job> jobs, JacopProvider jacopProvider) {
        this.jobs = jobs;
        this.jacopProvider = jacopProvider;
        this.costFunction = Optional.empty();
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public JacopProvider getJacopProvider() {
        return jacopProvider;
    }

    public Optional<Var> getCostFunction() {
        return costFunction;
    }
}
