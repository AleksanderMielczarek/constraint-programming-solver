package com.po.constraintprogrammingsolver.problems.jobshop;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopData {
    private final List<Job> jobs;

    public JobShopData(List<Job> jobs) {
        this.jobs = jobs;
    }

    public int numberOfJobs() {
        return jobs.size();
    }

    public Set<Integer> jobsNumbers() {
        return IntStream.range(1, numberOfJobs() + 1)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toSet());
    }

    public List<Task> tasks() {
        return jobs.stream()
                .map(Job::getTasks)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public Set<Integer> machines() {
        return tasks().stream().map(Task::getMachine).collect(Collectors.toSet());
    }

    public List<Task> tasksOnMachine(int machine) {
        return tasks().stream()
                .filter(task -> task.getMachine() == machine)
                .collect(Collectors.toList());
    }

    public List<Task> tasksOnJob(int number) {
        return jobs.get(number - 1).getTasks();

    }

    public int maxEndTime() {
        return jobs.stream()
                .mapToInt(job -> job.getStart() + job.getTasks().stream()
                        .mapToInt(Task::getDuration)
                        .sum())
                .sum();
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
