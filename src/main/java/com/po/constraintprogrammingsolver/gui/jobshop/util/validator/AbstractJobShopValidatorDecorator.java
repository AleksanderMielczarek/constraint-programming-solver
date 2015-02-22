package com.po.constraintprogrammingsolver.gui.jobshop.util.validator;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-24
 */
public abstract class AbstractJobShopValidatorDecorator implements JobShopValidator {
    protected final JobShopValidator validator;

    public AbstractJobShopValidatorDecorator(JobShopValidator validator) {
        this.validator = validator;
    }
}
