package com.po.constraintprogrammingsolver.problems.jobshop;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Aleksander on 2015-01-01.
 */
public class Job {
    private final int startTime;
    private final List<Task> tasks;

    private int jobNumber;

    public Job(int startTime, List<Task> tasks) {
        this.startTime = startTime;
        this.tasks = tasks;
        IntStream.range(1, tasks.size() + 1).forEach(i -> tasks.get(i - 1).setTaskNumber(i));
        tasks.forEach(task -> task.setJob(this));
    }

    public int numberOfTasks() {
        return tasks.size();
    }

    public Set<Integer> tasksNumbers() {
        return IntStream.range(1, numberOfTasks() + 1)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toSet());
    }

    public int getStartTime() {
        return startTime;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Optional<Integer> getJobNumber() {
        return Optional.of(jobNumber);
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        return jobNumber == job.jobNumber;

    }

    @Override
    public int hashCode() {
        return jobNumber;
    }
}
