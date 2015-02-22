package com.po.constraintprogrammingsolver.problems.strategy;

import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.SelectChoicePoint;

/**
 * In Jacop in order to solve any problem {@link org.jacop.search.Search} must be initialized.
 * In version 4.1.0 only one implementation exists: {@link org.jacop.search.DepthFirstSearch}.
 * To start labeling, user has to supply following objects:
 * <ul>
 * <li>{@link org.jacop.search.SelectChoicePoint}</li>
 * <li>searched variables</li>
 * <li>optional cost</li>
 * </ul>
 * {@link org.jacop.search.SelectChoicePoint} has many different implementations depending on other interfaces.
 * Using this interfaces and appropriate implementations, it simplifies process of selecting one.
 *
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
public interface JacopStrategyProvider {
    /**
     * From given variables occurring in commons Jacop use cases, returns select choice point.
     *
     * @param variables searched variables
     * @param store     store storing constraints
     * @return chosen select choice point strategy
     */
    public SelectChoicePoint<IntVar> getSelectChoicePoint(IntVar[] variables, Store store);
}
