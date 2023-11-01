package com.example.teamdeveloping;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class HelloController {
    @FXML
    private Pane mainPane;
    @FXML
    private Pane resultPane;
    @FXML
    private Line line;

   public void onAction(){
       resultPane.setLayoutY(17);
       mainPane.setDisable(true);
       line.setVisible(false);
   }
    public void cancelOAction(){
        resultPane.setLayoutY(300);
        mainPane.setDisable(false);
        line.setVisible(true);
    }
}