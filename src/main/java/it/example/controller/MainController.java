package it.example.controller;

import java.util.HashMap;
import java.util.Map;

import it.example.interfaces.Navigator;
import it.example.util.alert.AlertUtil;
import it.example.util.page.PageCache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainController implements Navigator {

    @FXML private VBox contentContainer;

    @FXML private Button explanationButton;
    @FXML private Button codingButton;

    private Button activeButton;

    private final PageCache pageCache = new PageCache();

    // Mapping of buttons to their corresponding page paths
    private final Map<Button, String> buttonPageMap = new HashMap<>();

    @FXML
    private void initialize() {
        // Initialize the button to page map
        buttonPageMap.put(explanationButton, "explaining");
        buttonPageMap.put(codingButton, "pageOne");
        
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
            Node pageNode = pageCache.switchPage(pagePath);
            contentContainer.getChildren().setAll(pageNode);
        } else {
            AlertUtil.showErrorAlert("Load Error", "Could not load the page for " + activeButton.getText());
        }
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        setActiveButton((Button) event.getSource());
    }

    @Override
    public void navigateTo(String pageId) {
        // Directly load and display the page based on the pageId argument
        Node pageNode = pageCache.switchPage(pageId); // Using getPage() which might need to be adjusted if it expects path instead of id
        if (pageNode != null) {
            contentContainer.getChildren().setAll(pageNode);
        } else {
            System.err.println("Page ID not recognized: " + pageId);
            AlertUtil.showErrorAlert("Navigation Error", "Could not navigate to the requested page.");
        }
    }
}
