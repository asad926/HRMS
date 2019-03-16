package com.clientside12;

import com.clientside12.otherClasses.loadNewScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserRegistrationController implements Initializable {


    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private TextField pass;

    @FXML
    void registerUser(ActionEvent event) {

        String user = username.getText();
        String pwd = pass.getText();
        String e_mail = email.getText();

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://localhost:8080/Login");
        // Request parameters and other properties.

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("user", user));
        params.add(new BasicNameValuePair("pass", pwd));
        params.add(new BasicNameValuePair("email", e_mail));
        params.add(new BasicNameValuePair("msg", "Register"));
        try { httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
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
                String content = EntityUtils.toString(respEntity);
                if(content.equals("Registered")){
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
                    loadNewScene newScene = new loadNewScene(event,pane);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetForm(ActionEvent event) {

        username.setText("");
        pass.setText("");
        email.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
