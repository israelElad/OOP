/**
 * Classname: SimplificationDemo
 * Test class for bonus part of ass4.
 *
 * @author Elad Israel
 * @version 1.0 01/05/2018
 */
public class SimplificationDemo {
    /**
     * main method- runs the test.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        SimplificationDemo demoSimp = new SimplificationDemo();
        demoSimp.minusSimplifications();
        demoSimp.multSimplifications();
        demoSimp.negSimplifications();
        demoSimp.plusSimplifications();
    }

    /**
     * demonstrates simplification that can be done to Minus.
     */
    public void minusSimplifications() {
        // ((x * 5) - (x * 2)) => (3 * x)
        Expression minusSimplify1 = new Minus(new Mult("x", 5), new Mult("x", 2));
        System.out.println("Non-Simplified:");
        System.out.println(minusSimplify1);
        System.out.println("Bonus Simplified:");
        System.out.println(minusSimplify1.simplifyBonus());
        System.out.println("--------------");

        // ((x * 5) - (2 * x)) --> (3 * x)
        Expression minusSimplify2 = new Minus(new Mult("x", 5), new Mult(2, "x"));
        System.out.println("Non-Simplified:");
        System.out.println(minusSimplify2);
        System.out.println("Bonus Simplified:");
        System.out.println(minusSimplify2.simplifyBonus());
        System.out.println("--------------");

        // ((5 * x) - (2 * x)) --> (3 * x)
        Expression minusSimplify3 = new Minus(new Mult(5, "x"), new Mult(2, "x"));
        System.out.println("Non-Simplified:");
        System.out.println(minusSimplify3);
        System.out.println("Bonus Simplified:");
        System.out.println(minusSimplify3.simplifyBonus());
        System.out.println("--------------");

        // ((5 * x) - (x * 2)) --> (3 * x)
        Expression minusSimplify4 = new Minus(new Mult(5, "x"), new Mult("x", 2));
        System.out.println("Non-Simplified:");
        System.out.println(minusSimplify4);
        System.out.println("Bonus Simplified:");
        System.out.println(minusSimplify4.simplifyBonus());
        System.out.println("--------------");

        //((-x) - (-y)) --> (y - x)
        Expression minusSimplify5 = new Minus(new Neg("x"), new Neg("y"));
        System.out.println("Non-Simplified:");
        System.out.println(minusSimplify5);
        System.out.println("Bonus Simplified:");
        System.out.println(minusSimplify5.simplifyBonus());
        System.out.println("--------------");

        //((-x) - x) --> ((-2) * x)
        Expression minusSimplify6 = new Minus(new Neg("x"), "x");
        System.out.println("Non-Simplified:");
        System.out.println(minusSimplify6);
        System.out.println("Bonus Simplified:");
        System.out.println(minusSimplify6.simplifyBonus());
        System.out.println("--------------");

        //(x - (-x)) --> (2 * x)
        Expression minusSimplify7 = new Minus("x", new Neg("x"));
        System.out.println("Non-Simplified:");
        System.out.println(minusSimplify7);
        System.out.println("Bonus Simplified:");
        System.out.println(minusSimplify7.simplifyBonus());
        System.out.println("--------------");

        //((-x) - y) --> (-(x + y))
        Expression minusSimplify8 = new Minus(new Neg("x"), "y");
        System.out.println("Non-Simplified:");
        System.out.println(minusSimplify8);
        System.out.println("Bonus Simplified:");
        System.out.println(minusSimplify8.simplifyBonus());
        System.out.println("--------------");

        //(x - (-y)) --> (x + y)
        Expression minusSimplify9 = new Minus("x", new Neg("y"));
        System.out.println("Non-Simplified:");
        System.out.println(minusSimplify9);
        System.out.println("Bonus Simplified:");
        System.out.println(minusSimplify9.simplifyBonus());
        System.out.println("--------------");
    }

    /**
     * demonstrates simplification that can be done to Mult.
     */
    public void multSimplifications() {
        //(X * X) --> (2 ^ X)
        Expression multSimplify1 = new Mult("x", "x");
        System.out.println("Non-Simplified:");
        System.out.println(multSimplify1);
        System.out.println("Bonus Simplified:");
        System.out.println(multSimplify1.simplifyBonus());
        System.out.println("--------------");

        // ((x + y) * (y + x)) --> (x + y)^2
        Expression multSimplify2 = new Mult(new Plus("x", "y"), new Plus("y", "x"));
        System.out.println("Non-Simplified:");
        System.out.println(multSimplify2);
        System.out.println("Bonus Simplified:");
        System.out.println(multSimplify2.simplifyBonus());
        System.out.println("--------------");

        //(-x) * (-y) --> (x * y)
        Expression multSimplify3 = new Mult(new Neg("x"), new Neg("y"));
        System.out.println("Non-Simplified:");
        System.out.println(multSimplify3);
        System.out.println("Bonus Simplified:");
        System.out.println(multSimplify3.simplifyBonus());
        System.out.println("--------------");

        // x * (-y) --> -(x * y)
        Expression multSimplify4 = new Mult("x", new Neg("y"));
        System.out.println("Non-Simplified:");
        System.out.println(multSimplify4);
        System.out.println("Bonus Simplified:");
        System.out.println(multSimplify4.simplifyBonus());
        System.out.println("--------------");

        //(-x) * y --> -(x * y)
        Expression multSimplify5 = new Mult(new Neg("x"), "y");
        System.out.println("Non-Simplified:");
        System.out.println(multSimplify5);
        System.out.println("Bonus Simplified:");
        System.out.println(multSimplify5.simplifyBonus());
        System.out.println("--------------");

    }

    /**
     * demonstrates simplification that can be done to Neg.
     */
    public void negSimplifications() {
        //(-(-x)) --> x
        Expression negSimplify1 = new Neg(new Neg("x"));
        System.out.println("Non-Simplified:");
        System.out.println(negSimplify1);
        System.out.println("Bonus Simplified:");
        System.out.println(negSimplify1.simplifyBonus());
        System.out.println("--------------");

        // (-0.0) --> 0.0
        Expression negSimplify2 = new Neg(new Num(0));
        System.out.println("Non-Simplified:");
        System.out.println(negSimplify2);
        System.out.println("Bonus Simplified:");
        System.out.println(negSimplify2.simplifyBonus());
        System.out.println("--------------");
    }

    /**
     * demonstrates simplification that can be done to Plus.
     */
    public void plusSimplifications() {
        // (x + x) --> (2.0 * x))
        Expression plusSimplify1 = new Plus("x", "x");
        System.out.println("Non-Simplified:");
        System.out.println(plusSimplify1);
        System.out.println("Bonus Simplified:");
        System.out.println(plusSimplify1.simplifyBonus());
        System.out.println("--------------");

        // ((x + y) + (y + x)) --> (2.0 * (x + y))
        Expression plusSimplify2 = new Plus(new Plus("x", "y"), new Plus("y", "x"));
        System.out.println("Non-Simplified:");
        System.out.println(plusSimplify2);
        System.out.println("Bonus Simplified:");
        System.out.println(plusSimplify2.simplifyBonus());
        System.out.println("--------------");

        // ((x * 2) + (x * 5)) => (7 * x)
        Expression plusSimplify3 = new Plus(new Mult("x", 2), new Mult("x", 5));
        System.out.println("Non-Simplified:");
        System.out.println(plusSimplify3);
        System.out.println("Bonus Simplified:");
        System.out.println(plusSimplify3.simplifyBonus());
        System.out.println("--------------");

        // ((x * 2) + (5 * x)) --> (7 * x)
        Expression plusSimplify4 = new Plus(new Mult("x", 2), new Mult(5, "x"));
        System.out.println("Non-Simplified:");
        System.out.println(plusSimplify4);
        System.out.println("Bonus Simplified:");
        System.out.println(plusSimplify4.simplifyBonus());
        System.out.println("--------------");

        // ((2 * x) + (5 * x)) --> (7 * x)
        Expression plusSimplify5 = new Plus(new Mult(2, "x"), new Mult(5, "x"));
        System.out.println("Non-Simplified:");
        System.out.println(plusSimplify5);
        System.out.println("Bonus Simplified:");
        System.out.println(plusSimplify5.simplifyBonus());
        System.out.println("--------------");

        // ((2 * x) + (x * 5)) --> (7 * x)
        Expression plusSimplify6 = new Plus(new Mult(2, "x"), new Mult("x", 5));
        System.out.println("Non-Simplified:");
        System.out.println(plusSimplify6);
        System.out.println("Bonus Simplified:");
        System.out.println(plusSimplify6.simplifyBonus());
        System.out.println("--------------");

        //((-x) + (-y)) --> (-(x + y))
        Expression plusSimplify7 = new Plus(new Neg("x"), new Neg("y"));
        System.out.println("Non-Simplified:");
        System.out.println(plusSimplify7);
        System.out.println("Bonus Simplified:");
        System.out.println(plusSimplify7.simplifyBonus());
        System.out.println("--------------");

        //(-x) + x --> 0
        Expression plusSimplify8 = new Plus(new Neg("x"), "x");
        System.out.println("Non-Simplified:");
        System.out.println(plusSimplify8);
        System.out.println("Bonus Simplified:");
        System.out.println(plusSimplify8.simplifyBonus());
        System.out.println("--------------");

        //x + (-x) --> 0
        Expression plusSimplify9 = new Plus("x", new Neg("x"));
        System.out.println("Non-Simplified:");
        System.out.println(plusSimplify9);
        System.out.println("Bonus Simplified:");
        System.out.println(plusSimplify9.simplifyBonus());
        System.out.println("--------------");

        //((-x) + y) --> (y - x)
        Expression plusSimplify10 = new Plus(new Neg("x"), "y");
        System.out.println("Non-Simplified:");
        System.out.println(plusSimplify10);
        System.out.println("Bonus Simplified:");
        System.out.println(plusSimplify10.simplifyBonus());
        System.out.println("--------------");

        //(x + (-y)) --> (x - y)
        Expression plusSimplify11 = new Plus("x", new Neg("y"));
        System.out.println("Non-Simplified:");
        System.out.println(plusSimplify11);
        System.out.println("Bonus Simplified:");
        System.out.println(plusSimplify11.simplifyBonus());
        System.out.println("--------------");
    }
}
