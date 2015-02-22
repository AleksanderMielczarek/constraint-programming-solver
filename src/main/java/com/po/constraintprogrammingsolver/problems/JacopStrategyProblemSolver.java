package com.po.constraintprogrammingsolver.problems;

import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProviders;
import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreType;

import java.util.Optional;

/**
 * Extension of {@link ProblemSolver}, allows user to specify concrete Jacop strategy used during solving
 *
 * @param <T> type of input problem data
 * @param <U> type of output data
 * @author Aleksander Mielczarek
 * @since 2015-01-21
 */
public interface JacopStrategyProblemSolver<T, U> extends ProblemSolver<T, U> {
    /**
     * Default implementation using {@link com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider}
     * specified in {@link #getDefaultJacopStrategyProvider()}
     *
     * @param data the input data
     * @return {@link java.util.Optional} object contains result (empty if no solution found)
     */
    @Override
    public default Optional<U> solveProblem(T data) {
        return solveProblem(data, getDefaultJacopStrategyProvider());
    }

    /**
     * Solve problem using provided {@link com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider} and problem data
     *
     * @param data                  object with input data
     * @param jacopStrategyProvider contains Jacop strategy used during solving
     * @return {@link java.util.Optional} object contains result (empty if no solution found)
     */
    public Optional<U> solveProblem(T data, JacopStrategyProvider jacopStrategyProvider);

    /**
     * Specify what Jacop strategy is used in {@link #solveProblem(Object)}. This is default implementation, which uses:
     * <ul>
     * <li>{@link com.po.constraintprogrammingsolver.problems.strategy.SimpleJacopStrategyProvider} is used as strategy provider</li>
     * <li>{@link com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType#INDOMAIN_MIN} as selected indomain</li>
     * <li>{@link com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreType#INPUT_ORDER_SELECT} as selected select choice point</li>
     * </ul>
     *
     * @return Jacop strategy used during solving
     */
    public default JacopStrategyProvider getDefaultJacopStrategyProvider() {
        return JacopStrategyProviders.simpleJacopStrategyProvider(IndomainType.INDOMAIN_MIN, SelectChoicePointStoreType.INPUT_ORDER_SELECT);
    }
}
