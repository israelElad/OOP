import java.util.ArrayList;
import java.util.List;

/**
 * Classname: BinaryExpression
 * An abstract class made to share common code between all binary expressions(for operator with two operands).
 *
 * @author Elad Israel
 * @version 1.0 01/05/2018
 */
public abstract class BinaryExpression extends BaseExpression {


    /**
     * Returns a list of the variables in the expression.
     *
     * @return list of Strings
     */
    public List<String> getVariables() {
        List<String> vars = new ArrayList<String>();
        if (getLeft().getVariables() != null) {
            //joins all lists returning from the left side of the recursion(which adds all left vars)
            vars.addAll(getLeft().getVariables());
        }
        if (getRight().getVariables() != null) {
            //joins all lists returning from the right side of the recursion(which adds all left vars)
            vars.addAll(getRight().getVariables());
        }
        //remove vars that appear more the once in the expression
        vars = removeDuplicates(vars);
        return vars;
    }

    /**
     * getter for left Expression.
     *
     * @return left Expression
     */
    public abstract Expression getLeft();

    /**
     * getter for right Expression.
     *
     * @return right Expression
     */
    public abstract Expression getRight();


}
