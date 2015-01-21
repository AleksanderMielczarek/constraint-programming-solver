package com.po.constraintprogrammingsolver.models;

import com.google.common.base.Stopwatch;
import com.po.constraintprogrammingsolver.problems.JacopStrategyProblemSolver;
import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Aleksander on 2014-12-18.
 */
public class ProblemService<T, S, U, V> extends Service<ProblemResult<V>> {
    private static final String SOLUTION_NOT_FOUND = "label.solution.not.found";

    private final T model;
    private final Function<T, S> modelToDataConverter;
    private final Function<T, JacopStrategyProvider> modelToJacopStrategyProviderConverter;
    private final JacopStrategyProblemSolver<S, U> problemSolver;
    private final Function<U, V> solutionToResultConverter;
    private final Function<T, ValidatorResult> validator;
    private final Supplier<V> defaultResultSupplier;
    private final Optional<Consumer<V>> resultConsumer;
    private final ResourceBundle resources;

    private final int steps;

    public ProblemService(T model,
                          Function<T, S> modelToDataConverter,
                          Function<T, JacopStrategyProvider> modelToJacopStrategyProviderConverter,
                          JacopStrategyProblemSolver<S, U> problemSolver,
                          Function<U, V> solutionToResultConverter,
                          Function<T, ValidatorResult> validator,
                          Supplier<V> defaultResultSupplier,
                          ResourceBundle resources) {
        this.model = model;
        this.modelToDataConverter = modelToDataConverter;
        this.modelToJacopStrategyProviderConverter = modelToJacopStrategyProviderConverter;
        this.problemSolver = problemSolver;
        this.solutionToResultConverter = solutionToResultConverter;
        this.validator = validator;
        this.defaultResultSupplier = defaultResultSupplier;
        this.resources = resources;
        this.resultConsumer = Optional.empty();
        steps = 6;
    }

    public ProblemService(T model,
                          Function<T, S> modelToDataConverter,
                          Function<T, JacopStrategyProvider> modelToJacopStrategyProviderConverter,
                          JacopStrategyProblemSolver<S, U> problemSolver,
                          Function<U, V> solutionToResultConverter,
                          Function<T, ValidatorResult> validator,
                          Supplier<V> defaultResultSupplier,
                          Consumer<V> resultConsumer,
                          ResourceBundle resources) {
        this.model = model;
        this.modelToDataConverter = modelToDataConverter;
        this.modelToJacopStrategyProviderConverter = modelToJacopStrategyProviderConverter;
        this.problemSolver = problemSolver;
        this.solutionToResultConverter = solutionToResultConverter;
        this.validator = validator;
        this.defaultResultSupplier = defaultResultSupplier;
        this.resultConsumer = Optional.of(resultConsumer);
        this.resources = resources;
        steps = 7;
    }

    @Override
    protected Task<ProblemResult<V>> createTask() {
        return new Task<ProblemResult<V>>() {
            @Override
            protected ProblemResult<V> call() throws Exception {
                Stopwatch stopwatch = Stopwatch.createStarted();

                ValidatorResult validatorResult = validator.apply(model);
                if (validatorResult.isError()) {
                    if (resultConsumer.isPresent()) {
                        resultConsumer.get().accept(defaultResultSupplier.get());
                    }
                    long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                    ProblemResult<V> result = new ProblemResult<>(time, defaultResultSupplier.get(), validatorResult.getMessage());
                    updateProgress(1, 1);
                    return result;
                }
                updateProgress(1, steps);

                //convert model to data
                S data = modelToDataConverter.apply(model);
                updateProgress(2, steps);

                //convert model to jacop provider
                JacopStrategyProvider jacopStrategyProvider = modelToJacopStrategyProviderConverter.apply(model);
                updateProgress(3, steps);

                //find solution
                Optional<U> solution = problemSolver.solveProblem(data, jacopStrategyProvider);
                updateProgress(4, steps);

                if (!solution.isPresent()) {
                    if (resultConsumer.isPresent()) {
                        resultConsumer.get().accept(defaultResultSupplier.get());
                    }
                    long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                    ProblemResult<V> result = new ProblemResult<>(time, defaultResultSupplier.get(), resources.getString(SOLUTION_NOT_FOUND));
                    updateProgress(1, 1);
                    return result;
                }

                //convert solution to result
                V convertedSolution = solutionToResultConverter.apply(solution.get());
                updateProgress(5, steps);

                if (resultConsumer.isPresent()) {
                    resultConsumer.get().accept(convertedSolution);
                    updateProgress(6, steps);
                }
                long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                ProblemResult<V> result = new ProblemResult<>(time, convertedSolution, validatorResult.getMessage());
                updateProgress(1, 1);
                return result;
            }
        };
    }
}
