package it.example;

import it.example.controller.MainController;
import it.example.interfaces.Navigator;
import javafx.application.Platform;

public class NavigationService implements Navigator {
    private static NavigationService instance;
    private MainController mainController;

    private NavigationService(MainController mainController) {
        this.mainController = mainController;
    }

    public static NavigationService getInstance(MainController mainController) {
        if (instance == null) {
            instance = new NavigationService(mainController);
        } else {
            instance.setMainController(mainController);
        }
        return instance;
    }

    public static NavigationService getInstance() {
        return instance;
    }

    @Override
    public void navigateTo(String pageIdentifier) {
        Platform.runLater(() -> mainController.navigateTo(pageIdentifier));
    }

    // Additional method to update MainController if needed
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
