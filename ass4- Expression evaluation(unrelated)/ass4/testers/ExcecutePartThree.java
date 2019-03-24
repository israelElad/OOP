import java.util.Map;
import java.util.TreeMap;

/**
 * Created by shimon on 5/11/2017.
 */
public class ExcecutePartThree {

    public static void main(String args[]) throws Exception {
        Expression e = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        System.out.println(e);//(x+y)^2
        System.out.println(e.differentiate("x"));
        // the result is:
        // (((x + y) ^ 2.0) * (((1.0 + 0.0) * (2.0 / (x + y))) + (0.0 * log(e, (x + y)))))
        System.out.println(e.differentiate("x").simplify());
        // the result is:
        // (((x + y) ^ 2.0) * (2.0 / (x + y)))
        e = new Pow(new Var("e"), new Var("x"));
        System.out.println(e);//e^x
        System.out.println(e.differentiate("x"));
        // ((e ^ x) * ((0.0 * (x / e)) + (1.0 * log(e, e))))
        System.out.println(e.differentiate("x").simplify());// (e ^ x)
        e = new Plus(new Sin(new Pow(new Var("e"), new Var("x"))), new Sin(new Var("y")));//Sin(e^x)+Sin(y)
        System.out.println(e.differentiate("x"));//(((e^x)*Log(e,e))*Cos((e^x)))
        System.out.println(e.differentiate("x").simplify());//((e^x)*Cos((e^x)))
        System.out.println(e.differentiate("y"));//1*Cos(y)
        System.out.println(e.differentiate("y").simplify());//Cos(y)
        e = new Sin("y");
        System.out.println(e.differentiate("x"));//0
        System.out.println(e.differentiate("x").simplify());//0
        e = new Sin(10);
        System.out.println(e.differentiate("x"));//0
        System.out.println(e.differentiate("x").simplify());//0
        e = new Minus(5, -4);
        System.out.println(e.simplify());//9
        e = new Mult(5, -4);
        System.out.println(e);//5*(-4)
        System.out.println(e.simplify());//(-20)
        e = new Pow(3, new Var("x"));
        System.out.println(e);//3^x
        System.out.println(e.differentiate("x"));//(3^x)*Log(3,e)
        // ((e ^ x) * ((0.0 * (x / e)) + (1.0 * log(e, e))))
        System.out.println(e.differentiate("x").simplify());//(3^x)*1.0986122886681098
        e = new Pow("x", "x");
        System.out.println(e);//x^x
        System.out.println(e.differentiate("x")); //(x^x)*(1*Log(e,x))+(x*((1*1)/(x*Log(e,e))))
        System.out.println(e.differentiate("x").simplify()); //(x^x)*(Log(e,x)+(x*(1/x)))
        e = new Pow("x", new Plus("x", "y"));
        System.out.println(e); //x^(x+y)
        System.out.println(e.differentiate("x")); //(x^(x+y))*(((1+0)*Log(e,x))+((x+y)*((1*1)/(x*Log(e,e)))))
        System.out.println(e.differentiate("x").simplify()); //(x^(x+y))*(Log(e,x)+((x+y)*(1/x)))



        //BONUS!!
        e = new Div(0, "y");
        System.out.println(e); //0/y
        System.out.println(e.simplify()); //0
        e = new Div(new Plus("y", "x"), new Plus("x", "y"));
        System.out.println(e); //(y+x)/(x+y)
        System.out.println(e.simplify()); //1
        e = new Pow("x", "y");
        System.out.println(e); //(y+x)/(x+y)
        System.out.println(e.differentiate("x")); //1
        e = new Div(new Plus(new Plus("x", new Log(2, new Plus("x", "y"))), "z"), new Plus("z", new Plus(new Log(2, new Plus("y", "x")), "x")));
        System.out.println(e); //((x + y) + z) / (z + (y + x))
        System.out.println(e.simplify()); //1
        e =  new Div(new Cos(new Plus("x", "y")), new Cos(new Plus("y", "x")));
        System.out.println(e); //((x + y) + z) / (z + (y + x))
        System.out.println(e.simplify()); //1
        e =  new Log(new Mult(2, "x"), new Pow("x", 2));
        System.out.println(e); //((x + y) + z) / (z + (y + x))
        System.out.println(e.differentiate("x").simplify()); //1




        e = new Div(5, 0);
        System.out.println(e); //5/0
        try {
            System.out.println(e.evaluate()); //RuntimeException
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }
}
