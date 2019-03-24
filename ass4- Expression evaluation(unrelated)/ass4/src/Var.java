import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classname: Var
 * A class representing a Var.
 *
 * @author Elad Israel
 * @version 1.0 01/05/2018
 */
public class Var implements Expression {

    private String var;

    /**
     * constructor.
     *
     * @param var variable
     */
    public Var(String var) {
        this.var = var;
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
        if (assignment.containsKey(this.var)) {
            return assignment.get(this.var);
        } else {
            if (this.var.equals("e") && !assignment.containsKey(this.var)) {
                return Math.E;
            }
            throw new Exception("The expression contains a variable which is not in the assignment!");
        }
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return result after assignment
     * @throws Exception If the expression contains a variable which is not in the assignment
     */
    public double evaluate() throws Exception {
        throw new Exception("The expression contains a variable which is not in the assignment!");
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return list of Strings
     */
    public List<String> getVariables() {
        List<String> vars = new ArrayList<String>();
        vars.add(this.var);
        return vars;
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return String representation
     */
    public String toString() {
        return var;
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the current expression).
     *
     * @param varToReplace variable to replace
     * @param expression   to assign instead of the var
     * @return new Expression after assigning
     */
    public Expression assign(String varToReplace, Expression expression) {
        if (varToReplace.equals(this.var)) {
            return expression;
        }
        //assignment of another variable - returns the Expression as is.
        return this;
    }

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param varToDif differentiating according to this variable
     * @return new Expression containing the differentiation of current expression.
     */
    public Expression differentiate(String varToDif) {
        if (varToDif.equals(this.var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
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
     * swaps the sides of this expression- left to right, right to left.
     *
     * @return new Expression of this expression with swapped sides
     */
    public Expression swapSides() {
        return null;
    }

    /**
     * Returns a simplified version of the current expression for the bonus part.
     *
     * @return simplified version of the current expression
     */
    public Expression simplifyBonus() {
        return this;
    }

}
