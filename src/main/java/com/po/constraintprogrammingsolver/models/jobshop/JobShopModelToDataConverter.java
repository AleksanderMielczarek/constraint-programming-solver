package com.po.constraintprogrammingsolver.models.jobshop;

import com.google.common.base.Splitter;
import com.po.constraintprogrammingsolver.problems.jobshop2.Job;
import com.po.constraintprogrammingsolver.problems.jobshop2.JobShopData;
import com.po.constraintprogrammingsolver.problems.jobshop2.Task;

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

        return new JobShopData(jobs, model.getIndomain());
    }
}
