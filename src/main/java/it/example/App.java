package it.example;

// import javax.sound.sampled.LineUnavailableException;

import it.example.util.SoundUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private Thread soundThread;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Esempio JavaFX con FXML");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(650);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> {
            SoundUtil.stop(); // Stop the sound playback when the application is closed
            Platform.exit();  // Properly close the JavaFX application
            if (soundThread != null) {
                try {
                    soundThread.join(); // Wait for the sound thread to finish before closing the application
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        primaryStage.show();
    }
}
