package it.example.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil {

    /**
     * Displays an error alert with a specified header and content message.
     * @param header The header text for the alert dialog.
     * @param content The content text for the alert dialog.
     */
    public static void showErrorAlert(String header, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }

    // You can also add other types of alerts here, such as informational or confirmation dialogs.
}

