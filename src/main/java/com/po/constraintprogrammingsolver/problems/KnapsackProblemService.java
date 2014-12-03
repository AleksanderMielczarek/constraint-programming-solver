package com.po.constraintprogrammingsolver.problems;

import com.google.common.base.Stopwatch;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.concurrent.TimeUnit;

/**
 * Created by Aleksander on 2014-12-03.
 */
public class KnapsackProblemService extends Service<String> {
    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                Stopwatch stopwatch = Stopwatch.createStarted();

                for (int i = 0; i <= 90000000; i++) {
                    updateProgress(i, 90000000);
                }

                StringBuilder stringBuilder = new StringBuilder("Total time [ms]: ")
                        .append(stopwatch.elapsed(TimeUnit.MILLISECONDS))
                        .append(System.getProperty("line.separator"))
                        .append("knapsack");

                return stringBuilder.toString();
            }
        };
    }
}
