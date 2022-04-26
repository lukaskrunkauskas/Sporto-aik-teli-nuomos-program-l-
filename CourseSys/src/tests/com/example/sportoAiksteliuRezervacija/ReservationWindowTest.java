package com.example.sportoAiksteliuRezervacija;

import com.example.sportoAiksteliuRezervacija.fxControllers.ReservationWindow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationWindowTest {

    @Mock
    ReservationWindow reservationWindow;

    @BeforeEach
    public void setUp() {
        reservationWindow = new ReservationWindow();
    }



    @Test
    void fillNameAndDescriptionFieldTest() {
        // Mockito.verify(reservationWindow, Mockito.atLeastOnce()).fillReservationDateListTable();
    }

    @Test
    void joinDates() {
        LocalDateTime date = LocalDateTime.now();

        //assertEquals(reservationWindow.joinDates(date, date), date + " - " + date);
    }

    @Test
    void validateAccountNumber() {
        String accountNumber = "1234567890123456";
          assertTrue(reservationWindow.validateAccountNumber("1234567890123456"));
    }
}
