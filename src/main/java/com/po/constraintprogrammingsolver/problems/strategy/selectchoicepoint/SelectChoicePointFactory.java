package com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint;

import org.jacop.core.IntVar;
import org.jacop.search.SelectChoicePoint;

/**
 * Created by Aleksander on 2015-01-21.
 */
public interface SelectChoicePointFactory<T extends Enum<T>> {
    public SelectChoicePoint<IntVar> createSelectChoicePoint(T selectChoicePointType);
}
