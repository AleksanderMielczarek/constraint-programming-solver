package com.po.constraintprogrammingsolver.gui.jobshop.service;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.util.ValueUpdater;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2015-01-25.
 */
public class JobShopGeneratorService extends Service<Void> implements ValueUpdater {
    private final JobShopModel model;
    private final ResourceBundle resources;

    public JobShopGeneratorService(JobShopModel model, ResourceBundle resources) {
        this.model = model;
        this.resources = resources;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println("test");
                return null;
            }
        };
    }

}
