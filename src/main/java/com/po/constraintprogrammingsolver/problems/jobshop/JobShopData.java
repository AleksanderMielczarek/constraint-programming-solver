package com.po.constraintprogrammingsolver.problems.jobshop;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Input data in {@link com.po.constraintprogrammingsolver.problems.jobshop.JobShopProblemSolver}.
 * Specify jobs {@link com.po.constraintprogrammingsolver.problems.jobshop.Job} to do.
 *
 * @author Aleksander Mielczarek
 * @since 2015-01-03
 */
public class JobShopData {
    private final List<Job> jobs;

    /**
     * Constructor taking list of {@link com.po.constraintprogrammingsolver.problems.jobshop.Job} to do
     *
     * @param jobs {@link java.util.List} with all jobs to do
     */
    public JobShopData(List<Job> jobs) {
        this.jobs = jobs;
        IntStream.range(1, jobs.size() + 1).forEach(i -> jobs.get(i - 1).setJobNumber(i));
    }

    /**
     * All tasks that job has to do.
     *
     * @return {@link java.util.List} with all {@link com.po.constraintprogrammingsolver.problems.jobshop.Job}
     */
    public List<Task> tasks() {
        return jobs.stream()
                .map(Job::getTasks)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    /**
     * Tasks on machines. Return a map where key is machine number and value is a list of tasks on that machine
     *
     * @return map of tasks on machines
     */
    public Multimap<Integer, Task> tasksOnMachines() {
        Multimap<Integer, Task> taskMultimap = ArrayListMultimap.create();
        tasks().forEach(task -> taskMultimap.put(task.getMachineNumber(), task));
        return taskMultimap;
    }

    /**
     * Tasks on jobs. Return a map where key is Job and value is a list of tasks on that Job.
     *
     * @return map of tasks on jobs
     */
    public Multimap<Job, Task> tasksOnJobs() {
        Multimap<Job, Task> taskMultimap = ArrayListMultimap.create();
        jobs.forEach(job -> taskMultimap.putAll(job, job.getTasks()));
        return taskMultimap;
    }

    /**
     * Total end time of all jobs. It is counted as sum of total time of all single jobs.
     * Total time of a single job it is a sum of start time and durations of tasks.
     *
     * @return total end time
     */
    public int totalEndTime() {
        return jobs.stream()
                .mapToInt(job -> job.getStartTime() + job.getTasks().stream()
                        .mapToInt(Task::getDuration)
                        .sum())
                .sum();
    }

    /**
     * It's a minimal value of total time of all jobs.
     *
     * @return min end time
     */
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
