package it.example.util.chart;

import java.awt.Dimension;

import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javafx.embed.swing.SwingNode;

public class ChartUtil {
    private static DefaultXYDataset dataset;

    /**
     * Creates an empty chart panel using JFreeChart and embeds it in the provided SwingNode.
     *
     * @param swingNode the SwingNode in which to embed the chart panel
     */
    public static void createChartPanel(SwingNode swingNode) {
        try {
            // Create an empty chart using JFreeChart
            JFreeChart chart = createEmptyChart();

            // Create the chart panel
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(800, 400));

            // Embed the chart panel in the SwingNode
            SwingUtilities.invokeLater(() -> swingNode.setContent(chartPanel));
        } catch (Exception e) {
            // Handle error during chart panel creation
            e.printStackTrace();
        }
    }

    /**
     * Creates an empty chart using JFreeChart.
     *
     * @return the empty chart
     */
    private static JFreeChart createEmptyChart() {
        // Create an empty dataset for the chart
        dataset = new DefaultXYDataset();

        // Create an empty XY line chart using JFreeChart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Empty Chart",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        // Set the chart style
        // chart.setBackgroundPaint(Color.ORANGE);
        // chart.getPlot().setBackgroundPaint(Color.BLUE);

        return chart;
    }

    /**
     * Updates the chart dataset with the new provided data.
     *
     * @param xData the new X-axis data to add to the dataset
     * @param yData the new Y-axis data to add to the dataset
     */
    public static void updateChartDataset(double[] xData, double[] yData) {
        // Create a new empty dataset
        DefaultXYDataset newDataset = new DefaultXYDataset();

        // Add the new data as a new series in the new dataset
        newDataset.addSeries("Series", new double[][] {xData, yData});

        // Update the reference to the new dataset
        dataset = newDataset;
    }

    /**
     * Returns the current chart dataset.
     *
     * @return the chart dataset
     */
    public static XYDataset getDataset() {
        return dataset;
    }
}
