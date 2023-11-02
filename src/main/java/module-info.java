module com.example.teamdeveloping {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires org.json;


    opens com.example.frontend to javafx.fxml;
    exports com.example.frontend;
}