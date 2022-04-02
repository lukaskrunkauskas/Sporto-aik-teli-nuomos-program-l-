package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.ds.Court;
import com.example.sportoAiksteliuRezervacija.ds.enums.CityType;
import com.example.sportoAiksteliuRezervacija.ds.enums.CourtType;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.CourtHibControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
    @FXML
    public ComboBox cityComboBox;
    @FXML
    public ComboBox typeComboBox;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    CourtHibControl courtHibControl = new CourtHibControl(entityManagerFactory);
    ObservableList<Court> observableList = FXCollections.observableArrayList();
    ObservableList<String> observableListComboCity = FXCollections.observableArrayList();
    ObservableList<String> observableListComboType = FXCollections.observableArrayList();
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
            observableListComboCity.add("VILNIUS");
            observableListComboCity.add("KAUNAS");
            observableListComboCity.add("KLAIPEDA");
            observableListComboCity.add("PANEVEZYS");
            observableListComboCity.add("SIAULIAI");
            observableListComboCity.add("VISI");
            cityComboBox.setItems(observableListComboCity);
            observableListComboType.add("LAUKO_FUTBOLAS");
            observableListComboType.add("SALES_FUTBOLAS");
            observableListComboType.add("KREPSINIS");
            observableListComboType.add("SALES_TINKLINIS");
            observableListComboType.add("LAUKO_TINKLINIS");
            observableListComboType.add("STALO_TENISAS");
            observableListComboType.add("LAUKO_TENISAS");
            observableListComboType.add("MANIEZAS");
            observableListComboType.add("VISI");
            typeComboBox.setItems(observableListComboType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void filterByCity(ActionEvent actionEvent) {
        courtTable.getItems().clear();
        for(Court court : CourtList){
            if(court.getCity().toString().equals(cityComboBox.getSelectionModel().getSelectedItem().toString())){
                observableList.add(court);
            } else if(cityComboBox.getSelectionModel().getSelectedItem().toString().equals("VISI")){
                observableList.add(court);
            }
        }
        courtTable.setItems(observableList);
    }

    public void filterByType(ActionEvent actionEvent) {
        courtTable.getItems().clear();
        for(Court court : CourtList){
            if(court.getType().toString().equals(typeComboBox.getSelectionModel().getSelectedItem().toString())){
                observableList.add(court);
            } else if(typeComboBox.getSelectionModel().getSelectedItem().toString().equals("VISI")){
                observableList.add(court);
            }
        }
        courtTable.setItems(observableList);
    }
}
