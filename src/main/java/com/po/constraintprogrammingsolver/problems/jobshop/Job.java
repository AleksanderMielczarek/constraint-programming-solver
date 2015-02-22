package com.po.constraintprogrammingsolver.problems.jobshop;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents single Job with start time and list of tasks. It has also a unique number, which is set later. Normally it's empty {@link java.util.Optional}
 *
 * @author Aleksander Mielczarek
 * @since 2015-01-01
 */
public class Job {
    private final int startTime;
    private final List<Task> tasks;

    private int jobNumber;

    /**
     * Constructor taking start time and tasks to do
     *
     * @param startTime time of start the job
     * @param tasks     {@link java.util.List} with all tasks to do
     */
    public Job(int startTime, List<Task> tasks) {
        this.startTime = startTime;
        this.tasks = tasks;
        IntStream.range(1, tasks.size() + 1).forEach(i -> tasks.get(i - 1).setTaskNumber(i));
        tasks.forEach(task -> task.setJob(this));
    }

    /**
     * @return number of tasks in task list
     */
    public int numberOfTasks() {
        return tasks.size();
    }

    /**
     * @return numbers of tasks on task list
     */
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
