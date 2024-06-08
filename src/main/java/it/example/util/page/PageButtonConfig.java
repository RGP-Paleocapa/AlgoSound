package it.example.util.page;

public class PageButtonConfig {
    private String buttonId;
    private String title;
    private String pagePath;

    // Constructor
    public PageButtonConfig(String buttonId, String title, String pagePath) {
        this.buttonId = buttonId;
        this.title = title;
        this.pagePath = pagePath;
    }

    // Getters
    public String getButtonId() {
        return buttonId;
    }

    public String getTitle() {
        return title;
    }

    public String getPagePath() {
        return pagePath;
    }

    // Setters if necessary
    // ...
}
