package com.po.constraintprogrammingsolver.gui.jobshop.service;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.ValueUpdater;
import com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue.DefaultValuesSupplier;
import com.po.constraintprogrammingsolver.gui.jobshop.util.validator.JobShopDataValidator;
import com.po.constraintprogrammingsolver.gui.jobshop.util.validator.JobShopValidator;
import javafx.concurrent.Service;

import java.util.ResourceBundle;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-24
 */
public abstract class AbstractJobShopService extends Service<Void> implements ValueUpdater {
    protected static final String MESSAGE_VALIDATION = "message.validation";

    protected final JobShopModel model;
    protected final ResourceBundle resources;
    protected final DefaultValuesSupplier defaultValuesSupplier;
    protected double step = 1;

    protected double numberOfSteps;

    protected final JobShopValidator validator;

    public AbstractJobShopService(JobShopModel model, ResourceBundle resources, DefaultValuesSupplier defaultValuesSupplier) {
        this.model = model;
        this.resources = resources;
        this.defaultValuesSupplier = defaultValuesSupplier;
        this.validator = new JobShopDataValidator(model, resources);
    }

    protected void setNumberOfSteps(double numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }
}
