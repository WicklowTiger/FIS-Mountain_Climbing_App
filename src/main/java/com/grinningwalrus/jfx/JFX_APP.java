package com.grinningwalrus.jfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JFX_APP extends Application {
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new Pane(), 800, 600));
        stage.show();
    }
}
