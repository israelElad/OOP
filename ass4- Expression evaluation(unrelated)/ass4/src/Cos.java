import java.util.Map;

/**
 * Classname: Cos
 * An Unary expression composed of an operator which provides the cosine of the expression.
 *
 * @author Elad Israel
 * @version 1.0 01/05/2018
 */

public class Cos extends UnaryExpression implements Expression {

    //members
    private Expression exp;

    /**
     * main constructor.
     *
     * @param exp Expression
     */
    public Cos(Expression exp) {
        this.exp = exp;
    }

    /**
     * shortcut constructor.
     *
     * @param num - double
     */
    public Cos(double num) {
        this(new Num(num));
    }

    /**
     * shortcut constructor.
     *
     * @param var - String
     */
    public Cos(String var) {
        this(new Var(var));
    }

    /**
     * getter for Expression.
     *
     * @return Expression
     */
    public Expression getExp() {
        return this.exp;
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
        double value = exp.evaluate(assignment);
        //fix incorrect values returned by Math.cos for these angles
        if (value % 360 == 90 || value % 360 == 270) {
            return 0;
        }
        return Math.cos(Math.toRadians(value));
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return String representation
     */
    public String toString() {
        return "cos(" + this.exp.toString() + ")";
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
        return new Cos(this.exp.assign(var, expression));
    }

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var differentiating according to this variable
     * @return new Expression containing the differentiation of current expression.
     */
    public Expression differentiate(String var) {
        return new Mult(new Neg(new Sin(exp)), exp.differentiate(var));
    }

    /**
     * Returns a simplified version of the current expression.
     *
     * @return simplified version of the current expression
     */
    public Expression simplify() {
        Cos cos = new Cos(this.exp.simplify());
        Expression newExp = cos.getExp();
        //Expression doesn't have any Variables
        if (newExp.getVariables().isEmpty()) {
            double value = 0;
            try {
                value = cos.evaluate();
            } catch (Exception evalFailed) {
                throw new RuntimeException("Evaluation failed!");
            }
            return new Num(value);
        }
        //Expression has a least one Var
        return new Cos(newExp.simplify());
    }

    /**
     * Returns a simplified version of the current expression for the bonus part.
     *
     * @return simplified version of the current expression
     */
    public Expression simplifyBonus() {
        return this.simplify();
    }
}