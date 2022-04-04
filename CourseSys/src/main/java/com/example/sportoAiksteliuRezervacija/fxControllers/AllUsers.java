package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.StartGui;
import com.example.sportoAiksteliuRezervacija.ds.Court;
import com.example.sportoAiksteliuRezervacija.ds.User;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.CourtHibControl;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.UserHibControl;
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

public class AllUsers implements Initializable {
    @FXML
    public TableColumn usernameColumn;
    @FXML
    public TableColumn emailColumn;
    @FXML
    public TableColumn passwordColumn;
    @FXML
    public TableColumn picUrlColumn;
    @FXML
    public TableColumn userTypeColumn;
    @FXML
    public TableView userTable;

    private int userId;

    public void setCourtFormData(int id){
        this.userId = id;
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);
    ObservableList<User> observableList = FXCollections.observableArrayList();
    ObservableList<String> observableListCombo = FXCollections.observableArrayList();
    List<User> UserList = new ArrayList<>();

    public void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("administration-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) userTable.getScene().getWindow();

        AdministrationWindow administrationWindow = fxmlLoader.getController();
        administrationWindow.setCourtFormData(userId);

        stage.setTitle("Admin window");
        stage.setScene(scene);
        stage.show();
    }
    public void getItemsFromDb() throws SQLException {
        UserList = userHibControl.getAllUser();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getItemsFromDb();
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            picUrlColumn.setCellValueFactory(new PropertyValueFactory<>("pictureUrl"));
            userTable.setItems(observableList);
            for(User user : UserList){
                observableList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void userClicked(MouseEvent mouseEvent) throws IOException {
        User user = userHibControl.getUserById(Integer.parseInt(userTable.getSelectionModel().getSelectedItem().toString().split(":")[0]));
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("user-update.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        UserUpdate userUpdate = fxmlLoader.getController();
        userUpdate.setCourtFormData(user.getId(),userId);

        Stage stage = (Stage) userTable.getScene().getWindow();
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();
    }


}
