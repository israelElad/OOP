import java.util.Map;
import java.util.TreeMap;
import java.util.List;

/**
 *
 */
public class Excecute {

    public static void main(String args[]) throws Exception {
        Expression e2 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        String s = e2.toString();
        System.out.println(s);
        List<String> vars = e2.getVariables();
        for (String v : vars) {
            System.out.println(v);
        }
        Expression e3 = e2.assign("y", e2);
        System.out.println(e3);
        Expression e4 = e3.assign("y", e3);
        System.out.println(e4);
        // (x + ((x + y)^2))^2
        e3 = e3.assign("x", new Num(1));
        System.out.println(e3);
        // (1 + ((1 + y)^2))^2
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 4.0);
        double value = e2.evaluate(assignment);
        System.out.println("The result is: " + value);//36
        System.out.println(new Minus(new Num(1), new Num(2)).evaluate());//-1
        Expression e5 = new Pow(new Plus(new Var("x"), new Var("x")), new Var("y"));
        System.out.println(e5.getVariables());// [x,y]
    /*    e2 = new Var("e");
        System.out.println(e2.evaluate());//2.718281828459045
    */    System.out.print(Math.pow(0, 0));
    }
}
