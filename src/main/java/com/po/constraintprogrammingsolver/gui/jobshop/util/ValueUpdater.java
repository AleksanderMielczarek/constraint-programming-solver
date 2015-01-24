package com.po.constraintprogrammingsolver.gui.jobshop.util;

import javafx.application.Platform;

import java.util.function.Consumer;

/**
 * Created by Aleksander on 2015-01-24.
 */
public interface ValueUpdater {
    public default <T> void valueUpdate(Consumer<T> consumer, T value) {
        if (Platform.isFxApplicationThread()) {
            consumer.accept(value);
        } else {
            Platform.runLater(() -> consumer.accept(value));
        }
    }

    public default void valueUpdate(Runnable action){
        if (Platform.isFxApplicationThread()) {
            action.run();
        } else {
            Platform.runLater(action);
        }
    }

}
