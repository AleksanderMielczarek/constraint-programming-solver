package com.po.constraintprogrammingsolver.gui.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Aleksander on 2014-12-18.
 */
public class ProblemResult<T> {
    private final StringProperty time;
    private final T result;
    private final StringProperty error;

    public ProblemResult(long time, T result, String error) {
        this.time = new SimpleStringProperty(String.valueOf(time));
        this.result = result;
        this.error = new SimpleStringProperty(error);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public T getResult() {
        return result;
    }

    public String getError() {
        return error.get();
    }

    public StringProperty errorProperty() {
        return error;
    }
}
