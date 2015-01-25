package com.po.constraintprogrammingsolver.gui.jobshop.util.converter;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.util.ValueUpdater;
import com.po.constraintprogrammingsolver.gui.jobshop.util.combination.BenchmarkCombinations;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.ParameterWrapper;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopSolution;
import javafx.scene.chart.XYChart;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2015-01-24.
 */
public class JobShopBenchmarkSolutionToModelConverter implements ValueUpdater {
    private final JobShopModel model;
    private final ResourceBundle resources;

    public JobShopBenchmarkSolutionToModelConverter(JobShopModel model, ResourceBundle resources) {
        this.model = model;
        this.resources = resources;
    }

    public void convert(List<JobShopSolution> solutions, BenchmarkCombinations benchmarkCombinations) {

        for (ParameterWrapper wrapper : ParameterWrapper.values()) {
            double average;

            if (!wrapper.getParameter().isPresent()) {
                average = solutions.stream()
                        .mapToLong(JobShopSolution::getTime)
                        .average().getAsDouble();
            } else {
                average = solutions.stream()
                        .mapToLong(JobShopSolution::getTime)
                        .average().getAsDouble();
                /*OptionalDouble average2 = solutions.stream()
                        .map(JobShopSolution::getParameters)
                        .mapToInt(parameters -> parameters.get(wrapper))
                        .average();
                System.out.println(average2.isPresent());*/
            }

            valueUpdate(() -> model.getLineChartDataMap().get(wrapper).get().get(0).getData().add(new XYChart.Data<>(benchmarkCombinations.label(resources), average)));
        }
    }
}
