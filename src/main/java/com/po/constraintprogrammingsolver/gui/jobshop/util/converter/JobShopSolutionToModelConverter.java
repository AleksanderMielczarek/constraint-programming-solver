package com.po.constraintprogrammingsolver.gui.jobshop.util.converter;

import com.google.common.collect.Iterables;
import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.ValueUpdater;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopSolution;
import com.po.constraintprogrammingsolver.problems.jobshop.Parameter;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.time.SimpleTimePeriod;

import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopSolutionToModelConverter implements ValueUpdater {
    private static final String CHART_LABEL = "chart.label";
    private static final String RESULT_JOB = "result.job";
    private static final String RESULT_TASK = "result.task";
    private static final String RESULT_TIME = "result.time";
    private static final String RESULT_MACHINE = "result.machine";
    private static final String COLON = " : ";
    private static final String TABULATOR = "\t";
    private static final String NEW_LINE = System.getProperty("line.separator");

    private final JobShopModel model;
    private final ResourceBundle resources;

    public JobShopSolutionToModelConverter(JobShopModel model, ResourceBundle resources) {
        this.model = model;
        this.resources = resources;
    }

    public void convert(JobShopSolution solution) {
        StringBuilder builder = new StringBuilder();

        solution.getJobShopData().tasksOnJobs().values().stream()
                .forEach(task ->
                                builder.append(resources.getString(RESULT_JOB) + COLON + task.getJob().get().getJobNumber().get())
                                        .append(TABULATOR)
                                        .append(resources.getString(RESULT_TASK) + COLON + task.getTaskNumber().get())
                                        .append(TABULATOR)
                                        .append(resources.getString(RESULT_MACHINE) + COLON + task.getMachineNumber())
                                        .append(TABULATOR)
                                        .append(resources.getString(RESULT_TIME) + COLON + task.startTime().get())
                                        .append(NEW_LINE)
                );

        valueUpdate(() -> model.getTaskSeriesCollection().clear());

        solution.getJobShopData().tasksOnJobs().asMap().values().stream()
                .map(tasks -> {
                    TaskSeries series = new TaskSeries(resources.getString(CHART_LABEL) + Iterables.getLast(tasks).getJob().get().getJobNumber().get());

                    tasks.stream()
                            .map(task -> {
                                String description = Integer.toString(task.getMachineNumber());
                                long start = task.startTime().get();
                                long end = start + task.getDuration();
                                return new Task(description, new SimpleTimePeriod(start, end));
                            })
                            .forEach(series::add);

                    return series;
                })
                .forEach(series -> valueUpdate(() -> model.getTaskSeriesCollection().add(series)));

        valueUpdate(model::setJobShopResult, builder.toString());

        valueUpdate(model::setCost, solution.getCost());
        valueUpdate(model::setBacktracks, solution.getParameters().get(Parameter.BACKTRACKS));
        valueUpdate(model::setDecisions, solution.getParameters().get(Parameter.DECISIONS));
        valueUpdate(model::setMaximumDepth, solution.getParameters().get(Parameter.MAXIMUM_DEPTH));
        valueUpdate(model::setNodes, solution.getParameters().get(Parameter.NODES));
        valueUpdate(model::setWrongDecisions, solution.getParameters().get(Parameter.WRONG_DECISIONS));
        valueUpdate(model::setTime, solution.getTime());
    }
}
