package com.po.constraintprogrammingsolver.problems.JobShop;

import com.po.constraintprogrammingsolver.problems.ProblemSolver;

/**
 * Created by Aleksander on 2014-12-03.
 */
public class JobShopProblemSolver implements ProblemSolver<String> {
    @Override
    public String solveProblem() {
        for (int i = 0; i < 90000000; i++) {
            int j = i + 1;
            int b = j / (i+1);
            Integer integer = new Integer(b);
            String test = String.valueOf(integer);
            String test2 = test + test + test;
        }
        System.out.println("test");
        return "test";
    }
}
