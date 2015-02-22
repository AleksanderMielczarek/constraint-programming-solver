package com.po.constraintprogrammingsolver.gui.main.util;

import com.po.constraintprogrammingsolver.gui.main.model.ConstraintProgrammingSolverModel;
import javafx.concurrent.Service;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Aleksander Mielczarek
 * @since 2014-12-20
 */
public class ServiceProvider {
    private final ConstraintProgrammingSolverModel model;

    private final Map<Problem, Service<?>> problems = new EnumMap<>(Problem.class);

    public ServiceProvider(ConstraintProgrammingSolverModel model) {
        this.model = model;
    }

    public void registerProblemService(Problem problem, Service<?> problemService) {
        problems.put(problem, problemService);
    }

    public Service<?> getProblemService() {
        return problems.get(model.getProblem());
    }

    public Map<Problem, Service<?>> getProblems() {
        return problems;
    }
}
