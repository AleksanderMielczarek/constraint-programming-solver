package com.po.constraintprogrammingsolver.problems.jobshop.factories.costfunction;

import com.po.constraintprogrammingsolver.problems.jobshop.costfunction.CostFunction;
import com.po.constraintprogrammingsolver.problems.jobshop.costfunction.MinMakespanCostFunction;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.JacopFactory;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class CostFunctionFactory implements JacopFactory<CostFunctionType, CostFunction> {
    @Override
    public CostFunction createJacopType(CostFunctionType jacopType) {
        switch (jacopType) {
            case MIN_MAKESPAN:
                return new MinMakespanCostFunction();
            default:
                throw new IllegalArgumentException();
        }
    }
}
