package com.po.constraintprogrammingsolver.problems;

import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProviders;
import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreType;

import java.util.Optional;

/**
 * Created by Aleksander on 2015-01-21.
 */
public interface JacopStrategyProblemSolver<T, U> extends ProblemSolver<T,U>{
    @Override
    public default Optional<U> solveProblem(T data) {
        return solveProblem(data, getDefaultJacopStrategyProvider());
    }

    public Optional<U> solveProblem(T data, JacopStrategyProvider jacopStrategyProvider);

    public default JacopStrategyProvider getDefaultJacopStrategyProvider() {
        return JacopStrategyProviders.simpleJacopStrategyProvider(IndomainType.INDOMAIN_MIN, SelectChoicePointStoreType.INPUT_ORDER_SELECT);
    }
}
