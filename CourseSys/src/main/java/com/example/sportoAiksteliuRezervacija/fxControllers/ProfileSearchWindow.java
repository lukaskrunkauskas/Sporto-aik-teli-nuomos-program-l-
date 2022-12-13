package com.example.sportoAiksteliuRezervacija.fxControllers;

import com.example.sportoAiksteliuRezervacija.StartGui;
import com.example.sportoAiksteliuRezervacija.ds.User;
import com.example.sportoAiksteliuRezervacija.hibernateControllers.UserHibControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class ProfileSearchWindow implements Initializable {

    @FXML
    public TableColumn usernameColumn;

    @FXML
    public Button backButton;
    @FXML
    public TextField usernameField;
    @FXML
    public TableView userTable;

    List<User> UserList = new ArrayList<>();

    private int userId;

    public void setProfileSearchFormData(int id) {
        this.userId = id;
    }

    public void getItemsFromDb() throws SQLException {
        UserList = userHibControl.getAllUser();
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseSystemMng");
    UserHibControl userHibControl = new UserHibControl(entityManagerFactory);
    ObservableList<User> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getItemsFromDb();
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            userTable.setItems(observableList);
            for (User user : UserList) {
                observableList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchUsers(ActionEvent actionEvent) {
        userTable.getItems().clear();
        for(User user : UserList){
            if(user.getUsername().contains(usernameField.getText())){
                observableList.add(user);
            }
        }
        userTable.setItems(observableList);

    }
    public void viewProfile(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("user-found.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        User user = userHibControl.getUserById(Integer.parseInt(userTable.getSelectionModel().getSelectedItem().toString().split(":")[0]));
        UserFound userFound = fxmlLoader.getController();
        userFound.setFormData(userId, user.getId());

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Profilio peržiūra");
        stage.setScene(scene);
        stage.show();
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGui.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MainWindow mainWindow = fxmlLoader.getController();
        mainWindow.setFormData(userId);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Aikštelių rezervacijos sistema");
        stage.setScene(scene);
        stage.show();
    }
}
