package com.po.constraintprogrammingsolver.problems.strategy;

import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreFactory;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreType;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.SelectChoicePoint;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class SimpleJacopStrategyProvider extends AbstractJacopStrategyProvider {
    private final SelectChoicePointStoreType selectChoicePointType;

    public SimpleJacopStrategyProvider(IndomainType indomainType, SelectChoicePointStoreType selectChoicePointType) {
        super(indomainType);
        this.selectChoicePointType = selectChoicePointType;
    }

    @Override
    public SelectChoicePoint<IntVar> getSelectChoicePoint(IntVar[] variables, Store store) {
        SelectChoicePointStoreFactory selectChoicePointFactory = new SelectChoicePointStoreFactory(variables, indomain, store);
        return selectChoicePointFactory.createSelectChoicePoint(selectChoicePointType);
    }
}

