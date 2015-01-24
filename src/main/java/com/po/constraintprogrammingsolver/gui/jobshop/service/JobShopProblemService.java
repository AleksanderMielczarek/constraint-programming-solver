package com.po.constraintprogrammingsolver.gui.jobshop.service;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.util.converter.JobShopModelToDataConverter;
import com.po.constraintprogrammingsolver.gui.jobshop.util.converter.JobShopModelToJacopProviderConverter;
import com.po.constraintprogrammingsolver.gui.jobshop.util.converter.JobShopSolutionToModelConverter;
import com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue.DefaultJobShopProblemResultValuesSupplier;
import com.po.constraintprogrammingsolver.problems.JacopStrategyProblemSolver;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopData;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopProblemSolver;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopSolution;
import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import javafx.concurrent.Task;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2015-01-23.
 */
public class JobShopProblemService extends AbstractJobShopService {
    private static final String SOLUTION_NOT_FOUND = "label.solution.not.found";
    private static final String MESSAGE_CONVERSION = "message.conversion";
    private static final String MESSAGE_SOLVING = "message.solving";
    private static final String MESSAGE_CHECKING = "message.checking";
    private static final String MESSAGE_UPDATING = "message.updating";
    private static final String MESSAGE_READY = "message.ready";

    private final JobShopModelToDataConverter modelToDataConverter;
    private final JobShopModelToJacopProviderConverter modelToJacopProviderConverter;
    private final JacopStrategyProblemSolver solver = new JobShopProblemSolver();
    private final JobShopSolutionToModelConverter solutionToModelConverter;

    public JobShopProblemService(JobShopModel model, ResourceBundle resources) {
        super(model, resources, new DefaultJobShopProblemResultValuesSupplier(model), 5);
        this.modelToDataConverter = new JobShopModelToDataConverter(model);
        this.modelToJacopProviderConverter = new JobShopModelToJacopProviderConverter(model);
        this.solutionToModelConverter = new JobShopSolutionToModelConverter(model, resources);
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
                    defaultValuesSupplier.supplyDefaultValues();
                    updateProgress(1, 1);
                    return null;
                }
                updateProgress(step++, numberOfSteps);

                //convert model to data
                updateMessage(resources.getString(MESSAGE_CONVERSION));
                JobShopData data = modelToDataConverter.convert();
                JacopStrategyProvider jacopStrategyProvider = modelToJacopProviderConverter.convert();
                updateProgress(step++, numberOfSteps);

                //find solution
                updateMessage(resources.getString(MESSAGE_SOLVING));
                Optional<JobShopSolution> solution = solver.solveProblem(data, jacopStrategyProvider);
                updateProgress(step++, numberOfSteps);

                //check if solution is present
                updateMessage(resources.getString(MESSAGE_CHECKING));
                if (!solution.isPresent()) {
                    valueUpdate(model::setError, resources.getString(SOLUTION_NOT_FOUND));
                    defaultValuesSupplier.supplyDefaultValues();
                    updateProgress(1, 1);
                    return null;
                }
                updateProgress(step++, numberOfSteps);

                //convert solution to result
                updateMessage(resources.getString(MESSAGE_UPDATING));
                solutionToModelConverter.convert(solution.get());

                updateMessage(resources.getString(MESSAGE_READY));
                updateProgress(1, 1);

                return null;
            }
        };
    }
}
