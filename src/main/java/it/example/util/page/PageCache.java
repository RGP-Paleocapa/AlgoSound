package it.example.util.page;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.example.util.alert.AlertUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class PageCache {
    private Map<String, Node> pageCache = new HashMap<>();
    // Define a map for page names to their respective FXML paths
    private static final Map<String, String> pageMap = new HashMap<>();
    
    // Static block to initialize page mappings
    static {
        pageMap.put("explaining", "pages/explainingCode.fxml");
        // pageMap.put("coding", "pages/coding.fxml");
        pageMap.put("coding", "pages/coding/pageTwo.fxml");
        pageMap.put("pageOne", "pages/coding/pageOne.fxml");
        pageMap.put("pageTwo", "pages/coding/pageTwo.fxml");
    }

    public Node getPage(String pagePath) {
        Node pageNode = pageCache.get(pagePath);
        if (pageNode == null) {
            try { 
                pageNode = loadPage(pagePath);
                pageCache.put(pagePath, pageNode);
            } catch (Exception e) {
                AlertUtil.showErrorAlert("Page Load Error", "Failed to load page: " + pagePath + ". " + e.getMessage());
            }
        }
        return pageNode;
    }

    public Node switchPage(String pageName) {
        String pagePath = pageMap.get(pageName);
        if (pagePath != null) {
            return getPage(pagePath);
        } else {
            System.err.println("Page Not Found");
            AlertUtil.showErrorAlert("Page Not Found!!!!", null);
            return null;
        }
    }

    public Node loadPage(String pagePath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + pagePath));
        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();

            AlertUtil.showErrorAlert("Page Load Error", "Failed to load page: " + pagePath + ". " + e.getMessage());
            return null;
        }
    }
}
