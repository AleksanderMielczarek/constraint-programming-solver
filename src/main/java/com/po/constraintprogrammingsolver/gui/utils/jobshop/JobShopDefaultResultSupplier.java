package com.po.constraintprogrammingsolver.gui.utils.jobshop;

import org.apache.commons.lang3.StringUtils;
import org.jfree.data.gantt.TaskSeriesCollection;

import java.util.function.Supplier;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopDefaultResultSupplier implements Supplier<JobShopResult> {
    @Override
    public JobShopResult get() {
        return new JobShopResult(new TaskSeriesCollection(), StringUtils.EMPTY, StringUtils.EMPTY);
    }
}