package com.clientside12.departmentControllers;

import com.clientside12.HttpRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class departmentDetailsController implements Initializable {

    @FXML
    private ComboBox<String> deptNames;

    @FXML
    private Label name;

    @FXML
    private Label street;

    @FXML
    private Label postal;

    @FXML
    private Label city;

    @FXML
    private Label state;

    @FXML
    private Label manager;

    @FXML
    private Label country;

    @FXML
    private Label region;

    @FXML
    private Label warning;

    @FXML
    void showDeptDetails(ActionEvent event) {

        System.out.println(deptNames.getValue());

        String url = "http://localhost:8080/DepartmentServlet";
        List<String> params = new ArrayList<String>();
        params.add("ShowDeptDetails");
        params.add(deptNames.getValue());

        String responce = HttpRequest.httpPostRequest(url,params);
        if(responce.equals("{}"))
        {
            warning.setText("Currently This Department Is Not Functioning.");
        }
        JSONObject res = new JSONObject(responce);

       name.setText(res.getString("dept_name")); warning.setText("");
        manager.setText(res.getString("mng_name"));
        street.setText(res.getString("street"));
        postal.setText(res.getString("postal"));
        city.setText(res.getString("city"));
        state.setText(res.getString("state"));
        country.setText(res.getString("country"));
        region.setText(res.getString("region"));


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String url = "http://localhost:8080/EmployeeServlet";
        List<String> params = new ArrayList<String>();
        params.add("GetDetails");

        String responce = HttpRequest.httpPostRequest(url,params);
        JSONObject res = new JSONObject(responce);
        System.out.println(res);
        JSONArray dept = (JSONArray) res.get("DeptNames");
        int i = 0;
        while(i<dept.length()){
            if(i<dept.length())
                deptNames.getItems().addAll(dept.getString(i));
            i++;
        }

    }
}
