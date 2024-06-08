package it.example.util.alert;

import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertUtil {

    private static boolean alertShowing = false;

    public static void showErrorAlert(String header, String content) {
        if (!alertShowing) {
            alertShowing = true;
            Platform.runLater(() -> {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(header);
                alert.setContentText(content);
                alert.setOnCloseRequest(event -> alertShowing = false);
                alert.showAndWait();
            });
        }
    }

    public static void showInformationAlert(String header, String content) {
        Platform.runLater(() -> {
            if (!alertShowing) {
                alertShowing = true;
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(header);
                alert.setContentText(content);
                alert.setOnCloseRequest(event -> alertShowing = false);
                alert.showAndWait();
            }
        });
    }
    
    public static boolean showConfirmationAlert(String header, String content) {
        final boolean[] response = new boolean[1];
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm Action");
            alert.setHeaderText(header);
            alert.setContentText(content);
            
            Optional<ButtonType> result = alert.showAndWait();
            response[0] = result.isPresent() && result.get() == ButtonType.OK;
        });
        return response[0];
    }

    public static void showWarningAlert(String header, String content) {
        Platform.runLater(() -> {
            if (!alertShowing) {
                alertShowing = true;
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(header);
                alert.setContentText(content);
                alert.setOnCloseRequest(event -> alertShowing = false);
                alert.showAndWait();
            }
        });
    }    

    // You can also add other types of alerts here, such as informational or confirmation dialogs.
}
