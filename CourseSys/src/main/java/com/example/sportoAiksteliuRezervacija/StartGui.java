package com.example.sportoAiksteliuRezervacija;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartGui extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("login-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Prisijungimas");
        stage.setScene(scene);
        stage.show();
    }
}
