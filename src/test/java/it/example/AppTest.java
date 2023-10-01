package it.example;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    private App app;

    @Before
    public void setUp() throws Exception {
        // Initialize JavaFX toolkit
        new JFXPanel();

        // Create an instance of the application before each test
        app = new App();
    }

    @Test
    public void testApplicationStart() {
        // Perform any initialization or assertions related to the application's startup here
        // For example, you can assert that the application's title is set correctly
        Platform.runLater(() -> {
            Stage stage = new Stage();
            try {
                app.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            assertEquals("Esempio JavaFX con FXML", stage.getTitle());
        });
    }

    @After
    public void tearDown() throws Exception {
        // Clean up resources after each test
        if (app != null) {
            // You may need to call stop() here if your App class requires it
        }
    }
}
