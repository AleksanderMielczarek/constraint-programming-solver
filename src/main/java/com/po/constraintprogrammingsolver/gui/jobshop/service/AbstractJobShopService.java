package com.po.constraintprogrammingsolver.gui.jobshop.service;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.util.JobShopValidator;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2015-01-24.
 */
public abstract class AbstractJobShopService extends Service<Void> {
    private static final String MESSAGE_VALIDATION= "message.validation";

    protected final JobShopModel model;
    protected final ResourceBundle resources;
    protected double step = 1;

    protected double numberOfSteps;

    private final JobShopValidator validator;

    public AbstractJobShopService(JobShopModel model, ResourceBundle resources) {
        this.model = model;
        this.resources = resources;
        this.validator = new JobShopValidator(model, resources);
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                numberOfSteps = numberOfSteps();
                updateMessage(resources.getString(MESSAGE_VALIDATION));
                if (!validator.validate()) {
                    updateProgress(1, 1);
                    return null;
                }
                updateProgress(step++, numberOfSteps);

                runTask();

                return null;
            }
        };
    }

    protected abstract void runTask();

    protected abstract double numberOfSteps();
}
