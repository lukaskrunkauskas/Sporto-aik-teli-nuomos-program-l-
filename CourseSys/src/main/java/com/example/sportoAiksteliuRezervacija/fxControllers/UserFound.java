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
    //public ImageView clientPicture;

    private int id;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);

    public void setFormData(int id) {
        this.id = id;
    }

    public UserFound() {
        this.clientUsername.setText(userHibControl.getUserById(id).getUsername());
        this.clientEmail.setText(userHibControl.getUserById(id).getEmail());
        this.clientBio.setText(userHibControl.getUserById(id).getInformation());
        //this.clientPicture = clientPicture;
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-window.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) clientUsername.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
