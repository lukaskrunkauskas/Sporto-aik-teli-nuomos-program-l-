package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.EmailService.EmailSendingService;
import com.example.sportoAiksteliuRezervacija.StartGui;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.UserHibControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class ConfirmWindow {
    @FXML
    public TextField codeF;
    private int userId;
    String code;
    String dbCode;

    EmailSendingService emailSendingService = new EmailSendingService();

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);

    public void setUserFormData(int id) throws IOException {
        if (!isNull(id)) {
            this.userId = id;
            codeF.setText(userHibControl.getUserById(userId).getRandomCode());
        } else {
            LoginWindow.alertMessage("Blogas naudotojo id perdavimas tarp langu");
            returnToPrevious();
        }
    }

    public static boolean isNull(int id) {
        return id == 0;
    }

    public void checkCode(ActionEvent actionEvent) throws IOException {
        code = codeF.getText();
        dbCode = userHibControl.getUserById(userId).getRandomCode();
        if (isCorrect(code, dbCode)) {
            LoginWindow.alertMessage("Naudotojo paskyra sėkmingai aktyvuota!");
            returnToPrevious();
        } else {
            LoginWindow.alertMessage("Blogas paskyros aktyvavimo kodas!");
        }
    }

    public void reSend(ActionEvent actionEvent) {

    }

    private void returnToPrevious() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("login-window.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) codeF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public static boolean isCorrect(String code, String dbCode) {
        return code.equals(dbCode);
    }
}
