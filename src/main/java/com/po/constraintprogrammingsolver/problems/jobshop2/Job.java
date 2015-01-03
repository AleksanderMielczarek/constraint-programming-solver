package com.po.constraintprogrammingsolver.problems.jobshop2;

import java.util.List;

/**
* Created by Aleksander on 2015-01-01.
*/
public class Job {
    private final int number;
    private final int start;
    private final List<Task> tasks;
    private final String name;

    public static final String BASE_NAME = "Job";

    public Job(int number, int start, List<Task> tasks) {
        this.number = number;
        this.start = start;
        this.tasks = tasks;
        name = BASE_NAME + number;
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

    public String getName() {
        return name;
    }
}
