package it.example.util.chart;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import it.example.util.alert.AlertUtil;
import javafx.embed.swing.SwingNode;

/**
 * Utility class for creating and managing charts.
 */
public class ChartUtil {
    private static DefaultXYDataset dataset = new DefaultXYDataset();
    private static List<DatasetUpdateListener> listeners = new ArrayList<>();
    private static final int DATA_LENGTH = 1000;

    /**
     * Interface for listening to dataset updates.
     */
    public interface DatasetUpdateListener {
        /**
         * Called when the dataset is updated.
         *
         * @param dataset the updated dataset
         */
        void onDatasetUpdated(XYDataset dataset);
    }

    /**
     * Adds a listener for dataset updates.
     *
     * @param listener the listener to add
     */
    public static void addDatasetUpdateListener(DatasetUpdateListener listener) {
        listeners.add(listener);
    }

    /**
     * Notifies all registered listeners that the dataset has been updated.
     */
    private static void notifyListeners() {
        for (DatasetUpdateListener listener : listeners) {
            listener.onDatasetUpdated(dataset);
        }
    }

    /**
     * Creates a chart panel and sets it in the provided SwingNode.
     *
     * @param swingNode the SwingNode to set the chart panel in
     */
    public static void createChartPanel(SwingNode swingNode) {
        try {
            JFreeChart chart = createXYLineChart("Empty Chart", "X", "Y", dataset, false);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(800, 400));
            SwingUtilities.invokeLater(() -> swingNode.setContent(chartPanel));
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Chart Creation Error", "An error occurred while creating the chart: " + e.getMessage());
        }
    }

    /**
     * Updates the chart with a new dataset and specified title and axis labels.
     *
     * @param swingNode the SwingNode containing the chart panel
     * @param newDataset the new dataset to update the chart with
     * @param title the title of the chart
     * @param xAxisLabel the label for the X axis
     * @param yAxisLabel the label for the Y axis
     */
    public static void updateChart(SwingNode swingNode, XYDataset newDataset, String title, String xAxisLabel, String yAxisLabel) {
        SwingUtilities.invokeLater(() -> {
            if (swingNode.getContent() instanceof ChartPanel) {
                ChartPanel chartPanel = (ChartPanel) swingNode.getContent();
                JFreeChart chart = createXYLineChart(title, xAxisLabel, yAxisLabel, newDataset, true);
                chartPanel.setChart(chart);
                chartPanel.repaint();
            }
        });
    }

    /**
     * Updates the chart dataset with the provided X and Y data.
     *
     * @param xData the X data
     * @param yData the Y data
     */
    public static void updateChartDataset(short[] xData, short[] yData) {
        double[] xDataDouble = new double[DATA_LENGTH];
        double[] yDataDouble = new double[DATA_LENGTH];
        final double shortMaxValue = 32767.0; // maximum value of a short

        for (int i = 0; i < DATA_LENGTH; i++) {
            xDataDouble[i] = i;
            yDataDouble[i] = yData[i] / shortMaxValue;
        }
        
        // Update the dataset with the double arrays
        dataset.addSeries("Series", new double[][] {xDataDouble, yDataDouble});
        notifyListeners(); // Notify all listeners that the dataset has been updated
    }

    /**
     * Creates an XY line chart with the specified parameters.
     *
     * @param title the title of the chart
     * @param xAxisLabel the label for the X axis
     * @param yAxisLabel the label for the Y axis
     * @param dataset the dataset for the chart
     * @param legend whether to include a legend
     * @return the created XY line chart
     */
    private static JFreeChart createXYLineChart(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, boolean legend) {
        return ChartFactory.createXYLineChart(title, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, legend, true, false);
    }

    /**
     * Gets the current dataset.
     *
     * @return the current dataset
     */
    public static XYDataset getDataset() {
        return dataset;
    }
}
