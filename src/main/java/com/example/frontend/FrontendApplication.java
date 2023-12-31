package com.example.frontend;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FrontendApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FrontendApplication.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 398, 204);
        stage.setTitle("Emapa CarWash");

        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}