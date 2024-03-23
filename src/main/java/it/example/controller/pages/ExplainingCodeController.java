package it.example.controller.pages;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import it.example.controller.components.ItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ExplainingCodeController {

    @FXML
    private ListView<String> listView;
    @FXML
    private Text text;

    private JSONArray items;
    private Map<String, Integer> itemNameToIndex = new HashMap<>();
    private Stage itemStage; // Reference to the item window stage
    private ObservableList<String> itemNames = FXCollections.observableArrayList();

    /**
     * Initializes the controller, called when the FXML is loaded.
     */
    public void initialize() {
        text.setX(250);
        text.setY(250);

        try {
            loadItemsFromJSON();
            populateItemNames();
            listView.setItems(itemNames);
            listView.setOnMouseClicked(this::handleListViewClick);
        } catch (JSONException | IOException e) {
            e.printStackTrace(); // In a real application, log this error or show to the user.
        }
    }

    /**
     * Populates itemNames list and itemNameToIndex map from JSON data.
     */
    private void populateItemNames() throws JSONException {
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String itemName = item.getString("name");
            itemNames.add(itemName);
            itemNameToIndex.put(itemName, i);
        }
    }

    /**
     * Loads items from a JSON file into the 'items' JSONArray.
     */
    private void loadItemsFromJSON() throws JSONException, IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/data/data.json");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
            JSONTokener tokener = new JSONTokener(inputStreamReader);
            items = new JSONArray(tokener);
        }
    }

    /**
     * Handles ListView item click events, opening or updating an item window.
     */
    private void handleListViewClick(MouseEvent event) {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Integer selectedIndex = itemNameToIndex.get(selectedItem);
            if (selectedIndex != null) {
                try {
                    JSONObject selectedItemObject = items.getJSONObject(selectedIndex);
                    manageItemStage(selectedItemObject.getString("title"), selectedItemObject.getString("text"));
                } catch (JSONException e) {
                    e.printStackTrace(); // Log or show error
                }
            }
        }
    }

    /**
     * Manages the item stage, either creating a new one or updating an existing window.
     */
    private void manageItemStage(String title, String text) {
        if (itemStage == null || !itemStage.isShowing()) {
            createOrUpdateItemStage(title, text, true);
        } else {
            createOrUpdateItemStage(title, text, false);
        }
    }

    /**
     * Creates or updates an item window with the specified title and text.
     */
    private void createOrUpdateItemStage(String title, String text, boolean createNew) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/components/item.fxml"));
            Parent root = loader.load();
            ItemController itemController = loader.getController();
            itemController.setTitle(title);
            itemController.setText(text);

            if (createNew) {
                itemStage = new Stage();
                itemStage.setScene(new Scene(root, 400, 350));
                itemStage.setOnCloseRequest(this::onItemStageClosed);
            } else {
                itemStage.getScene().setRoot(root);
            }

            itemStage.setTitle(title);
            itemStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the window close event by setting the reference to null.
     */
    private void onItemStageClosed(WindowEvent event) {
        itemStage = null;
    }
}
