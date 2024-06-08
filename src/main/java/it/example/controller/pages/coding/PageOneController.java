package it.example.controller.pages.coding;

// import javax.swing.SwingUtilities;

import it.example.NavigationService;
import it.example.controller.components.AnimatedButton;
import it.example.controller.components.AnimatedChoiceBox;
// import it.example.controller.components.AnimatedTextArea;
import it.example.controller.components.AnimatedTextField;
import it.example.controller.components.SyntaxHighlightTextArea;
// import it.example.controller.components.SyntaxHighlightTextArea;
import it.example.util.alert.AlertUtil;
import it.example.util.alert.AlertingTask;
import it.example.util.chart.ChartUtil;
import it.example.util.math.FormulaDataGenerator;
import it.example.util.sound.SoundManager;
// import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;

public class PageOneController {
    @FXML private AnimatedChoiceBox<String> choiceBox;
    @FXML private AnimatedTextField formulaTextField;
    @FXML private SyntaxHighlightTextArea formulaTextArea;
    @FXML private AnimatedButton generateButton;

    // @FXML private SwingNode syntaxArea;

    @FXML
    private void initialize() {
        // SwingUtilities.invokeLater(() -> {
        //     syntaxArea.setContent(new SyntaxHighlightTextArea(10, 2));
        // });
    }

    @FXML
    private void generateChartButton() {
        try {
            String formulaString = getFormulaString();
            if (formulaString == null || formulaString.trim().isEmpty()) {
                AlertUtil.showErrorAlert("Input Error", "Please enter a formula.");
                return;
            }
            AlertingTask<short[][]> generateDataTask = createGenerateDataTask(formulaString);
            setGenerateDataTaskEventHandler(generateDataTask);
            executeGenerateDataTask(generateDataTask);
            NavigationService.getInstance().navigateTo("pageTwo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFormulaString() {
        // Assuming choiceBox might be null or its value might be null
        if (choiceBox == null || choiceBox.getValue() == null) {
            AlertUtil.showErrorAlert("Selection Error", "Please select a formula type.");
            return null;
        }
        return "Formula".equals(choiceBox.getValue()) ? formulaTextField.getText() : formulaTextArea.getText();
    }

    private AlertingTask<short[][]> createGenerateDataTask(String formulaString) {
        return new AlertingTask<>() {
            @Override
            protected short[][] call() throws Exception {
                // Example: If formulaString is not valid for generation, throw an exception
                if (formulaString.isEmpty()) {
                    throw new IllegalArgumentException("Formula string is empty.");
                }
                return FormulaDataGenerator.generate(formulaString);
            }
        };
    }

    private void setGenerateDataTaskEventHandler(AlertingTask<short[][]> generateDataTask) {
        generateDataTask.setOnSucceeded(event -> {
            short[][] data = generateDataTask.getValue();
            updateChartAndSound(data);
        });
        generateDataTask.setOnFailed(event -> {
            Throwable ex = generateDataTask.getException();
            AlertUtil.showErrorAlert("Generation Error", "Failed to generate data: " + ex.getMessage());
        });
    }

    private void updateChartAndSound(short[][] data) {
        // Assuming data might be null if generation failed
        if (data == null || data.length == 0) {
            AlertUtil.showErrorAlert("Data Error", "Generated data is empty or null.");
            return;
        }

        // Original dataset for SoundManager
        SoundManager.getInstance().setWaveData(data[1]);

        // Update chart with extended data representing the required number of periods to reach 1000 points
        ChartUtil.updateChartDataset(data[0], data[1]);
    }


    private void executeGenerateDataTask(AlertingTask<short[][]> generateDataTask) {
        Thread generateDataThread = new Thread(generateDataTask);
        generateDataThread.start();
    }
}
