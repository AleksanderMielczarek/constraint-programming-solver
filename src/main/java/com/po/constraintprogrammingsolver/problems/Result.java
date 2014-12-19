package com.po.constraintprogrammingsolver.problems;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Aleksander on 2014-12-18.
 */
public class Result<T> {
    private final SimpleStringProperty time;
    private final SimpleObjectProperty<T> solution;

    public Result(long time, T solution) {
        this.time = new SimpleStringProperty(String.valueOf(time));
        this.solution = new SimpleObjectProperty<>(solution);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public T getSolution() {
        return solution.get();
    }

    public SimpleObjectProperty<T> solutionProperty() {
        return solution;
    }
}
