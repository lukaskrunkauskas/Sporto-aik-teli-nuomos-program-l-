package com.example.sportoAiksteliuRezervacija;

import com.example.sportoAiksteliuRezervacija.ds.Court;
import com.example.sportoAiksteliuRezervacija.ds.enums.CityType;
import com.example.sportoAiksteliuRezervacija.ds.enums.CourtType;
import com.example.sportoAiksteliuRezervacija.fxControllers.MainWindow;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class filterByCity {

    @Test
    public void filterByCity_1(){
        String comboBox;
        List<Court> courtList = new ArrayList<>();
        Court court1 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.VILNIUS, CourtType.KREPSINIS, 2.5, "pvz1", Collections.emptyList());
        Court court2 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KAUNAS, CourtType.LAUKO_FUTBOLAS, 2.5, "pvz1", Collections.emptyList());
        Court court3 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KLAIPEDA, CourtType.LAUKO_TINKLINIS, 2.5, "pvz1", Collections.emptyList());
        Court court4 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.SIAULIAI, CourtType.LAUKO_TENISAS, 2.5, "pvz1", Collections.emptyList());
        Court court5 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.PANEVEZYS, CourtType.STALO_TENISAS, 2.5, "pvz1", Collections.emptyList());
        Court court6 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.VILNIUS, CourtType.MANIEZAS, 2.5, "pvz1", Collections.emptyList());
        Court court7 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KAUNAS, CourtType.SALES_FUTBOLAS, 2.5, "pvz1", Collections.emptyList());
        courtList.add(court1);
        courtList.add(court2);
        courtList.add(court3);
        courtList.add(court4);
        courtList.add(court5);
        courtList.add(court6);
        courtList.add(court7);
        comboBox = "VISI";
        MainWindow mainWindow = new MainWindow();
        assertEquals(courtList, mainWindow.filterByCityForTest(courtList, comboBox));
    }

    @Test
    public void filterByCity_2(){
        String comboBox;
        List<Court> courtList = new ArrayList<>();
        Court court1 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.VILNIUS, CourtType.KREPSINIS, 2.5, "pvz1", Collections.emptyList());
        Court court2 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KAUNAS, CourtType.LAUKO_FUTBOLAS, 2.5, "pvz1", Collections.emptyList());
        Court court3 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KLAIPEDA, CourtType.LAUKO_TINKLINIS, 2.5, "pvz1", Collections.emptyList());
        Court court4 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.SIAULIAI, CourtType.LAUKO_TENISAS, 2.5, "pvz1", Collections.emptyList());
        Court court5 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.PANEVEZYS, CourtType.STALO_TENISAS, 2.5, "pvz1", Collections.emptyList());
        Court court6 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.VILNIUS, CourtType.MANIEZAS, 2.5, "pvz1", Collections.emptyList());
        Court court7 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KAUNAS, CourtType.SALES_FUTBOLAS, 2.5, "pvz1", Collections.emptyList());
        courtList.add(court1);
        courtList.add(court6);
        comboBox = "VILNIUS";
        MainWindow mainWindow = new MainWindow();
        assertEquals(courtList, mainWindow.filterByCityForTest(courtList, comboBox));
    }

    @Test
    public void filterByCity_3(){
        String comboBox;
        List<Court> courtList = new ArrayList<>();
        Court court1 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.VILNIUS, CourtType.KREPSINIS, 2.5, "pvz1", Collections.emptyList());
        Court court2 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KAUNAS, CourtType.LAUKO_FUTBOLAS, 2.5, "pvz1", Collections.emptyList());
        Court court3 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KLAIPEDA, CourtType.LAUKO_TINKLINIS, 2.5, "pvz1", Collections.emptyList());
        Court court4 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.SIAULIAI, CourtType.LAUKO_TENISAS, 2.5, "pvz1", Collections.emptyList());
        Court court5 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.PANEVEZYS, CourtType.STALO_TENISAS, 2.5, "pvz1", Collections.emptyList());
        Court court6 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.VILNIUS, CourtType.MANIEZAS, 2.5, "pvz1", Collections.emptyList());
        Court court7 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KAUNAS, CourtType.SALES_FUTBOLAS, 2.5, "pvz1", Collections.emptyList());
        courtList.add(court2);
        courtList.add(court7);
        comboBox = "KAUNAS";
        MainWindow mainWindow = new MainWindow();
        assertEquals(courtList, mainWindow.filterByCityForTest(courtList, comboBox));
    }

    @Test
    public void filterByCity_4(){
        String comboBox;
        List<Court> courtList = new ArrayList<>();
        Court court1 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.VILNIUS, CourtType.KREPSINIS, 2.5, "pvz1", Collections.emptyList());
        Court court2 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KAUNAS, CourtType.LAUKO_FUTBOLAS, 2.5, "pvz1", Collections.emptyList());
        Court court3 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KLAIPEDA, CourtType.LAUKO_TINKLINIS, 2.5, "pvz1", Collections.emptyList());
        Court court4 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.SIAULIAI, CourtType.LAUKO_TENISAS, 2.5, "pvz1", Collections.emptyList());
        Court court5 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.PANEVEZYS, CourtType.STALO_TENISAS, 2.5, "pvz1", Collections.emptyList());
        Court court6 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.VILNIUS, CourtType.MANIEZAS, 2.5, "pvz1", Collections.emptyList());
        Court court7 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KAUNAS, CourtType.SALES_FUTBOLAS, 2.5, "pvz1", Collections.emptyList());
        courtList.add(court3);
        comboBox = "KLAIPEDA";
        MainWindow mainWindow = new MainWindow();
        assertEquals(courtList, mainWindow.filterByCityForTest(courtList, comboBox));
    }

    @Test
    public void filterByCity_5(){
        String comboBox;
        List<Court> courtList = new ArrayList<>();
        Court court1 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.VILNIUS, CourtType.KREPSINIS, 2.5, "pvz1", Collections.emptyList());
        Court court2 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KAUNAS, CourtType.LAUKO_FUTBOLAS, 2.5, "pvz1", Collections.emptyList());
        Court court3 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KLAIPEDA, CourtType.LAUKO_TINKLINIS, 2.5, "pvz1", Collections.emptyList());
        Court court4 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.SIAULIAI, CourtType.LAUKO_TENISAS, 2.5, "pvz1", Collections.emptyList());
        Court court5 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.PANEVEZYS, CourtType.STALO_TENISAS, 2.5, "pvz1", Collections.emptyList());
        Court court6 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.VILNIUS, CourtType.MANIEZAS, 2.5, "pvz1", Collections.emptyList());
        Court court7 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KAUNAS, CourtType.SALES_FUTBOLAS, 2.5, "pvz1", Collections.emptyList());
        courtList.add(court4);
        comboBox = "SIAULIAI";
        MainWindow mainWindow = new MainWindow();
        assertEquals(courtList, mainWindow.filterByCityForTest(courtList, comboBox));
    }

    @Test
    public void filterByCity_6(){
        String comboBox;
        List<Court> courtList = new ArrayList<>();
        Court court1 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.VILNIUS, CourtType.KREPSINIS, 2.5, "pvz1", Collections.emptyList());
        Court court2 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KAUNAS, CourtType.LAUKO_FUTBOLAS, 2.5, "pvz1", Collections.emptyList());
        Court court3 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KLAIPEDA, CourtType.LAUKO_TINKLINIS, 2.5, "pvz1", Collections.emptyList());
        Court court4 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.SIAULIAI, CourtType.LAUKO_TENISAS, 2.5, "pvz1", Collections.emptyList());
        Court court5 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.PANEVEZYS, CourtType.STALO_TENISAS, 2.5, "pvz1", Collections.emptyList());
        Court court6 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.VILNIUS, CourtType.MANIEZAS, 2.5, "pvz1", Collections.emptyList());
        Court court7 = new Court(1, "pvz1", "pvz1", "pvz1", CityType.KAUNAS, CourtType.SALES_FUTBOLAS, 2.5, "pvz1", Collections.emptyList());
        courtList.add(court5);
        comboBox = "PANEVEZYS";
        MainWindow mainWindow = new MainWindow();
        assertEquals(courtList, mainWindow.filterByCityForTest(courtList, comboBox));
    }
}
