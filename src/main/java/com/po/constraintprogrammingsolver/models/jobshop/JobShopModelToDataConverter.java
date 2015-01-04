package com.po.constraintprogrammingsolver.models.jobshop;

import com.google.common.base.Splitter;
import com.po.constraintprogrammingsolver.problems.jobshop.Job;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopData;
import com.po.constraintprogrammingsolver.problems.jobshop.Task;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.JacopProvider;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.JacopProviders;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.comparatorvariable.ComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.costfunction.CostFunctionType;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.selectchoicepoint.SelectChoicePointComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.selectchoicepoint.SelectChoicePointStoreType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopModelToDataConverter implements Function<JobShopModel, JobShopData> {
    private static final String SEPARATOR = ";";

    @Override
    public JobShopData apply(JobShopModel model) {
        Scanner jobScanner = new Scanner(model.getJobs());
        List<Job> jobs = new ArrayList<>();
        int jobNumber = 1;

        while (jobScanner.hasNextLine()) {
            String line = jobScanner.nextLine();
            List<String> lines = Splitter.on(SEPARATOR).splitToList(line);

            int start = Integer.valueOf(lines.get(0));

            Scanner machineScanner = new Scanner(lines.get(1));
            Scanner timeScanner = new Scanner(lines.get(2));
            List<Task> tasks = new ArrayList<>();
            int taskNumber = 1;

            while (machineScanner.hasNextInt()) {
                Task task = new Task(taskNumber++, machineScanner.nextInt(), timeScanner.nextInt());
                tasks.add(task);
            }

            Job job = new Job(jobNumber++, start, tasks);
            jobs.add(job);
        }

        JacopProvider jacopProvider;
        IndomainType indomainType = model.getIndomainTypeWrapper().getIndomainType();
        if (model.getSelectChoicePointTypeWrapper().isStore()) {
            SelectChoicePointStoreType selectChoicePointType = model.getSelectChoicePointTypeWrapper().getSelectChoicePointStoreType().get();
            if (model.getCostFunctionTypeWrapper().getCostFunctionType().isPresent()) {
                CostFunctionType costFunctionType = model.getCostFunctionTypeWrapper().getCostFunctionType().get();
                jacopProvider = JacopProviders.simpleJacopProviderWithCostFunction(indomainType, selectChoicePointType, costFunctionType);
            } else {
                jacopProvider = JacopProviders.simpleJacopProvider(indomainType, selectChoicePointType);
            }
        } else {
            ComparatorVariableType comparatorVariableType = model.getComparatorVariableTypeWrapper().getComparatorVariableType();
            SelectChoicePointComparatorVariableType selectChoicePointComparatorVariableType = model.getSelectChoicePointTypeWrapper().getSelectChoicePointComparatorVariableType().get();
            if (model.getCostFunctionTypeWrapper().getCostFunctionType().isPresent()) {
                CostFunctionType costFunctionType = model.getCostFunctionTypeWrapper().getCostFunctionType().get();
                jacopProvider = JacopProviders.comparatorVariableJacopProviderWithCostFunction(indomainType, comparatorVariableType, selectChoicePointComparatorVariableType, costFunctionType);
            } else {
                jacopProvider = JacopProviders.comparatorVariableJacopProvider(indomainType, comparatorVariableType, selectChoicePointComparatorVariableType);
            }
        }

        return new JobShopData(jobs, jacopProvider);
    }
}
