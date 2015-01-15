package com.po.constraintprogrammingsolver.problems.factories;


import com.po.constraintprogrammingsolver.problems.factories.indomain.IndomainFactory;
import com.po.constraintprogrammingsolver.problems.factories.indomain.IndomainType;
import org.jacop.core.IntVar;
import org.jacop.search.Indomain;

/**
 * Created by Aleksander on 2015-01-04.
 */
public abstract class AbstractJacopProvider implements JacopProvider {
    protected final Indomain<IntVar> indomain;

    public AbstractJacopProvider(IndomainType indomainType) {
        IndomainFactory indomainFactory = new IndomainFactory();
        indomain = indomainFactory.createJacopType(indomainType);
    }
}
