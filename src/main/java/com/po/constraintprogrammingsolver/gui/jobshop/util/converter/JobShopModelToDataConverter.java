package com.po.constraintprogrammingsolver.gui.jobshop.util.converter;

import com.google.common.base.Splitter;
import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.problems.jobshop.Job;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopData;
import com.po.constraintprogrammingsolver.problems.jobshop.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopModelToDataConverter {
    public static final String PART_SEPARATOR = ";";
    public static final String NUMBER_SEPARATOR = " ";

    private final JobShopModel model;

    public JobShopModelToDataConverter(JobShopModel model) {
        this.model = model;
    }

    public JobShopData convert() {
        Scanner jobScanner = new Scanner(model.getJobShopData());
        List<Job> jobs = new ArrayList<>();

        while (jobScanner.hasNextLine()) {
            String line = jobScanner.nextLine();
            List<String> lines = Splitter.on(PART_SEPARATOR).splitToList(line);

            int start = Integer.valueOf(lines.get(0));

            Scanner machineScanner = new Scanner(lines.get(1));
            Scanner timeScanner = new Scanner(lines.get(2));
            List<Task> tasks = new ArrayList<>();

            while (machineScanner.hasNextInt()) {
                Task task = new Task(machineScanner.nextInt(), timeScanner.nextInt());
                tasks.add(task);
            }

            Job job = new Job(start, tasks);
            jobs.add(job);
        }

        return new JobShopData(jobs);
    }
}
