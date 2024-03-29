package com.po.constraintprogrammingsolver.gui.main.util;

import com.po.constraintprogrammingsolver.gui.ProblemController;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-03
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
