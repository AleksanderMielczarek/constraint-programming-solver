package com.po.constraintprogrammingsolver.problems.strategy.indomain;

import org.jacop.core.IntVar;
import org.jacop.search.*;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
public class IndomainFactory {
    private IndomainFactory() {
    }

    public static Indomain<IntVar> createIndomain(IndomainType jacopType) {
        switch (jacopType) {
            case INDOMAIN_MIN:
                return new IndomainMin<>();
            case INDOMAIN_MAX:
                return new IndomainMax<>();
            case INDOMAIN_MIDDLE:
                return new IndomainMiddle<>();
            case INDOMAIN_RANDOM:
                return new IndomainRandom<>();
            case INDOMAIN_MEDIAN:
                return new IndomainMedian<>();
            case INDOMAIN_SIMPLE_RANDOM:
                return new IndomainSimpleRandom<>();
            default:
                throw new IllegalArgumentException();
        }
    }
}
