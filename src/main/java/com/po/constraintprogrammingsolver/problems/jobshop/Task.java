package com.po.constraintprogrammingsolver.problems.jobshop;

/**
 * Created by Aleksander on 2015-01-01.
 */
public class Task {
    private final int machine;
    private final int time;

    public Task(int machine, int time) {
        this.machine = machine;
        this.time = time;
    }

    public int getMachine() {
        return machine;
    }

    public int getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (machine != task.machine) return false;
        if (time != task.time) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = machine;
        result = 31 * result + time;
        return result;
    }
}
