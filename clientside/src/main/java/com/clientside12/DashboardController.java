package com.clientside12;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    @FXML
    private Button showDate;
    @FXML
    private AnchorPane loadScene;
    @FXML
    void getRegisteredEmployee(ActionEvent event) throws IOException {
        loadScene.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/employeeFxmls/EmployeeRegistration.fxml"));
        loadScene.getChildren().addAll(pane);

    }

    @FXML
    void loadRegistrationForm(ActionEvent event) throws IOException {

        loadScene.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/employeeFxmls/LoadTableView.fxml"));
        loadScene.getChildren().addAll(pane);
    }

    @FXML
    void addNewDepartment(ActionEvent event) {


    }

    @FXML
    void deptWorkingEmployees(ActionEvent event) throws IOException {

        loadScene.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/deptFxmls/WorkingEmployee.fxml"));
        loadScene.getChildren().addAll(pane);
    }

    @FXML
    void displayDepartmentsDetails(ActionEvent event) throws IOException {
        loadScene.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/deptFxmls/DepartmentDetails.fxml"));
        loadScene.getChildren().addAll(pane);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
