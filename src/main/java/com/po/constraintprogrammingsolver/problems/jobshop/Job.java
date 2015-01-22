package com.po.constraintprogrammingsolver.problems.jobshop;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Aleksander on 2015-01-01.
 */
public class Job {
    private final int start;
    private final List<Task> tasks;

    public Job(int start, List<Task> tasks) {
        this.start = start;
        this.tasks = tasks;
    }

    public int numberOfTasks() {
        return tasks.size();
    }

    public Set<Integer> tasksNumbers() {
        return IntStream.range(1, numberOfTasks() + 1)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toSet());
    }

    public int getStart() {
        return start;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
