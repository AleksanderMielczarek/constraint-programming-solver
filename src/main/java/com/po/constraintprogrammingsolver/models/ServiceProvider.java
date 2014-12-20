package com.po.constraintprogrammingsolver.models;

import com.po.constraintprogrammingsolver.problems.Problem;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Aleksander on 2014-12-20.
 */
public class ServiceProvider {
    private final Map<Problem, ProblemService<?>> problems = new EnumMap<>(Problem.class);
    private final ObjectProperty<Problem> problem = new SimpleObjectProperty<>();

    public void registerService(Problem problem, ProblemService<?> problemService) {
        problems.put(problem, problemService);
    }

    public ProblemService<?> getService() {
        return problems.get(problem.get());
    }

    public Problem getProblem() {
        return problem.get();
    }

    public ObjectProperty<Problem> problemProperty() {
        return problem;
    }
}
