package com.po.constraintprogrammingsolver.problems.jobshop.factories;

/**
 * Created by Aleksander on 2015-01-04.
 */
public interface JacopFactory<T extends Enum<T>, U> {
    public U createJacopType(T jacopType);
}
