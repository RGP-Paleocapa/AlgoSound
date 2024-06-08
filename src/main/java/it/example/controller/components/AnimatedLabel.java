package it.example.controller.components;

import javafx.scene.control.Label;

public class AnimatedLabel extends Label {

    public AnimatedLabel() {
        super();
        initAnimation();
        this.getStyleClass().add("label-style");
    }

    public AnimatedLabel(String text) {
        super(text);
        initAnimation();
        this.getStyleClass().add("label-style");
    }

    private void initAnimation() {
        // // Create fade in transition
        // FadeTransition fadeInTransition = new FadeTransition(Duration.millis(500), this);
        // fadeInTransition.setFromValue(0.0);
        // fadeInTransition.setToValue(1.0);

        // // Play fade in transition when label is shown
        // fadeInTransition.play();
    }
}
