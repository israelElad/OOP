import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lizah on 15/05/2017.
 */
public class ExpressionsTestPart3 {

    static int gradePart3 = 0;

    public static void main(String[] args) throws Exception {

        Map<String, Double> assignment = new TreeMap<String, Double>();
        Expression e = null;
        double value = 0;
        String s = null;

        //	System.out.println("-----------test 43----------------------------------");

        try
        {
            // ((2*8)-6)^2 =====> 100
            e = new Mult(new Num(2) , new Num(8));
            e = new Pow( new Minus(new Mult(new Num(2) , new Num(8)) , new Num(6)), new Num(2));
            value = e.evaluate();
            if (value == 100)
            {
                gradePart3++;
            }
            else {
                System.out.println("1");
            }
        }


        catch(Exception exp)
        {
            System.out.println("Test 43 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //		System.out.println("-----------test 44----------------------------------");

        try
        {
            // ((((2.0 * 8.0) - 6.0)^2.0) * 0.0) = 100 * 0 = 0
            // ans : 0
            e = new Mult( new Pow( new Minus(new Mult(new Num(2) , new Num(8)) , new Num(6)), new Num(2))
                    , new Num(0));
            value = e.evaluate();

            if (value == 0)
            {
                gradePart3++;
            } else {
                System.out.println("2");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 44 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //		System.out.println("-----------test 45----------------------------------");

        try
        {
            // ((((2.0 * 8.0) - 6.0)^2.0) / (((2.0 * 8.0) - 6.0)^2.0)) =
            // 100 / 100 = 1
            e = new Div(new Pow( new Minus(new Mult(new Num(2) , new Num(8)) , new Num(6)), new Num(2)) ,
                    new Pow( new Minus(new Mult(new Num(2) , new Num(8)) , new Num(6)), new Num(2)));
            e = e.simplify();
            if (e.toString().equals("1.0") || e.toString().equals("1"))
            {
                gradePart3++;
            } else {
                System.out.println("3");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 45 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //		System.out.println("-----------test 46----------------------------------");

        try
        {

            // ((((x * 2.0) + (x * 2.0)) + ((x * 2.0) + (x * 2.0))) * 1.0)
            // x = 2
            // 8x * 1 = 8x
            // ans : 16

            e = new Mult(new Plus(new Plus(new Mult(new Var("x") , new Num(2)) ,new Mult(new Var("x") , new Num(2)) ) , new Plus(new Mult(new Var("x") , new Num(2)) ,new Mult(new Var("x") , new Num(2)) )) , new Num(1));
            e = e.assign("x", new Num(2));
            value = e.evaluate();

            if (value == 16)
            {
                gradePart3++;
            } else {
                System.out.println("4");
            }

        }

        catch(Exception exp)
        {
            System.out.println("Test 46 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }


        //	System.out.println("-----------test 47----------------------------------");


        try
        {

            // ((e^(((2.0 * 8.0) - 6.0)^2.0)) * 1.0)
            // (e^100.0) * 1 = (e^100.0)

            e = new Mult(new Pow(new Var("e"), new Pow( new Minus(new Mult(new Num(2) , new Num(8)) , new Num(6)), new Num(2))) , new Num(1));
            e = e.simplify();
            s = e.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");
            s = s.toLowerCase();

            if (s.equals("(e^100.0)") || s.equals("e^100.0") || s.equals("e^100") || s.equals("(e^100)"))
            {
                gradePart3++;
            } else {
                System.out.println("5");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 47 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }


        //		System.out.println("-----------test 48----------------------------------");

        try
        {
            // ((((12.0 - 3.0) * 8.0) + 20.0) / 4.0)
            // ans ---> 23

            e = new Div(new Plus(new Mult(new Minus(new Num(12) , new Num(3)) , new Num(8)) , new Num(20)) , new Num(4));
            s = e.simplify().toString();
            if (s.equals("23.0") || s.equals("23"))
            {
                gradePart3++;
            } else {
                System.out.println("6");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 48 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //		System.out.println("-----------test 49----------------------------------");
        // (((-1.0 + 1.0) + (-1.0 + 1.0)) + ((-1.0 + 1.0) + (-1.0 + 1.0)))
        // ans = 0
        try
        {
            Expression a1 = new Plus( new Plus( new Plus(new Num(-1) , new Num(1)) , new Plus(new Num(-1) , new Num(1)))
                    ,new Plus( new Plus(new Num(-1) , new Num(1)) , new Plus(new Num(-1) , new Num(1))) );

            double d = a1.evaluate();
            if (d == 0)
            {
                gradePart3++;
            } else {
                System.out.println("7");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 49 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }
        //		System.out.println("-----------test 50----------------------------------");


        try
        {
            // like : X - X = 0
            // ((x^5.0) - (x^5.0))
            // 5x^4 - 5x^4

            e = new Minus(new Pow(new Var("x"), new Num(5)) ,new Pow(new Var("x"), new Num(5)) );
            Expression de = e.differentiate("x");
            Expression simp = de.simplify();  // 0.0

            assignment.clear();
            assignment.put("x", 13.0);
            value = simp.evaluate(assignment);

            if (value == 0)
            {
                gradePart3++;
            } else {
                System.out.println("8");
            }

        }

        catch(Exception exp)
        {
            System.out.println("Test 50 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //		System.out.println("-----------test 51----------------------------------");

        try
        {
            // like : 0 - X = -X
            // (0.0 - (e^(x * 2.0)))
            // should return : -7.389
            // We accepted answers in the range : value >= -7.4 && value <= -7.3

            e = new Minus(new Num(0) , new Pow(new Var("e"), new Mult(new Var("x") , new Num(2))));
            assignment.clear();
            assignment.put("x", 1.0);
            assignment.put("e", 2.71);
            value = e.evaluate(assignment);
            value  = Double.parseDouble(new DecimalFormat("##.###").format(value));

            if (value >= -7.4 && value <= -7.3 )
            {
                gradePart3++;
            } else {
                System.out.println("9");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 51 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //		System.out.println("-----------test 52----------------------------------");

        try
        {
            // like : 0 - X = -X
            // ((0.0 - 0.0) - (0.0 - (e^(x * 2.0))))
            // 0 - 0 - (-e^2x)  = e^(2x)
            // after assignment ---> 7.389
            e = new Minus( new Minus(new Num(0) , new Num(0)),
                    new Minus(new Num(0) , new Pow(new Var("e"), new Mult(new Var("x") , new Num(2)))));

            value = e.evaluate(assignment);
            // some cosmetics - uncomment this for some cosmetics to your answer
            // value  = Double.parseDouble(new DecimalFormat("##.###").format(value));

            if (value >= 7.3 && value <= 7.4)
            {
                gradePart3++;
            } else {
                System.out.println("10");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 52 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //		System.out.println("-----------test 53----------------------------------");

        try
        {
            // ((Log((2.0 * z), (2.0 * z))) * x)
            // Log(2z,2z) * x = x
            e = new Mult(new Log(new Mult(new Num(2) , new Var("z")) , new Mult(new Num(2) , new Var("z"))) ,
                    new Var("x"));

            if (e.simplify().toString().equals("x"))
            {
                gradePart3++;
            } else {
                System.out.println("11");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 53 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //		System.out.println("-----------test 54----------------------------------");

        try
        {
            // (((Log((2.0 * z), (2.0 * z))) * (Cos(0.0))) * (Sin(90.0)))
            // Log(2z,z2) * Cos(0) * sin(90) = 1
            // 1 * 1 * 1 = 1

            e = new Mult(new Mult(new Log(new Mult(new Num(2) , new Var("z")) , new Mult(new Num(2) , new Var("z"))) , new Cos(new Num(0))) , new Sin(new Num(90)) );

            if (e.simplify().toString().equals("1") || e.simplify().toString().equals("1.0"))
            {
                gradePart3++;
            } else {
                System.out.println("12");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 54 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        // System.out.println("-----------test 55----------------------------------");


        try
        {
            // (((3.0 + 10.0) * x) + ((y * 15.0) * (Sin(0.0))))

            e = new Plus (new Mult(new Plus(new Num(3) , new Num(10))  , new Var("x"))  ,new Mult( new Mult(new Var("y") , new Num(15)) , new Sin(new Num(0))) );
            // no need of "y" anymore ...

            e = e.simplify();
            e = e.assign("x", new Num(4));

            if (e.evaluate() == 52.0  || e.evaluate() == 52)
            {
                gradePart3++;
            } else {
                System.out.println("13");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 55 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }



        //		System.out.println("-----------test 56----------------------------------");
        // check if it still can be here ...

        try
        {
            // ((((20.0 * (x^5.0)) * (3.0 * (e^x))) * (Cos((x^2.0)))) * (x - x))
            // (20*x^5 * 3e^x ) * Cos(x^2) * (x - x)
            // this little beauty equals 0

            e = new Mult(new Mult( new Mult( new Mult(new Num(20) ,new Pow(new Var("x") , new Num(5))) , new Mult(new Num(3) , new Pow(new Var("e"), new Var("x"))) )
                    , new Cos(new Pow(new Var("x") , new Num(2)))) , new Minus(new Var("x") , new Var("x")));

String dds =e.simplify().toString();
            if (e.simplify().toString().equals("0.0") || e.simplify().toString().equals("0"))
            {
                gradePart3++;
            } else {
                System.out.println("14");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 56 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //		System.out.println("-----------test 57----------------------------------");

        try
        {
            // ((((20.0 * (x^5.0)) + (3.0 * (e^x))) * (Cos((x^2.0)))) + (x - x))
            // answer is : 3.0

            e = new Plus(new Mult( new Plus( new Mult(new Num(20) ,new Pow(new Var("x") , new Num(5))) , new Mult(new Num(3) , new Pow(new Var("e"), new Var("x"))) )
                    , new Cos(new Pow(new Var("x") , new Num(2)))) , new Minus(new Var("x") , new Var("x")));
            assignment.clear();
            assignment.put("x", 0.0);
            assignment.put("e", 2.71);
            value = e.evaluate(assignment);

            if (value == 3.0 || value == 3)
            {
                gradePart3++;
            } else {
                System.out.println("15");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 57 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //		System.out.println("-----------test 58----------------------------------");

        // new one :

        // x * 0 = 0

        try
        {
            // ((((e^x) * (e^x)) * ((e^x) * (e^x))) * 0.0)
            // ans : 0

            e = new Mult(new Mult(new Mult (new Pow(new Var("e"), new Var("x")) , new Pow(new Var("e"), new Var("x"))) , new Mult (new Pow(new Var("e"), new Var("x")) , new Pow(new Var("e"), new Var("x")))) , new Num(0));
            e = e.assign("x", new Num(4));
            e = e.assign("e", new Num(2.71));
            value = e.evaluate();
            if (value == 0)
            {
                gradePart3++;
            } else {
                System.out.println("16");
            }

        }

        catch(Exception exp)
        {
            System.out.println("Test 57 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //		System.out.println("-----------test 59----------------------------------");

        // new one : x = 0 , e = 2.71
        // your output should be : 1
        // [ (e^x * e^2x) / (e^3x) ]  * [(e^4x * e^3x) / e^7x] * [e^15x / (e^7x * e^8x)]

        try
        {
            // ((((e^x) * ((e^x)^2.0)) / ((e^x)^3.0)) * ((((e^x)^4.0) * ((e^x)^3.0)) / ((e^x)^7.0)))
            // ans : 1

            e = new Mult(new Div(new Mult(new Pow(new Var("e"), new Var("x")) ,new Pow(new Pow(new Var("e"), new Var("x")) , new Num(2))) , new Pow(new Pow(new Var("e"), new Var("x")) , new Num(3)))
                    , new Div( new Mult(new Pow(new Pow(new Var("e"), new Var("x")) , new Num(4)) , new Pow(new Pow(new Var("e"), new Var("x")) , new Num(3)) ) ,e = new Pow(new Pow(new Var("e"), new Var("x")) , new Num(7)) ));

            e = e.assign("e", new Num(2.71));
            e = e.assign("x", new Num(0));
            value = e.evaluate();

            if (value == 1)
            {
                gradePart3++;
            } else {
                System.out.println("17");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 57 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }




        //	System.out.println("-----------test 60----------------------------------");

        // (((((e^x) * ((e^x)^2.0)) / ((e^x)^3.0)) / ((((e^x)^4.0) * ((e^x)^3.0)) / ((e^x)^7.0))) * (Sin(0.0)))
        // x = 0
        // your output should be : 0

        try
        {
            e = new Mult(new Div( new Div(new Mult(new Pow(new Var("e"), new Var("x")) ,new Pow(new Pow(new Var("e"), new Var("x")) , new Num(2))) , new Pow(new Pow(new Var("e"), new Var("x")) , new Num(3)))
                    , new Div( new Mult(new Pow(new Pow(new Var("e"), new Var("x")) , new Num(4)) , new Pow(new Pow(new Var("e"), new Var("x")) , new Num(3)) ) ,e = new Pow(new Pow(new Var("e"), new Var("x")) , new Num(7)) )) , new Sin(new Num(0)));

            e = e.assign("e", new Num(2.71));
            e = e.assign("x", new Num(0));
            value = e.evaluate();

            if (value == 0)
            {
                gradePart3++;
            } else {
                System.out.println("18");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 57 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //   	System.out.println("-----------test 61----------------------------------");

        try
        {
            // (Sin(-(-(-(-(-(0.0))))))) = 0
            // -0 is also ok

            e = new Sin(new Neg(new Neg(new Neg(new Neg(new Neg(new Num(0)))))));
            value = e.evaluate();

            if (value == 0)
            {
                gradePart3++;
            } else {
                System.out.println("19");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 61 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }


        //		System.out.println("-----------test 62 ----------------------------------");

        try
        {
            // (x + y)
            // check if an exception is thrown when there is a missing assignment
            e = new Plus(new Var("x") , new Var("y"));
            e = e.assign("x", new Num(2));
            // e = e.assign("y", new Num(2));
            value = e.evaluate();
        }

        catch(Exception exp)
        {
            // System.out.println("Test 62  succeeded");
            gradePart3++;
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }



        //		System.out.println("-----------test 63 ----------------------------------");

        try
        {
            // (((x * 2.0) - ((x * 2.0) + (y * 3.0))) * ((-(9.0) / -(9.0)) + ((-(9.0) / -(9.0)) * -(-1.0))))
            // (((2.0 * 2.0) - ((2.0 * 2.0) + (3.0 * 3.0))) * ((-(9.0) / -(9.0)) + ((-(9.0) / -(9.0)) * -(-1.0))))
            e = new Mult(new Minus(new Mult(new Var("x") ,new Num(2) ) , new Plus(new Mult("x" , 2) , new Mult("y" , 3))) ,new Plus(new Div(new Neg(new Num(9)) , new Neg(new Num(9))) , new Mult(new Div(new Neg(new Num(9)) , new Neg(new Num(9))) , new Neg(new Num(-1)))) );
            e = e.assign("x", new Num(2));
            e = e.assign("y", new Num(3));
            value = e.evaluate();
            if (value == -18)
            {
                gradePart3++;
            } else {
                System.out.println("19");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 63  failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }



        try
        {
            //		System.out.println("-----------test 64 ----------------------------------");
            // ((1.0 + -(-9.0)) * -(-(-(-(-(-(-(-(-(-(-(-(-1.0))))))))))))) = -10.0
            e = new Mult(new Plus(new Var("x") , new Neg(new Num(-9))) ,new Neg(new Neg(new Neg(new Neg(new Neg(new Neg(new Neg(new Neg(new Neg(new Neg(new Neg(new Neg(new Num(-1))))))))))))) );
            e = e.assign("x", new Num(1));
            value = e.evaluate();


            if (value == -10)
            {
                gradePart3++;
            } else {
                System.out.println("20");
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 64  failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        System.out.println(gradePart3);

    }
}
