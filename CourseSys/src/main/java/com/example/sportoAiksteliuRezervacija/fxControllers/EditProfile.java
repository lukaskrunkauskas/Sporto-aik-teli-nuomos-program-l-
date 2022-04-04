package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.StartGui;
import com.example.sportoAiksteliuRezervacija.ds.User;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.UserHibControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfile implements Initializable {
    @FXML
    public TextField name;
    @FXML
    public TextField email;
    @FXML
    public TextField password;
    @FXML
    public TextArea information;
    User user;
    private int userId;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public TextArea getInformation() {
        return information;
    }

    public void setInformation(TextArea information) {
        this.information = information;
    }

    public void confirmChange(ActionEvent actionEvent) {
        user.setUsername(name.getText());
        user.setPassword(password.getText());
        user.setEmail(email.getText());
        user.setInformation(information.getText());
        userHibControl.editUser(user);
    }

    public void setEditProfile(int userId) {
            this.userId = userId;
            User user = userHibControl.getUserById(userId);
            setData(userId, user);
    }

    public void setData(int userId, User user) {
        this.userId = userId;
        this.user = user;
        user = userHibControl.getUserById(user.getId());
        name.setText(user.getUsername());
        information.setText(user.getInformation());
        email.setText(user.getEmail());
        password.setText(user.getPassword());
    }

    public void returnProfile(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("your-profile-window.fxml"));
        Parent root = fxmlLoader.load();
        YourProfileWindow yourProfileWindow = fxmlLoader.getController();
        yourProfileWindow.setYourProfileWindow(userId);
        Scene scene = new Scene(root);
        Stage stage = (Stage) name.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
