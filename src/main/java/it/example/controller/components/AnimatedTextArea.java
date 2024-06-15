package it.example.controller.components;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

/**
 * A custom text area with animation effects.
 */
public class AnimatedTextArea extends TextArea {

    /**
     * Constructs a new AnimatedTextArea with default settings.
     */
    public AnimatedTextArea() {
        super();
        initStyle();
        initialize();
    }

    /**
     * Constructs a new AnimatedTextArea with the specified text.
     *
     * @param text the text to display in the text area
     */
    public AnimatedTextArea(String text) {
        super(text);
        initStyle();
        initialize();
    }

    /**
     * Initializes the style of the text area.
     */
    private void initStyle() {
        this.getStyleClass().add("animated-text-area");
    }

    /**
     * Initializes the text area with listeners and animations.
     */
    private void initialize() {
        // Add a listener to trigger the animation when the text area is focused and empty
        focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal && getText().isEmpty()) {
                shakeAnimation();
            }
        });

        // Apply CSS style class
        getStyleClass().add("animated-text-area");
    }

    /**
     * Plays a shake animation when the text area is focused and empty.
     */
    private void shakeAnimation() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(translateXProperty(), 0)),
                new KeyFrame(Duration.millis(100), new KeyValue(translateXProperty(), -5)),
                new KeyFrame(Duration.millis(200), new KeyValue(translateXProperty(), 5)),
                new KeyFrame(Duration.millis(300), new KeyValue(translateXProperty(), -5)),
                new KeyFrame(Duration.millis(400), new KeyValue(translateXProperty(), 5)),
                new KeyFrame(Duration.millis(500), new KeyValue(translateXProperty(), -5)),
                new KeyFrame(Duration.millis(600), new KeyValue(translateXProperty(), 0))
        );
        timeline.play();
    }
}
