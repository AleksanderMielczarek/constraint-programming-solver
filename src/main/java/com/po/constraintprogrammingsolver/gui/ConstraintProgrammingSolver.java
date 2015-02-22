package com.po.constraintprogrammingsolver.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

/**
 * @author Aleksander Mielczarek
 * @since 2014-12-01
 */
public class ConstraintProgrammingSolver extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Context.INSTANCE.setStage(primaryStage);
        Context.INSTANCE.setExecutor(Executors.newSingleThreadExecutor());

        URL fxml = getClass().getResource("/fxml/ConstraintProgrammingSolver.fxml");
        ResourceBundle bundle = ResourceBundle.getBundle("bundle/bundle");
        FXMLLoader fxmlLoader = new FXMLLoader(fxml, bundle);
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
