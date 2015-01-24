package com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.util.ValueUpdater;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Aleksander on 2015-01-24.
 */
public class DefaultJobShopProblemResultValuesSupplier implements DefaultValuesSupplier, ValueUpdater {
    private final JobShopModel model;

    public DefaultJobShopProblemResultValuesSupplier(JobShopModel model) {
        this.model = model;
    }

    @Override
    public void supplyDefaultValues() {
        valueUpdate(model::setJobShopResult, StringUtils.EMPTY);

        valueUpdate(model::setCost, 0);
        valueUpdate(model::setBacktracks, 0);
        valueUpdate(model::setDecisions, 0);
        valueUpdate(model::setMaximumDepth, 0);
        valueUpdate(model::setNodes, 0);
        valueUpdate(model::setWrongDecisions, 0);
        valueUpdate(model::setTime, 0l);

        valueUpdate(() -> model.getTaskSeriesCollection().clear());
        //valueUpdate(() -> model.getTaskSeriesCollection().remove(0, model.getTaskSeriesCollection().size()));
    }
}
