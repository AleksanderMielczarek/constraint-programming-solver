package com.po.constraintprogrammingsolver.problems.factories;

import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.SelectChoicePoint;

/**
 * Created by Aleksander on 2015-01-04.
 */
public interface JacopProvider {
    public SelectChoicePoint<IntVar> getSelectChoicePoint(IntVar[] variables, Store store);
}
