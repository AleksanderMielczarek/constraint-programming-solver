package com.po.constraintprogrammingsolver.problems;

import java.util.Optional;

/**
 * Interface, which solves problem.
 *
 * @param <T> the input data
 * @param <U> the output data
 * @author Aleksander Mielczarek
 * @since 2014-12-19
 */
public interface ProblemSolver<T, U> {
    /**
     * Calculating final, optimal solution.
     *
     * @param data the input data
     * @return the output data
     */
    public Optional<U> solveProblem(T data);
}
