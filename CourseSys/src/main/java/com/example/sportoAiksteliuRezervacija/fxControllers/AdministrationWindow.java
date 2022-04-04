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

    private int userId;

    public void setCourtFormData(int id){
        this.userId = id;
    }


    @FXML
    public Button newCourtButton;

    public void newCourtButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("new-court.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        NewCourt newCourt = fxmlLoader.getController();
        newCourt.setCourtFormData(userId);

        Stage stage = (Stage) newCourtButton.getScene().getWindow();
        stage.setTitle("Add New Court");
        stage.setScene(scene);
        stage.show();
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MainWindow mainWindow = fxmlLoader.getController();
        mainWindow.setCourtFormData(userId);

        Stage stage = (Stage) newCourtButton.getScene().getWindow();
        stage.setTitle("All Courts");
        stage.setScene(scene);
        stage.show();
    }

    public void courtListButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("all-courts.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        AllCourts allCourts = fxmlLoader.getController();
        allCourts.setCourtFormData(userId);

        Stage stage = (Stage) newCourtButton.getScene().getWindow();
        stage.setTitle("All Courts");
        stage.setScene(scene);
        stage.show();


    }

    public void userListButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("all-users.fxml"));
        Scene scene = new Scene(fxmlLoader.load());


        AllUsers allUsers = fxmlLoader.getController();
        allUsers.setCourtFormData(userId);


        Stage stage = (Stage) newCourtButton.getScene().getWindow();
        stage.setTitle("Add New Court");
        stage.setScene(scene);
        stage.show();
    }
}
