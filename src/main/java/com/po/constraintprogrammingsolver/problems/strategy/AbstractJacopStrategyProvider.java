package com.po.constraintprogrammingsolver.problems.strategy;


import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainFactory;
import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType;
import org.jacop.core.IntVar;
import org.jacop.search.Indomain;

/**
 * Add indomain which is common for all implementations.
 *
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
abstract class AbstractJacopStrategyProvider implements JacopStrategyProvider {
    protected final Indomain<IntVar> indomain;

    /**
     * Constructor
     *
     * @param indomainType selected indomain
     */
    AbstractJacopStrategyProvider(IndomainType indomainType) {
        indomain = IndomainFactory.createIndomain(indomainType);
    }
}
