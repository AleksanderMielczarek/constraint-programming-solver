package com.po.constraintprogrammingsolver.problems.jobshop;

import java.util.List;

/**
* Created by Aleksander on 2015-01-01.
*/
public class Job {
    private final int number;
    private final int start;
    private final List<Task> tasks;

    public Job(int number, int start, List<Task> tasks) {
        this.number = number;
        this.start = start;
        this.tasks = tasks;
    }

    public int getNumber() {
        return number;
    }

    public int getStart() {
        return start;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
