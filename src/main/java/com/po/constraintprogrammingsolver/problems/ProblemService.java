package com.po.constraintprogrammingsolver.problems;

import com.google.common.base.Stopwatch;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.concurrent.TimeUnit;

/**
 * Created by Aleksander on 2014-12-18.
 */
public class ProblemService<T> extends Service<Result<T>> {
    private final ProblemSolver<T> problemSolver;

    public ProblemService(ProblemSolver<T> problemSolver) {
        this.problemSolver = problemSolver;
    }

    @Override
    protected Task<Result<T>> createTask() {
        return new Task<Result<T>>() {
            @Override
            protected Result<T> call() throws Exception {
                Stopwatch stopwatch = Stopwatch.createStarted();
                T solution = problemSolver.solveProblem();
                long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                Result<T> result = new Result<>(time, solution);
                updateProgress(1, 1);
                return result;
            }
        };
    }
}
