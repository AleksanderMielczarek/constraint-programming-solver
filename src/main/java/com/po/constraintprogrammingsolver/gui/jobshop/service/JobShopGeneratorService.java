package com.po.constraintprogrammingsolver.gui.jobshop.service;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.ValueUpdater;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Aleksander on 2015-01-25.
 */
public class JobShopGeneratorService extends Service<Void> implements ValueUpdater {
    private static final String MESSAGE_READY = "message.ready";

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String PART_SEPARATOR = ";";
    private static final String NUMBER_SEPARATOR = " ";

    private static final int MIN_JOB_START = 0;
    private static final int MAX_JOB_START = 20;
    private static final int MIN_NUMBER_OF_MACHINES = 4;
    private static final int MAX_NUMBER_OF_MACHINES = 6;
    private static final int MIN_TASK_DURATION = 5;
    private static final int MAX_TASK_DURATION = 20;
    private static final int MIN_NUMBER_OF_JOBS = 4;
    private static final int MAX_NUMBER_OF_JOBS = 6;

    private static final Random generator = new Random();
    private static final PrimitiveIterator.OfInt jobStartGenerator = generator.ints(MIN_JOB_START, MAX_JOB_START + 1).iterator();
    private static final PrimitiveIterator.OfInt numberOfMachinesGenerator = generator.ints(MIN_NUMBER_OF_MACHINES, MAX_NUMBER_OF_MACHINES + 1).iterator();
    private static final PrimitiveIterator.OfInt numberOfMachineGenerator = generator.ints(1, MAX_NUMBER_OF_MACHINES + 1).iterator();
    private static final PrimitiveIterator.OfInt taskDurationGenerator = generator.ints(MIN_TASK_DURATION, MAX_TASK_DURATION + 1).iterator();
    private static final PrimitiveIterator.OfInt numberOfJobsGenerator = generator.ints(MIN_NUMBER_OF_JOBS, MAX_NUMBER_OF_JOBS + 1).iterator();

    private final JobShopModel model;
    private final ResourceBundle resources;

    public JobShopGeneratorService(JobShopModel model, ResourceBundle resources) {
        this.model = model;
        this.resources = resources;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                StringBuilder builder = new StringBuilder();

                int numberOfJobs = numberOfJobsGenerator.nextInt();

                for (int i = 1; i <= numberOfJobs; i++) {
                    //start time
                    int jobStart = jobStartGenerator.nextInt();

                    //machines
                    int numberOfMachines = numberOfMachinesGenerator.nextInt();
                    Set<Integer> machines = new HashSet<>(numberOfMachines);
                    do {
                        machines.add(numberOfMachineGenerator.nextInt());
                    } while (machines.size() < numberOfMachines);

                    String machineLine = machines.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(NUMBER_SEPARATOR));

                    //times
                    List<Integer> times = new ArrayList<>(numberOfMachines);
                    do {
                        times.add(taskDurationGenerator.nextInt());
                    } while (times.size() < numberOfMachines);

                    String timeLine = times.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(NUMBER_SEPARATOR));

                    //build line
                    builder.append(jobStart)
                            .append(PART_SEPARATOR)
                            .append(machineLine)
                            .append(PART_SEPARATOR)
                            .append(timeLine)
                            .append(LINE_SEPARATOR);
                }

                valueUpdate(model::setJobShopData, builder.toString());

                updateMessage(resources.getString(MESSAGE_READY));
                updateProgress(1, 1);
                return null;
            }
        };
    }

}
