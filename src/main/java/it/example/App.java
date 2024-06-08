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

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(App::handleGlobalException);
        primaryStage.setTitle("AlgoSound");
        setupPrimaryStage(primaryStage);
        primaryStage.show();
    }

    private void setupPrimaryStage(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = loader.load();

        MainController mainController = loader.getController();
        NavigationService.getInstance(mainController);

        Scene scene = new Scene(root, 1000, 650);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(this::handleCloseRequest);
    }

    private void handleCloseRequest(WindowEvent event) {
        SoundManager.getInstance().stop();
        Platform.runLater(() -> Platform.exit());
    }

    private static void handleGlobalException(Thread thread, Throwable throwable) {
        AlertUtil.showErrorAlert("message", throwable.getMessage());;
    }
}
