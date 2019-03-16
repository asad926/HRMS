package com.clientside12.otherClasses;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class loadNewScene {

   public loadNewScene(ActionEvent event, AnchorPane pane){
        Scene scene = new Scene(pane);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
        window.show();
    }
}
