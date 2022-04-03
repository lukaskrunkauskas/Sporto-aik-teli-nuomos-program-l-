package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.StartGui;
import com.example.sportoAiksteliuRezervacija.ds.Court;
import com.example.sportoAiksteliuRezervacija.ds.Schedule;
import com.example.sportoAiksteliuRezervacija.ds.enums.CityType;
import com.example.sportoAiksteliuRezervacija.ds.enums.CourtType;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.CourtHibControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class CourtUpdate implements Initializable {
    @FXML
    public Button backButton;
    @FXML
    public Button submitButton;
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
    public TextField addressField;
    @FXML
    public ImageView imageViewField;
    @FXML
    public TextField imageUrlField;

    private int courtId;
    private int userId;

    public void setCourtFormData(int id, int userId){
        this.courtId = id;
        this.userId = userId;
        nameField.setText(courtHibControl.getCourseById(courtId).getName());
        addressField.setText(courtHibControl.getCourseById(courtId).getAddress());
        costField.setText(courtHibControl.getCourseById(courtId).getCost().toString());
        descriptionField.setText(courtHibControl.getCourseById(courtId).getDescription());
        imageUrlField.setText(courtHibControl.getCourseById(courtId).getPictureUrl());
        cityComboBox.getSelectionModel().select(courtHibControl.getCourseById(courtId).getCity());
        typeComboBox.getSelectionModel().select(courtHibControl.getCourseById(courtId).getType());

    }


    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    CourtHibControl courtHibControl = new CourtHibControl(entityManagerFactory);
    ObservableList<String> observableListComboType = FXCollections.observableArrayList();
    ObservableList<String> observableListComboCity = FXCollections.observableArrayList();

    public void backButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("all-courts.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) imageViewField.getScene().getWindow();

        AllCourts allCourts = fxmlLoader.getController();
        allCourts.setCourtFormData(userId);

        stage.setTitle("Add New Court");
        stage.setScene(scene);
        stage.show();
    }

    public void submitButton(ActionEvent actionEvent) throws IOException {
        List<Schedule> EmptyList = Collections.<Schedule>emptyList();

        Court court = courtHibControl.getCourseById(courtId);
        court.setAddress(addressField.getText());
        court.setName(nameField.getText());
        court.setCost(Double.parseDouble(costField.getText()));
        court.setDescription(descriptionField.getText());
        court.setPictureUrl(imageUrlField.getText());
        court.setCity(CityType.valueOf(cityComboBox.getSelectionModel().getSelectedItem().toString()));
        court.setType(CourtType.valueOf(typeComboBox.getSelectionModel().getSelectedItem().toString()));
        courtHibControl.editCourt(court);
        backButton();
    }

    public void filterCityComboBox(ActionEvent actionEvent) {
    }

    public void filterTypeComboBox(ActionEvent actionEvent) {
    }

    public void imageView(MouseEvent mouseEvent) {
    }

    public void uploadButton(ActionEvent actionEvent) {
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


}
