package it.example.interfaces;

/**
 * Interface for controllers that can receive data.
 */
public interface DataPassingController {
    /**
     * Sets the data for the controller.
     *
     * @param data the data to set
     */
    void setData(Object data);
}
