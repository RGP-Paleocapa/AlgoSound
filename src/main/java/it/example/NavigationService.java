package it.example;

import it.example.controller.MainController;
import it.example.interfaces.Navigator;
import javafx.application.Platform;

/**
 * Singleton service for handling navigation within the application.
 */
public class NavigationService implements Navigator {
    private static NavigationService instance;
    private MainController mainController;

    /**
     * Private constructor to enforce singleton pattern.
     *
     * @param mainController the main controller to use for navigation
     */
    private NavigationService(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Gets the singleton instance of the NavigationService, initializing it if necessary.
     *
     * @param mainController the main controller to use for navigation
     * @return the singleton instance of the NavigationService
     */
    public static NavigationService getInstance(MainController mainController) {
        if (instance == null) {
            instance = new NavigationService(mainController);
        } else {
            instance.setMainController(mainController);
        }
        return instance;
    }

    /**
     * Gets the singleton instance of the NavigationService.
     *
     * @return the singleton instance of the NavigationService
     */
    public static NavigationService getInstance() {
        return instance;
    }

    /**
     * Navigates to a specific page based on the page identifier.
     *
     * @param pageIdentifier the identifier of the page to navigate to
     */
    @Override
    public void navigateTo(String pageIdentifier) {
        Platform.runLater(() -> mainController.navigateTo(pageIdentifier));
    }

    /**
     * Updates the main controller used for navigation.
     *
     * @param mainController the new main controller to use
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
