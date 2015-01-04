package com.po.constraintprogrammingsolver.problems.jobshop.factories;

import com.po.constraintprogrammingsolver.problems.jobshop.factories.costfunction.CostFunctionType;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.selectchoicepoint.SelectChoicePointStoreFactory;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.selectchoicepoint.SelectChoicePointStoreType;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.Indomain;
import org.jacop.search.SelectChoicePoint;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class SimpleJacopProvider extends AbstractJacopProvider {
    private final SelectChoicePointStoreType selectChoicePointType;

    public SimpleJacopProvider(IndomainType indomainType, SelectChoicePointStoreType selectChoicePointType, CostFunctionType costFunctionType) {
        super(indomainType, costFunctionType);

        this.selectChoicePointType = selectChoicePointType;
    }


    public SimpleJacopProvider(IndomainType indomainType, SelectChoicePointStoreType selectChoicePointType) {
        super(indomainType);

        this.selectChoicePointType = selectChoicePointType;
    }

    @Override
    public SelectChoicePoint<IntVar> getSelectChoicePoint(IntVar[] variables, Store store, Indomain<IntVar> indomain) {
        SelectChoicePointStoreFactory selectChoicePointFactory = new SelectChoicePointStoreFactory(variables, indomain, store);
        return selectChoicePointFactory.createJacopType(selectChoicePointType);
    }
}

