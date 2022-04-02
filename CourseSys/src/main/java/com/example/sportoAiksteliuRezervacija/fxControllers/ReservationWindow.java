package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.ds.Court;
import com.example.sportoAiksteliuRezervacija.ds.Schedule;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.CourtHibControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;
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
    public TextField expirationDateField;

    int courtId=1; //veliau gausiu is paieskos lango

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    CourtHibControl courtHibControl = new CourtHibControl(entityManagerFactory);

    private void fillReservationDateListTable() {
        reservationDateList.getItems().clear();
        Court selectedCourt = courtHibControl.getCourtById(courtId);
        List<Schedule> reservationDatesFromDb = selectedCourt.getSchedules();
        for(Schedule schedule : reservationDatesFromDb) {
            System.out.println("+");
            if(!schedule.getTaken()) {
                reservationDateList.getItems().add(schedule.getStartDate() + " - " + schedule.getEndDate());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillReservationDateListTable();
    }
}
