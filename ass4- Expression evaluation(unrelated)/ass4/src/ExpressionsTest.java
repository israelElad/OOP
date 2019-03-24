import java.util.Map;
import java.util.TreeMap;

/**
 * Classname: ExpressionsTest
 * Test class for ass4.
 *
 * @author Elad Israel
 * @version 1.0 01/05/2018
 */
public class ExpressionsTest {
    /**
     * main method- runs the test.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        try {
            //Create the expression (2x) + (sin(4y)) + (e^x).
            Expression exp = new Plus(new Plus(new Mult(2, "x"), new Sin(new Mult(4, "y"))), new Pow("e", "x"));
            //Print the expression.
            System.out.println(exp);
            //Print the value of the expression with (x=2,y=0.25,e=2.71).
            Map<String, Double> assignment = new TreeMap<String, Double>();
            assignment.put("x", 2.0);
            assignment.put("y", 0.25);
            assignment.put("e", 2.71);
            System.out.println(exp.evaluate(assignment));
            //Print the differentiated expression according to x.
            System.out.println(exp.differentiate("x"));
            //Print the value of the differentiated expression according to x with the assignment above.
            System.out.println(exp.differentiate("x").evaluate(assignment));
            //Print the simplified differentiated expression.
            System.out.println(exp.differentiate("x").simplify());
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }
}
