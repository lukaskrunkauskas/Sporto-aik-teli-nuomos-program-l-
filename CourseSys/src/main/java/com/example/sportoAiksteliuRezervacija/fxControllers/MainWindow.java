package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.controls.DbUtils;
import com.example.sportoAiksteliuRezervacija.ds.Court;
import com.example.sportoAiksteliuRezervacija.ds.enums.CityType;
import com.example.sportoAiksteliuRezervacija.ds.enums.CourtType;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.CourtHibControl;
import com.mysql.cj.xdevapi.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.Proxy;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    @FXML
    public TableColumn<String, Court> nameColumn;
    @FXML
    public TableColumn<String, Court> addressColumn;
    @FXML
    public TableColumn<CityType, Court> cityColumn;
    @FXML
    public TableColumn<CourtType, Court> typeColumn;
    @FXML
    public TableColumn<String, Court> costColumn;
    @FXML
    public TableView courtTable;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    CourtHibControl courtHibControl = new CourtHibControl(entityManagerFactory);
    ObservableList<Court> observableList = FXCollections.observableArrayList();
    ObservableList<String> observableListCombo = FXCollections.observableArrayList();
    List<Court> CourtList = new ArrayList<>();

    public void getItemsFromDb() throws SQLException {
        CourtList = courtHibControl.getAllCourses(true, -1, -1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getItemsFromDb();
            CourtList.sort((o1, o2) -> Double.compare(o2.getCost(), o1.getCost()));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
            courtTable.setItems(observableList);
            for(Court court : CourtList){
                observableList.add(court);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
