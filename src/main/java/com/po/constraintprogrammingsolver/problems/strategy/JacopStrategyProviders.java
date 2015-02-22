package com.po.constraintprogrammingsolver.problems.strategy;

import com.po.constraintprogrammingsolver.problems.strategy.comparatorvariable.ComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreType;

/**
 * Class contains factory methods for creating {@link com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider}
 *
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
public class JacopStrategyProviders {
    private JacopStrategyProviders() {
    }

    /**
     * Create {@link com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider}
     *
     * @param indomainType          selected indomain
     * @param selectChoicePointType selected select choice point
     * @return {@link com.po.constraintprogrammingsolver.problems.strategy.SimpleJacopStrategyProvider}
     */
    public static JacopStrategyProvider simpleJacopStrategyProvider(IndomainType indomainType, SelectChoicePointStoreType selectChoicePointType) {
        return new SimpleJacopStrategyProvider(indomainType, selectChoicePointType);
    }

    /**
     * Create {@link com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider}
     *
     * @param indomainType           selected indomain
     * @param comparatorVariableType selected comparator variable
     * @param selectChoicePointType  selected select choice point
     * @return {@link com.po.constraintprogrammingsolver.problems.strategy.ComparatorVariableJacopStrategyProvider}
     */
    public static JacopStrategyProvider comparatorVariableJacopStrategyProvider(IndomainType indomainType, ComparatorVariableType comparatorVariableType, SelectChoicePointComparatorVariableType selectChoicePointType) {
        return new ComparatorVariableJacopStrategyProvider(indomainType, comparatorVariableType, selectChoicePointType);
    }

}