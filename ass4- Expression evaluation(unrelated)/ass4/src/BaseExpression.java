import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Classname: BaseExpression
 * An abstract class made to share common code between both BinaryExpression and UnaryExpression.
 *
 * @author Elad Israel
 * @version 1.0 01/05/2018
 */
public abstract class BaseExpression {

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return result after assignment
     * @throws Exception If the expression contains a variable which is not in the assignment
     */
    public double evaluate() throws Exception {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        return this.evaluate(assignment);
    }

    /**
     * Removes duplicated values in the List of String received.
     *
     * @param listWithDup list with duplicates(possibly).
     * @return new List without duplicates.
     */
    public List<String> removeDuplicates(List<String> listWithDup) {
        if (listWithDup == null || listWithDup.isEmpty()) {
            return listWithDup;
        }
        List<String> listWithoutDup = new ArrayList<>();
        while (!listWithDup.isEmpty()) {
            if (!listWithoutDup.contains(listWithDup.get(0))) {
                listWithoutDup.add(listWithDup.remove(0));
            } else {
                listWithDup.remove(0);
            }
        }
        return listWithoutDup;
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
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * swaps the sides of this expression- left to right, right to left.
     *
     * @return new Expression of this expression with swapped sides
     */
    public Expression swapSides() {
        return null;
    }


}
