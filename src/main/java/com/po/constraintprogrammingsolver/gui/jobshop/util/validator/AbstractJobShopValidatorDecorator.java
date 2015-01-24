package com.po.constraintprogrammingsolver.gui.jobshop.util.validator;

/**
 * Created by Aleksander on 2015-01-24.
 */
public abstract class AbstractJobShopValidatorDecorator implements JobShopValidator {
    protected final JobShopValidator validator;

    public AbstractJobShopValidatorDecorator(JobShopValidator validator) {
        this.validator = validator;
    }
}
