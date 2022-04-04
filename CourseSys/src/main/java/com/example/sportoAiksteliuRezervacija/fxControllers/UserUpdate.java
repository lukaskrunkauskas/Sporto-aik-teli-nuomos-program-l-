package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.StartGui;
import com.example.sportoAiksteliuRezervacija.ds.User;
import com.example.sportoAiksteliuRezervacija.ds.enums.UserType;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.UserHibControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserUpdate implements Initializable {

    @FXML
    public TextField usernameField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField passwordField;
    @FXML
    public TextField pictureUrlField;
    @FXML
    public ComboBox userTypeComboBox;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);
    ObservableList<String> observableListComboUserType = FXCollections.observableArrayList();

    private int userId;
    private int myId;

    public void setCourtFormData(int id, int userId) {
        this.userId = id;
        this.myId = userId;
        usernameField.setText(userHibControl.getUserById(this.userId).getUsername());
        emailField.setText(userHibControl.getUserById(this.userId).getEmail());
        passwordField.setText(userHibControl.getUserById(this.userId).getPassword());
        pictureUrlField.setText(userHibControl.getUserById(this.userId).getPictureUrl());
        userTypeComboBox.getSelectionModel().select(userHibControl.getUserById(this.userId).getUserType());

    }

    public void backButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("all-users.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        AllUsers allUsers = fxmlLoader.getController();
        allUsers.setCourtFormData(myId);


        Stage stage = (Stage) pictureUrlField.getScene().getWindow();
        stage.setTitle("Users List");
        stage.setScene(scene);
        stage.show();


    }

    public void submitButton(ActionEvent actionEvent) throws IOException {
        User user = userHibControl.getUserById(userId);
        user.setEmail(emailField.getText());
        user.setUsername(usernameField.getText());
        user.setPassword(passwordField.getText());
        user.setPictureUrl(pictureUrlField.getText());
        user.setUserType(UserType.valueOf(userTypeComboBox.getSelectionModel().getSelectedItem().toString()));
        userHibControl.editUser(user);
        backButton();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableListComboUserType.add("USER");
        observableListComboUserType.add("ADMIN");

        userTypeComboBox.setItems(observableListComboUserType);
    }
}
