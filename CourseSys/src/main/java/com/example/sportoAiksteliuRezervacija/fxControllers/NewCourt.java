package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.StartGui;
import com.example.sportoAiksteliuRezervacija.ds.Court;
import com.example.sportoAiksteliuRezervacija.ds.Schedule;
import com.example.sportoAiksteliuRezervacija.ds.enums.CityType;
import com.example.sportoAiksteliuRezervacija.ds.enums.CourtType;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.CourtHibControl;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.ScheduleHibControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class NewCourt implements Initializable {

    @FXML
    public TextField nameField;
    @FXML
    public TextField costField;
    @FXML
    public TextArea descriptionField;
    @FXML
    public ComboBox cityComboBox;
    @FXML
    public ComboBox typeComboBox;
    @FXML
    public Button backButton;
    @FXML
    public Button submitButton;
    @FXML
    public TextField addressField;
    @FXML
    public ImageView imageViewField;
    @FXML
    public TextField imageUrlField;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    CourtHibControl courtHibControl = new CourtHibControl(entityManagerFactory);
    ObservableList<String> observableListComboType = FXCollections.observableArrayList();
    ScheduleHibControl scheduleHibControl = new ScheduleHibControl(entityManagerFactory);
    ObservableList<String> observableListComboCity = FXCollections.observableArrayList();

    private int userId;

    public void setCourtFormData(int id) {
        this.userId = id;
    }

    public void filterCityComboBox(ActionEvent actionEvent) {


    }

    public void filterTypeComboBox(ActionEvent actionEvent) {
    }

    public void backButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("administration-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        AdministrationWindow administrationWindow = fxmlLoader.getController();
        administrationWindow.setCourtFormData(userId);


        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();
    }

    public void alertMsg() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Klaida!");
        alert.setHeaderText(null);
        alert.setContentText("Neužpildėte visų laukų/netaisyklingai užpildėte");

        alert.showAndWait();
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
            if(d <=0 ) return false;
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean checkIfEmpty(String fieldValue) {
        if (fieldValue.length() == 0) return true;
        return false;
    }
    private LocalDateTime getDateTime(int i, int j) {
        LocalDateTime localDate = LocalDateTime.now();
        return localDate.plusDays(1+j).withHour(i).withMinute(0).withSecond(0).withNano(0);

    }

    public void submitButton(ActionEvent actionEvent) throws IOException {
        if (checkIfEmpty(nameField.getText()) == true || checkIfEmpty(addressField.getText()) == true || checkIfEmpty(descriptionField.getText()) == true || cityComboBox.getSelectionModel().getSelectedIndex() == -1 || typeComboBox.getSelectionModel().getSelectedIndex() == -1 || checkIfEmpty(imageUrlField.getText()) == true || isNumeric(costField.getText()) == false)
            alertMsg();
        else {
            List<Schedule> schedules = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                for (int i = 8; i < 17; i++) {
                    Schedule schedule = new Schedule(getDateTime(i + 1, j), getDateTime(i,j), false);
                    scheduleHibControl.createSchedule(schedule);
                    schedules.add(schedule);
                }
            }
            Court court = new Court(nameField.getText(), addressField.getText(), descriptionField.getText(), CityType.valueOf(cityComboBox.getSelectionModel().getSelectedItem().toString()), CourtType.valueOf(typeComboBox.getSelectionModel().getSelectedItem().toString()), Double.parseDouble(costField.getText()), imageUrlField.getText(), schedules);
            courtHibControl.createCourt(court);
            backButton();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableListComboCity.add("VILNIUS");
        observableListComboCity.add("KAUNAS");
        observableListComboCity.add("KLAIPEDA");
        observableListComboCity.add("PANEVEZYS");
        observableListComboCity.add("SIAULIAI");
        cityComboBox.setItems(observableListComboCity);

        observableListComboType.add("LAUKO_FUTBOLAS");
        observableListComboType.add("SALES_FUTBOLAS");
        observableListComboType.add("KREPSINIS");
        observableListComboType.add("SALES_TINKLINIS");
        observableListComboType.add("LAUKO_TINKLINIS");
        observableListComboType.add("STALO_TENISAS");
        observableListComboType.add("LAUKO_TENISAS");
        observableListComboType.add("MANIEZAS");
        typeComboBox.setItems(observableListComboType);


    }

    public void imageView(MouseEvent mouseEvent) throws IOException {
        Image image = new Image(imageUrlField.getText());
        imageViewField.setImage(image);
    }

    public void uploadButton(ActionEvent actionEvent) {
        Image image = new Image(imageUrlField.getText());
        imageViewField.setImage(image);
    }
}
