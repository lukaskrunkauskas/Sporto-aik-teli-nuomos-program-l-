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

public class ProfileWindow implements Initializable {
    @FXML
    public TextField nameField;
    @FXML
    public TextArea information;
    User user;
    private int userId = 1;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        User user = userHibControl.getUserById(userId);
        setData(userId, user);
    }

    public void setData(int userId, User user) {
        this.userId = userId;
        this.user = user;
        user = userHibControl.getUserById(user.getId());
        nameField.setText(user.getUsername());
        information.setText(user.getInformation());
        nameField.setEditable(false);
        information.setEditable(false);
    }

    public void back(ActionEvent actionEvent) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-window.fxml"));
//        Parent root = fxmlLoader.load();
//
//        MainWindow mainWindow = fxmlLoader.getController();
//        mainWindow.setMainWindow(userId);
//
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) nameField.getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
    }
}
