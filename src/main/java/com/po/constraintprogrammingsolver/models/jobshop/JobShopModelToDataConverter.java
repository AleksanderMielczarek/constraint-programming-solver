package com.po.constraintprogrammingsolver.models.jobshop;

import com.google.common.base.Splitter;
import com.po.constraintprogrammingsolver.problems.jobshop.Job;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopData;
import com.po.constraintprogrammingsolver.problems.jobshop.Task;
import com.po.constraintprogrammingsolver.problems.factories.JacopProvider;
import com.po.constraintprogrammingsolver.problems.factories.JacopProviders;
import com.po.constraintprogrammingsolver.problems.factories.comparatorvariable.ComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.factories.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.factories.selectchoicepoint.SelectChoicePointComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.factories.selectchoicepoint.SelectChoicePointStoreType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopModelToDataConverter implements Function<JobShopModel, JobShopData> {
    public static final String PART_SEPARATOR = ";";
    public static final String NUMBER_SEPARATOR = " ";

    @Override
    public JobShopData apply(JobShopModel model) {
        Scanner jobScanner = new Scanner(model.getJobs());
        List<Job> jobs = new ArrayList<>();

        for (int jobNumber = 1; jobScanner.hasNextLine(); jobNumber++) {
            String line = jobScanner.nextLine();
            List<String> lines = Splitter.on(PART_SEPARATOR).splitToList(line);

            int start = Integer.valueOf(lines.get(0));

            Scanner machineScanner = new Scanner(lines.get(1));
            Scanner timeScanner = new Scanner(lines.get(2));
            List<Task> tasks = new ArrayList<>();

            for (int taskNumber = 1; machineScanner.hasNextLine(); taskNumber++) {
                Task task = new Task(taskNumber, machineScanner.nextInt(), timeScanner.nextInt());
                tasks.add(task);
            }

            Job job = new Job(jobNumber, start, tasks);
            jobs.add(job);
        }

        JacopProvider jacopProvider;
        IndomainType indomainType = model.getIndomainTypeWrapper().getIndomainType();
        if (model.getSelectChoicePointTypeWrapper().isStore()) {
            SelectChoicePointStoreType selectChoicePointType = model.getSelectChoicePointTypeWrapper().getSelectChoicePointStoreType().get();
            jacopProvider = JacopProviders.simpleJacopProvider(indomainType, selectChoicePointType);
        } else {
            ComparatorVariableType comparatorVariableType = model.getComparatorVariableTypeWrapper().getComparatorVariableType();
            SelectChoicePointComparatorVariableType selectChoicePointComparatorVariableType = model.getSelectChoicePointTypeWrapper().getSelectChoicePointComparatorVariableType().get();
            jacopProvider = JacopProviders.comparatorVariableJacopProvider(indomainType, comparatorVariableType, selectChoicePointComparatorVariableType);
        }

        return new JobShopData(jobs, jacopProvider);
    }
}
