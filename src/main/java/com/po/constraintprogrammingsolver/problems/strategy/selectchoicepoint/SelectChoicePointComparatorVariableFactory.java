package com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint;

import org.jacop.core.IntVar;
import org.jacop.search.*;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class SelectChoicePointComparatorVariableFactory {
    private final IntVar[] variables;
    private final Indomain<IntVar> indomain;
    private final ComparatorVariable<IntVar> comparatorVariable;

    public SelectChoicePointComparatorVariableFactory(IntVar[] variables, Indomain<IntVar> indomain, ComparatorVariable<IntVar> comparatorVariable) {
        this.variables = variables;
        this.indomain = indomain;
        this.comparatorVariable = comparatorVariable;
    }

    public SelectChoicePoint<IntVar> createSelectChoicePoint(SelectChoicePointComparatorVariableType selectChoicePointType) {
        switch (selectChoicePointType) {
            case SIMPLE_SELECT:
                return new SimpleSelect<>(variables, comparatorVariable, indomain);
            case SPLIT_SELECT:
                return new SplitSelect<>(variables, comparatorVariable, indomain);
            default:
                throw new IllegalArgumentException();
        }
    }
}
