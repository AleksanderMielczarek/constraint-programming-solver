package com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.ValueUpdater;
import javafx.beans.property.ObjectProperty;
import javafx.scene.chart.XYChart;

import java.util.List;

/**
 * Created by Aleksander on 2015-01-24.
 */
public class DefaultJobShopBenchmarkValuesSupplier implements DefaultValuesSupplier, ValueUpdater {
    private final JobShopModel model;

    public DefaultJobShopBenchmarkValuesSupplier(JobShopModel model) {
        this.model = model;
    }

    @Override
    public void supplyDefaultValues() {
        valueUpdate(() ->
                model.getLineChartDataMap().values().stream()
                        .map(ObjectProperty::getValue)
                        .map(list -> list.get(0))
                        .map(XYChart.Series::getData)
                        .forEach(List::clear));
    }
}
