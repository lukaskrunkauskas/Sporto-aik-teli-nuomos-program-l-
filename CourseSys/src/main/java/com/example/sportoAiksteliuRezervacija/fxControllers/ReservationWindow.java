package com.example.sportoAiksteliuRezervacija.fxControllers;

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
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ReservationWindow implements Initializable {
    @FXML
    public ListView reservationDateList;
    @FXML
    public Text courtNameField;
    @FXML
    public TextField courtDescriptionField;
    @FXML
    public TextField bankAccountField;
    @FXML
    public TextField csvField;
    @FXML
    public TextField nameAndSurnameField;
    @FXML
    public DatePicker cardExpirationDateField;

    int courtId=1; //veli// au gausiu is paieskos lango
    int userId=1;
    private Connection connection;
    private Statement statement;
    private boolean check=false;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    CourtHibControl courtHibControl = new CourtHibControl(entityManagerFactory);
    ReservationHibControl reservationHibControl = new ReservationHibControl(entityManagerFactory);
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);
    ScheduleHibControl scheduleHibControl = new ScheduleHibControl(entityManagerFactory);

    private void fillReservationDateListTable() {
        reservationDateList.getItems().clear();
        Court selectedCourt = courtHibControl.getCourtById(courtId);
        List<Schedule> reservationDatesFromDb = selectedCourt.getSchedules();
        for(Schedule schedule : reservationDatesFromDb) {
            if(!schedule.getTaken()) {
                reservationDateList.getItems().add(schedule.getStartDate() + " - " + schedule.getEndDate());
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
        while(rs.next()) {
            id = rs.getInt("userReservations_id");
        }
        DbUtils.disconnectFromDb(connection, statement);
        check = id != 0;
        if(check) {
            Reservation reservation = reservationHibControl.getReservationById(id);
            nameAndSurnameField.setText(String.valueOf(reservation.getCardHolder()));
            bankAccountField.setText(String.valueOf(reservation.getCardNumber()));
            csvField.setText(String.valueOf(reservation.getCardCvc()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fillInputFieldsIfNotFirstUse();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fillReservationDateListTable();
        fillNameAndDescriptionFields();
    }

    public void saveReservationAndReserve(ActionEvent actionEvent) {
        Court selectedCourt = courtHibControl.getCourtById(courtId);
        List<Schedule> reservationDatesFromDb = selectedCourt.getSchedules();
        List<String> selectedIntervalDates = reservationDateList.getSelectionModel().getSelectedItems();
        List<Reservation> userReservationList = new ArrayList<>();
        User user = userHibControl.getUserById(userId);

        Reservation reservation = new Reservation(nameAndSurnameField.getText(), Integer.parseInt(bankAccountField.getText()), Integer.parseInt(csvField.getText()), cardExpirationDateField.getValue(), selectedCourt);
        if(!check) {
            reservationHibControl.createReservation(reservation);
        }
        userReservationList.add(reservation);


        for(Schedule schedule : reservationDatesFromDb) {
            if (!schedule.getTaken()) {
                for (String dateInterval : selectedIntervalDates) {
                    if ((schedule.getStartDate() + " - " + schedule.getEndDate()).equals(dateInterval)) {

                        scheduleHibControl.editSchedule(schedule);
                        user.setUserReservations(userReservationList);
                        userHibControl.editUser(user);
                        schedule.setTaken(true);
                    }
                }
            }
        }
    }
}