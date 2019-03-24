/**
 * Created by lizah on 12/05/2017.
 */

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
public class ShimonTester {


    public static void main(String args[]) throws Exception {

        Expression e11111 = new Plus(new Plus(new Plus("x","y"),2),new Plus(new Plus("y","x"),2));
        System.out.println(e11111.simplify());

        Expression e1111 = new Div(new Plus(new Plus("x","y"),2),new Plus(new Plus("y","x"),2));
        System.out.println(e1111.simplify());

        Expression xxx = new Pow(new Pow(new Pow("x","y"), "Z"),"x");
        System.out.println(xxx);
        System.out.println(xxx.simplify());

       /* Expression le = new Pow("x",new Minus("Y",1));
        System.out.println(le);
        System.out.println(le.differentiate("x"));
        System.out.println(le.differentiate("x").simplify());*/
        Map<String, Double> assignment1 = new TreeMap<String, Double>();
       // assignment.put("x", 2.0);
        assignment1.put("y", -1.0);
      //  System.out.println(le.differentiate("x").simplify().evaluate(assignment1));
        Expression x = new Plus("x","y");
        Expression y = new Plus("y", "x");
        Expression l = new Log(x,y);
        System.out.println(l);
        System.out.println(l.simplify());
        Expression l1 = new Div(x,y);
        System.out.println(l1);
        System.out.println(l1.simplify());
        Expression l11 = new Minus(x,y);
        System.out.println(l11);
        System.out.println(l11.simplify());

        Expression ar = new Mult(new Sin(0), 1);
        System.out.println(ar);
        System.out.println(ar.simplify());

        Expression e111 = new Pow(new Var("e"), new Var("x"));
        System.out.println(e111);//e^x
        System.out.println(e111.differentiate("x"));
        // ((e ^ x) * ((0.0 * (x / e)) + (1.0 * log(e, e))))
        System.out.println(e111.differentiate("x").simplify());
        Expression e = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        System.out.println(e.differentiate("x"));
// the result is:
// (((x + y) ^ 2.0) * (((1.0 + 0.0) * (2.0 / (x + y))) + (0.0 * log(e, (x + y)))))
        System.out.println(e.differentiate("x").simplify());
// the result is:
// (((x + y) ^ 2.0) * (2.0 / (x + y)))

        e = new Pow(new Var("e"), new Var("x"));
        System.out.println(e.differentiate("x"));
// ((e ^ x) * ((0.0 * (x / e)) + (1.0 * log(e, e))))
        System.out.println(e.differentiate("x").simplify());
// (e ^ x)

        Expression e13 = new Pow(new Var("x"), new Var("x"));
        System.out.println(e13);
        System.out.println(e13.differentiate("x"));

        Expression e12 = new Mult(new Var("x"),new Num(5));
        System.out.println(e12);
        System.out.println(e12.differentiate("x"));
        Expression e11 = new Plus(new Var("x"),new Var("y"));
        System.out.println(e11);
        System.out.println(e11.differentiate("x"));


        Expression e10 = new Neg(new Var("x"));
        System.out.println(e10);
        System.out.println(e10.differentiate("x"));
Expression e7 = new Var("x");
Expression e8 = new Plus(new Var("y"),e7);
Expression e9 = new Mult(e7,e8);
        System.out.println(e9);
        System.out.println(e9.differentiate("x"));



        Expression e6 = new Log(new Num(5),new Var("x"));
        System.out.println(e6.differentiate("x"));
        Expression e2 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        String s = e2.toString();
        System.out.println(s); // ((x + y) ^ 2.0)
        List<String> vars = e2.getVariables();
        //x
        //y
        for (String v : vars) {
            System.out.println(v);
        }
        Expression e3 = e2.assign("y", e2);
        System.out.println(e3); //((x + ((x + y) ^ 2.0)) ^ 2.0)
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
    }
}
