package com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.util.ValueUpdater;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.ComparatorVariableTypeWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.IndomainTypeWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.SelectChoicePointTypeWrapper;

/**
 * Created by Aleksander on 2015-01-23.
 */
public class DefaultInitValuesSupplier implements DefaultValuesSupplier, ValueUpdater {
    private static final String DEFAULT_JOBS = new StringBuilder("10;2 1 3 4;10 5 15 5")
            .append(System.getProperty("line.separator"))
            .append("5;3 2 1 4;10 5 5 10")
            .append(System.getProperty("line.separator"))
            .append("0;1 3 4 2;5 5 5 5")
            .append(System.getProperty("line.separator"))
            .append("5;3 2 4;5 10 15")
            .toString();
    private static final IndomainTypeWrapper DEFAULT_INDOMAIN = IndomainTypeWrapper.INDOMAIN_MIN;
    private static final SelectChoicePointTypeWrapper DEFAULT_SELECT_CHOICE_POINT = SelectChoicePointTypeWrapper.INPUT_ORDER_SELECT;
    private static final ComparatorVariableTypeWrapper DEFAULT_COMPARATOR_VARIABLE = ComparatorVariableTypeWrapper.SMALLEST_MIN;

    private final JobShopModel model;

    public DefaultInitValuesSupplier(JobShopModel model) {
        this.model = model;
    }

    @Override
    public void supplyDefaultValues() {
        valueUpdate(model::setJobShopData, DEFAULT_JOBS);
        valueUpdate(model::setIndomain, DEFAULT_INDOMAIN);
        valueUpdate(model::setSelectChoicePoint, DEFAULT_SELECT_CHOICE_POINT);
        valueUpdate(model::setComparatorVariable, DEFAULT_COMPARATOR_VARIABLE);
    }
}
