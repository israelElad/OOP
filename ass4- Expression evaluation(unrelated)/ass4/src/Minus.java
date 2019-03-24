import java.util.Map;

/**
 * Classname: Minus
 * A binary expression composed of an operator which subtracts the expressions from both of its sides.
 *
 * @author Elad Israel
 * @version 1.0 01/05/2018
 */
public class Minus extends BinaryExpression implements Expression {

    //members
    private Expression left;
    private Expression right;

    /**
     * main constructor.
     *
     * @param left  left Expression
     * @param right right Expression
     */
    public Minus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * shortcut constructor.
     *
     * @param exp left Expression - Expression
     * @param num right Expression - double
     */
    public Minus(Expression exp, double num) {
        this(exp, new Num(num));
    }

    /**
     * shortcut constructor.
     *
     * @param exp left Expression - Expression
     * @param var right Expression - String
     */
    public Minus(Expression exp, String var) {
        this(exp, new Var(var));
    }

    /**
     * shortcut constructor.
     *
     * @param num1 left Expression - double
     * @param num2 right Expression - double
     */
    public Minus(double num1, double num2) {
        this(new Num(num1), new Num(num2));
    }

    /**
     * shortcut constructor.
     *
     * @param num left Expression - double
     * @param exp right Expression - Expression
     */
    public Minus(double num, Expression exp) {
        this(new Num(num), exp);
    }

    /**
     * shortcut constructor.
     *
     * @param num left Expression - double
     * @param var right Expression - String
     */
    public Minus(double num, String var) {
        this(new Num(num), new Var(var));
    }

    /**
     * shortcut constructor.
     *
     * @param var1 left Expression - String
     * @param var2 right Expression - String
     */
    public Minus(String var1, String var2) {
        this(new Var(var1), new Var(var2));
    }

    /**
     * shortcut constructor.
     *
     * @param var left Expression - String
     * @param num right Expression - double
     */
    public Minus(String var, double num) {
        this(new Var(var), new Num(num));
    }

    /**
     * shortcut constructor.
     *
     * @param var left Expression - String
     * @param exp right Expression - Expression
     */
    public Minus(String var, Expression exp) {
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
        return left.evaluate(assignment) - right.evaluate(assignment);
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return String representation
     */
    public String toString() {
        return "(" + this.left.toString() + " - " + this.right.toString() + ")";
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
        return new Minus(this.left.assign(var, expression), this.right.assign(var, expression));
    }

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var differentiating according to this variable
     * @return new Expression containing the differentiation of current expression.
     */
    public Expression differentiate(String var) {
        return new Minus(this.left.differentiate(var), this.right.differentiate(var));
    }

    /**
     * Returns a simplified version of the current expression.
     *
     * @return simplified version of the current expression
     */
    public Expression simplify() {
        Minus minus = new Minus(this.left.simplify(), this.right.simplify());
        Expression newLeft = minus.getLeft();
        Expression newRight = minus.getRight();
        //Expression doesn't have any Variables
        if (newLeft.getVariables().isEmpty() && newRight.getVariables().isEmpty()) {
            double value = 0;
            try {
                value = minus.evaluate();
            } catch (Exception evalFailed) {
                throw new RuntimeException("Evaluation failed!");
            }
            return new Num(value);
        } else { //Expression has a least one Var
            //simplifying X - 0 --> X
            if (newRight.toString().equals("0") || newRight.toString().equals("0.0")) {
                return newLeft.simplify();
            }
            //simplifying 0 - X --> -X
            if (newLeft.toString().equals("0") || newLeft.toString().equals("0.0")) {
                return new Neg(newRight.simplify());
            }
            //simplifying X - X --> 0
            if (newLeft.toString().equals(newRight.toString())) {
                return new Num(0);
            }
        }
        return new Minus(newLeft.simplify(), newRight.simplify());
    }

    /**
     * Returns a simplified version of the current expression for the bonus part.
     *
     * @return simplified version of the current expression
     */
    public Expression simplifyBonus() {
        if (this.left instanceof Mult && this.right instanceof Mult) {
            Mult multLeft = (Mult) this.left;
            Mult multRight = (Mult) this.right;
            // ((x * 5) - (x * 2)) => (3 * x)
            if (multLeft.getLeft().toString().equals(multRight.getLeft().toString()) && multLeft.getRight()
                    instanceof Num && multRight.getRight() instanceof Num) {
                try {
                    double value = multLeft.getRight().evaluate() - multRight.getRight().evaluate();
                    return new Mult(value, multLeft.getLeft()).simplify().simplifyBonus();
                } catch (Exception evalExc) {
                    String exc = evalExc.toString();
                }
            }
            // ((x * 5) - (2 * x)) --> (3 * x)
            if (multLeft.getLeft().toString().equals(multRight.getRight().toString()) && multLeft.getRight()
                    instanceof Num && multRight.getLeft() instanceof Num) {
                try {
                    double value = multLeft.getRight().evaluate() - multRight.getLeft().evaluate();
                    return new Mult(value, multLeft.getLeft()).simplify().simplifyBonus();
                } catch (Exception evalExc) {
                    String exc = evalExc.toString();
                }
            }
            // ((5 * x) - (2 * x)) --> (3 * x)
            if (multLeft.getRight().toString().equals(multRight.getRight().toString()) && multLeft.getLeft()
                    instanceof Num && multRight.getLeft() instanceof Num) {
                try {
                    double value = multLeft.getLeft().evaluate() - multRight.getLeft().evaluate();
                    return new Mult(value, multLeft.getRight()).simplify().simplifyBonus();
                } catch (Exception evalExc) {
                    String exc = evalExc.toString();
                }
            }
            // ((5 * x) - (x * 2)) --> (3 * x)
            if (multLeft.getRight().toString().equals(multRight.getLeft().toString()) && multLeft.getLeft()
                    instanceof Num && multRight.getRight() instanceof Num) {
                try {
                    double value = multLeft.getLeft().evaluate() - multRight.getRight().evaluate();
                    return new Mult(value, multLeft.getRight()).simplify().simplifyBonus();
                } catch (Exception evalExc) {
                    String exc = evalExc.toString();
                }
            }
        }
        if (this.left instanceof Neg || this.right instanceof Neg) {
            //((-x) - (-y)) --> (y - x)
            if (this.left instanceof Neg && this.right instanceof Neg) {
                Neg negLeft = (Neg) this.left;
                Neg negRight = (Neg) this.right;
                return new Minus(negRight.getExp(), negLeft.getExp()).simplify().simplifyBonus();
            }

            if (this.left instanceof Neg) {
                Neg negLeft = (Neg) this.left;
                //((-x) - x) --> (-2) * x
                if (negLeft.getExp().toString().equals(this.right.toString())) {
                    return new Mult(new Neg(2), negLeft.getExp()).simplify().simplifyBonus();
                } else { //((-x) - y) --> -(x + y)
                    return new Neg(new Plus(negLeft.getExp(), this.right)).simplify();
                }
            }
            if (this.right instanceof Neg) {
                Neg negRight = (Neg) this.right;
                //(x - (-x)) --> 2 * x
                if (negRight.getExp().toString().equals(this.left.toString())) {
                    return new Mult(2, this.left).simplify().simplifyBonus();
                } else { //(x - (-y)) --> (x + y)
                    return new Plus(this.left, negRight.getExp()).simplify().simplifyBonus();
                }
            }
        }
        return new Minus(this.getLeft().simplify().simplifyBonus(), this.getRight().simplify().simplifyBonus());
    }
}