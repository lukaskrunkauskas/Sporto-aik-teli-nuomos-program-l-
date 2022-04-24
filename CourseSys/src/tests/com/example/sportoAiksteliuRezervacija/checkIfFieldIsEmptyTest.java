package com.example.sportoAiksteliuRezervacija;

import com.example.sportoAiksteliuRezervacija.fxControllers.NewCourt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class checkIfFieldIsEmptyTest {
    @Test
    public void checkIfEmptyTest_1(){
        //arrange
        String fieldValue;
        fieldValue = "";
        NewCourt newCourt = new NewCourt();


        //act
        boolean actualResult = newCourt.checkIfEmpty(fieldValue);
        boolean expectedResult = true;

        //assert
        assertEquals(actualResult, expectedResult);
    }
    @Test
    public void checkIfEmptyTest_2(){
        //arrange
        String fieldValue;
        fieldValue = "testas";
        NewCourt newCourt = new NewCourt();

        //act
        boolean actualResult = newCourt.checkIfEmpty(fieldValue);
        boolean expectedResult = false;

        //assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkIfEmptyTest_3(){
        //arrange
        String fieldValue;
        fieldValue = "0.25 dd";
        NewCourt newCourt = new NewCourt();


        //act
        boolean actualResult = newCourt.checkIfEmpty(fieldValue);
        boolean expectedResult = false;

        //assert
        assertEquals(actualResult, expectedResult);
    }
    @Test
    public void checkIfEmptyTest_4(){
        //arrange
        String fieldValue;
        fieldValue = ".";
        NewCourt newCourt = new NewCourt();


        //act
        boolean actualResult = newCourt.checkIfEmpty(fieldValue);
        boolean expectedResult = false;

        //assert
        assertEquals(actualResult, expectedResult);
    }
}
