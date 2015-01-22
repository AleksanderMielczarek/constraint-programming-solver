package com.po.constraintprogrammingsolver.problems.jobshop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksander on 2015-01-01.
 */
public class Job {
    private final int start;
    private final ArrayList<Task> tasks = new ArrayList<>();

    public Job(int start, List<Task> tasks) {
        this.start = start;
        this.tasks.addAll(tasks);
    }

    public Job(int start) {
        this.start = start;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addTask(int number, Task task) {
        tasks.add(number - 1, task);
    }

    public void addTasks(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void removeTask(int number) {
        tasks.remove(number - 1);
    }

    public void removeTasks(List<Task> tasks1) {

    }

    public int numberOfTasks() {
        return tasks.size();
    }

    public int getStart() {
        return start;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (start != job.start) return false;
        if (!tasks.equals(job.tasks)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = start;
        result = 31 * result + tasks.hashCode();
        return result;
    }
}
