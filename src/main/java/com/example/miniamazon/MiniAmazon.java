package com.example.miniamazon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MiniAmazon extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MiniAmazon.class.getResource("/Fxml/BeforeMainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Welcome! ");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}