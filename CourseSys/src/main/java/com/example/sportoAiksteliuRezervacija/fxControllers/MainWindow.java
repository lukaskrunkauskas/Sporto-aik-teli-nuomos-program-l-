package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.StartGui;
import com.example.sportoAiksteliuRezervacija.ds.Court;
import com.example.sportoAiksteliuRezervacija.ds.User;
import com.example.sportoAiksteliuRezervacija.ds.enums.CityType;
import com.example.sportoAiksteliuRezervacija.ds.enums.CourtType;
import com.example.sportoAiksteliuRezervacija.ds.enums.UserType;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.CourtHibControl;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.UserHibControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
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
    @FXML
    public Button systemAdministrationButton;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    CourtHibControl courtHibControl = new CourtHibControl(entityManagerFactory);
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);
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
            // TODO is logino lango gauti userid pakeisti eilute
            User user = userHibControl.getUserById(1);
            if(user.getUserType().equals(UserType.USER)) systemAdministrationButton.setVisible(false);
            else systemAdministrationButton.setVisible(true);
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
    //TODO paduoti userio id
    public void moveToSystemAdministration(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("administration-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) systemAdministrationButton.getScene().getWindow();
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();
    }
    //TODO pakeisti log-in fxml
    public void logOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("log-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) systemAdministrationButton.getScene().getWindow();
        stage.setTitle("Log in");
        stage.setScene(scene);
        stage.show();
    }
    //TODO pakeisti profile edi fxml
    public void moveToProfileEdit(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("profile-edit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) systemAdministrationButton.getScene().getWindow();
        stage.setTitle("Profile edit");
        stage.setScene(scene);
        stage.show();
    }
    //TODO paduoti aiksteles ir userio id
    public void moveToCourtReservation(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("reservation-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) systemAdministrationButton.getScene().getWindow();
        stage.setTitle("Profile edit");
        stage.setScene(scene);
        stage.show();
    }
}
