package it.example.util.math;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.example.util.alert.AlertUtil;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Utility class for generating data based on mathematical formulas.
 */
public class FormulaDataGenerator {

    /**
     * Evaluates the given equation at the specified value of x.
     *
     * @param equation the equation to evaluate
     * @param x the value of the variable x
     * @return the result of the evaluation
     */
    public static double evaluate(String equation, double x) {
        try {
            equation = prepareEquation(equation);
            checkForUnknownOperatorsOrCharacters(equation);
            Expression expression = new ExpressionBuilder(equation)
                    .variable("x")
                    .build()
                    .setVariable("x", x);

            return expression.evaluate();
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Evaluation Error", e.getMessage());
            return Double.NaN;
        }
    }

    /**
     * Checks the equation for unknown operators or characters and throws an exception if any are found.
     *
     * @param equation the equation to check
     */
    private static void checkForUnknownOperatorsOrCharacters(String equation) {
        // Directly look for sequences that don't match the allowed pattern
        Pattern pattern = Pattern.compile("[^0-9x+\\-*/^().eEsincoqrtlgpium ]");
        Matcher matcher = pattern.matcher(equation);

        if (matcher.find()) {
            throw new IllegalArgumentException("Unknown or not allowed operator/character: " + matcher.group());
        }
    }

    /**
     * Prepares the equation by replacing certain patterns with appropriate values.
     *
     * @param equation the equation to prepare
     * @return the prepared equation
     */
    private static String prepareEquation(String equation) {
        equation = equation.trim();
        return equation
                .replaceAll("\\bi\\b", "x")
                .replaceAll("\\ba\\b", "Math.PI * 2 * (x / 44100)") // Use "PI" to match the updated pattern
                .replaceAll("Math.sin", "sin")
                .replaceAll("Math.cos", "cos")
                .replaceAll("Math.tan", "tan")
                .replaceAll("Math.asin", "asin")
                .replaceAll("Math.atan", "atan")
                .replaceAll("Math.signum", "signum")
                .replaceAll("Math.E", "e")
                .replaceAll("Math.PI", "pi")
                .replaceAll(";", ""); // Match against the updated allowed pattern
    }

    /**
     * Generates data based on the specified formula.
     *
     * @param formula the mathematical formula
     * @return a two-dimensional array containing the generated data
     * @throws Exception if an error occurs during data generation
     */
    public static short[][] generate(String formula) throws Exception {
        final int numPoints = 44_100; // Assuming we need 44100 points for 1 second at a 44100 Hz sample rate
        short[] xData = new short[numPoints];
        short[] yData = new short[numPoints];

        for (int i = 0; i < numPoints; i++) {
            xData[i] = (short) i;
            yData[i] = (short) (FormulaDataGenerator.evaluate(formula, i) * Short.MAX_VALUE);
        }

        return new short[][] {xData, yData};
    }
}
