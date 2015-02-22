package com.po.constraintprogrammingsolver;

import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-25
 */
public enum Context {
    INSTANCE;

    private Stage stage;
    private ExecutorService executor;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }
}
