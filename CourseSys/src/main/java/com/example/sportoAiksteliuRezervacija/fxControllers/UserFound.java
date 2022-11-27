package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.StartGui;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.UserHibControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserFound implements Initializable {
    @FXML
    public TextField clientUsername;
    @FXML
    public TextField clientEmail;
    @FXML
    public TextField clientBio;
    @FXML
    public ImageView clientPicture;

    private int userId;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);

    public void setFormData(int userId, int pickedUserId) {
        this.userId = userId;
        this.clientUsername.setText(userHibControl.getUserById(pickedUserId).getUsername());
        this.clientEmail.setText(userHibControl.getUserById(pickedUserId).getEmail());
        this.clientBio.setText(userHibControl.getUserById(pickedUserId).getInformation());
        Image image = new Image(userHibControl.getUserById(pickedUserId).getPictureUrl());
        this.clientPicture.setImage(image);
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("profile-search-window.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        ProfileSearchWindow profileSearchWindow = fxmlLoader.getController();
        profileSearchWindow.setProfileSearchFormData(userId);
        Stage stage = (Stage) clientUsername.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
