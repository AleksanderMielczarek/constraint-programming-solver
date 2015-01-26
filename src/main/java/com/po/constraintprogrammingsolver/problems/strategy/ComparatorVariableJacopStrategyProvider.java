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
 * Created by Aleksander on 2015-01-04.
 */
public class ComparatorVariableJacopStrategyProvider extends AbstractJacopStrategyProvider {
    private final SelectChoicePointComparatorVariableType selectChoicePointType;
    private final ComparatorVariable<IntVar> comparatorVariable;

    public ComparatorVariableJacopStrategyProvider(IndomainType indomainType, ComparatorVariableType comparatorVariableType, SelectChoicePointComparatorVariableType selectChoicePointType) {
        super(indomainType);
        this.selectChoicePointType = selectChoicePointType;
        this.comparatorVariable = ComparatorVariableFactory.createComparatorVariable(comparatorVariableType);
    }

    @Override
    public SelectChoicePoint<IntVar> getSelectChoicePoint(IntVar[] variables, Store store) {
        SelectChoicePointComparatorVariableFactory selectChoicePointFactory = new SelectChoicePointComparatorVariableFactory(variables, indomain, comparatorVariable);
        return selectChoicePointFactory.createSelectChoicePoint(selectChoicePointType);
    }
}
