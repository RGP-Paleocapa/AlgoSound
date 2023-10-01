package it.example.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.fxml.FXMLLoader;

public class PageCache {
    private Map<String, Node> pageCache = new HashMap<>();

    public Node getPage(String pagePath) {
        Node pageNode = pageCache.get(pagePath);
        if (pageNode == null) {
            pageNode = loadPage(pagePath);
            pageCache.put(pagePath, pageNode);
        }
        return pageNode;
    }

    private Node loadPage(String page) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + page));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
