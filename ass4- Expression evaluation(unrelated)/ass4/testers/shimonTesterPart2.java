/**
 * Created by shimon on 5/10/2017.
 */
public class shimonTesterPart2 {

    public static void main(String args[]) throws Exception {
        Expression log = new Log(new Num(7), new Plus(new Num(4), new Num(3)));
        System.out.println(log);
        System.out.println(log.simplify());


        Expression minus = new Minus(new Num(0), new Var("y"));
        System.out.println(minus);
        System.out.println(minus.simplify());


        Expression d = new Log(new Pow( new Var("x"),new Num(2)),new Var("x"));
        System.out.println(d);
        System.out.println(d.differentiate("x"));
        Expression f = new Plus(new Mult(new Mult(new Num(1), new Var("x")),new Num(1)),new Num(0));
        System.out.println(f);
        System.out.println(f.simplify());



        Expression simplify2 = new Div(new Plus(new Num(7), new Num(3)),new Plus(new Num(1), new Num(9) ));//((7.0 + 3.0) / (1.0 + 9.0))
        System.out.println(simplify2);
        System.out.println(simplify2.simplify());
        Expression simplify1 = new Mult(new Num(0),new Var("x") );
        System.out.println(simplify1);//(0.0 * x)
        System.out.println(simplify1.simplify());
        Expression simplify = new Mult(new Num(1), new Var("x"));
        System.out.println(simplify);//(1.0 * x)
        System.out.println(simplify.simplify());
        int j  = 1;
        double u = 1.0;
        if (j==u) {
            System.out.println("what an amazing day");
        }
        Expression e6 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        System.out.println(e6.differentiate("x"));
// the result is:
// (((x + y) ^ 2.0) * (((1.0 + 0.0) * (2.0 / (x + y))) + (0.0 * log(e, (x + y)))))
        Expression e = new Pow(new Var("x"), new Num(4));
        Expression de = e.differentiate("x");
        System.out.println(e);//x ^ 4
        System.out.println(de); // we expect to see 4*(x^3)
        // but seeing: ((x ^ 4.0) * ((1.0 * (4.0 / x)) + (0.0 * log(e, x))))
        // is also fine, as it is equivalent (we will improve it in the next part).
        //Expression e1 = new Sin(new Pow(new Var("x"), new Num(4)));
        //Expression de1 = e1.differentiate("x");
        //System.out.println(e1);//Sin(x ^ 4)
       // System.out.println(de1);//4 * (x ^ 3) * Cos(x ^ 4)
        //e1 = e1.assign("x", new Num(3));
        //System.out.println(e1);//-0.6298879942744539

        //bonus
        Expression e2 = new Mult(new Num(3), new Var("x"));
        System.out.println(e2);//3x
        Expression e3 = new Plus(new Num(3), new Num(0));
        System.out.println(e3);//3
        Expression e4 = new Sin(new Plus(new Num(3), new Num(0)));
        System.out.println(e4);//Sin(3)
        Expression e5 = new Log(new Sin(new Mult(new Num(3), new Num(0))), new Var("x"));
        System.out.println(e5);//Sin(3)

    }
}