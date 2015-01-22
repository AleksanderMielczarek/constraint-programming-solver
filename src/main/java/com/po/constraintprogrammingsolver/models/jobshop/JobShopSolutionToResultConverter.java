package com.po.constraintprogrammingsolver.models.jobshop;

import com.po.constraintprogrammingsolver.problems.jobshop.JobShopSolution;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

import java.util.ResourceBundle;
import java.util.function.Function;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopSolutionToResultConverter implements Function<JobShopSolution, JobShopResult> {
    private static final String CHART_LABEL = "chart.label";
    private static final String RESULT_JOB = "result.job";
    private static final String RESULT_TASK = "result.task";
    private static final String RESULT_TIME = "result.time";
    private static final String RESULT_MACHINE = "result.machine";
    private static final String COLON = " : ";
    private static final String TABULATOR = "\t";
    private static final String NEW_LINE = System.getProperty("line.separator");

    private final ResourceBundle resources;

    public JobShopSolutionToResultConverter(ResourceBundle resources) {
        this.resources = resources;
    }

    @Override
    public JobShopResult apply(JobShopSolution solution) {
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        StringBuilder builder = new StringBuilder();

        solution.getSolution().asMap().entrySet().stream()
                .map(entry -> {
                    TaskSeries series = new TaskSeries(resources.getString(CHART_LABEL) + entry.getKey());
                    entry.getValue().stream()
                            .map(wrapper -> {
                                builder.append(resources.getString(RESULT_JOB) + COLON + entry.getKey())
                                        .append(TABULATOR)
                                        .append(resources.getString(RESULT_TASK) + COLON + wrapper.getTask().getNumber())
                                        .append(TABULATOR)
                                        .append(resources.getString(RESULT_MACHINE) + COLON + wrapper.getTask().getMachine())
                                        .append(TABULATOR)
                                        .append(resources.getString(RESULT_TIME) + COLON + wrapper.getIntVar().value())
                                        .append(NEW_LINE);

                                String description = Integer.toString(wrapper.getTask().getMachine());
                                long start = wrapper.getIntVar().value();
                                long end = start + wrapper.getTask().getDuration();
                                return new Task(description, new SimpleTimePeriod(start, end));
                            })
                            .forEach(series::add);
                    return series;
                })
                .forEach(dataset::add);

        return new JobShopResult(dataset, String.valueOf(solution.getCost()), builder.toString());
    }
}
