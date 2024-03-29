package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.StartGui;
import com.example.sportoAiksteliuRezervacija.controls.DbUtils;
import com.example.sportoAiksteliuRezervacija.ds.Court;
import com.example.sportoAiksteliuRezervacija.ds.Reservation;
import com.example.sportoAiksteliuRezervacija.ds.Schedule;
import com.example.sportoAiksteliuRezervacija.ds.User;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.CourtHibControl;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.ReservationHibControl;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.ScheduleHibControl;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.UserHibControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationWindow implements Initializable {
    @FXML
    public ListView reservationDateList;
    @FXML
    public Text courtNameField;
    @FXML
    public Text courtDescriptionField;
    @FXML
    public TextField bankAccountField;
    @FXML
    public TextField csvField;
    @FXML
    public TextField nameAndSurnameField;
    @FXML
    public DatePicker cardExpirationDateField;
    @FXML
    public ImageView imageView;

    private int courtId; //veli// au gausiu is paieskos lango
    private int userId;
    private Connection connection;
    private Statement statement;
    private boolean check = false;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    CourtHibControl courtHibControl = new CourtHibControl(entityManagerFactory);
    ReservationHibControl reservationHibControl = new ReservationHibControl(entityManagerFactory);
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);
    ScheduleHibControl scheduleHibControl = new ScheduleHibControl(entityManagerFactory);

    public void setCourtFormData(int userId, int courtId) {
        this.userId = userId;
        this.courtId = courtId;
        try {
            fillInputFieldsIfNotFirstUse();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fillReservationDateListTable();
        fillNameAndDescriptionFields();
    }

    public void fillReservationDateListTable() {
        reservationDateList.getItems().clear();
        Court selectedCourt = courtHibControl.getCourtById(courtId);
        List<Schedule> reservationDatesFromDb = selectedCourt.getSchedules();
        for (Schedule schedule : reservationDatesFromDb) {
            if (!schedule.getTaken()) {
                reservationDateList.getItems().add(joinDates(schedule.getEndDate(), schedule.getStartDate()));
            }
        }
    }

    private void fillNameAndDescriptionFields() {
        Court selectedCourt = courtHibControl.getCourtById(courtId);
        courtNameField.setText(selectedCourt.getName());
        courtDescriptionField.setText(selectedCourt.getDescription());
    }

    private void fillInputFieldsIfNotFirstUse() throws SQLException {
        connection = DbUtils.connectToDb();
        statement = connection.createStatement();
        String query = "SELECT userReservations_id FROM User_Reservation WHERE User_id = '" + userId + "'";
        ResultSet rs = statement.executeQuery(query);
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("userReservations_id");
        }
        DbUtils.disconnectFromDb(connection, statement);
        check = id != 0;
        if(courtHibControl.getCourtById(courtId).getPictureUrl() != null) {
            Image image = new Image(courtHibControl.getCourtById(courtId).getPictureUrl());
            imageView.setImage(image);
        }
        if (check) {
            Reservation reservation = reservationHibControl.getReservationById(id);
            nameAndSurnameField.setText(String.valueOf(reservation.getCardHolder()));
            bankAccountField.setText(String.valueOf(reservation.getCardNumber()));
            csvField.setText(String.valueOf(reservation.getCardCvc()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void saveReservationAndReserve(ActionEvent actionEvent) throws IOException {
        Court selectedCourt = courtHibControl.getCourtById(courtId);
        List<Schedule> reservationDatesFromDb = selectedCourt.getSchedules();
        List<String> selectedIntervalDates = reservationDateList.getSelectionModel().getSelectedItems();
        List<Reservation> userReservationList = new ArrayList<>();
        User user = userHibControl.getUserById(userId);

        if(validateAccountNumber(bankAccountField.getText())) {
            Reservation reservation = new Reservation(nameAndSurnameField.getText(), bankAccountField.getText(), Integer.parseInt(csvField.getText()), cardExpirationDateField.getValue(), selectedCourt);
            if (!check) {
                reservationHibControl.createReservation(reservation);
            }
            userReservationList.add(reservation);


            for (Schedule schedule : reservationDatesFromDb) {
                if (!schedule.getTaken()) {
                    for (String dateInterval : selectedIntervalDates) {
                        if ((joinDates(schedule.getEndDate(), schedule.getStartDate())).equals(dateInterval)) {
                            user.setUserReservations(userReservationList);
                            userHibControl.editUser(user);
                            schedule.setTaken(true);
                            scheduleHibControl.editSchedule(schedule);
                            alertMsg();
                            fillReservationDateListTable();
                        }
                    }
                }
            }
        }
    }

    public void backToMainWIndow(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //TODO paduoti userio id, kai bus sujungta(atkomentinti)
        MainWindow mainWindow = fxmlLoader.getController();
        mainWindow.setFormData(userId);
        Stage stage = (Stage) csvField.getScene().getWindow();
        stage.setTitle("Aikštelių rezervacijos sistema");
        stage.setScene(scene);
        stage.show();
    }

    public void alertMsg() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacija");
        alert.setHeaderText(null);
        alert.setContentText("Pasirinktas laikas užrezervuotas");

        alert.showAndWait();
    }

    public void validationAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Informacija");
        alert.setHeaderText(null);
        alert.setContentText("Neteisingai ivesti duomenys");

        alert.showAndWait();
    }
    public String joinDates(LocalDateTime firstDate, LocalDateTime secondDate) {
        return firstDate + " - " + secondDate;
    }

    public boolean validateAccountNumber(String accountNumber) {
        Pattern pattern  = Pattern.compile("\\b\\d{4}(| |-)\\d{4}\\1\\d{4}\\1\\d{4}\\b");
        Matcher matcher = pattern.matcher(accountNumber);
        if(!matcher.find()) {
            validationAlert();
            return false;
        }
        return true;
    }
}
