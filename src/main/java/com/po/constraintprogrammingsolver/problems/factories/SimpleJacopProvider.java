package com.po.constraintprogrammingsolver.problems.factories;

import com.po.constraintprogrammingsolver.problems.factories.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.factories.selectchoicepoint.SelectChoicePointStoreFactory;
import com.po.constraintprogrammingsolver.problems.factories.selectchoicepoint.SelectChoicePointStoreType;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.SelectChoicePoint;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class SimpleJacopProvider extends AbstractJacopProvider {
    private final SelectChoicePointStoreType selectChoicePointType;

    public SimpleJacopProvider(IndomainType indomainType, SelectChoicePointStoreType selectChoicePointType) {
        super(indomainType);

        this.selectChoicePointType = selectChoicePointType;
    }

    @Override
    public SelectChoicePoint<IntVar> getSelectChoicePoint(IntVar[] variables, Store store) {
        SelectChoicePointStoreFactory selectChoicePointFactory = new SelectChoicePointStoreFactory(variables, indomain, store);
        return selectChoicePointFactory.createJacopType(selectChoicePointType);
    }
}

