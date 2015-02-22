package com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint;

import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.Indomain;
import org.jacop.search.InputOrderSelect;
import org.jacop.search.SelectChoicePoint;

/**
 * Factory creating {@link org.jacop.search.SelectChoicePoint}.
 * Unlike in {@link com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointComparatorVariableFactory}
 * these values don't depends on {@link org.jacop.search.ComparatorVariable}.
 *
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
public class SelectChoicePointStoreFactory {
    private final IntVar[] variables;
    private final Indomain<IntVar> indomain;
    private final Store store;

    /**
     * Constructor
     *
     * @param variables searched variables
     * @param indomain  selected indomain
     * @param store     store storing constraints
     */
    public SelectChoicePointStoreFactory(IntVar[] variables, Indomain<IntVar> indomain, Store store) {
        this.variables = variables;
        this.indomain = indomain;
        this.store = store;
    }

    /**
     * Create {@link org.jacop.search.SelectChoicePoint}
     *
     * @param selectChoicePointType selected select choice point
     * @return {@link org.jacop.search.SelectChoicePoint}
     */
    public SelectChoicePoint<IntVar> createSelectChoicePoint(SelectChoicePointStoreType selectChoicePointType) {
        switch (selectChoicePointType) {
            case INPUT_ORDER_SELECT:
                return new InputOrderSelect<>(store, variables, indomain);
            default:
                throw new IllegalArgumentException();
        }
    }
}
