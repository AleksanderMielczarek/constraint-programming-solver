package com.po.constraintprogrammingsolver.problems.jobshop2;

/**
* Created by Aleksander on 2015-01-01.
*/
public class Task {
    private final int number;
    private final int machine;
    private final int time;
    private final String name;

    public static final String BASE_NAME = "Task";

    public Task(int number, int machine, int time) {
        this.number = number;
        this.machine = machine;
        this.time = time;
        name = BASE_NAME + number;
    }

    public int getNumber() {
        return number;
    }

    public int getMachine() {
        return machine;
    }

    public int getTime() {
        return time;
    }

    public String getName() {
        return name;
    }
}
