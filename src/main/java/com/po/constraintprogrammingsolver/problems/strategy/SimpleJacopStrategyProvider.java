package com.po.constraintprogrammingsolver.problems.strategy;

import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreFactory;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreType;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.SelectChoicePoint;

/**
 * Unlike {@link com.po.constraintprogrammingsolver.problems.strategy.ComparatorVariableJacopStrategyProvider}, this implementation uses only additional select choice point.
 *
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
public class SimpleJacopStrategyProvider extends AbstractJacopStrategyProvider {
    private final SelectChoicePointStoreType selectChoicePointType;

    /**
     * Constructor
     *
     * @param indomainType selected indomain
     * @param selectChoicePointType selected select choice point
     */
    public SimpleJacopStrategyProvider(IndomainType indomainType, SelectChoicePointStoreType selectChoicePointType) {
        super(indomainType);
        this.selectChoicePointType = selectChoicePointType;
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
        SelectChoicePointStoreFactory selectChoicePointFactory = new SelectChoicePointStoreFactory(variables, indomain, store);
        return selectChoicePointFactory.createSelectChoicePoint(selectChoicePointType);
    }
}

