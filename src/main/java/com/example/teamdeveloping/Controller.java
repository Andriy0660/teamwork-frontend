package com.example.teamdeveloping;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public class Controller {
    ApiService apiService = new ApiService();
    HashSet<String> namesOfAllEmps;
    @FXML
    private Pane mainPane;
    @FXML
    private Pane resultPane;
    @FXML
    private Line line;

    //add record
    private final HashSet<String> namesOfSelectedEmps = new HashSet<>();
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
    private Pane empsPane;
    @FXML
    private VBox empsBox;

    public void initialize(){
        //add record
        //namesOfAllEmps = apiService.getAllEmps();
        namesOfAllEmps = new HashSet<>();
        namesOfAllEmps.add("Микола");
        namesOfAllEmps.add("Петро");
        namesOfAllEmps.add("Василь");

        List<HBox> namesAndCheckBoxesForAllEmps = namesOfAllEmps.stream().map(this::createLabelCheckBoxPair).toList();
        empsBox.getChildren().addAll(namesAndCheckBoxesForAllEmps);

        addRecordSelectTypeOfWashing.setItems(FXCollections.observableArrayList("Малий", "Середній", "Великий"));
        addRecordSelectTypeOfWashing.getSelectionModel().select(0);
        addRecordSelectTypeOfWashing.setVisibleRowCount(3);

        //calculate salary
        calcSalarySelectEmp.setItems(FXCollections.observableList(
                namesOfAllEmps.stream().toList())
        );
        calcSalarySelectEmp.getSelectionModel().select(0);
        calcSalarySelectEmp.setVisibleRowCount(3);

        calcSalaryDate.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            String[] parts = newValue.split("\\.");
            if (parts.length == 3) {
                try{
                    int day = Integer.parseInt(parts[0]);
                    int month = Integer.parseInt(parts[1]);
                    int year = Integer.parseInt(parts[2]);
                    LocalDate selected = LocalDate.of(year,month,day);
                    calcSalaryButton.setDisable(selected.isAfter(LocalDate.now()));
                }catch (NumberFormatException | DateTimeException e ){
                    calcSalaryButton.setDisable(true);
                }

            } else {
                calcSalaryButton.setDisable(true);
            }

        });
    }
    private HBox createLabelCheckBoxPair(String name) {
        HBox labelCheckBoxPair = new HBox(10);

        Label labelForName = new Label(name);
        labelForName.setStyle("-fx-font-family: Cambria;" +
                "-fx-font-size: 16;" +
                "-fx-text-fill: #242b8b;" +
                "-fx-pref-width: 65");
        HBox.setMargin(labelForName, new Insets(0, 0, 10, 0));

        CheckBox checkBox = new CheckBox();
        checkBox.setOnMouseClicked((e)->{
            if(checkBox.isSelected())
                namesOfSelectedEmps.add(name);
            else
                namesOfSelectedEmps.remove(name);

            addRecordButton.setDisable(namesOfSelectedEmps.size() == 0);
            System.out.println(namesOfSelectedEmps);
        });
        HBox.setMargin(checkBox, new Insets(0, 20, 0, 0));

        labelCheckBoxPair.getChildren().addAll(labelForName, checkBox);

        return labelCheckBoxPair;
    }

    //add record
    public void addRecordSelectEmpsButtonOnAction(){
        empsPane.setLayoutY(32);
        mainPane.setDisable(true);
        line.setVisible(false);
    }
    public void addRecordCancelSelectEmpsButtonOnAction(){
        empsPane.setLayoutY(300);
        mainPane.setDisable(false);
        line.setVisible(true);
    }

    public void addRecordButtonOnAction(){
        apiService.addRecord(namesOfSelectedEmps,
                getPriceForTypeOfWashing(addRecordSelectTypeOfWashing.getValue()));
    }
    private Double getPriceForTypeOfWashing(String type){
        return switch (type){
            case "Малий" -> 250.;
            case "Середній" -> 350.;
            case "Великий" -> 500.;
            default -> throw new IllegalArgumentException("ERROR. Invalid type");
        };
    }

    //calculate salary
    public void calcSalaryButtonOnAction(){
        resultPane.setLayoutY(17);
        mainPane.setDisable(true);
        line.setVisible(false);

        String nameOfEmp = calcSalarySelectEmp.getValue();
        LocalDate selectedDate = calcSalaryDate.getValue();
        Double salary = apiService.calcSalary(nameOfEmp, selectedDate);
        resultLabel.setText(nameOfEmp + " за " + selectedDate + " повинен отримати " + salary + "грн.");
    }
    public void resultCancelButtonOnAction(){
        resultPane.setLayoutY(300);
        mainPane.setDisable(false);
        line.setVisible(true);
    }

}