package com.po.constraintprogrammingsolver.problems.jobshop.factories;

import com.po.constraintprogrammingsolver.problems.jobshop.JobShopTemporaryData;
import com.po.constraintprogrammingsolver.problems.jobshop.costfunction.CostFunction;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.Indomain;
import org.jacop.search.SelectChoicePoint;

import java.util.Optional;

/**
 * Created by Aleksander on 2015-01-04.
 */
public interface JacopProvider {
    public Indomain<IntVar> getIndomain();

    public SelectChoicePoint<IntVar> getSelectChoicePoint(IntVar[] variables, Store store, Indomain<IntVar> indomain);

    public Optional<CostFunction> getCostFunction();
}
