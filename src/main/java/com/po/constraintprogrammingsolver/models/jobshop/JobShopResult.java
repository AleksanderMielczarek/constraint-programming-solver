package com.po.constraintprogrammingsolver.models.jobshop;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.jfree.data.gantt.TaskSeriesCollection;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class JobShopResult {
    private final ObjectProperty<TaskSeriesCollection> taskSeriesCollection;

    public JobShopResult(TaskSeriesCollection taskSeriesCollection) {
        this.taskSeriesCollection = new SimpleObjectProperty<>(taskSeriesCollection);
    }

    public TaskSeriesCollection getTaskSeriesCollection() {
        return taskSeriesCollection.get();
    }

    public ObjectProperty<TaskSeriesCollection> taskSeriesCollectionProperty() {
        return taskSeriesCollection;
    }
}
