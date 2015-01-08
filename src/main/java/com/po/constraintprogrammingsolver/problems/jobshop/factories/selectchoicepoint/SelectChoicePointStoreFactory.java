package com.po.constraintprogrammingsolver.problems.jobshop.factories.selectchoicepoint;

import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.Indomain;
import org.jacop.search.InputOrderSelect;
import org.jacop.search.SelectChoicePoint;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class SelectChoicePointStoreFactory implements SelectChoicePointFactory<SelectChoicePointStoreType> {
    private final IntVar[] variables;
    private final Indomain<IntVar> indomain;
    private final Store store;

    public SelectChoicePointStoreFactory(IntVar[] variables, Indomain<IntVar> indomain, Store store) {
        this.variables = variables;
        this.indomain = indomain;
        this.store = store;
    }

    @Override
    public SelectChoicePoint<IntVar> createJacopType(SelectChoicePointStoreType jacopType) {
        switch (jacopType) {
            case INPUT_ORDER_SELECT:
                return new InputOrderSelect<>(store, variables, indomain);
            default:
                throw new IllegalArgumentException();
        }
    }
}
