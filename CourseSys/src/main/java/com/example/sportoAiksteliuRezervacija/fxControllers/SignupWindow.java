package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.StartGui;
import com.example.sportoAiksteliuRezervacija.ds.User;
import com.example.sportoAiksteliuRezervacija.ds.enums.UserType;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.UserHibControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class SignupWindow {
    @FXML
    public TextField loginF;
    @FXML
    public PasswordField pswF;
    @FXML
    public TextField emailF;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);

    public void createUser(ActionEvent actionEvent) throws IOException {
        if (loginF.getText().length() > 10) {
            LoginWindow.alertMessage("Naudotojo slapyvardis turi būti ne ilgesnis nei 10 simbolių ilgio");
        } else if (pswF.getText().length() < 5 && pswF.getText().length() > 15) {
            LoginWindow.alertMessage("Naudotojo slaptažodžio ilgis turi būti tarp 5 ir 15 simbolių ilgio");
        } else {
            String generatedString = getAlphaNumericString(10);
            User user = new User(loginF.getText(), pswF.getText(), emailF.getText(), UserType.USER, generatedString);
            userHibControl.createUser(user);
            LoginWindow.alertMessage("Naudotojo paskyra sėkmingai sukurta");

            FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("confirm-window.fxml"));
            Parent root = fxmlLoader.load();

            ConfirmWindow confirmWindow = fxmlLoader.getController();
            confirmWindow.setUserFormData(user.getId());

            Scene scene = new Scene(root);

            Stage stage = (Stage) pswF.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    private void returnToPrevious() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("login-window.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) pswF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void returnToLogin(ActionEvent actionEvent) throws IOException {
        returnToPrevious();
    }

    static String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
