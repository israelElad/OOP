/**
 * Created by shimon on 5/10/2017.
 */
public class ExcecutePartTwo {

    public static void main(String args[]) throws Exception {
        Expression e = new Pow(new Var("x"), new Num(4));
        Expression de = e.differentiate("x");
        System.out.println(e);//x ^ 4
        System.out.println(de); // we expect to see 4*(x^3)
        // but seeing: ((x ^ 4.0) * ((1.0 * (4.0 / x)) + (0.0 * log(e, x))))
        // is also fine, as it is equivalent (we will improve it in the next part).
        Expression e1 = new Sin(new Pow(new Var("x"), new Num(4)));
        Expression de1 = e1.differentiate("x");
        System.out.println(e1);//Sin(x ^ 4)
        //System.out.println(de1.simplify());//4 * (x ^ 3) * Cos(x ^ 4)
        e1 = e1.assign("x", new Num(3));
        System.out.println(e1.evaluate());//0.9876883405951378
        Expression e2 = new Mult(new Num(3), new Var("x"));
        System.out.println(e2);//3x
        Expression e3 = new Plus(new Num(3), new Num(0));
        System.out.println(e3.simplify());//3
        //Expression e4 = new Sin(new Plus(new Num(3), new Num(0)));
        //System.out.println(e4.simplify());//0.1411200080598672
        Expression e5 = new Log(new Sin(new Mult(new Num(3), new Num(0))), new Mult(4, "x"));
        System.out.println(e5);//Log((Sin(3*0)),4x)
        Expression e6 = new Plus(new Pow("x", 2), new Pow("y", 2));
        System.out.println(e6.differentiate("x"));//2*(x^(2 - 1)) + 0)
        System.out.println(e6.differentiate("x").simplify());//2x
        e6 = new Log(0, 1);
        try {
            System.out.println(e6.evaluate());// Exception!
        } catch (Exception e11) {
            System.out.println(e11.getMessage());
        }
        e6 = new Pow(0, 0);
        try {
            System.out.println(e6.evaluate());// Exception!
        } catch (Exception e11) {
            System.out.println(e11.getMessage());
        }
    }
}
