package com.po.constraintprogrammingsolver.problems.jobshop;

import java.util.Optional;

/**
 * Created by Aleksander on 2015-01-01.
 */
public class Task {
    private final int machine;
    private final int duration;
    private Optional<Integer> startTime;

    public Task(int machine, int duration) {
        this.machine = machine;
        this.duration = duration;
        this.startTime = Optional.empty();
    }

    public Task(int machine, int startTime, int duration) {
        this.machine = machine;
        this.duration = duration;
        this.startTime = Optional.of(duration);
    }

    public int getMachine() {
        return machine;
    }

    public int getDuration() {
        return duration;
    }

    public Optional<Integer> getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = Optional.of(startTime);
    }
}
