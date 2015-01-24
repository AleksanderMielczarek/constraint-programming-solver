package com.po.constraintprogrammingsolver.gui.jobshop.service;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.util.combination.BenchmarkCombinations;
import com.po.constraintprogrammingsolver.gui.jobshop.util.converter.JobShopBenchmarkSolutionToModelConverter;
import com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue.DefaultJobShopBenchmarkValuesSupplier;
import com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue.DefaultValuesSupplier;
import com.po.constraintprogrammingsolver.gui.jobshop.util.validator.JobShopRepetitionsValidator;
import com.po.constraintprogrammingsolver.gui.jobshop.util.validator.JobShopValidator;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopData;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopSolution;
import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import javafx.concurrent.Task;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2015-01-24.
 */
public class JobShopBenchmarkService extends JobShopProblemService {
    protected static final String MESSAGE_SOLVING_BENCHMARK = "message.solving.benchmark";

    private final JobShopValidator validator;
    private final DefaultValuesSupplier defaultBenchmarkValuesSupplier;
    private final JobShopBenchmarkSolutionToModelConverter benchmarkSolutionToModelConverter;

    public JobShopBenchmarkService(JobShopModel model, ResourceBundle resources) {
        super(model, resources);
        validator = new JobShopRepetitionsValidator(super.validator, model, resources);
        this.defaultBenchmarkValuesSupplier = new DefaultJobShopBenchmarkValuesSupplier(model);
        benchmarkSolutionToModelConverter = new JobShopBenchmarkSolutionToModelConverter(model);
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int repetitions = Integer.parseInt(model.getRepetitions());
                setNumberOfSteps(3 + repetitions + 2 * repetitions * BenchmarkCombinations.values().length);
                step = 1;
                valueUpdate(model::setError, StringUtils.EMPTY);
                updateMessage(StringUtils.EMPTY);
                updateProgress(0, 1);

                //validation
                updateMessage(resources.getString(MESSAGE_VALIDATION));
                if (!validator.validate()) {
                    defaultBenchmarkValuesSupplier.supplyDefaultValues();
                    updateProgress(1, 1);
                    return null;
                }
                updateProgress(step++, numberOfSteps);

                //convert model to data
                updateMessage(resources.getString(MESSAGE_CONVERSION));
                JobShopData data = modelToDataConverter.convert();
                updateProgress(step++, numberOfSteps);

                //combinations
                for (BenchmarkCombinations benchmark : BenchmarkCombinations.values()) {
                    JacopStrategyProvider jacopStrategyProvider = benchmark.getJacopStrategyProvider();
                    List<JobShopSolution> solutions = new ArrayList<>(repetitions);

                    for (int i = 1; i <= repetitions; i++) {
                        //solving
                        updateMessage(MessageFormat.format(resources.getString(MESSAGE_SOLVING_BENCHMARK), benchmark.name(resources), i));
                        Optional<JobShopSolution> solution = solver.solveProblem(data, jacopStrategyProvider);
                        updateProgress(step++, numberOfSteps);

                        //check if solution is present
                        updateMessage(resources.getString(MESSAGE_CHECKING));
                        if (!solution.isPresent()) {
                            step += (repetitions - i) * 2;
                            updateProgress(step++, numberOfSteps);
                            break;
                        }
                        updateProgress(step++, numberOfSteps);

                        solutions.add(solution.get());
                    }
                    //convert solution
                    updateMessage(resources.getString(MESSAGE_UPDATING));
                    benchmarkSolutionToModelConverter.convert(solutions, benchmark);
                    updateProgress(step++, numberOfSteps);
                }

                updateMessage(resources.getString(MESSAGE_READY));
                System.out.println(step);
                updateProgress(1, 1);

                return null;
            }
        };
    }

}
