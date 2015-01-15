package com.po.constraintprogrammingsolver.problems.factories.selectchoicepoint;

import com.po.constraintprogrammingsolver.problems.factories.JacopFactory;
import org.jacop.core.IntVar;
import org.jacop.search.SelectChoicePoint;

/**
 * Created by Aleksander on 2015-01-04.
 */
public interface SelectChoicePointFactory<T extends Enum<T>> extends JacopFactory<T, SelectChoicePoint<IntVar>> {
}
