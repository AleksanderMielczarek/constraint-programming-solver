package com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.ValueUpdater;
import com.po.constraintprogrammingsolver.gui.jobshop.util.data.JobShopTestData;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.ComparatorVariableTypeWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.IndomainTypeWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.SelectChoicePointTypeWrapper;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-23
 */
public class DefaultInitValuesSupplier implements DefaultValuesSupplier, ValueUpdater {
    private static final JobShopTestData DEFAULT_JOBS = JobShopTestData.DATA_1;
    private static final IndomainTypeWrapper DEFAULT_INDOMAIN = IndomainTypeWrapper.INDOMAIN_MIN_WRAPPER;
    private static final SelectChoicePointTypeWrapper DEFAULT_SELECT_CHOICE_POINT = SelectChoicePointTypeWrapper.INPUT_ORDER_SELECT_WRAPPER;
    private static final ComparatorVariableTypeWrapper DEFAULT_COMPARATOR_VARIABLE = ComparatorVariableTypeWrapper.SMALLEST_MIN_WRAPPER;
    private static final String DEFAULT_REPETITIONS = "10";

    private final JobShopModel model;

    public DefaultInitValuesSupplier(JobShopModel model) {
        this.model = model;
    }

    @Override
    public void supplyDefaultValues() {
        valueUpdate(model::setJobShopTestData, DEFAULT_JOBS);
        valueUpdate(model::setIndomain, DEFAULT_INDOMAIN);
        valueUpdate(model::setSelectChoicePoint, DEFAULT_SELECT_CHOICE_POINT);
        valueUpdate(model::setComparatorVariable, DEFAULT_COMPARATOR_VARIABLE);
        valueUpdate(model::setRepetitions, DEFAULT_REPETITIONS);
    }
}
