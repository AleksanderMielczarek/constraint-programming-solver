package com.po.constraintprogrammingsolver.problems.jobshop;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopData {
    private final List<Job> jobs;

    public JobShopData(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public List<Task> getTasks() {
        return jobs.stream()
                .map(Job::getTasks)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Task> getTasksOnMachine(int machine) {
        return getTasks().stream()
                .filter(task -> task.getMachine() == machine)
                .collect(Collectors.toList());
    }

    public List<Task> getTasksOnJob(int number) {
        return jobs.stream()
        .filter(job->job.getNumber()==number)
                .map(Job::getTasks)

    }
}
