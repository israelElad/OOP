import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classname: Num
 * A class representing a number.
 *
 * @author Elad Israel
 * @version 1.0 01/05/2018
 */
public class Num implements Expression {
    private double num;

    /**
     * double constructor.
     *
     * @param num number
     */
    public Num(double num) {
        this.num = num;
    }

    /**
     * int constructor.
     *
     * @param num number
     */
    public Num(int num) {
        this.num = (double) num;
    }


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
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.num;
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return result after assignment
     * @throws Exception If the expression contains a variable which is not in the assignment
     */
    public double evaluate() throws Exception {
        return this.num;
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return list of Strings
     */
    public List<String> getVariables() {
        return new ArrayList<String>();
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return String representation
     */
    public String toString() {
        return String.valueOf(this.num);
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the current expression).
     *
     * @param var        to replace
     * @param expression to assign instead of the var
     * @return new Expression after assigning
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }


    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var differentiating according to this variable
     * @return new Expression containing the differentiation of current expression.
     */
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * Returns a simplified version of the current expression.
     *
     * @return simplified version of the current expression
     */
    public Expression simplify() {
        return this;
    }

    /**
     * Returns a simplified version of the current expression for the bonus part.
     *
     * @return simplified version of the current expression
     */
    public Expression simplifyBonus() {
        return this;
    }

    /**
     * swaps the sides of this expression- left to right, right to left.
     *
     * @return new Expression of this expression with swapped sides
     */
    public Expression swapSides() {
        return null;
    }
}
