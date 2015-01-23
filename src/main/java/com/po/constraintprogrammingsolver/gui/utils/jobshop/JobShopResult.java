package com.po.constraintprogrammingsolver.gui.utils.jobshop;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jfree.data.gantt.TaskSeriesCollection;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class JobShopResult {
    private final ObjectProperty<TaskSeriesCollection> taskSeriesCollection;
    private final StringProperty cost;
    private final StringProperty result;

    public JobShopResult(TaskSeriesCollection taskSeriesCollection, String cost, String result) {
        this.taskSeriesCollection = new SimpleObjectProperty<>(taskSeriesCollection);
        this.cost = new SimpleStringProperty(cost);
        this.result = new SimpleStringProperty(result);
    }

    public TaskSeriesCollection getTaskSeriesCollection() {
        return taskSeriesCollection.get();
    }

    public ObjectProperty<TaskSeriesCollection> taskSeriesCollectionProperty() {
        return taskSeriesCollection;
    }

    public String getCost() {
        return cost.get();
    }

    public StringProperty costProperty() {
        return cost;
    }

    public String getResult() {
        return result.get();
    }

    public StringProperty resultProperty() {
        return result;
    }
}
