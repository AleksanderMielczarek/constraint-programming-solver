package com.po.constraintprogrammingsolver.models;

import com.google.common.base.Stopwatch;
import com.po.constraintprogrammingsolver.problems.ProblemSolver;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Aleksander on 2014-12-18.
 */
public class ProblemService<T, S, U, V> extends Service<ProblemResult<V>> {
    private static final String SOLUTION_NOT_FOUND = "label.solution.not.found";

    private final T model;
    private final Function<T, S> modelToDataConverter;
    private final ProblemSolver<S, U> problemSolver;
    private final Function<U, V> solutionToResultConverter;
    private final Function<T, ValidatorResult> validator;
    private final Supplier<V> defaultResultSupplier;
    private final ResourceBundle resources;

    public ProblemService(T model,
                          Function<T, S> modelToDataConverter,
                          ProblemSolver<S, U> problemSolver,
                          Function<U, V> solutionToResultConverter,
                          Function<T, ValidatorResult> validator,
                          Supplier<V> defaultResultSupplier,
                          ResourceBundle resources) {
        this.model = model;
        this.modelToDataConverter = modelToDataConverter;
        this.problemSolver = problemSolver;
        this.solutionToResultConverter = solutionToResultConverter;
        this.validator = validator;
        this.defaultResultSupplier = defaultResultSupplier;
        this.resources = resources;
    }

    @Override
    protected Task<ProblemResult<V>> createTask() {
        return new Task<ProblemResult<V>>() {
            @Override
            protected ProblemResult<V> call() throws Exception {
                Stopwatch stopwatch = Stopwatch.createStarted();

                ValidatorResult validatorResult = validator.apply(model);
                if (validatorResult.isError()) {
                    long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                    ProblemResult<V> result = new ProblemResult<>(time, defaultResultSupplier.get(), validatorResult.getMessage());
                    updateProgress(1, 1);
                    return result;
                }
                updateProgress(1, 5);

                //convert model to data
                S data = modelToDataConverter.apply(model);
                updateProgress(2, 5);

                //find solution
                Optional<U> solution = problemSolver.solveProblem(data);
                updateProgress(3, 5);

                if (!solution.isPresent()) {
                    long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                    ProblemResult<V> result = new ProblemResult<>(time, defaultResultSupplier.get(), resources.getString(SOLUTION_NOT_FOUND));
                    updateProgress(1, 1);
                    return result;
                }

                //convert solution to result
                V convertedSolution = solutionToResultConverter.apply(solution.get());
                updateProgress(4, 5);

                long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                ProblemResult<V> result = new ProblemResult<>(time, convertedSolution, validatorResult.getMessage());
                updateProgress(1, 1);
                return result;
            }
        };
    }
}
