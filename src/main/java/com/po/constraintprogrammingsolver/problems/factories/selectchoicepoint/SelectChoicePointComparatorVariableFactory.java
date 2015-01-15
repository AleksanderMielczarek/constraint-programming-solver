package com.po.constraintprogrammingsolver.problems.factories.selectchoicepoint;

import org.jacop.core.IntVar;
import org.jacop.search.*;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class SelectChoicePointComparatorVariableFactory implements SelectChoicePointFactory<SelectChoicePointComparatorVariableType> {
    private final IntVar[] variables;
    private final Indomain<IntVar> indomain;
    private final ComparatorVariable<IntVar> comparatorVariable;

    public SelectChoicePointComparatorVariableFactory(IntVar[] variables, Indomain<IntVar> indomain, ComparatorVariable<IntVar> comparatorVariable) {
        this.variables = variables;
        this.indomain = indomain;
        this.comparatorVariable = comparatorVariable;
    }

    @Override
    public SelectChoicePoint<IntVar> createJacopType(SelectChoicePointComparatorVariableType jacopType) {
        switch (jacopType) {
            case SIMPLE_SELECT:
                return new SimpleSelect<>(variables, comparatorVariable, indomain);
            case SPLIT_SELECT:
                return new SplitSelect<>(variables, comparatorVariable, indomain);
            default:
                throw new IllegalArgumentException();
        }
    }
}
