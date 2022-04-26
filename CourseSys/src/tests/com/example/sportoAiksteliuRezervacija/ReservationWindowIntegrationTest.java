package com.example.sportoAiksteliuRezervacija;


import com.example.sportoAiksteliuRezervacija.ds.Court;
import com.example.sportoAiksteliuRezervacija.ds.Reservation;
import com.example.sportoAiksteliuRezervacija.ds.Schedule;
import com.example.sportoAiksteliuRezervacija.ds.User;
import com.example.sportoAiksteliuRezervacija.ds.enums.UserType;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.ReservationHibControl;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.ScheduleHibControl;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.UserHibControl;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationWindowIntegrationTest {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);
    ReservationHibControl reservationHibControl = new ReservationHibControl(entityManagerFactory);
    ScheduleHibControl scheduleHibControl = new ScheduleHibControl(entityManagerFactory);

//    @Test
//    public void userUpdateTest() {
//        String cardHolder = "Petras";
//        Reservation reservation = reservationHibControl.getReservationById(1);
//        List<Reservation> reservationList = new ArrayList<>();
//        reservation.setCardHolder(cardHolder);
//        reservationList.add(reservation);
//        User user = userHibControl.getUserById(1);
//        user.setUserReservations(reservationList);
//        userHibControl.editUser(user);
//
//        assertEquals(userHibControl.getUserById(1).getUserReservations(), reservationList);
//    }

    @Test
    public void scheduleUpdateTest() {
        boolean scheduleStatus = true;
        Schedule schedule = scheduleHibControl.getScheduleById(1);
        schedule.setTaken(scheduleStatus);

        assertEquals(scheduleHibControl.getScheduleById(1).getTaken(), scheduleStatus);

    }

    @Test
    public void reservationUpdateTest() {
        String cardHolder = "Petras";
        Reservation reservation = reservationHibControl.getReservationById(1);
        reservation.setCardHolder(cardHolder);
        reservationHibControl.editReservation(reservation);

        assertEquals(reservationHibControl.getReservationById(1).getCardHolder(), cardHolder);
    }

}
