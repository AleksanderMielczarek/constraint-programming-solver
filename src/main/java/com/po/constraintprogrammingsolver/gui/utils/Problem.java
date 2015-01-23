package com.po.constraintprogrammingsolver.gui.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Aleksander on 2014-12-20.
 */
public enum Problem {
    TRUCKS("trucks"), JOB_SHOP("jobshop");

    private final String id;

    Problem(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private final static Map<String, Problem> problemMap;

    static {
        problemMap = Arrays.stream(values())
                .collect(Collectors.toMap(Problem::getId, problem -> problem));
    }

    public static Problem valueOfId(String id) {
        return problemMap.get(id);
    }
}
