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

/**
 * Controller class for the main page of the application.
 */
public class MainController implements Navigator {

    @FXML private VBox contentContainer;

    @FXML private Button explanationButton;
    @FXML private Button codingButton;

    private Button activeButton;

    private final PageCache pageCache = new PageCache();

    // Mapping of buttons to their corresponding page paths
    private final Map<Button, String> buttonPageMap = new HashMap<>();

    /**
     * Initializes the controller, called when the FXML is loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the button to page map
        buttonPageMap.put(explanationButton, "explaining");
        buttonPageMap.put(codingButton, "pageOne");
        
        // Set the initial active button and load its page
        setActiveButton(explanationButton);
    }

    /**
     * Sets the active button and updates its style.
     *
     * @param button the button to set as active
     */
    private void setActiveButton(Button button) {
        if (activeButton != null) {
            activeButton.getStyleClass().remove("active");
        }
        activeButton = button;
        activeButton.getStyleClass().add("active");

        loadPageForActiveButton();
    }

    /**
     * Loads the page corresponding to the active button.
     */
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

    /**
     * Handles button click events to switch the active button and load the corresponding page.
     *
     * @param event the action event
     */
    @FXML
    private void handleButtonClick(ActionEvent event) {
        setActiveButton((Button) event.getSource());
    }

    /**
     * Navigates to a specific page based on the pageId.
     *
     * @param pageId the id of the page to navigate to
     */
    @Override
    public void navigateTo(String pageId) {
        // Directly load and display the page based on the pageId argument
        Node pageNode = pageCache.switchPage(pageId);
        if (pageNode != null) {
            contentContainer.getChildren().setAll(pageNode);
        } else {
            System.err.println("Page ID not recognized: " + pageId);
            AlertUtil.showErrorAlert("Navigation Error", "Could not navigate to the requested page.");
        }
    }
}
