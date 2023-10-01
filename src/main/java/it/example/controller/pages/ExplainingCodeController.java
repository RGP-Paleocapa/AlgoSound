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

import it.example.controller.pages.components.ItemController;
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
    private Stage itemStage; // Store a reference to the item window stage

    private ObservableList<String> itemNames;

    /**
     * Initializes the ExplainingCodeController.
     * This method is automatically called when the FXML is loaded.
     * It sets the initial position of the 'text' element on the scene,
     * loads JSON data from a file, initializes data structures, and sets up event handling.
     */
    public void initialize() throws JSONException {
        text.setX(250);
        text.setY(250);

        // Load JSON data from a file
        loadItemsFromJSON();

        // Create an ObservableList to hold the item names for the ListView
        itemNames = FXCollections.observableArrayList();

        // Populate the itemNames list and itemNameToIndex map
        getItemNames();

        listView.setItems(itemNames);

        // Set up a click event handler for the ListView
        listView.setOnMouseClicked(event -> {
            try {
                handleListViewClick(event);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Populates the itemNames list and itemNameToIndex map from JSON data.
     */
    private void getItemNames() {
        try {
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String itemName = item.getString("name");
                itemNames.add(itemName); // Add item name to the list
                itemNameToIndex.put(itemName, i); // Map item name to its index
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads items from a JSON file into the 'items' JSONArray.
     */
    private void loadItemsFromJSON() throws JSONException {
        // Load JSON data from a file
        InputStream inputStream = getClass().getResourceAsStream("/data/data.json");
        JSONTokener tokener = new JSONTokener(new InputStreamReader(inputStream));
        items = new JSONArray(tokener);
    }

    /**
     * Handles the ListView item click event.
     * It opens a new item window or updates an existing one based on the selected item.
     */
    private void handleListViewClick(MouseEvent event) throws JSONException {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Integer selectedIndex = itemNameToIndex.get(selectedItem);
            if (selectedIndex != null) {
                JSONObject selectedItemObject = items.getJSONObject(selectedIndex);

                String title = selectedItemObject.getString("title");
                String itemText = selectedItemObject.getString("text");

                if (itemStage == null) {
                    // Create a new window to display item details if it doesn't exist
                    createItemStage(title, itemText);
                } else {
                    // Check if the existing window is closed
                    if (!itemStage.isShowing()) {
                        createItemStage(title, itemText);
                    } else {
                        // Update the scene and title of the existing window
                        updateItemStage(title, itemText);
                    }
                }
            }
        }
    }

    /**
     * Creates a new item window with the specified title and text.
     */
    private void createItemStage(String title, String text) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pages/components/item.fxml"));
        try {
            Parent root = loader.load();
            ItemController itemController = loader.getController();
            itemController.setTitle(title);
            itemController.setText(text);

            itemStage = new Stage();
            itemStage.setScene(new Scene(root, 400, 350));
            itemStage.setTitle(title);
            itemStage.setOnCloseRequest(this::onItemStageClosed);
            itemStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing item window with the specified title and text.
     */
    private void updateItemStage(String title, String text) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pages/components/item.fxml"));
        try {
            Parent root = loader.load();
            ItemController itemController = loader.getController();
            itemController.setTitle(title);
            itemController.setText(text);

            itemStage.getScene().setRoot(root);
            itemStage.setTitle(title);
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
