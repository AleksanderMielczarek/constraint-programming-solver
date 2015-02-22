package com.po.constraintprogrammingsolver.problems.strategy.comparatorvariable;

import org.jacop.core.IntVar;
import org.jacop.search.*;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
public class ComparatorVariableFactory {
    private ComparatorVariableFactory() {
    }

    public static ComparatorVariable<IntVar> createComparatorVariable(ComparatorVariableType comparatorVariableType) {
        switch (comparatorVariableType) {
            case LARGEST_DOMAIN:
                return new LargestDomain<>();
            case LARGEST_MAX:
                return new LargestMax<>();
            case LARGEST_MIN:
                return new LargestMin<>();
            case MAX_REGRET:
                return new MaxRegret<>();
            case MIN_DOMAIN_OVER_DEGREE:
                return new MinDomainOverDegree<>();
            case MOST_CONSTRAINED_DYNAMIC:
                return new MostConstrainedDynamic<>();
            case MOST_CONSTRAINED_STATIC:
                return new MostConstrainedStatic<>();
            case SMALLEST_DOMAIN:
                return new SmallestDomain<>();
            case SMALLEST_MAX:
                return new SmallestMax<>();
            case SMALLEST_MIN:
                return new SmallestMin<>();
            case WEIGHTED_DEGREE:
                return new WeightedDegree<>();
            default:
                throw new IllegalArgumentException();
        }
    }
}
