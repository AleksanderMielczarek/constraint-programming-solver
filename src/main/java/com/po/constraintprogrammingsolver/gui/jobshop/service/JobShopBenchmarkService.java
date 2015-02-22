package com.po.constraintprogrammingsolver.gui.jobshop.service;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.util.data.BenchmarkCombination;
import com.po.constraintprogrammingsolver.gui.jobshop.util.converter.JobShopBenchmarkSolutionToModelConverter;
import com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue.DefaultJobShopBenchmarkValuesSupplier;
import com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue.DefaultValuesSupplier;
import com.po.constraintprogrammingsolver.gui.jobshop.util.validator.JobShopRepetitionsValidator;
import com.po.constraintprogrammingsolver.gui.jobshop.util.validator.JobShopValidator;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopData;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopSolution;
import com.po.constraintprogrammingsolver.problems.Parameter;
import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import javafx.concurrent.Task;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.*;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-24
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
        benchmarkSolutionToModelConverter = new JobShopBenchmarkSolutionToModelConverter(model, resources);
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                step = 1;
                valueUpdate(model::setError, StringUtils.EMPTY);
                updateMessage(StringUtils.EMPTY);
                updateProgress(0, 1);

                //validation
                updateMessage(resources.getString(MESSAGE_VALIDATION));
                if (!validator.validate()) {
                    defaultBenchmarkValuesSupplier.supplyDefaultValues();
                    updateMessage(resources.getString(MESSAGE_READY));
                    updateProgress(1, 1);
                    return null;
                }
                int repetitions = Integer.parseInt(model.getRepetitions());
                setNumberOfSteps(3 + BenchmarkCombination.values().length + 2 * repetitions * BenchmarkCombination.values().length);
                updateProgress(step++, numberOfSteps);


                valueUpdate(model::setBenchmarkExpanded, true);

                //convert model to data
                updateMessage(resources.getString(MESSAGE_CONVERSION));
                JobShopData data = modelToDataConverter.convert();
                updateProgress(step++, numberOfSteps);

                //combinations
                defaultBenchmarkValuesSupplier.supplyDefaultValues();
                for (BenchmarkCombination benchmark : BenchmarkCombination.values()) {
                    JacopStrategyProvider jacopStrategyProvider = benchmark.getJacopStrategyProvider();
                    List<JobShopSolution> solutions = new ArrayList<>(repetitions);
                    boolean noSolution = false;

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
                            noSolution = true;
                            break;
                        }
                        updateProgress(step++, numberOfSteps);

                        solutions.add(solution.get());
                    }

                    //convert solution
                    if (noSolution) {
                        Map<Parameter, Integer> parameters = new EnumMap<>(Parameter.class);
                        parameters.put(Parameter.BACKTRACKS, 0);
                        parameters.put(Parameter.DECISIONS, 0);
                        parameters.put(Parameter.MAXIMUM_DEPTH, 0);
                        parameters.put(Parameter.NODES, 0);
                        parameters.put(Parameter.WRONG_DECISIONS, 0);
                        solutions = Collections.nCopies(repetitions, new JobShopSolution(null, 0, 0, parameters));
                    }
                    updateMessage(resources.getString(MESSAGE_UPDATING));
                    benchmarkSolutionToModelConverter.convert(solutions, benchmark);
                    updateProgress(step++, numberOfSteps);
                }

                updateMessage(resources.getString(MESSAGE_READY));
                updateProgress(1, 1);
                return null;
            }
        };
    }

}
