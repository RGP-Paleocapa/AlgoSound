package it.example.util.page;

/**
 * Configuration class for page buttons.
 */
public class PageButtonConfig {
    private String buttonId;
    private String title;
    private String pagePath;

    /**
     * Constructs a new PageButtonConfig with the specified button ID, title, and page path.
     *
     * @param buttonId the ID of the button
     * @param title the title of the button
     * @param pagePath the path to the page associated with the button
     */
    public PageButtonConfig(String buttonId, String title, String pagePath) {
        this.buttonId = buttonId;
        this.title = title;
        this.pagePath = pagePath;
    }

    /**
     * Gets the ID of the button.
     *
     * @return the button ID
     */
    public String getButtonId() {
        return buttonId;
    }

    /**
     * Gets the title of the button.
     *
     * @return the button title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the path to the page associated with the button.
     *
     * @return the page path
     */
    public String getPagePath() {
        return pagePath;
    }

    // Setters if necessary
    // ...
}
