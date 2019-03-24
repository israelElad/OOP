/**
 * SimplificationDemo class for bonus part.
 */
public class SimplificationDemo {
    /**
     * main function.
     * calls different functions to print simplifications examples.
     * @param args - not used.
     */
    public static void main(String[] args) {
        SimplificationDemo obj = new SimplificationDemo();
        obj.powerSimplify();
        obj.plusSimplify();
        obj.negSimplify();
        obj.minusSimplify();
        obj.divSimplify();

    }

    /**
     * powerSimplify function.
     * prints pow simplify examples.
     */
    private void powerSimplify() {
        System.out.println("Power simplify examples:");
        Expression powSimplify1 = new Pow(new Pow("x", "y"), "Z");
        System.out.println("Before Simplify:");
        System.out.println(powSimplify1);
        System.out.println("After Simplify:");
        System.out.println(powSimplify1.simplify());
        System.out.println("-----");
        Expression powSimplify2 = new Pow(new Pow(new Pow("x", "y"), "Z"), "x");
        System.out.println("Before Simplify:");
        System.out.println(powSimplify2);
        System.out.println("After Simplify:");
        System.out.println(powSimplify2.simplify());
        System.out.println("-----");
    }
    /**
     * plusSimplify function.
     * prints plus simplify examples.
     */
    private void plusSimplify() {
        System.out.println("Plus simplify examples:");
        Expression plusSimplify1 = new Plus(new Mult(2, "x"), new Mult(6, "x"));
        System.out.println("Before Simplify:");
        System.out.println(plusSimplify1);
        System.out.println("After Simplify:");
        System.out.println(plusSimplify1.simplify());
        System.out.println("-----");
        Expression plusSimplify2 = new Plus(new Mult(2, "x"), new Mult("x", 6));
        System.out.println("Before Simplify:");
        System.out.println(plusSimplify2);
        System.out.println("After Simplify:");
        System.out.println(plusSimplify2.simplify());
        System.out.println("-----");
        Expression plusSimplify3 = new Plus(new Plus(new Mult(2, "x"), new Mult("x", 6)), new Mult(5, "x"));
        System.out.println("Before Simplify:");
        System.out.println(plusSimplify3);
        System.out.println("After Simplify:");
        System.out.println(plusSimplify3.simplify());
        System.out.println("-----");
        Expression plusSimplify4 = new Plus(new Mult("x", 2), new Mult(6, "x"));
        System.out.println("Before Simplify:");
        System.out.println(plusSimplify4);
        System.out.println("After Simplify:");
        System.out.println(plusSimplify4.simplify());
        System.out.println("-----");
        Expression plusSimplify5 = new Plus(new Mult("x", 2), new Mult("x", 6));
        System.out.println("Before Simplify:");
        System.out.println(plusSimplify5);
        System.out.println("After Simplify:");
        System.out.println(plusSimplify5.simplify());
        System.out.println("-----");
        Expression plusSimplify6 = new Plus(new Plus("x", "y"), new Plus("y", "x"));
        System.out.println("Before Simplify:");
        System.out.println(plusSimplify6);
        System.out.println("After Simplify:");
        System.out.println(plusSimplify6.simplify());
        System.out.println("-----");
        Expression plusSimplify7 = new Plus(new Log("x", "y"), new Log("x", "y"));
        System.out.println("Before Simplify:");
        System.out.println(plusSimplify7);
        System.out.println("After Simplify:");
        System.out.println(plusSimplify7.simplify());
        System.out.println("-----");
        Expression plusSimplify8 = new Plus(new Div("x", "y"), new Div("x", "y"));
        System.out.println("Before Simplify:");
        System.out.println(plusSimplify8);
        System.out.println("After Simplify:");
        System.out.println(plusSimplify8.simplify());
        System.out.println("-----");
        Expression plusSimplify9 = new Plus(new Mult(new Plus("x", "y"), 2), new Mult(new Plus("x", "y"), 5));
        System.out.println("Before Simplify:");
        System.out.println(plusSimplify9);
        System.out.println("After Simplify:");
        System.out.println(plusSimplify9.simplify());
        System.out.println("-----");
        Expression plusSimplify10 = new Plus(new Plus(new Plus("x", "y"), new Plus("y", "x")),
                new Mult(new Plus("x", "y"), 5));
        System.out.println("Before Simplify:");
        System.out.println(plusSimplify10);
        System.out.println("After Simplify:");
        System.out.println(plusSimplify10.simplify());
        System.out.println("-----");
    }

    /**
     * negSimplify function.
     * prints neg simplify examples.
     */
    private  void negSimplify() {
        System.out.println("Neg simplify examples:");
        Expression negSimplify = new Neg(new Neg("x"));
        System.out.println("Before Simplify:");
        System.out.println(negSimplify);
        System.out.println("After Simplify:");
        System.out.println(negSimplify.simplify());
        System.out.println("-----");
        Expression negSimplify1 = new Neg(new Neg(new Plus("x", "y")));
        System.out.println("Before Simplify:");
        System.out.println(negSimplify1);
        System.out.println("After Simplify:");
        System.out.println(negSimplify1.simplify());
        System.out.println("-----");

    }
    /**
     * minusSimplify function.
     * prints minus simplify examples.
     */
    private void minusSimplify() {
        System.out.println("Minus simplify examples:");
        Expression minusSimplify = new Minus(new Plus("x", "y"), new Plus("y", "x"));
        System.out.println("Before Simplify:");
        System.out.println(minusSimplify);
        System.out.println("After Simplify:");
        System.out.println(minusSimplify.simplify());
        System.out.println("-----");

    }
    /**
     * divSimplify function.
     * prints div simplify examples.
     */
    private void divSimplify() {
        System.out.println("Div simplify examples:");
        Expression divSimplify = new Div(new Plus(5, new Neg(5)), new Plus("y", "x"));
        System.out.println("Before Simplify:");
        System.out.println(divSimplify);
        System.out.println("After Simplify:");
        System.out.println(divSimplify.simplify());
        System.out.println("-----");

    }


}
