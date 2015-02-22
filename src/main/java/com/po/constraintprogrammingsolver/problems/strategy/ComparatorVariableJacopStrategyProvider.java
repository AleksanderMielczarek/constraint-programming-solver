package com.po.constraintprogrammingsolver.problems.strategy;


import com.po.constraintprogrammingsolver.problems.strategy.comparatorvariable.ComparatorVariableFactory;
import com.po.constraintprogrammingsolver.problems.strategy.comparatorvariable.ComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointComparatorVariableFactory;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointComparatorVariableType;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.ComparatorVariable;
import org.jacop.search.SelectChoicePoint;

/**
 * It uses additional comparator variable and select choice point which depends on comparator variable.
 *
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
public class ComparatorVariableJacopStrategyProvider extends AbstractJacopStrategyProvider {
    private final SelectChoicePointComparatorVariableType selectChoicePointType;
    private final ComparatorVariable<IntVar> comparatorVariable;

    /**
     * Constructor
     *
     * @param indomainType           selected indomain
     * @param comparatorVariableType selected comparator variable
     * @param selectChoicePointType  selected select choice point
     */
    public ComparatorVariableJacopStrategyProvider(IndomainType indomainType, ComparatorVariableType comparatorVariableType, SelectChoicePointComparatorVariableType selectChoicePointType) {
        super(indomainType);
        this.selectChoicePointType = selectChoicePointType;
        this.comparatorVariable = ComparatorVariableFactory.createComparatorVariable(comparatorVariableType);
    }

    /**
     * From given variables occurring in commons Jacop use cases, returns select choice point.
     *
     * @param variables searched variables
     * @param store     store storing constraints
     * @return chosen select choice point strategy
     */
    @Override
    public SelectChoicePoint<IntVar> getSelectChoicePoint(IntVar[] variables, Store store) {
        SelectChoicePointComparatorVariableFactory selectChoicePointFactory = new SelectChoicePointComparatorVariableFactory(variables, indomain, comparatorVariable);
        return selectChoicePointFactory.createSelectChoicePoint(selectChoicePointType);
    }
}
