package it.example;

import java.io.IOException;

// import javax.sound.sampled.LineUnavailableException;

import it.example.util.SoundManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("AlgoSound");
        setupPrimaryStage(primaryStage);
        primaryStage.show();
    }

    private void setupPrimaryStage(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root, 1000, 650);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(this::handleCloseRequest);
    }

    private void handleCloseRequest(WindowEvent event) {
        SoundManager.getInstance().stop();
    }
}
