package it.example.util.chart;

/**
 * Class for generating data based on a mathematical formula.
 */
public class DataGenerator {

    /**
     * Generates data based on the specified formula.
     *
     * @param formula the mathematical formula
     * @return a two-dimensional array containing the generated data
     */
    public static double[][] generate(String formula) {
        double minX = 0;      // Minimum value of x
        double maxX = 1_000;  // Maximum value of x
        double step = 1.0;    // Step between x values

        // Calculate the number of points based on the range and step
        int numPoints = (int) ((maxX - minX) / step) + 1;

        // Arrays for x and y data
        double[] xData = new double[numPoints];
        double[] yData = new double[numPoints];

        // Generate data for each point
        for (int i = 0; i < numPoints; i++) {
            double x = minX + i * step;

            // Evaluate the equation using EquationEvaluator
            double y = EquationEvaluator.evaluate(formula, x);

            // Save the x and y data in the respective arrays
            xData[i] = x;
            yData[i] = y;
        }

        // Return a two-dimensional array containing the generated data
        return new double[][] {xData, yData};
    }

}
