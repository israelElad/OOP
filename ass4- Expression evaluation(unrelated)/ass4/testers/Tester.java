/********************************************************
 * Checking code of Ass4
 * @author Ori Kopel <okopel@gmail.com>
 * @version 2.0
 * @since 02-05-2018.
 ******************************************************/

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Test Class.
 */
public class Tester {
    public static void main(String[] args) throws Exception {
        System.out.println("Start of checking...");
        Expression ex;
        ex = new Plus(10, 10);
        if (!ex.toString().equals("(10.0 + 10.0)"))
            System.out.println("#1:Error in" + ex);
        ex = new Plus(10, "x");
        if (!ex.toString().equals("(10.0 + x)"))
            System.out.println("#2:Error in" + ex);
        ex = new Plus("x", 10);
        if (!ex.toString().equals("(x + 10.0)"))
            System.out.println("#3:Error in" + ex);
        ex = new Plus("x", "x");
        if (!ex.toString().equals("(x + x)"))
            System.out.println("#4:Error in" + ex);
        //minus check
        ex = new Minus(10, 10);
        if (!ex.toString().equals("(10.0 - 10.0)"))
            System.out.println("#5:Error in" + ex);
        ex = new Minus(10, "x");
        if (!ex.toString().equals("(10.0 - x)"))
            System.out.println("#6:Error in" + ex);
        ex = new Minus("x", 10);
        if (!ex.toString().equals("(x - 10.0)"))
            System.out.println("#7:Error in" + ex);
        ex = new Minus("x", "x");
        if (!ex.toString().equals("(x - x)"))
            System.out.println("#8:Error in" + ex);

        //log check
        ex = new Log(10, 10);
        if (!ex.toString().equals("log(10.0, 10.0)"))
            System.out.println("#9:Error in" + ex);
        ex = new Log(10, "x");
        if (!ex.toString().equals("log(10.0, x)"))
            System.out.println("#10:Error in" + ex);
        ex = new Log("x", 10);
        if (!ex.toString().equals("log(x, 10.0)"))
            System.out.println("#11:Error in" + ex);
        ex = new Log("x", "x");
        if (!ex.toString().equals("log(x, x)"))
            System.out.println("#12:Error in" + ex);

        //div check
        ex = new Div(10, 10);
        if (!ex.toString().equals("(10.0 / 10.0)"))
            System.out.println("#13:Error in" + ex);
        ex = new Div(10, "x");
        if (!ex.toString().equals("(10.0 / x)"))
            System.out.println("#14:Error in" + ex);
        ex = new Div("x", 10);
        if (!ex.toString().equals("(x / 10.0)"))
            System.out.println("#15:Error in" + ex);
        ex = new Div("x", "x");
        if (!ex.toString().equals("(x / x)"))
            System.out.println("#16:Error in" + ex);

        //Mult check
        ex = new Mult(10, 10);
        if (!ex.toString().equals("(10.0 * 10.0)"))
            System.out.println("#17:Error in" + ex);
        ex = new Mult(10, "x");
        if (!ex.toString().equals("(10.0 * x)"))
            System.out.println("#18:Error in" + ex);
        ex = new Mult("x", 10);
        if (!ex.toString().equals("(x * 10.0)"))
            System.out.println("#19:Error in" + ex);
        ex = new Mult("x", "x");
        if (!ex.toString().equals("(x * x)"))
            System.out.println("#20:Error in" + ex);
        //Pow check
        ex = new Pow(10, 10);
        if (!ex.toString().equals("(10.0^10.0)"))
            System.out.println("#21:Error in" + ex);
        ex = new Pow(10, "x");
        if (!ex.toString().equals("(10.0^x)"))
            System.out.println("#22:Error in" + ex);
        ex = new Pow("x", 10);
        if (!ex.toString().equals("(x^10.0)"))
            System.out.println("#23:Error in" + ex);
        ex = new Pow("x", "x");
        if (!ex.toString().equals("(x^x)"))
            System.out.println("#24:Error in" + ex);

        //Cos check
        ex = new Cos(10);
        if (!ex.toString().equals("cos(10.0)"))
            System.out.println("#25:Error in" + ex);
        ex = new Cos("x");
        if (!ex.toString().equals("cos(x)"))
            System.out.println("#26:Error in" + ex);

        //sin check
        ex = new Sin(10);
        if (!ex.toString().equals("sin(10.0)"))
            System.out.println("#27:Error in radian:" + ex);
        ex = new Sin("x");
        if (!ex.toString().equals("sin(x)"))
            System.out.println("#28:Error in" + ex);

        //neg check
        ex = new Neg(10);
        if (!ex.toString().equals("(-10.0)"))
            System.out.println("#29:Error in" + ex);
        ex = new Neg("x");
        if (!ex.toString().equals("(-x)"))
            System.out.println("#30:Error in" + ex);
        //simplify checking
        //x*1 or 1*x
        ex = new Mult("x", 1);
        if (!ex.simplify().toString().equals("x"))
            System.out.println("#31 :Error in simplify of" + ex.simplify());
        ex = new Mult(new Mult("x", 8), 1);
        if (!(ex.simplify().toString().equals("(x * 8.0)") || ex.simplify().toString().equals("(8.0 * x)")))
            System.out.println("#32:Error in simplify of" + ex.simplify());
        ex = new Mult(1, new Mult("x", 8));
        if (!(ex.simplify().toString().equals("(x * 8.0)") || ex.simplify().toString().equals("(8.0 * x)")))
            System.out.println("#33:Error in simplify of" + ex.simplify());
        ex = new Mult(1, "x");
        if (!ex.simplify().toString().equals("x"))
            System.out.println("#34:Error in simplify of" + ex.simplify());
        //x*0
        ex = new Mult("x", 0);
        if (!ex.simplify().toString().equals("0.0"))
            System.out.println("#35:Error in simplify of" + ex.simplify());
        ex = new Mult(new Pow(new Mult(3, 2), 150), 0);
        if (!ex.simplify().toString().equals("0.0"))
            System.out.println("#36:Error in simplify of" + ex.simplify());
        ex = new Mult(0, "x");
        if (!ex.simplify().toString().equals("0.0"))
            System.out.println("#37:Error in simplify of" + ex.simplify());
        ex = new Mult(0, new Pow(new Mult(10, 50), 50));
        if (!ex.simplify().toString().equals("0.0"))
            System.out.println("#38:Error in simplify of" + ex.simplify());
        //x+0
        ex = new Plus("x", 0);
        if (!ex.simplify().toString().equals("x"))
            System.out.println("#39:Error in simplify of" + ex.simplify());
        ex = new Plus(new Pow("x", "y"), 0);
        if (!ex.simplify().toString().equals("(x^y)"))
            System.out.println("#40:Error in simplify of" + ex.simplify());
        ex = new Plus(0, new Pow("x", "y"));
        if (!ex.simplify().toString().equals("(x^y)"))
            System.out.println("#41:Error in simplify of" + ex.simplify());
        ex = new Plus(0, "x");
        if (!ex.simplify().toString().equals("x"))
            System.out.println("#42:Error in simplify of" + ex.simplify());
        //x/x
        ex = new Div("x", "x");
        if (!ex.simplify().toString().equals("1.0"))
            System.out.println("#43:Error in simplify of" + ex.simplify());
        ex = new Div(new Mult(9, "x"), new Mult(9, "x"));
        if (!ex.simplify().toString().equals("1.0"))
            System.out.println("#44:Error in simplify of" + ex.simplify());
        ex = new Div(5, 5);
        if (!ex.simplify().toString().equals("1.0"))
            System.out.println("#45:Error in simplify of" + ex.simplify());
        //x/1
        ex = new Div("x", 1);
        if (!ex.simplify().toString().equals("x"))
            System.out.println("#46:Error in simplify of" + ex.simplify());
        ex = new Div(new Pow(10, "y"), 1);
        if (!ex.simplify().toString().equals("(10.0^y)"))
            System.out.println("#47:Error in simplify of" + ex.simplify());
        //x-0
        ex = new Minus("x", 0);
        if (!ex.simplify().toString().equals("x"))
            System.out.println("#48:Error in simplify of" + ex.simplify());
        ex = new Minus(new Div(10, "x"), 0);
        if (!ex.simplify().toString().equals("(10.0 / x)"))
            System.out.println("#49:Error in simplify of" + ex.simplify());
        //0-x
        ex = new Minus(0, "x");
        if (!ex.simplify().toString().equals("(-x)"))
            System.out.println("#50:Error in simplify of" + ex.simplify());
        ex = new Minus(0, new Cos("y"));
        if (!ex.simplify().toString().equals("(-cos(y))"))
            System.out.println("#51:Error in simplify of" + ex.simplify());
        //x-x
        ex = new Minus("x", "x");
        if (!ex.simplify().toString().equals("0.0"))
            System.out.println("#52:Error in simplify of" + ex.simplify());
        ex = new Minus(new Div(10, "x"), new Div(10, "x"));
        if (!ex.simplify().toString().equals("0.0"))
            System.out.println("#53:Error in simplify of" + ex.simplify());
        //log (x,x)
        ex = new Log("x", "x");
        if (!ex.simplify().toString().equals("1.0"))
            System.out.println("#54:Error in simplify of" + ex.simplify());
        ex = new Log(105, 105);
        if (!ex.simplify().toString().equals("1.0"))
            System.out.println("#55:Error in simplify of" + ex.simplify());
        ex = new Log(new Sin(new Pow(10, "x")), new Sin(new Pow(10, "x")));
        if (!ex.simplify().toString().equals("1.0"))
            System.out.println("#56:Error in simplify of" + ex.simplify());

        //BONUS!!!


        //x^1
        ex = new Pow("x", 1);
        if (!ex.simplify().toString().equals("x"))
            System.out.println("#57:Error in simplify of" + ex.simplify());
        ex = new Pow(new Pow("x", "y"), 1);
        if (!ex.simplify().toString().equals("(x^y)"))
            System.out.println("#58:Error in simplify of" + ex.simplify());
        //x^0
        ex = new Pow(new Pow("x", "y"), 0);
        if (!ex.simplify().toString().equals("1.0"))
            System.out.println("#59:Error in simplify of" + ex.simplify());
        //result
        ex = new Plus(10, new Minus(50, new Pow(3, new Div(10, new Sin(new Cos(0))))));
        if (!ex.simplify().toString().equals("-2.422264153431509E273"))
            System.out.println("#60:Error in simplify of" + ex.simplify());
        ex = new Cos(0);
        if (ex.evaluate() != 1)
            System.out.println("#61:Error in:" + ex.evaluate());
        //evaluate of cos(x) is NAN
        ex = new Cos("x");
        try {
            double d = ex.evaluate();
            System.out.println("#62:Error in:" + ex);
        } catch (Exception e) {
        }
        //neg
        ex = new Neg(-1);
        if (!ex.toString().equals("(--1.0)"))
            System.out.println("#63:Error in" + ex);
        ex = new Neg(-1);
        if (!ex.simplify().toString().equals("1.0"))
            System.out.println("#64:Error in" + ex);
        ex = new Neg(1);
        if (!ex.toString().equals("(-1.0)"))
            System.out.println("#65:Error in" + ex);
        ex = new Neg("x");
        if (!ex.toString().equals("(-x)"))
            System.out.println("#66:Error in" + ex);
        //log rules
        ex = new Log(2, 8);
        if (!ex.simplify().toString().equals("3.0"))
            System.out.println("#67:Error in simplify of" + ex.simplify());
        ex = new Log(2, "x");
        if (!ex.simplify().toString().equals("log(2.0, x)"))
            System.out.println("#68:Error in simplify of" + ex.simplify());
        //base>0
        ex = new Log(-1, 10);
        try {
            System.out.println("#69:Error in log(0, x" + ex.evaluate());
        } catch (Exception e) {
        }
        //base !=1
        ex = new Log(1, 10);
        try {
            System.out.println("#70:Error in log(1, x" + ex.evaluate());
        } catch (Exception e) {
        }
        //expression >0
        ex = new Log(5, 0);
        try {
            System.out.println("#71:Error in log(x, 0)" + ex.evaluate());
        } catch (Exception e) {
        }
//Differentitation
        ex = new Num(10);
        if (!ex.differentiate("x").toString().equals("0.0"))
            System.out.println("#72:Error in Differentitation of:" + ex);
        ex = new Var("x");
        if (!ex.differentiate("x").toString().equals("1.0"))
            System.out.println("#73:Error in Differentitation of:" + ex);
        ex = new Var("y");
        if (!ex.differentiate("x").toString().equals("0.0"))
            System.out.println("#74:Error in Differentitation of:" + ex);
        ex = new Cos(10);
        if (!(ex.differentiate("x").toString().equals("(-(sin(10.0) * 0.0))") || (ex.differentiate("x").toString().equals("((-sin(10.0)) * 0.0)"))))
            System.out.println("#75:Error in Differentitation of:" + ex);
        ex = new Cos("x");
        System.out.println(ex.differentiate("x").toString());
        //((-sin(x)) * 1.0) no???
        if (!ex.differentiate("x").toString().equals("(-(sin(x) * 1.0))"))
            System.out.println("#76:Error in Differentitation of:" + ex);
        ex = new Div(10, "x");
        if (!ex.differentiate("x").toString().equals("(((0.0 * x) - (10.0 * 1.0)) / (x^2.0))"))
            System.out.println("#77:Error in Differentitation of:" + ex);
        ex = new Log(10, "x");
        if (!ex.differentiate("x").toString().equals("(1.0 / (x * log(e, 10.0)))"))
            System.out.println("#78:Error in Differentitation of:" + ex);
        ex = new Minus(10, "x");
        if (!ex.differentiate("x").toString().equals("(0.0 - 1.0)"))
            System.out.println("#79:Error in Differentitation of:" + ex);
        ex = new Mult(10, "x");
        if (!ex.differentiate("x").toString().equals("((0.0 * x) + (10.0 * 1.0))"))
            System.out.println("#80:Error in Differentitation of:" + ex);
        ex = new Plus(10, "x");
        if (!ex.differentiate("x").toString().equals("(0.0 + 1.0)"))
            System.out.println("#81:Error in Differentitation of:" + ex);
        ex = new Pow(10, "x");
        if (!ex.differentiate("x").toString().equals("((10.0^x) * ((0.0 * (x / 10.0)) + (1.0 * log(e, 10.0))))"))
            System.out.println("#82:Error in Differentitation of:" + ex);
        ex = new Pow("x", "x");
        if (!ex.differentiate("x").toString().equals("((x^x) * ((1.0 * (x / x)) + (1.0 * log(e, x))))"))
            System.out.println("#83:Error in Differentitation of:" + ex);
        ex = new Pow("x", 3);
        if (!ex.differentiate("x").toString().equals("((x^3.0) * ((1.0 * (3.0 / x)) + (0.0 * log(e, x))))"))
            System.out.println("#84:Error in Differentitation of:" + ex);

        //Yoav tests
        ex = new Sin(new Pow(new Mult(new Plus(new Mult(new Num(2), new Var("x")), new Var("y")), new Num(4)), new Var("x")));
        if (!ex.toString().equals("sin(((((2.0 * x) + y) * 4.0)^x))"))
            System.out.println("#85:Error in simplify of" + ex);
        List<String> vars = ex.getVariables();
        if (!(vars.get(0).equals("x") && vars.get(1).equals("y"))) {
            System.out.println("#86:Error in getVarList");
        }

        Expression ex2 = ex.assign("x", new Num(10));
        if (!ex2.toString().equals("sin(((((2.0 * 10.0) + y) * 4.0)^10.0))")) {
            System.out.println("#87:Error in:" + ex2);
        }
        ex2 = ex.assign("x", ex);
        if (!ex2.toString().equals("sin(((((2.0 * sin(((((2.0 * x) + y) * 4.0)^x))) + y) * 4.0)^sin(((((2.0 * x) + y) * 4.0)^x))))")) {
            System.out.println("#88:Error in:" + ex2);
        }
// map checking
        ex = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 4.0);
        double value = ex.evaluate(assignment);
        if (value != 36) {
            System.out.println("#89:Error in map");
        }
        ex = new Sin(180);
        if (ex.evaluate() != 1.2246467991473532E-16)
            System.out.println("#90:Error in Radians:" + ex);
        ex = new Cos(180);
        if (ex.evaluate() != -1.0)
            System.out.println("#91:Error in Radians:" + ex);

//more tests
        ex = new Pow(new Var("x"), new Num(4)).differentiate("x");
        if (!ex.toString().equals("((x^4.0) * ((1.0 * (4.0 / x)) + (0.0 * log(e, x))))")) {
            System.out.println("#92:Error in:" + ex);
        }
        ex = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        if (!ex.differentiate("x").toString().equals("(((x + y)^2.0) * (((1.0 + 0.0) * (2.0 / (x + y))) + (0.0 * log(e, (x + y)))))")) {
            System.out.println("#93:Error in diff:" + ex.differentiate("x"));
        }
        ex = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        if (!ex.differentiate("x").simplify().toString().equals("(((x + y)^2.0) * (2.0 / (x + y)))")) {
            System.out.println("#94:Error in simplify of:" + ex.differentiate("x"));
        }

        ex = new Pow(new Var("e"), new Var("x"));
        if (!ex.differentiate("x").toString().equals("((e^x) * ((0.0 * (x / e)) + (1.0 * log(e, e))))")) {
            System.out.println("#95:Error in diff of:" + ex);
        }
        ex = ex.simplify();
        if (!ex.toString().equals("(e^x)")) {
            System.out.println("#96:Error in simplify of:" + ex);
        }

        ex = new Minus(new Plus("x", "y"), new Plus("y", "x"));
        if (!ex.simplify().toString().equals("0.0")) {
            System.out.println("#97:Error in simplify of:" + ex);
        }

        ex = new Div(new Plus("x", "y"), new Plus("y", "x"));
        if (!ex.simplify().toString().equals("1.0")) {
            System.out.println("#98:Error in simplify of:" + ex);
        }

        ex = new Log(new Plus("x", "y"), new Plus("y", "x"));
        if (!ex.simplify().toString().equals("1.0")) {
            System.out.println("#99:Error in simplify of:" + ex);
        }

        ex = new Plus(new Mult(new Plus(3, 6), "x"), new Mult(new Mult(4, "x"), new Sin(0)));
        if (!ex.simplify().toString().equals("(9.0 * x)")) {
            System.out.println("#100:Error in simplify of:" + ex);
        }
        try {
            ex = new Num(10);
        } catch (Exception e) {
            System.out.println("#101:you have to do int constructor");
        }
        ex = new Cos(new Plus("x", "y"));
        System.out.println(ex.differentiate("x").toString());
        if (!(ex.differentiate("x").toString().equals("(-(sin((x + y)) * (1.0 + 0.0)))")) || (ex.differentiate("x").toString().equals("((-sin((x + y)) * (1.0 + 0.0)))"))) {
            System.out.println("#102:Error in diff of:" + ex);
        }
        //bonus checking

        ex = new Pow(new Pow("x", "y"), "z");
        if (!ex.simplify().toString().equals("(x^(y * z))")) {
            System.out.println("#103:Error in bonus simplify of:" + ex.simplify());
        }
        ex = new Div(new Pow("x", 2), new Pow("y", 2));
        if (!ex.simplify().toString().equals("((x / y)^2.0)")) {
            System.out.println("#104:Error in bonus simplify of:" + ex);
        }
        ex = new Mult(new Pow("x", 2), new Pow("x", 3));
        if (!ex.simplify().toString().equals("(x^5.0)")) {
            System.out.println("#105:Error in bonus simplify of:" + ex);
        }
        ex = new Div(new Pow("x", 5), new Pow("x", 3));
        if (!ex.simplify().toString().equals("(x^2.0)")) {
            System.out.println("#106:Error in bonus simplify of:" + ex);
        }
        ex = new Div(new Pow("x", 2), new Pow("x", 3));
        if (!ex.simplify().toString().equals("(1.0 / x)")) {
            System.out.println("#107:Error in bonus simplify of:" + ex);
        }
        ex = new Mult(new Pow("x", 3), new Pow("y", 3));
        if (!ex.simplify().toString().equals("((x * y)^3.0)")) {
            System.out.println("#108:Error in bonus simplify of:" + ex);
        }
        ex = new Plus(new Mult("x", 2), new Mult("x", 4));
        if (!ex.simplify().toString().equals("(6.0 * x)")) {
            System.out.println("#109:Error in bonus simplify of:" + ex);
        }
        ex = new Mult(new Mult(2, "x"), new Mult("x", 3));
        if (!ex.simplify().toString().equals("(6.0 * (x^2.0))")) {
            System.out.println("#110:Error in bonus simplify of:" + ex);
        }
        ex = new Div(new Div(2, "x"), new Div("x", 3));
        if (!ex.simplify().toString().equals("(6.0 / (x^2.0))")) {
            System.out.println("#111:Error in bonus simplify of:" + ex);
        }
        ex = new Pow(-2, 0.5);
        if (!ex.toString().equals("(-2.0^0.5)")) {
            System.out.println("#112:Error in:" + ex);
        }
        try {
            System.out.println("#113:Error in evaluate:" + ex.evaluate());
        } catch (Exception e) {
        }
        try {
            System.out.println("#114:Error in simplify:" + ex.simplify());
        } catch (Exception e) {
        }

        System.out.println("...End of checking!");
    }
}
