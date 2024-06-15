package it.example.util.page;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.example.util.alert.AlertUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * Utility class for caching and loading pages.
 */
public class PageCache {
    private Map<String, Node> pageCache = new HashMap<>();
    
    // Define a map for page names to their respective FXML paths
    private static final Map<String, String> pageMap = new HashMap<>();
    
    // Static block to initialize page mappings
    static {
        pageMap.put("explaining", "pages/explainingCode.fxml");
        pageMap.put("coding", "pages/coding/pageTwo.fxml");
        pageMap.put("pageOne", "pages/coding/pageOne.fxml");
        pageMap.put("pageTwo", "pages/coding/pageTwo.fxml");
    }

    /**
     * Gets a page from the cache, loading it if necessary.
     *
     * @param pagePath the path to the page
     * @return the loaded page node
     */
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

    /**
     * Switches to a specified page by its name.
     *
     * @param pageName the name of the page
     * @return the loaded page node, or null if the page is not found
     */
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

    /**
     * Loads a page from the specified path.
     *
     * @param pagePath the path to the page
     * @return the loaded page node
     * @throws IOException if an I/O error occurs during loading
     */
    public Node loadPage(String pagePath) throws IOException {
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
