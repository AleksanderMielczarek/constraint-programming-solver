package com.po.constraintprogrammingsolver.problems.strategy.comparatorvariable;

/**
 * Types of supported {@link org.jacop.search.ComparatorVariable}.
 *
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
public enum ComparatorVariableType {
    LARGEST_DOMAIN,
    LARGEST_MAX,
    LARGEST_MIN,
    MAX_REGRET,
    MIN_DOMAIN_OVER_DEGREE,
    MOST_CONSTRAINED_DYNAMIC,
    MOST_CONSTRAINED_STATIC,
    SMALLEST_DOMAIN,
    SMALLEST_MAX,
    SMALLEST_MIN,
    WEIGHTED_DEGREE
}
