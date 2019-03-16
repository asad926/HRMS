package com.clientside12.departmentControllers;

import com.clientside12.HttpRequest;
import com.clientside12.employeeControllers.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WorkingEmployeeController implements Initializable {
    @FXML
    private ComboBox<String> deptNames;


    @FXML
    private TableView<Departments> TableView;

    @FXML
    private TableColumn<Departments, String> fname;

    @FXML
    private TableColumn<Departments, String> lname;

    @FXML
    private TableColumn<Departments, String> deptName;

    @FXML
    private TableColumn<Departments, String> hire;

    @FXML
    private TableColumn<Departments, String>job;

    @FXML
    private TableColumn<Departments, String>salary;

    @FXML
    private TableColumn<Departments, String> email;


    @FXML
    void showWorkingDetails(ActionEvent event) throws IOException {

        System.out.println(deptNames.getValue());

        String url = "http://localhost:8080/DepartmentServlet";
        List<String> params = new ArrayList<String>();
        params.add("WorkingDetails");
        params.add(deptNames.getValue());

        String responce = HttpRequest.httpPostRequest(url,params);
        System.out.println(responce);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        TypeReference<List<Departments>> mapType = new TypeReference<List<Departments>>() {};
        List<Departments> deptList = objectMapper.readValue(responce, mapType);
        System.out.println("\n2. Convert JSON to List of person objects :");

        pupolateTableView(deptList);

    }
    private ObservableList<Departments> data;
    private void pupolateTableView(List<Departments> deptList) {
        data = FXCollections.observableArrayList(deptList);
        fname.setCellValueFactory(
                new PropertyValueFactory<Departments,String>("dept_name"));
        lname.setCellValueFactory(
                new PropertyValueFactory<Departments,String>("mng_name"));
        email.setCellValueFactory(
                new PropertyValueFactory<Departments,String>("country"));
        hire.setCellValueFactory(
                new PropertyValueFactory<Departments,String>("postal"));
        job.setCellValueFactory(
                new PropertyValueFactory<Departments,String>("city"));
        salary.setCellValueFactory(
                new PropertyValueFactory<Departments,String>("state"));
        deptName.setCellValueFactory(
                new PropertyValueFactory<Departments,String>("street"));


        TableView.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String url = "http://localhost:8080/EmployeeServlet";
        List<String> params = new ArrayList<String>();
        params.add("GetDetails");

        String responce = HttpRequest.httpPostRequest(url,params);
        JSONObject res = new JSONObject(responce);
        System.out.println(res);
        JSONArray dept = (JSONArray) res.get("WorkingDepts");
        int i = 0;
        while(i<dept.length()){
            if(i<dept.length())
                deptNames.getItems().addAll(dept.getString(i));
            i++;
        }

    }
}
