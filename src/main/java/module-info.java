module com.example.teamdeveloping {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.example.teamdeveloping to javafx.fxml, com.jfoenix;
    exports com.example.teamdeveloping;
}