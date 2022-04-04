package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.StartGui;
import com.example.sportoAiksteliuRezervacija.controls.DbUtils;
import com.example.sportoAiksteliuRezervacija.ds.Court;
import com.example.sportoAiksteliuRezervacija.ds.enums.CityType;
import com.example.sportoAiksteliuRezervacija.ds.enums.CourtType;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.CourtHibControl;
import com.mysql.cj.xdevapi.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

public class AllCourts implements Initializable {
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

    private int userId;

    public void setCourtFormData(int id) {
        this.userId = id;
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    CourtHibControl courtHibControl = new CourtHibControl(entityManagerFactory);
    ObservableList<Court> observableList = FXCollections.observableArrayList();
    ObservableList<String> observableListCombo = FXCollections.observableArrayList();
    List<Court> CourtList = new ArrayList<>();

    public void getItemsFromDb() throws SQLException {
        CourtList = courtHibControl.getAllCourts(true, -1, -1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getItemsFromDb();
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
            courtTable.setItems(observableList);
            for (Court court : CourtList) {
                observableList.add(court);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void courtClicked(MouseEvent mouseEvent) throws IOException {
        if (courtTable.getSelectionModel().getSelectedIndex() != -1) {
            Court court = courtHibControl.getCourtById(Integer.parseInt(courtTable.getSelectionModel().getSelectedItem().toString().split(":")[0]));
            FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("court-update.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            CourtUpdate courtUpdate = fxmlLoader.getController();
            courtUpdate.setCourtFormData(court.getId(), userId);


            Stage stage = (Stage) courtTable.getScene().getWindow();
            stage.setTitle("Admin");
            stage.setScene(scene);
            stage.show();
        }

    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("administration-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) courtTable.getScene().getWindow();

        AdministrationWindow administrationWindow = fxmlLoader.getController();
        administrationWindow.setCourtFormData(userId);


        stage.setTitle("Add New Court");
        stage.setScene(scene);
        stage.show();
    }


}
