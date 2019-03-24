/**
 * Created by lizah on 11/05/2017.
 */
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MyTester {
    public static void main(String[] args)  {
        Expression e6 = new Plus("x", 5);
        Expression e5 = new Neg(e6);
        System.out.println(e5);

        Expression e1 =new Div(new Var("x"), new Num(0));
        e1.assign("x",new Num(5));

        Expression p = new Plus(new Var("x"), new Var("x"));
        Expression F = new Plus(new Num(6), p);
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 4.0);
        List<String> vars1 = F.getVariables();
        for (String v : vars1) {
            System.out.println(v.toString());
        }
       // System.out.println(F.toString());
        try {
            double d =F.evaluate(assignment);
           //List<> F.getVariables();
           // System.out.println(String.valueOf(d));
            List<String> vars = F.getVariables();
            for (String v : vars) {
                //System.out.println(v.toString());
            }
        } catch (Exception ex) {

        }


       // System.out.println(F.assign("x",F).toString());
        System.out.println(F.assign("x",F).assign("x",new Num(5)).toString());

        Expression e2 = new Plus(new Var("y"), new Var("x"));
        Expression e3 = e2.assign("y", e2);
        //Map<String, Double> assignment = new TreeMap<String, Double>();
        //assignment.put("y", 5.0);
        List<String> vars = e2.getVariables();
        for (String v : vars) {
            //System.out.println(v.toString());
        }
        //System.out.println(e3.toString());
    }
}
