package it.example.controller.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemController {

    @FXML
    private Label textLabel;
    @FXML
    private Label titleLabel;

    public void setText(String text) {
        textLabel.setText(text);
    }

    public void setTitle(String text) {
        titleLabel.setText(text);
    }
}

