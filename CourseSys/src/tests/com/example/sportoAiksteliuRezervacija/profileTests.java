package com.example.sportoAiksteliuRezervacija;

import com.example.sportoAiksteliuRezervacija.fxControllers.EditProfile;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class profileTests {


    @Mock
    EditProfile editProfile = new EditProfile();

    @Test
    public void emailValidationTest1() {
        String emailAddress = "username@domain.com";
        assertTrue(editProfile.isValidEmail(emailAddress));
    }

    @Test
    public void emailValidationTest2() {
        String emailAddress = "username@domain..com";
        assertFalse(editProfile.isValidEmail(emailAddress));
    }

    @Test
    public void emailValidationTest3() {
        String emailAddress = "username.domain.com";
        assertFalse(editProfile.isValidEmail(emailAddress));
    }

    @Test
    public void passwordValidationTest1() {
        String password = "Arkliukas5+";
        assertTrue(editProfile.isValidPassword(password));
    }

    @Test
    public void passwordValidationTest2() {
        String password = "Arklys=";
        assertFalse(editProfile.isValidPassword(password));
    }

    @Test
    public void passwordValidationTest3() {
        String password = "Zirgogalva123";
        assertFalse(editProfile.isValidPassword(password));
    }

    @Test
    public void nameValidationTest1() {
        String name = "Labas";
        assertTrue(editProfile.checkIfValid(name));
    }

    @Test
    public void nameValidationTest2() {
        String name = "";
        assertFalse(editProfile.checkIfValid(name));
    }
}

