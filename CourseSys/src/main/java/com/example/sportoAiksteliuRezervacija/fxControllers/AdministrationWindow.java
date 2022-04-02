package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.StartGui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministrationWindow {

    @FXML
    public Button newCourtButton;

    public void newCourtButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("new-court.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) newCourtButton.getScene().getWindow();
        stage.setTitle("Add New Court");
        stage.setScene(scene);
        stage.show();
    }

    public void backButton(ActionEvent actionEvent) {
        //Kai bus paruostas main window.
    }

    public void courtListButton(ActionEvent actionEvent) {
    }

    public void userListButton(ActionEvent actionEvent) {
    }
}
