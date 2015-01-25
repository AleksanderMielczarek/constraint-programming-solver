package com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers;

import com.po.constraintprogrammingsolver.problems.jobshop.Parameter;

import java.util.Optional;

/**
 * Created by Aleksander on 2015-01-25.
 */
public enum ParameterWrapper {
    BACKTRACKS_WRAPPER(Parameter.BACKTRACKS),
    DECISIONS_WRAPPER(Parameter.DECISIONS),
    MAXIMUM_DEPTH_WRAPPER(Parameter.MAXIMUM_DEPTH),
    NODES_WRAPPER(Parameter.NODES),
    WRONG_DECISIONS_WRAPPER(Parameter.WRONG_DECISIONS),
    TIME_WRAPPER;

    private final Optional<Parameter> parameter;

    ParameterWrapper(Parameter parameter) {
        this.parameter = Optional.of(parameter);
    }

    ParameterWrapper() {
        this.parameter = Optional.empty();
    }

    public Optional<Parameter> getParameter() {
        return parameter;
    }
}
