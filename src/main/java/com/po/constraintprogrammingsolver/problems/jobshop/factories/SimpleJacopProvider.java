package com.po.constraintprogrammingsolver.problems.jobshop.factories;

import com.po.constraintprogrammingsolver.problems.jobshop.factories.indomain.IndomainFactory;
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
public class SimpleJacopProvider implements JacopProvider {
    private final IndomainType indomainType;
    private final SelectChoicePointStoreType selectChoicePointType;

    private final IndomainFactory indomainFactory;

    private final Indomain<IntVar> indomain;

    public SimpleJacopProvider(IndomainType indomainType, SelectChoicePointStoreType selectChoicePointType) {
        this.indomainType = indomainType;
        this.selectChoicePointType = selectChoicePointType;

        indomainFactory = new IndomainFactory();
        indomain = indomainFactory.createJacopType(indomainType);
    }

    @Override
    public Indomain<IntVar> getIndomain() {
        return indomain;
    }

    @Override
    public SelectChoicePoint<IntVar> getSelectChoicePoint(IntVar[] variables, Store store, Indomain<IntVar> indomain) {
        SelectChoicePointStoreFactory selectChoicePointFactory = new SelectChoicePointStoreFactory(variables, indomain, store);
        return selectChoicePointFactory.createJacopType(selectChoicePointType);
    }
}

