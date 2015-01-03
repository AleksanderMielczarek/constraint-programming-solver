package com.po.constraintprogrammingsolver.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Aleksander on 2014-12-18.
 */
public class ProblemResult<T> {
    private final StringProperty time;
    private final ObjectProperty<T> solution;
    private final StringProperty error;

    public ProblemResult(long time, T solution, String error) {
        this.time = new SimpleStringProperty(String.valueOf(time));
        this.solution = new SimpleObjectProperty<>(solution);
        this.error = new SimpleStringProperty(error);
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

    public String getError() {
        return error.get();
    }

    public StringProperty errorProperty() {
        return error;
    }
}
