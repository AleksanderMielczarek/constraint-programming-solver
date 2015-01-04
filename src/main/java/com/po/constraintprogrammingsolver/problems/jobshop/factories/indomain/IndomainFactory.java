package com.po.constraintprogrammingsolver.problems.jobshop.factories.indomain;

import com.po.constraintprogrammingsolver.problems.jobshop.factories.JacopFactory;
import org.jacop.core.IntVar;
import org.jacop.search.*;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class IndomainFactory implements JacopFactory<IndomainType, Indomain<IntVar>> {
    @Override
    public Indomain<IntVar> createJacopType(IndomainType jacopType) {
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
