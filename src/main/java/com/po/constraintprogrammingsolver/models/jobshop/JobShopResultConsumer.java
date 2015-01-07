package com.po.constraintprogrammingsolver.models.jobshop;

import javafx.application.Platform;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import java.util.function.Consumer;

/**
 * Created by Aleksander on 2015-01-07.
 */
public class JobShopResultConsumer implements Consumer<JobShopResult> {
    private final TaskSeriesCollection taskSeriesCollection;

    public JobShopResultConsumer(TaskSeriesCollection taskSeriesCollection) {
        this.taskSeriesCollection = taskSeriesCollection;
    }

    @Override
    public void accept(JobShopResult jobShopResult) {
        Platform.runLater(taskSeriesCollection::removeAll);
        TaskSeriesCollection newValue = jobShopResult.getTaskSeriesCollection();

        int series = newValue.getSeriesCount();
        for (int i = 0; i < series; i++) {
            TaskSeries taskSeries = newValue.getSeries(i);
            Platform.runLater(() -> taskSeriesCollection.add(taskSeries));
        }
    }
}
