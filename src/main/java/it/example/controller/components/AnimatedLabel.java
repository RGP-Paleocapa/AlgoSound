package it.example.controller.components;

import javafx.scene.control.Label;

/**
 * A custom label with animation effects.
 */
public class AnimatedLabel extends Label {

    /**
     * Constructs a new AnimatedLabel with default settings.
     */
    public AnimatedLabel() {
        super();
        initAnimation();
        this.getStyleClass().add("label-style");
    }

    /**
     * Constructs a new AnimatedLabel with the specified text.
     *
     * @param text the text to display on the label
     */
    public AnimatedLabel(String text) {
        super(text);
        initAnimation();
        this.getStyleClass().add("label-style");
    }

    /**
     * Initializes the animation for the label.
     */
    private void initAnimation() {
        // TODO: Implement animation
        // Create fade in transition
        // FadeTransition fadeInTransition = new FadeTransition(Duration.millis(500), this);
        // fadeInTransition.setFromValue(0.0);
        // fadeInTransition.setToValue(1.0);

        // Play fade in transition when label is shown
        // fadeInTransition.play();
    }
}
