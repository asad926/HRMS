package com.clientside12.employeeControllers;

import com.clientside12.HttpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoadTableViewController implements Initializable {
    @FXML
    private TableView<Employee> TableView;

    @FXML
    private TableColumn<Employee,Integer> id;

    @FXML
    private TableColumn<Employee,String> fname;

    @FXML
    private TableColumn<Employee,String> lname;

    @FXML
    private TableColumn<Employee,String> email;

    @FXML
    private TableColumn<Employee,String> phone;

    @FXML
    private TableColumn<Employee,String> hire;

    @FXML
    private TableColumn<Employee,String> job;

    @FXML
    private TableColumn<Employee,String> salary;

    @FXML
    private TableColumn<Employee,String> comPer;

    @FXML
    private TableColumn<Employee,String> mngID;

    @FXML
    private TableColumn<Employee,String> deptID;

    @FXML
    void editFirstNameCells(TableColumn.CellEditEvent event) {
        Employee empSelected = TableView.getSelectionModel().getSelectedItem();
        empSelected.setFname(event.getNewValue().toString());
    }
    @FXML
    void editLastNameCells(TableColumn.CellEditEvent event) {
        Employee empSelected = TableView.getSelectionModel().getSelectedItem();
        empSelected.setLname(event.getNewValue().toString());
    }
    @FXML
    void editEmailCells(TableColumn.CellEditEvent event) {
        Employee empSelected = TableView.getSelectionModel().getSelectedItem();
        empSelected.setEmail(event.getNewValue().toString());
    }
    @FXML
    void editHireDateCells(TableColumn.CellEditEvent event) {
        Employee empSelected = TableView.getSelectionModel().getSelectedItem();
        empSelected.setHire(event.getNewValue().toString());
    }
    @FXML
    void editJobCells(TableColumn.CellEditEvent event) {
        Employee empSelected = TableView.getSelectionModel().getSelectedItem();
        empSelected.setJob_id(event.getNewValue().toString());
    }
    @FXML
    void editSalaryCells(TableColumn.CellEditEvent event) {
        Employee empSelected = TableView.getSelectionModel().getSelectedItem();
        empSelected.setSalary(event.getNewValue().toString());
    }

    @FXML
    void editDeptNameCells(TableColumn.CellEditEvent event) {
        Employee empSelected = TableView.getSelectionModel().getSelectedItem();
        empSelected.setDept_id(event.getNewValue().toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableView.setEditable(true);
        fname.setCellFactory(TextFieldTableCell.forTableColumn());
        lname.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setCellFactory(TextFieldTableCell.forTableColumn());
        deptID.setCellFactory(TextFieldTableCell.forTableColumn());
       // mngID.setCellFactory(TextFieldTableCell.forTableColumn());
        salary.setCellFactory(TextFieldTableCell.forTableColumn());
        hire.setCellFactory(TextFieldTableCell.forTableColumn());
        job.setCellFactory(TextFieldTableCell.forTableColumn());

        try {
            selectDetailsFromServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void deleteSelectedRow(ActionEvent event) {

       Employee emp = TableView.getSelectionModel().getSelectedItem();
        String url = "http://localhost:8080/EmployeeServlet";
        List<String> params = new ArrayList<String>();
        params.add("DeleteEmployee");
        params.add(String.valueOf(emp.getEmp_id()));
        System.out.println(String.valueOf(emp.getEmp_id()));
        HttpRequest.httpPostRequest(url,params);
        data.removeAll(emp);
        TableView.getProperties().put(TableViewSkinBase.RECREATE, Boolean.TRUE );

    }

    @FXML
    void updateSelectedRow(ActionEvent event) throws JsonProcessingException {

        Employee emp = TableView.getSelectionModel().getSelectedItem();
        String url = "http://localhost:8080/EmployeeServlet";
        List<String> params = new ArrayList<String>();
        params.add("UpdateDetails");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = objectMapper.writeValueAsString(emp);
        params.add(arrayToJson);
        HttpRequest.httpPostRequest(url,params);
        TableView.getProperties().put(TableViewSkinBase.RECREATE, Boolean.TRUE );

    }


    private void selectDetailsFromServer() throws IOException {

        String url = "http://localhost:8080/EmployeeServlet";
        List<String> params = new ArrayList<String>();
        params.add("SelectDetails");
                            String responce = HttpRequest.httpPostRequest(url,params);

                ObjectMapper objectMapper = new ObjectMapper();
                //Set pretty printing of json
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println(responce);
                TypeReference<List<Employee>> mapType = new TypeReference<List<Employee>>() {};
                List<Employee> EmployeeList = objectMapper.readValue(responce, mapType);
                System.out.println("\n2. Convert JSON to List of person objects :");

                System.out.println(EmployeeList.get(0).getFname());

                pupolateTableView(EmployeeList);
            }

    private ObservableList<Employee> data;
    private void pupolateTableView(List<Employee> employeeList) {
         data = FXCollections.observableArrayList(employeeList);
        id.setCellValueFactory(
                new PropertyValueFactory<Employee,Integer>("emp_id"));
        fname.setCellValueFactory(
                new PropertyValueFactory<Employee,String>("fname"));
        lname.setCellValueFactory(
                new PropertyValueFactory<Employee,String>("lname"));
        email.setCellValueFactory(
                new PropertyValueFactory<Employee,String>("email"));
        hire.setCellValueFactory(
                new PropertyValueFactory<Employee,String>("hire"));
        job.setCellValueFactory(
                new PropertyValueFactory<Employee,String>("job_id"));
        salary.setCellValueFactory(
                new PropertyValueFactory<Employee,String>("salary"));
        mngID.setCellValueFactory(
                new PropertyValueFactory<Employee,String>("mng_id"));
        deptID.setCellValueFactory(
                new PropertyValueFactory<Employee,String>("dept_id"));


        TableView.setItems(data);
    }
}
