import java.util.Map;

/**
 * Classname: Mult
 * A binary expression composed of an operator which multiplies the expressions from both of its sides.
 *
 * @author Elad Israel
 * @version 1.0 01/05/2018
 */
public class Mult extends BinaryExpression implements Expression {

    //members
    private Expression left;
    private Expression right;

    /**
     * main constructor.
     *
     * @param left  left Expression
     * @param right right Expression
     */
    public Mult(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * shortcut constructor.
     *
     * @param exp left Expression - Expression
     * @param num right Expression - double
     */
    public Mult(Expression exp, double num) {
        this(exp, new Num(num));
    }

    /**
     * shortcut constructor.
     *
     * @param exp left Expression - Expression
     * @param var right Expression - String
     */
    public Mult(Expression exp, String var) {
        this(exp, new Var(var));
    }

    /**
     * shortcut constructor.
     *
     * @param num1 left Expression - double
     * @param num2 right Expression - double
     */
    public Mult(double num1, double num2) {
        this(new Num(num1), new Num(num2));
    }

    /**
     * shortcut constructor.
     *
     * @param num left Expression - double
     * @param exp right Expression - Expression
     */
    public Mult(double num, Expression exp) {
        this(new Num(num), exp);
    }

    /**
     * shortcut constructor.
     *
     * @param num left Expression - double
     * @param var right Expression - String
     */
    public Mult(double num, String var) {
        this(new Num(num), new Var(var));
    }

    /**
     * shortcut constructor.
     *
     * @param var1 left Expression - String
     * @param var2 right Expression - String
     */
    public Mult(String var1, String var2) {
        this(new Var(var1), new Var(var2));
    }

    /**
     * shortcut constructor.
     *
     * @param var left Expression - String
     * @param num right Expression - double
     */
    public Mult(String var, double num) {
        this(new Var(var), new Num(num));
    }

    /**
     * shortcut constructor.
     *
     * @param var left Expression - String
     * @param exp right Expression - Expression
     */
    public Mult(String var, Expression exp) {
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
        return left.evaluate(assignment) * right.evaluate(assignment);
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return String representation
     */
    public String toString() {
        return "(" + this.left.toString() + " * " + this.right.toString() + ")";
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
        return new Mult(this.left.assign(var, expression), this.right.assign(var, expression));
    }


    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var differentiating according to this variable
     * @return new Expression containing the differentiation of current expression.
     */
    public Expression differentiate(String var) {
        return new Plus(new Mult(this.left.differentiate(var), this.right),
                new Mult(this.left, this.right.differentiate(var)));
    }

    /**
     * Returns a simplified version of the current expression.
     *
     * @return simplified version of the current expression
     */
    public Expression simplify() {
        Mult mult = new Mult(this.left.simplify(), this.right.simplify());
        Expression newLeft = mult.getLeft();
        Expression newRight = mult.getRight();
        //Expression doesn't have any Variables
        if (newLeft.getVariables().isEmpty() && newRight.getVariables().isEmpty()) {
            double value = 0;
            try {
                value = mult.evaluate();
            } catch (Exception evalFailed) {
                throw new RuntimeException("Evaluation failed!");
            }
            return new Num(value);
        } else { //Expression has a least one Var
            //simplifying X * 0 --> 0
            if (newRight.toString().equals("0") || newRight.toString().equals("0.0")) {
                return new Num(0);
            }
            //simplifying 0 * X --> X
            if (newLeft.toString().equals("0") || newLeft.toString().equals("0.0")) {
                return new Num(0);
            }
            //simplifying X * 1 --> X
            if (newRight.toString().equals("1") || newRight.toString().equals("1.0")) {
                return newLeft.simplify();
            }
            //simplifying 1 * X --> X
            if (newLeft.toString().equals("1") || newLeft.toString().equals("1.0")) {
                return newRight.simplify();
            }
        }
        return new Mult(newLeft.simplify(), newRight.simplify());
    }

    /**
     * Returns a simplified version of the current expression for the bonus part.
     *
     * @return simplified version of the current expression
     */
    public Expression simplifyBonus() {
        //((x + y) * (y + x)) --> (x + y)^2, (X * X) --> (2 ^ X)
        if (leftEqualsRight()) {
            return new Pow(this.left.simplifyBonus(), 2);
        }
        // (-x) * (-y) --> (x * y)
        if (this.left instanceof Neg && this.right instanceof Neg) {
            Neg negLeft = (Neg) this.left;
            Neg negRight = (Neg) this.right;
            return new Mult(negLeft.getExp(), negRight.getExp());
        }
        // x * (-y) --> -(x * y)
        if (this.right instanceof Neg) {
            Neg negRight = (Neg) this.right;
            return new Neg(new Mult(this.left, negRight.getExp()));
        }
        // (-x) * y --> -(x * y)
        if (this.left instanceof Neg) {
            Neg negLeft = (Neg) this.left;
            return new Neg(new Mult(negLeft.getExp(), this.right));
        }
        return new Mult(this.getLeft().simplify().simplifyBonus(), this.getRight().simplify().simplifyBonus());
    }

    /**
     * swaps the sides of this expression- left to right, right to left.
     *
     * @return new Expression of this expression with swapped sides
     */
    @Override
    public Expression swapSides() {
        return new Plus(this.right, this.left);
    }

    /**
     * checks whether the left side of the Expression equals(mathematically!) the right side.
     *
     * @return true if equals, false otherwise.
     */
    public Boolean leftEqualsRight() {
        if (this.left.toString().equals(this.right.toString())) {
            return true;
        }
        if ((this.left instanceof Plus || this.left instanceof Mult)
                && (this.right instanceof Plus || this.right instanceof Mult)) {
            if (this.left.swapSides().toString().equals(this.right.toString())) {
                return true;
            }
            if (this.left.toString().equals(this.right.swapSides().toString())) {
                return true;
            }
        }
        return false;
    }
}