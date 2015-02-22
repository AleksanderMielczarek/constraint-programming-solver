package com.po.constraintprogrammingsolver.problems.jobshop;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-03
 */
public class JobShopData {
    private final List<Job> jobs;

    public JobShopData(List<Job> jobs) {
        this.jobs = jobs;
        IntStream.range(1, jobs.size() + 1).forEach(i -> jobs.get(i - 1).setJobNumber(i));
    }

    public List<Task> tasks() {
        return jobs.stream()
                .map(Job::getTasks)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public Multimap<Integer, Task> tasksOnMachines() {
        Multimap<Integer, Task> taskMultimap = ArrayListMultimap.create();
        tasks().forEach(task -> taskMultimap.put(task.getMachineNumber(), task));
        return taskMultimap;
    }

    public Multimap<Job, Task> tasksOnJobs() {
        Multimap<Job, Task> taskMultimap = ArrayListMultimap.create();
        jobs.forEach(job -> taskMultimap.putAll(job, job.getTasks()));
        return taskMultimap;
    }

    public int totalEndTime() {
        return jobs.stream()
                .mapToInt(job -> job.getStartTime() + job.getTasks().stream()
                        .mapToInt(Task::getDuration)
                        .sum())
                .sum();
    }

    public int minEndTime() {
        return jobs.stream()
                .mapToInt(job -> job.getStartTime() + job.getTasks().stream()
                        .mapToInt(Task::getDuration)
                        .sum())
                .min().getAsInt();
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
