package it.example.controller.components;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

/**
 * A custom choice box with animation effects.
 *
 * @param <T> the type of the items in the choice box
 */
public class AnimatedChoiceBox<T> extends ChoiceBox<T> {

    /**
     * Constructs a new AnimatedChoiceBox with default settings.
     */
    public AnimatedChoiceBox() {
        super();
        initStyle();
        initAnimation();
    }

    /**
     * Constructs a new AnimatedChoiceBox with the specified items.
     *
     * @param items the items to display in the choice box
     */
    public AnimatedChoiceBox(ObservableList<T> items) {
        super(items);
        initStyle();
        initAnimation();
    }

    /**
     * Initializes the animation for the choice box.
     */
    private void initAnimation() {
        // TODO: Implement animation
    }

    /**
     * Initializes the style of the choice box.
     */
    private void initStyle() {
        this.getStyleClass().add("animated-choice-box");
    }

}
