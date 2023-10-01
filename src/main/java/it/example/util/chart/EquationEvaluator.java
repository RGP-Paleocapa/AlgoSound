package it.example.util.chart;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * Class for evaluating mathematical equations.
 */
public class EquationEvaluator {

    /**
     * Evaluates the given equation for a specific value of x.
     *
     * @param equation the equation to evaluate
     * @param x the value of x
     * @return the result of evaluating the equation for the given x value
     */
    public static double evaluate(String equation, double x) {
        // Replace occurrences of the letter "i" by itself with the letter "x"
        equation = equation
            .replaceAll("\\bi\\b", "x")
            .replace("signum", "sign")
            .replaceAll("\\ba\\b", "Math.PI * 2.0 * (x / 44100.0)");
    
        // Create a Rhino context
        Context rhinoContext = Context.enter();
        try {
            // Create a scope and initialize standard objects
            Scriptable scope = rhinoContext.initStandardObjects();
    
            // Set the "x" variable in the scope with the provided value
            ScriptableObject.putProperty(scope, "x", x);
    
            // Evaluate the equation within the current scope
            Object result = rhinoContext.evaluateString(scope, equation, "equation", 1, null);
    
            // Convert the result to a double number
            double y = Context.toNumber(result);
    
            // Return the result of evaluating the equation
            return y;
        } finally {
            // Exit the Rhino context and release resources
            Context.exit();
        }
    }    

}
