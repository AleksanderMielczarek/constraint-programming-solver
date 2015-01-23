package com.po.constraintprogrammingsolver.problems.jobshop;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.List;
import java.util.Optional;
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
        IntStream.range(1, jobs.size() + 1).forEach(i -> jobs.get(i - 1).setJobNumber(i));
    }

    public int numberOfJobs() {
        return jobs.size();
    }

    public Set<Integer> jobsNumbers() {
        return jobs.stream()
                .map(Job::getJobNumber)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    public List<Task> tasks() {
        return jobs.stream()
                .map(Job::getTasks)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public Set<Integer> machinesNumbers() {
        return tasks().stream()
                .map(Task::getMachineNumber)
                .collect(Collectors.toSet());
    }

    public List<Task> tasksOnMachine(int machine) {
        return tasks().stream()
                .filter(task -> task.getMachineNumber() == machine)
                .collect(Collectors.toList());
    }

    public Multimap<Integer, Task> tasksOnMachines() {
        Multimap<Integer, Task> taskMultimap = ArrayListMultimap.create();
        machinesNumbers().forEach(machine -> taskMultimap.putAll(machine, tasksOnMachine(machine)));
        return taskMultimap;
    }

    public List<Task> tasksOnJob(int number) {
        return jobs.get(number - 1).getTasks();

    }

    public Multimap<Job, Task> tasksOnJobs() {
        Multimap<Job, Task> taskMultimap = ArrayListMultimap.create();
        jobs.forEach(job -> taskMultimap.putAll(job, job.getTasks()));
        return taskMultimap;
    }

    public Multimap<Integer, Task> tasksOnJobsNumbers() {
        Multimap<Integer, Task> taskMultimap = ArrayListMultimap.create();
        jobsNumbers().forEach(job -> taskMultimap.putAll(job, tasksOnMachine(job)));
        return taskMultimap;
    }

    public int maxEndTime() {
        return jobs.stream()
                .mapToInt(job -> job.getStartTime() + job.getTasks().stream()
                        .mapToInt(Task::getDuration)
                        .sum())
                .sum();
    }

    public int minStartTime() {
        return jobs.stream()
                .mapToInt(Job::getStartTime)
                .min().getAsInt();
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
