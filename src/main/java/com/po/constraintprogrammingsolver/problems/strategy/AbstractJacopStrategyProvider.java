package com.po.constraintprogrammingsolver.problems.strategy;


import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainFactory;
import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType;
import org.jacop.core.IntVar;
import org.jacop.search.Indomain;

/**
 * Created by Aleksander on 2015-01-04.
 */
public abstract class AbstractJacopStrategyProvider implements JacopStrategyProvider {
    protected final Indomain<IntVar> indomain;

    public AbstractJacopStrategyProvider(IndomainType indomainType) {
        indomain = IndomainFactory.createIndomain(indomainType);
    }
}
