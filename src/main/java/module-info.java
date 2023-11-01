module com.example.teamdeveloping {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.teamdeveloping to javafx.fxml;
    exports com.example.teamdeveloping;
}