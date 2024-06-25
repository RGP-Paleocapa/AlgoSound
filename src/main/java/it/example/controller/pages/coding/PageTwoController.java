package it.example.controller.pages.coding;

import javax.swing.SwingUtilities;

import org.jfree.data.xy.XYDataset;

import it.example.NavigationService;
// import it.example.controller.components.AnimatedButton;
import it.example.util.alert.AlertUtil;
import it.example.util.alert.AlertingTask;
import it.example.util.chart.ChartUtil;
import it.example.util.chart.ChartUtil.DatasetUpdateListener;
import it.example.util.sound.SoundManager;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * Controller class for the second page of the application.
 */
public class PageTwoController implements DatasetUpdateListener {

    @FXML
    private SwingNode swingChartsSection;

    @FXML
    // private AnimatedButton soundButton;
    private Button soundButton;

    @FXML
    // private AnimatedButton resetButton;
    private Button resetButton;

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        SwingUtilities.invokeLater(() -> ChartUtil.createChartPanel(swingChartsSection));
        ChartUtil.addDatasetUpdateListener(this);
    }

    /**
     * Called when the dataset is updated.
     *
     * @param dataset the updated dataset
     */
    @Override
    public void onDatasetUpdated(XYDataset dataset) {
        // Delay the update to ensure the chart becomes visible without user interaction
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); // Adjust the delay as needed
        pause.setOnFinished(event -> Platform.runLater(() -> ChartUtil.updateChart(swingChartsSection, dataset, "Waveform graph", "X Frequency", "Y Amplitude")));
        pause.play();
    }

    /**
     * Handles the sound button action.
     */
    @FXML
    private void handleSound() {
        if ("Play".equals(soundButton.getText())) {
            executeSoundTask(SoundManager.getInstance()::play, "Stop");
        } else {
            executeSoundTask(SoundManager.getInstance()::stop, "Play");
        }
    }

    /**
     * Executes a sound task and updates the button text upon completion.
     *
     * @param soundAction the sound action to execute
     * @param buttonText the text to set on the button after the action
     */
    private void executeSoundTask(Runnable soundAction, String buttonText) {
        AlertingTask<Void> soundTask = new AlertingTask<>() {
            @Override
            protected Void call() throws Exception {
                soundAction.run();
                return null;
            }
        };
        soundTask.setOnSucceeded(event -> Platform.runLater(() -> soundButton.setText(buttonText)));
        soundTask.setOnFailed(event -> {
            Throwable ex = event.getSource().getException();
            Platform.runLater(() -> {
                AlertUtil.showErrorAlert("Sound Error", "An error occurred: " + ex.getMessage());
                soundButton.setText("Play");
            });
        });
        new Thread(soundTask).start();
    }

    /**
     * Handles the reset button action.
     */
    @FXML
    private void resetButton() {
        executeSoundTask(SoundManager.getInstance()::stop, "Play");
        NavigationService.getInstance().navigateTo("pageOne");
    }
}
