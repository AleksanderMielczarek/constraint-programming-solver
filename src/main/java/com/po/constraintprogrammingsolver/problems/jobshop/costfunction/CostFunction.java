package com.po.constraintprogrammingsolver.problems.jobshop.costfunction;

import com.po.constraintprogrammingsolver.problems.jobshop.JobShopTemporaryData;
import org.jacop.core.IntVar;

/**
 * Created by Aleksander on 2015-01-04.
 */
public interface CostFunction {
    public IntVar getCostFunction(JobShopTemporaryData temporaryData);
}
