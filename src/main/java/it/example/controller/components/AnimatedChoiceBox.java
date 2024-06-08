package it.example.controller.components;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

public class AnimatedChoiceBox<T> extends ChoiceBox<T> {

    public AnimatedChoiceBox() {
        super();
        initStyle();
        initAnimation();
    }

    public AnimatedChoiceBox(ObservableList<T> items) {
        super(items);
        initStyle();
        initAnimation();
    }

    private void initAnimation() {
        // TODO
    }

    private void initStyle() {
        this.getStyleClass().add("animated-choice-box");
    }

}
