package com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;

/**
 * Created by Aleksander on 2015-01-24.
 */
public class DefaultJobShopBenchmarkValuesSupplier implements DefaultValuesSupplier {
    private final JobShopModel model;

    public DefaultJobShopBenchmarkValuesSupplier(JobShopModel model) {
        this.model = model;
    }

    @Override
    public void supplyDefaultValues() {

    }
}
