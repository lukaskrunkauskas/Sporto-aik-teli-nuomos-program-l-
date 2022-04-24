package com.example.sportoAiksteliuRezervacija;

import com.example.sportoAiksteliuRezervacija.fxControllers.NewCourt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class checkIfNumericTest {

    @Test
    public void checkIfNumeric_1(){
        //arrange
        String fieldValue;
        fieldValue = "";
        NewCourt newCourt = new NewCourt();


        //act
        boolean actualResult = newCourt.isNumeric(fieldValue);
        boolean expectedResult = false;

        //assert
        assertEquals(actualResult, expectedResult);
    }
    @Test
    public void checkIfNumeric_2(){
        //arrange
        String fieldValue;
        fieldValue = "dsadqw";
        NewCourt newCourt = new NewCourt();


        //act
        boolean actualResult = newCourt.isNumeric(fieldValue);
        boolean expectedResult = false;

        //assert
        assertEquals(actualResult, expectedResult);
    }
    @Test
    public void checkIfNumeric_3(){
        //arrange
        String fieldValue;
        fieldValue = "1";
        NewCourt newCourt = new NewCourt();


        //act
        boolean actualResult = newCourt.isNumeric(fieldValue);
        boolean expectedResult = true;

        //assert
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkIfNumeric_4(){
        //arrange
        String fieldValue;
        fieldValue = "1.54";
        NewCourt newCourt = new NewCourt();


        //act
        boolean actualResult = newCourt.isNumeric(fieldValue);
        boolean expectedResult = true;

        //assert
        assertEquals(actualResult, expectedResult);
    }
    @Test
    public void checkIfNumeric_5(){
        //arrange
        String fieldValue;
        fieldValue = "-5";
        NewCourt newCourt = new NewCourt();


        //act
        boolean actualResult = newCourt.isNumeric(fieldValue);
        boolean expectedResult = false;

        //assert
        assertEquals(actualResult, expectedResult);
    }
}
