package com.po.constraintprogrammingsolver.problems.jobshop.costfunction;

import com.po.constraintprogrammingsolver.problems.jobshop.JobShopTemporaryData;
import org.jacop.core.IntVar;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class MinMakespanCostFunction implements CostFunction {
    @Override
    public IntVar getCostFunction(JobShopTemporaryData temporaryData) {
        return new IntVar(temporaryData.getStore(), 1, 2);
    }
}
