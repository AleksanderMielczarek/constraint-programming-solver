package com.po.constraintprogrammingsolver.gui.utils;

import com.po.constraintprogrammingsolver.gui.controllers.ProblemController;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class ControllerProvider {
    private final Map<Problem, ProblemController> controllers = new EnumMap<>(Problem.class);

    public void registerProblemController(Problem problem, ProblemController controller) {
        controllers.put(problem, controller);
    }

    public Map<Problem, ProblemController> getControllers() {
        return controllers;
    }
}
