package com.po.constraintprogrammingsolver.problems;

import java.util.Optional;

/**
 * Created by Aleksander on 2014-12-19.
 */
public interface ProblemSolver<T, U> {
    public Optional<U> solveProblem(T data);
}
