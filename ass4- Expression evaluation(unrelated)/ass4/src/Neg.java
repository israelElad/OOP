import java.util.Map;

/**
 * Classname: Neg
 * An Unary operator which provides the negation of the expression.
 *
 * @author Elad Israel
 * @version 1.0 01/05/2018
 */

public class Neg extends UnaryExpression implements Expression {

    //members
    private Expression exp;

    /**
     * main constructor.
     *
     * @param exp Expression
     */
    public Neg(Expression exp) {
        this.exp = exp;
    }

    /**
     * shortcut constructor.
     *
     * @param num - double
     */
    public Neg(double num) {
        this(new Num(num));
    }

    /**
     * shortcut constructor.
     *
     * @param var - String
     */
    public Neg(String var) {
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
        return -this.exp.evaluate(assignment);
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return String representation
     */
    public String toString() {
        return "(-" + this.exp.toString() + ")";
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
        return new Neg(this.exp.assign(var, expression));
    }

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var differentiating according to this variable
     * @return new Expression containing the differentiation of current expression.
     */
    public Expression differentiate(String var) {
        return new Neg(this.exp.differentiate(var));
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
     * activates the additional simplification if exists for the bonus part.
     *
     * @return new bonus-simplified Expression.
     */
    public Expression simplifyBonus() {
        //-0 --> 0
        if (this.exp.simplifyBonus().simplify().toString().equals("0.0")) {
            return new Num(0.0);
        }
        //(-(-x)) --> x
        if (this.exp instanceof Neg) {
            return ((Neg) this.exp).exp;
        }
        return new Neg(this.getExp().simplify().simplifyBonus());
    }

}
