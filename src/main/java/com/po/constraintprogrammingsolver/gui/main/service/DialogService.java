package com.po.constraintprogrammingsolver.gui.main.service;

import com.po.constraintprogrammingsolver.gui.ValueUpdater;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2015-01-26.
 */
public class DialogService extends Service<Void> implements ValueUpdater {
    private static final String DIALOG_TITLE = "dialog.title";
    private static final String DIALOG_CONTENT = "dialog.content";

    private final ResourceBundle resources;
    private final Alert dialog;

    public DialogService(ResourceBundle resources) {
        this.resources = resources;
        this.dialog = createDialog();
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                valueUpdate(dialog::show);
                return null;
            }
        };
    }

    private Alert createDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(resources.getString(DIALOG_TITLE));
        alert.setHeaderText(null);
        alert.setContentText(StringEscapeUtils.unescapeJava(resources.getString(DIALOG_CONTENT)));
        alert.setResizable(true);

        return alert;
    }
}
