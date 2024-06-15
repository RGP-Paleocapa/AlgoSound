package it.example.interfaces;

/**
 * An interface for navigation actions.
 */
public interface Navigator {
    
    /**
     * Navigate to a specified page.
     *
     * @param pageIdentifier a unique identifier for the page to navigate to
     */
    void navigateTo(String pageIdentifier);
}
