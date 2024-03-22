package it.example.util.chart;

/**
 * Class for generating data based on a mathematical formula.
 */
public class DataGenerator {

    // Constants
    private static final double MIN_X = 0;
    private static final double MAX_X = 1_000;
    private static final double STEP = 1.0;

    /**
     * Generates data based on the specified formula.
     *
     * @param formula the mathematical formula
     * @return a two-dimensional array containing the generated data
     */
    public static double[][] generate(String formula) {


        // Calculate the number of points based on the range and STEP
        int numPoints = (int) ((MAX_X - MIN_X) / STEP) + 1;

        // Arrays for x and y data
        double[] xData = new double[numPoints];
        double[] yData = new double[numPoints];

        // Generate data for each point
        for (int i = 0; i < numPoints; i++) {
            double x = MIN_X + i * STEP;

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
