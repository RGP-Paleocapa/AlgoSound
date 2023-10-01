// import javafx.fxml.FXMLLoader;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.testfx.api.FxToolkit;
// import org.testfx.framework.junit5.ApplicationTest;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.testfx.api.FxAssert.verifyThat;
// import static org.testfx.matcher.control.LabeledMatchers.hasText;

// public class MainControllerTest extends ApplicationTest {

//     private MainController mainController;

//     @BeforeEach
//     public void setUp() throws Exception {
//         FxToolkit.registerPrimaryStage();
//         FxToolkit.setupApplication(App.class);
//         mainController = FxToolkit.setupController(MainController.class);
//     }

//     @Override
//     public void start(Stage stage) {
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
//         VBox root;
//         try {
//             root = loader.load();
//         } catch (Exception e) {
//             e.printStackTrace();
//             root = new VBox();
//         }
//         Scene scene = new Scene(root);
//         stage.setScene(scene);
//         stage.show();
//     }

//     @Test
//     public void testInitialization() {
//         // Verify that the "Coding" button is initially the active button
//         verifyThat(mainController.activeButton, hasText("Coding"));
//     }

//     @Test
//     public void testSwitchPage() {
//         // Click the "HomePage" button
//         clickOn("#homeButton");

//         // Verify that the active button is now "HomePage"
//         verifyThat(mainController.activeButton, hasText("HomePage"));

//         // Click the "Explanation" button
//         clickOn("#explanationButton");

//         // Verify that the active button is now "Explanation"
//         verifyThat(mainController.activeButton, hasText("Explanation"));

//         // Click the "Coding" button
//         clickOn("#codingButton");

//         // Verify that the active button is now "Coding"
//         verifyThat(mainController.activeButton, hasText("Coding"));
//     }
// }
