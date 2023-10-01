package it.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainController {
    @FXML
    private VBox contentContainer;
    @FXML
    private Button explanationButton;
    @FXML
    private Button codingButton;
    @FXML
    private Button activeButton;

    @FXML
    private Button switchToPage2Button; // Add the reference to the button

    private PageCache pageCache = new PageCache(); // Instance of PageCache

    @FXML
    private void initialize() {
        activeButton = explanationButton; // Active page
    }

    /**
     * Handle the button click event to switch pages.
     *
     * @param event The action event triggered by a button click.
     */
    @FXML
    private void switchPage(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String directory = "pages/";

        // Remove the 'active' class from the previous button
        if (activeButton != null) {
            activeButton.getStyleClass().remove("active");
        }

        // Set the new currently active button
        activeButton = clickedButton;
        activeButton.getStyleClass().add("active");

        // Load the desired page from the cache or from the FXML file
        String pagePath = directory;
        if (clickedButton.getText().equals("Explanation")) {
            pagePath += "explainingCode.fxml";
        } else if (clickedButton.getText().equals("Coding")) {
            pagePath += "coding.fxml";
        }

        Node pageNode = pageCache.getPage(pagePath); // Use of PageCache
        contentContainer.getChildren().setAll(pageNode);
    }
}
