package com.po.constraintprogrammingsolver.problems.jobshop;

import org.jacop.core.IntVar;

import java.util.Optional;

/**
 * Represent a single task in Jobshop problem.
 * It has a unique number, which is set later. Normally it's empty {@link java.util.Optional}.
 * It has also a start time value, which is represent by {@link org.jacop.core.IntVar} and it's set later by {@link com.po.constraintprogrammingsolver.problems.jobshop.JobShopProblemSolver}.
 *
 * @author Aleksander Mielczarek
 * @since 2015-01-01
 */
public class Task {
    private final int machineNumber;
    private final int duration;

    private int taskNumber;
    private Job job;
    private IntVar startTimeVar;

    /**
     * Constructor
     *
     * @param machineNumber number of machine where task will be executed
     * @param duration      duration of task
     */
    public Task(int machineNumber, int duration) {
        this.machineNumber = machineNumber;
        this.duration = duration;
    }

    public Optional<Integer> startTime() {
        return Optional.of(startTimeVar.value());
    }

    public int getMachineNumber() {
        return machineNumber;
    }

    public int getDuration() {
        return duration;
    }

    public Optional<Integer> getTaskNumber() {
        return Optional.of(taskNumber);
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Optional<Job> getJob() {
        return Optional.of(job);
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Optional<IntVar> getStartTimeVar() {
        return Optional.of(startTimeVar);
    }

    public void setStartTimeVar(IntVar startTimeVar) {
        this.startTimeVar = startTimeVar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return taskNumber == task.taskNumber && job.equals(task.job);
    }

    @Override
    public int hashCode() {
        int result = taskNumber;
        result = 31 * result + job.hashCode();
        return result;
    }
}
