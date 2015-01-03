package com.po.constraintprogrammingsolver.controllers;

import com.po.constraintprogrammingsolver.models.ProblemService;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Aleksander on 2015-01-03.
 */
public interface ProblemController<T, S, U, V> {
    public ProblemService<T, S, U, V> getProblemService();

    public void setTimeProperty(StringProperty timeProperty);

    public void setErrorProperty(StringProperty errorProperty);

    public void setProgressProperty(DoubleProperty progressProperty);
}
