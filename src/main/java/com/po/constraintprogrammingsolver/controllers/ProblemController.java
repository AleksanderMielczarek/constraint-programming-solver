package com.po.constraintprogrammingsolver.controllers;

import com.po.constraintprogrammingsolver.models.ProblemService;
import com.po.constraintprogrammingsolver.models.ValidatorResult;
import com.po.constraintprogrammingsolver.problems.ProblemSolver;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Aleksander on 2015-01-03.
 */
public interface ProblemController<T, S, U, V> {
    public ProblemService<T, S, U, V> getProblemService();

    public void setTimeProperty(StringProperty timeProperty);

    public void setErrorProperty(StringProperty errorProperty);

    public void setProgressProperty(DoubleProperty progressProperty);

    public T getModel();

    public Function<T, S> getModelToDataConverter();

    public ProblemSolver<S, U> getProblemSolver();

    public Function<U, V> getSolutionToResultConverter();

    public Function<T, ValidatorResult> getValidator();

    public Supplier<V> getDefaultResultSupplier();
}
