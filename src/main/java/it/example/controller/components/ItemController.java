package it.example.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller class for an item with text and title labels.
 */
public class ItemController {

    @FXML
    private Label textLabel;

    @FXML
    private Label titleLabel;

    /**
     * Sets the text for the text label.
     *
     * @param text the text to set
     */
    public void setText(String text) {
        textLabel.setText(text);
    }

    /**
     * Sets the text for the title label.
     *
     * @param text the text to set
     */
    public void setTitle(String text) {
        titleLabel.setText(text);
    }
}
