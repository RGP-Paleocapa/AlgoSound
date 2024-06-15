package it.example.interfaces;

import org.jfree.data.xy.XYDataset;

/**
 * An interface for listening to updates in an XY dataset.
 */
public interface DatasetUpdateListener {
    
    /**
     * Called when the XY dataset is updated.
     *
     * @param dataset the updated XY dataset
     */
    void onDatasetUpdated(XYDataset dataset);
}
