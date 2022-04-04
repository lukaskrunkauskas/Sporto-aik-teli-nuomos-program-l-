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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindow implements Initializable {
    @FXML
    public TextField loginF;
    @FXML
    public PasswordField pswF;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);

    public void signIn(ActionEvent actionEvent) throws IOException {
        User user = userHibControl.getUserByLoginData(loginF.getText(), pswF.getText());
        if (user != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-window.fxml"));
            Parent root = fxmlLoader.load();

            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setCourseFormData(user.getId());

            Scene scene = new Scene(root);

            Stage stage = (Stage) loginF.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            alertMessage("Wrong input data, no such user found!");
        }
    }

    private void alertMessage(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Message text:");
        alert.setContentText(s);
        alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait();
    }

    public void signUp(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
