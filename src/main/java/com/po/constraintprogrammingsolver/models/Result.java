package com.po.constraintprogrammingsolver.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Aleksander on 2014-12-18.
 */
public class Result<T> {
    private final StringProperty time;
    private final ObjectProperty<T> solution;

    public Result(long time, T solution) {
        this.time = new SimpleStringProperty(String.valueOf(time));
        this.solution = new SimpleObjectProperty<>(solution);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public T getSolution() {
        return solution.get();
    }

    public ObjectProperty<T> solutionProperty() {
        return solution;
    }
}
