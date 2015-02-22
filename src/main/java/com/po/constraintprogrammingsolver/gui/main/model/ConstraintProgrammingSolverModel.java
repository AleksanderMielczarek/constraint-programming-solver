package com.po.constraintprogrammingsolver.gui.main.model;

import com.po.constraintprogrammingsolver.gui.main.util.Problem;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-24
 */
public class ConstraintProgrammingSolverModel {
    private final ObjectProperty<Problem> problem = new SimpleObjectProperty<>();

    public Problem getProblem() {
        return problem.get();
    }

    public ObjectProperty<Problem> problemProperty() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem.set(problem);
    }
}
