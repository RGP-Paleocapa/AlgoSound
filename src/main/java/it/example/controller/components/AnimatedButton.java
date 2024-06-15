package it.example.controller.components;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * A custom button with animation effects.
 */
public class AnimatedButton extends Button {

    /**
     * Constructs a new AnimatedButton with default settings.
     */
    public AnimatedButton() {
        initStyle();
        initAnimation();
    }

    /**
     * Constructs a new AnimatedButton with the specified text.
     *
     * @param text the text to display on the button
     */
    public AnimatedButton(String text) {
        super(text);
        initStyle();
        initAnimation();
    }

    /**
     * Initializes the style of the button.
     */
    private void initStyle() {
        this.getStyleClass().add("button-style");
    }

    /**
     * Initializes the hover animation for the button.
     */
    private void initAnimation() {
        // Create a timeline for hover animation
        Timeline hoverTimeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(this.opacityProperty(), 1.0)), // Start with opacity 1
                new KeyFrame(Duration.millis(300), // Delay of 0.3 seconds
                        new KeyValue(this.opacityProperty(), 1.0)), // Maintain opacity
                new KeyFrame(Duration.millis(600), // Animation duration
                        new KeyValue(this.opacityProperty(), 0.7)) // Reduce opacity on hover
        );

        // Set animation to play when mouse enters
        this.setOnMouseEntered(event -> hoverTimeline.play());

        // Set animation to play when mouse exits
        this.setOnMouseExited(event -> {
            hoverTimeline.jumpTo(Duration.millis(300)); // Jump to delay point
            hoverTimeline.stop(); // Stop the timeline
            this.setOpacity(1.0); // Restore original opacity
        });
    }
}
