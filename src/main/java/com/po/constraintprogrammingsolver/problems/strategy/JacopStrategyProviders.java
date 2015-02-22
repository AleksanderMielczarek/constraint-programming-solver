package com.po.constraintprogrammingsolver.problems.strategy;

import com.po.constraintprogrammingsolver.problems.strategy.comparatorvariable.ComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreType;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
public class JacopStrategyProviders {
    private JacopStrategyProviders() {
    }

    public static JacopStrategyProvider simpleJacopStrategyProvider(IndomainType indomainType, SelectChoicePointStoreType selectChoicePointType) {
        return new SimpleJacopStrategyProvider(indomainType, selectChoicePointType);
    }

    public static JacopStrategyProvider comparatorVariableJacopStrategyProvider(IndomainType indomainType, ComparatorVariableType comparatorVariableType, SelectChoicePointComparatorVariableType selectChoicePointType) {
        return new ComparatorVariableJacopStrategyProvider(indomainType, comparatorVariableType, selectChoicePointType);
    }

}