package com.example.teamdeveloping;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.List;

public class Controller {
    @FXML
    private Pane mainPane;
    @FXML
    private Pane resultPane;
    @FXML
    private Line line;

    //add record
    @FXML
    private Button addRecordSelectEmps;
    @FXML
    private ComboBox<String> addRecordSelectTypeOfWashing;
    @FXML
    private Button addRecordButton;

    //calculate salary
    @FXML
    private ComboBox<String> calcSalarySelectEmp;
    @FXML
    private DatePicker calcSalaryDate;
    @FXML
    private Button calcSalaryButton;

    //result
    @FXML
    private Label resultLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private Pane empsPane;
    @FXML
    private VBox empsBox;

    public void initialize(){

        HBox pair1 = createLabelCheckBoxPair("Label 1");
        HBox pair2 = createLabelCheckBoxPair("Label 2");
        HBox pair3 = createLabelCheckBoxPair("Label 3");
//        HBox pair4 = createLabelCheckBoxPair("Label 3");
//        HBox pair5 = createLabelCheckBoxPair("Label 3");
//        HBox pair6 = createLabelCheckBoxPair("Label 3");

        empsBox.getChildren().addAll(pair1, pair2, pair3);
        List<String> selectedNamesOfEmps = empsBox.getChildren().stream()
                .map(i->(HBox)i)
                .filter(i -> ((CheckBox)i.getChildren().get(1)).isSelected())
                .map(i->((Label)i.getChildren().get(0)).getText())
                .toList();

    }
    private HBox createLabelCheckBoxPair(String labelText) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-family: Cambria;-fx-font-size: 16;-fx-text-fill: #242b8b");
        HBox.setMargin(label, new Insets(0, 0, 10, 0));
        CheckBox checkBox = new CheckBox();
        HBox.setMargin(checkBox, new Insets(0, 20, 0, 0));

        HBox pair = new HBox(10); // 10 pixels spacing
        pair.getChildren().addAll(label, checkBox);

        return pair;
    }
    public void onAction(){
       resultPane.setLayoutY(17);
       mainPane.setDisable(true);
       line.setVisible(false);
    }
    public void onAction2(){
        empsPane.setLayoutY(32);
        mainPane.setDisable(true);
        line.setVisible(false);
    }
    public void cancelOAction(){
        resultPane.setLayoutY(300);
        mainPane.setDisable(false);
        line.setVisible(true);
    }
    public void cancelOAction2(){
        empsPane.setLayoutY(300);
        mainPane.setDisable(false);
        line.setVisible(true);
    }
}