package com.clientside12;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.clientside12.otherClasses.loadNewScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
import sun.net.www.protocol.http.HttpURLConnection;

public class FXMLController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label label;
    @FXML
    private Button button;

    @FXML
    private Button button1;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField user;
    @FXML
    private void employeeLogin(ActionEvent event) throws IOException {
        String username = user.getText();
        String password = pass.getText();
         //Create the POST request
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://localhost:8080/Login");
        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("user", username));
        params.add(new BasicNameValuePair("pass", password));
        params.add(new BasicNameValuePair("msg", "Login"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }
         // Execute the HTTP Request

        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity respEntity = response.getEntity();

            if (respEntity != null) {
                // EntityUtils to get the response content
                String content =  EntityUtils.toString(respEntity);

                System.out.println(content);
                if(content.equals("Login")){
                    user.setText("");
                    pass.setText("");
                    System.out.println("Login successfully!!!");
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
                    loadNewScene newScene = new loadNewScene(event,pane);
                }else{
                    System.out.println("Invalid Username or Password.");
                }
            }
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String status = "NOT FOUND!!";
        try {
            URL ur =
                    new URL("http://localhost:8080");
                     HttpURLConnection con =
                    (HttpURLConnection) ur.openConnection();
                     con.setRequestMethod("POST");

                     if(con.getResponseCode() == 200)
                            status = "OK";
                     label.setText("WebServer Connection: "+status);
        } catch (MalformedURLException e) {
            label.setText("WebServer Connection: "+status);
            e.printStackTrace();
        } catch (IOException e) {
            label.setText("WebServer Connection: "+status);
            e.printStackTrace();
        }


    }

    public void userRegister(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/UserRegistration.fxml"));
        loadNewScene newScene = new loadNewScene(event,pane);
    }

}
