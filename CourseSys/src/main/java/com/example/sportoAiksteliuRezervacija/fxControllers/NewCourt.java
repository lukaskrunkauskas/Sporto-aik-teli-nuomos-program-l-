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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

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
    public ImageView imageView;
    @FXML
    public ImageView imageViewField;
    @FXML
    public TextField imageUrlField;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    CourtHibControl courtHibControl = new CourtHibControl(entityManagerFactory);
    ObservableList<String> observableListComboType = FXCollections.observableArrayList();
    ObservableList<String> observableListComboCity = FXCollections.observableArrayList();

    private int userId;

    public void setCourtFormData(int id){
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

    public void submitButton(ActionEvent actionEvent) throws IOException {
        List<Schedule> EmptyList = Collections.<Schedule>emptyList();
        Court court = new Court(nameField.getText(), addressField.getText(), descriptionField.getText(), CityType.valueOf(cityComboBox.getSelectionModel().getSelectedItem().toString()), CourtType.valueOf(typeComboBox.getSelectionModel().getSelectedItem().toString()), Double.parseDouble(costField.getText()), imageUrlField.getText(), EmptyList);
        courtHibControl.createCourt(court);
        backButton();

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

    //-------------------------NUOTRAUKOS IKELIMAS -----------------------
    public void imageView(MouseEvent mouseEvent) throws IOException {


//        URL url = new URL(imageUrlField.getText());
//        Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        imageView.setImageBitmap(bitmap)
    }

    public void uploadButton(ActionEvent actionEvent) {
//        File file = new File(imageUrlField.getText());
//        Image image = new Image(file.toURI().toString());
//        imageView = new ImageView(image);
//        imageView.setImage(image);
//        Image image = new Image(getClass().getResourceAsStream("s.png"));
//        imageView.setImage(image);
    }
}
