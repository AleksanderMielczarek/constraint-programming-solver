package com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint;

import org.jacop.core.IntVar;
import org.jacop.search.*;

/**
 * Factory creating {@link org.jacop.search.SelectChoicePoint} depending on {@link org.jacop.search.ComparatorVariable}.
 *
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
public class SelectChoicePointComparatorVariableFactory {
    private final IntVar[] variables;
    private final Indomain<IntVar> indomain;
    private final ComparatorVariable<IntVar> comparatorVariable;

    /**
     * Constructor
     *
     * @param variables          searched variables
     * @param indomain           selected indomain
     * @param comparatorVariable selected comparator variable
     */
    public SelectChoicePointComparatorVariableFactory(IntVar[] variables, Indomain<IntVar> indomain, ComparatorVariable<IntVar> comparatorVariable) {
        this.variables = variables;
        this.indomain = indomain;
        this.comparatorVariable = comparatorVariable;
    }

    /**
     * Create {@link org.jacop.search.SelectChoicePoint}
     *
     * @param selectChoicePointType selected select choice point
     * @return {@link org.jacop.search.SelectChoicePoint}
     */
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
