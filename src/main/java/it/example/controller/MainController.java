package it.example.controller;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML
    private VBox contentContainer;

    @FXML
    private Button explanationButton, codingButton;

    private Button activeButton;

    private final PageCache pageCache = new PageCache();

    // Mapping of buttons to their corresponding page paths
    private final Map<Button, String> buttonPageMap = new HashMap<>();

    @FXML
    private void initialize() {
        // Initialize the button to page map
        buttonPageMap.put(explanationButton, "pages/explainingCode.fxml");
        buttonPageMap.put(codingButton, "pages/coding.fxml");
        
        // Set the initial active button and load its page
        setActiveButton(explanationButton);
    }

    private void setActiveButton(Button button) {
        if (activeButton != null) {
            activeButton.getStyleClass().remove("active");
        }
        activeButton = button;
        activeButton.getStyleClass().add("active");

        loadPageForActiveButton();
    }

    private void loadPageForActiveButton() {
        // Retrieve and load the page for the active button
        String pagePath = buttonPageMap.get(activeButton);
        if (pagePath != null) {
            Node pageNode = pageCache.getPage(pagePath);
            contentContainer.getChildren().setAll(pageNode);
        }
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        setActiveButton((Button) event.getSource());
    }
}
