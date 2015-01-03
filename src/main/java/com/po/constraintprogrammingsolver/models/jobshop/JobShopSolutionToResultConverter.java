package com.po.constraintprogrammingsolver.models.jobshop;

import com.google.common.collect.Multimap;
import com.po.constraintprogrammingsolver.problems.jobshop2.TaskIntVarWrapper;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

import java.util.ResourceBundle;
import java.util.function.Function;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopSolutionToResultConverter implements Function<Multimap<Integer, TaskIntVarWrapper>, TaskSeriesCollection> {
    private static final String CHART_LABEL = "chart.label";

    private final ResourceBundle resources;

    public JobShopSolutionToResultConverter(ResourceBundle resources) {
        this.resources = resources;
    }

    @Override
    public TaskSeriesCollection apply(Multimap<Integer, TaskIntVarWrapper> taskJob) {
        TaskSeriesCollection dataset = new TaskSeriesCollection();

        taskJob.asMap().entrySet().stream()
                .map(entry -> {
                    TaskSeries series = new TaskSeries(resources.getString(CHART_LABEL) + entry.getKey());
                    entry.getValue().stream()
                            .map(wrapper -> {
                                String description = Integer.toString(wrapper.getTask().getMachine());
                                long start = wrapper.getIntVar().value();
                                long end = start + wrapper.getTask().getTime();
                                return new Task(description, new SimpleTimePeriod(start, end));
                            })
                            .forEach(series::add);
                    return series;
                })
                .forEach(dataset::add);

        return dataset;
    }
}
