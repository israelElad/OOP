import java.util.List;
import java.util.Map;

/**
 * interface defining an expression.
 * an Expression can be evaluated, printed, differentiated, simplified.
 * you can also get the variables in the expression and assign a value to them.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a Map contains Vars as keys and their values to assign
     * @return result after assignment
     * @throws Exception If the expression contains a variable which is not in the assignment
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return result after assignment
     * @throws Exception If the expression contains a variable which is not in the assignment
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return list of Strings
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return String representation
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the current expression).
     *
     * @param var        to replace
     * @param expression to assign instead of the var
     * @return new Expression after assigning
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var differentiating according to this variable
     * @return new Expression containing the differentiation of current expression.
     */
    Expression differentiate(String var);

    /**
     * Returns a simplified version of the current expression.
     *
     * @return simplified version of the current expression
     */
    Expression simplify();

    /**
     * swaps the sides of this expression- left to right, right to left.
     *
     * @return new Expression of this expression with swapped sides
     */
    Expression swapSides();

    /**
     * activates the additional simplification if exists for the bonus part.
     *
     * @return new bonus-simplified Expression.
     */
    Expression simplifyBonus();
}