package it.example.interfaces;

import org.jfree.data.xy.XYDataset;

public interface DatasetUpdateListener {
    void onDatasetUpdated(XYDataset dataset);
}
