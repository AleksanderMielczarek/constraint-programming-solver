package com.po.constraintprogrammingsolver.gui.jobshop.service;

import com.po.constraintprogrammingsolver.Context;
import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.ValueUpdater;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

/**
 * Created by Aleksander on 2015-01-25.
 */
public class JobShopLoaderService extends Service<Void> implements ValueUpdater {
    private static final String ERROR_FILE_UPLOAD = "label.error.file.upload";
    private static final String FILE_CHOOSER_TITLE = "file.chooser.title";

    private final JobShopModel model;
    private final ResourceBundle resources;
    private final FileChooser fileChooser;

    public JobShopLoaderService(JobShopModel model, ResourceBundle resources) {
        this.model = model;
        this.resources = resources;
        this.fileChooser = createFileChooser();
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                FutureTask<File> futureTask = createFutureTask(fileChooser);
                valueUpdate(futureTask);
                File file = futureTask.get();

                if (file != null) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String job = reader.lines().collect(Collectors.joining(System.getProperty("line.separator")));
                        valueUpdate(model::setJobShopData, job);
                    } catch (IOException e) {
                        valueUpdate(model::setError, resources.getString(ERROR_FILE_UPLOAD));
                    }
                }

                return null;
            }
        };
    }

    private FileChooser createFileChooser() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle(resources.getString(FILE_CHOOSER_TITLE));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));

        return fileChooser;
    }

    private static FutureTask<File> createFutureTask(FileChooser fileChooser) {
        return new FutureTask<>(() -> fileChooser.showOpenDialog(Context.INSTANCE.getStage()));
    }
}
