import java.util.ArrayList;
import java.util.List;

/**
 * Classname: UnaryExpression
 * An abstract class made to share common code between all unary expressions(for operator on one operands).
 *
 * @author Elad Israel
 * @version 1.0 01/05/2018
 */
public abstract class UnaryExpression extends BaseExpression {

    /**
     * Returns a list of the variables in the expression.
     *
     * @return list of Strings
     */
    public List<String> getVariables() {
        List<String> vars = new ArrayList<String>();
        if (getExp().getVariables() != null) {
            //joins all lists returning from the recursion(which adds all vars)
            vars.addAll(getExp().getVariables());
        }
        //remove vars that appear more the once in the expression
        vars = removeDuplicates(vars);
        return vars;
    }


    /**
     * getter for Expression.
     *
     * @return Expression
     */
    public abstract Expression getExp();
}
