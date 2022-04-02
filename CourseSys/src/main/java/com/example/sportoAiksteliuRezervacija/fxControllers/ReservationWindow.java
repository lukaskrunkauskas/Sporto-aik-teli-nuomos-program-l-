package com.example.sportoAiksteliuRezervacija.fxControllers;

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
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

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
    public TextField nameField;
    @FXML
    public TextField surnameField;
    @FXML
    public DatePicker cardExpirationDateField;

    int courtId=1; //veliau gausiu is paieskos lango
    int userId=1;

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

    private void fillInputFieldsIfNotFirstUse() {
        Court selectedCourt = courtHibControl.getCourtById(courtId);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillReservationDateListTable();
        fillNameAndDescriptionFields();
    }

    public void confirmAndReserve(ActionEvent actionEvent) {
        Court selectedCourt = courtHibControl.getCourtById(courtId);
        List<Schedule> reservationDatesFromDb = selectedCourt.getSchedules();
        List<String> selectedIntervalDates = reservationDateList.getSelectionModel().getSelectedItems();

        for(Schedule schedule : reservationDatesFromDb) {
            if (!schedule.getTaken()) {
                for (String dateInterval : selectedIntervalDates) {
                    if ((schedule.getStartDate() + " - " + schedule.getEndDate()).equals(dateInterval)) {
                        schedule.setTaken(true);
                        scheduleHibControl.editSchedule(schedule);
                    }
                }
            }
        }
    }
}
