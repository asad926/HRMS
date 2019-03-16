package com.clientside12.employeeControllers;

import com.clientside12.HttpRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmployeeRegistrationController implements Initializable {

    @FXML
    private TextField emp_id;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TextField salary;

    @FXML
    private TextField comPer;

    @FXML
    private ComboBox<String> deptName;

    @FXML
    private DatePicker hire;

    @FXML
    private ComboBox<String> job;

    @FXML
    void resetDetails(ActionEvent event) {
        emp_id.setText("");
        comPer.setText("");
        deptName.setValue("");
        job.setValue("");
        salary.setText("");
        email.setText("");
        phone.setText("");
        fname.setText("");
        lname.setText("");
    }

    @FXML
    void submitDetails(ActionEvent event) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String empID = emp_id.getText();
        String Fname = fname.getText();
        String Lname = lname.getText();
        String phoneNo = phone.getText();
        String Email = email.getText();
        String salarys = salary.getText();
        String comper = comPer.getText();
        String jobtitle = job.getValue();
        String dept = deptName.getValue();
        String hireDate = hire.getValue().format(formatters);

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://localhost:8080/EmployeeServlet");
        // Request parameters and other properties.

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("msg", "SetDetails"));
        params.add(new BasicNameValuePair("empID", empID));
        params.add(new BasicNameValuePair("fname", Fname));
        params.add(new BasicNameValuePair("lname", Lname));
        params.add(new BasicNameValuePair("phone", phoneNo));
        params.add(new BasicNameValuePair("salary", salarys));
        params.add(new BasicNameValuePair("hire", hireDate));
        params.add(new BasicNameValuePair("comper", comper));
        params.add(new BasicNameValuePair("deptname", dept));
        params.add(new BasicNameValuePair("jobtitle", jobtitle));
        params.add(new BasicNameValuePair("email", Email));
        try { httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity respEntity = response.getEntity();
            if (respEntity != null) {
                String content = EntityUtils.toString(respEntity);
                System.out.println(content);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        resetDetails(event);

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
                JSONArray jobs = (JSONArray) res.get("JobTitles");
                int i = 0;
             while(i<dept.length()){
                 if(i<dept.length())
                     deptName.getItems().addAll(dept.getString(i));
                     if(i<jobs.length())
                         job.getItems().addAll(jobs.getString(i));
                 i++;
             }
            }

}
