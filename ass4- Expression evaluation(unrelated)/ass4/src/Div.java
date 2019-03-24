import java.util.Map;

/**
 * Classname: Div
 * A binary expression composed of an operator which Divides the left Expression by the right Expression.
 *
 * @author Elad Israel
 * @version 1.0 01/05/2018
 */
public class Div extends BinaryExpression implements Expression {

    //members
    private Expression left; //numerator
    private Expression right; //denominator

    /**
     * main constructor.
     *
     * @param left  left Expression
     * @param right right Expression
     */
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * shortcut constructor.
     *
     * @param exp left Expression - Expression
     * @param num right Expression - double
     */
    public Div(Expression exp, double num) {
        this(exp, new Num(num));
    }

    /**
     * shortcut constructor.
     *
     * @param exp left Expression - Expression
     * @param var right Expression - String
     */
    public Div(Expression exp, String var) {
        this(exp, new Var(var));
    }

    /**
     * shortcut constructor.
     *
     * @param num1 left Expression - double
     * @param num2 right Expression - double
     */
    public Div(double num1, double num2) {
        this(new Num(num1), new Num(num2));
    }

    /**
     * shortcut constructor.
     *
     * @param num left Expression - double
     * @param exp right Expression - Expression
     */
    public Div(double num, Expression exp) {
        this(new Num(num), exp);
    }

    /**
     * shortcut constructor.
     *
     * @param num left Expression - double
     * @param var right Expression - String
     */
    public Div(double num, String var) {
        this(new Num(num), new Var(var));
    }

    /**
     * shortcut constructor.
     *
     * @param var1 left Expression - String
     * @param var2 right Expression - String
     */
    public Div(String var1, String var2) {
        this(new Var(var1), new Var(var2));
    }

    /**
     * shortcut constructor.
     *
     * @param var left Expression - String
     * @param num right Expression - double
     */
    public Div(String var, double num) {
        this(new Var(var), new Num(num));
    }

    /**
     * shortcut constructor.
     *
     * @param var left Expression - String
     * @param exp right Expression - Expression
     */
    public Div(String var, Expression exp) {
        this(new Var(var), exp);
    }

    /**
     * getter for left Expression.
     *
     * @return left Expression
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * getter for right Expression.
     *
     * @return right Expression
     */
    public Expression getRight() {
        return right;
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

        if (right.evaluate(assignment) == 0) {
            throw new RuntimeException("Cannot divide by 0!");
        }
        return left.evaluate(assignment) / right.evaluate(assignment);
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return String representation
     */
    public String toString() {
        return "(" + this.left.toString() + " / " + this.right.toString() + ")";
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
        return new Div(this.left.assign(var, expression), this.right.assign(var, expression));
    }

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var differentiating according to this variable
     * @return new Expression containing the differentiation of current expression.
     */
    public Expression differentiate(String var) {
        Expression leftDif = this.left.differentiate(var);
        Expression rightDif = this.right.differentiate(var);
        return new Div(new Minus(new Mult(leftDif, this.right), new Mult(this.left, rightDif)), new Pow(this.right, 2));
    }

    /**
     * Returns a simplified version of the current expression.
     *
     * @return simplified version of the current expression
     */
    public Expression simplify() {
        //Expression doesn't have any Variables
        Div div = new Div(this.left.simplify(), this.right.simplify());
        Expression newLeft = div.getLeft();
        Expression newRight = div.getRight();
        if (newLeft.getVariables().isEmpty() && newRight.getVariables().isEmpty()) {
            double value = 0;
            try {
                value = div.evaluate();
            } catch (Exception evalFailed) {
                throw new RuntimeException("Evaluation failed!");
            }
            return new Num(value);
        } else { //Expression has a least one Var
            //simplifying X / X --> 1
            if (newRight.toString().equals(newLeft.toString())) {
                return new Num(1);
            }
            //simplifying X / 1 --> X
            if (newRight.toString().equals("1") || newRight.toString().equals("1.0")) {
                return newLeft.simplify();
            }
        }
        return new Div(newLeft.simplify(), newRight.simplify());
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