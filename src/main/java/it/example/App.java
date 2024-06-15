/**
 * This package contains the main application class and entry point for the AlgoSound application.
 */
package it.example;

import java.io.IOException;

import org.kordamp.bootstrapfx.BootstrapFX;

import it.example.controller.MainController;
import it.example.util.alert.AlertUtil;
import it.example.util.sound.SoundManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Main application class for the AlgoSound application.
 */
public class App extends Application {

    /**
     * Width of the main scene.
     */
    private static final int SCENE_WIDTH = 1000;

    /**
     * Height of the main scene.
     */
    private static final int SCENE_HEIGHT = 650;

    /**
     * Starts the JavaFX application.
     *
     * @param primaryStage the primary stage for this application
     * @throws IOException if the main FXML file cannot be loaded
     */
    @Override
    public void start(final Stage primaryStage) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(
            App::handleGlobalException
        );
        primaryStage.setTitle("AlgoSound");
        setupPrimaryStage(primaryStage);
        primaryStage.show();
    }

    /**
     * Sets up the primary stage with the main scene.
     *
     * @param primaryStage the primary stage to set up
     * @throws IOException if the main FXML file cannot be loaded
     */
    private void setupPrimaryStage(final Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/main.fxml")
        );
        Parent root = loader.load();

        MainController mainController = loader.getController();
        NavigationService.getInstance(mainController);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets()
             .add(BootstrapFX.bootstrapFXStylesheet());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(this::handleCloseRequest);
    }

    /**
     * Handles the window close request by stopping the sound manager and exiting the platform.
     *
     * @param event the window event
     */
    private void handleCloseRequest(final WindowEvent event) {
        SoundManager.getInstance().stop();
        Platform.runLater(() -> Platform.exit());
    }

    /**
     * Handles uncaught global exceptions by showing an error alert.
     *
     * @param thread the thread that encountered the exception
     * @param throwable the throwable that was thrown
     */
    private static void handleGlobalException(
        final Thread thread, final Throwable throwable
    ) {
        AlertUtil.showErrorAlert("Error", throwable.getMessage());
    }
}
