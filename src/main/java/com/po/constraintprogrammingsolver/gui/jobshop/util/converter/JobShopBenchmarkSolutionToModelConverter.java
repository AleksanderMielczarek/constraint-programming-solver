package com.po.constraintprogrammingsolver.gui.jobshop.util.converter;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.util.ValueUpdater;
import com.po.constraintprogrammingsolver.gui.jobshop.util.combination.BenchmarkCombinations;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopSolution;

import java.util.List;

/**
 * Created by Aleksander on 2015-01-24.
 */
public class JobShopBenchmarkSolutionToModelConverter implements ValueUpdater {
    private final JobShopModel model;

    public JobShopBenchmarkSolutionToModelConverter(JobShopModel model) {
        this.model = model;
    }

    public void convert(List<JobShopSolution> solutions, BenchmarkCombinations benchmarkCombinations) {

    }
}
