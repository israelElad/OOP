import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by lizah on 15/05/2017.
 */
public class ExpressionsTestPart1 {
    static int gradePart1 = 0;

    /**
     * Testing code, using examples.
     * @param args the arguments for the application
     * @throws Exception
     */
    // public static void main(String[] args) throws Exception {

    public static void main(String[] args) throws Exception {


        // part 1

        // Expression
        // getVariables
        // assign
        // evaluate
        // Var , Sin , Plus , Mult , Pow

        Expression e14 = null;
        Expression e22 = null;
        double value = 0;
        String valString = null;
        String s = null;
        Expression e23 = null;
        Map<String, Double> assignment = new TreeMap<String, Double>();
        List<String> vars = null;


//    	System.out.println("-----------test 1----------------------------------");


        try
        {
            // ans is : (((x + y) + z)^2.0)
            e22 = new Pow(new Plus (new Plus(new Var("x"), new Var("y")) ,new Var("z")), new Num(2));
            s = e22.toString().toLowerCase().replaceAll("\\s+","").replaceAll("\\s+","");

            if (s.equals("(((x+y)+z)^2.0)"))
            {
                gradePart1++;
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 1 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

//    	System.out.println("-----------test 2----------------------------------");


        try
        {
            // ans is : (((x + y) + (z + w))^4.0)
            e23 = new Pow(new Plus (new Plus(new Var("x"), new Var("y")) , new Plus(new Var("z"), new Var("w")) ), new Num(4));
            s = e23.toString().toLowerCase().replaceAll("\\s+","");

            // (((x+y)+(z+w))^4.0)
            if (s.equals("(((x+y)+(z+w))^4.0)"))
            {
                gradePart1++;
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 2 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

//    	System.out.println("-----------test 3----------------------------------");
        // ans is : 234256.0


        try
        {
            // The result is: 234256.0
            assignment.put("x",  2.0);
            assignment.put("y",  4.0);
            assignment.put("z",  6.0);
            assignment.put("w",  10.0);
            value = e23.evaluate(assignment);

            if (value == 234256.0)
            {
                gradePart1++;
            }
        }


        catch(Exception exp)
        {
            System.out.println("Test 3 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }


//    	System.out.println("-----------test 4----------------------------------");

        try
        {
            // get vars of e23
            // ans is (the order is not important) :
            // x
            // y
            // z
            // w
            vars = e23.getVariables();
            Collections.sort(vars);

            List<String> correct = new ArrayList<String>();
            correct.add("x");
            correct.add("y");
            correct.add("z");
            correct.add("w");

            boolean ans = correct.retainAll(vars);
            if (!ans)
            {
                gradePart1++;
            }
        }


        catch(Exception exp)
        {
            System.out.println("Test 4 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

//    	System.out.println("-----------test 5----------------------------------");

        try
        {
            // (x + y)^0 = 1 , always !
            // ans is : 1.0 or 1
            Expression e2 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(0));
            value = e2.evaluate(assignment);
            if (value == 1)
            {
                gradePart1++;
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 5 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }


        //   	System.out.println("-----------test 6----------------------------------");

        try
        {
            // (x + y)^(-1)
            // Accepted answers :
            // 0.167
            // 0.166

            // ans is : (value >= 0.16 && value <= 0.17)
            // here we accept 0.16 + epsilon

            Expression e4 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(-1));
            value = e4.evaluate(assignment);
            value  = Double.parseDouble(new DecimalFormat("##.###").format(value));

            if (value >= 0.16 && value <= 0.17)
            {
                gradePart1++;
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 6 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }


        // 	System.out.println("-----------test 7----------------------------------");

        try
        {
            // -(x)
            // ans is : -2 (the assignment is above)

            Expression e5 = new Neg(new Var("x"));
            valString =  e5.toString().toLowerCase().replaceAll("\\s+","");
            value = e5.evaluate(assignment);

            if (value == -2)
            {
                gradePart1++;
            }

        }

        catch(Exception exp)
        {
            System.out.println("Test 7 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //  	System.out.println("-----------test 8----------------------------------");
        // ans is : x , where x = 2
        // final ans is : 2
        try
        {
            // -(-x) = x
            Expression e6 = new Neg(new Neg( new Var("x")));
            value = e6.evaluate(assignment);
            if (value == 2)
            {
                gradePart1++;
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 8 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //  	System.out.println("-----------test 9----------------------------------");


        try
        {
            // -(-(-(-y))) = y
            // y equals to 4
            // final ans : 4.0
            Expression e7 = new Neg( new Neg(new Neg(new Neg(new Var("y")))));
            value = e7.evaluate(assignment);
            if (value == 4)
            {
                gradePart1++;
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 9 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //   	System.out.println("-----------test 10----------------------------------");


        try
        {
            //   [ (-x) - (-y) ] ^ 2 = [ -x + y ] ^ 2 =  [ -2 + 4 ] ^ 2 = 2^2 = 4
            // ans is : 4.0
            Expression e8 = new Pow(new Minus(new Neg (new Var("x")), new Neg(new Var("y"))), new Num(2));
            valString = e8.toString().toLowerCase().replaceAll("\\s+","");

            assignment.clear();
            assignment.put("x",  -2.0);
            assignment.put("y",  -4.0);
            value = e8.evaluate(assignment);
            if (value == 4)
            {
                gradePart1++;
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 10 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //    	System.out.println("-----------test 11----------------------------------");

        try
        {
            // (-x) * (-(-y)) = (-)*x*y = -xy
            // -2 * 4 = - 8
            // ans is : -8
            Expression e9 = new Mult(new Neg(new Var("x")) , new Neg(new Neg(new Var("y"))));
            assignment.clear();
            assignment.put("x",  (double) -2);
            assignment.put("y",  (double) -4);
            value = e9.evaluate(assignment);
            if (value == -8)
            {
                gradePart1++;
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 11 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //   	System.out.println("-----------test 12----------------------------------");

        try
        {
            // log of 1 with base 2
            // ans is : 0
            Expression e10 = new Log(new Num(2) ,new Num(1));
            value = e10.evaluate(assignment);
            if (value == 0)
            {
                gradePart1++;
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 12 failed");
        }


        //  	System.out.println("-----------test 13----------------------------------");

        try
        {
            // (cos(0) - cos(0)) - (cos0 + cos0)
            // 0 - (1 + 1) = 0 - 2 = -2
            // ans is : -2
            Expression e11 = new Cos(new Num (0));
            e11 = new Minus( new Minus(new Cos(new Num (0)) ,new Cos(new Num (0)) ) , new Plus(new Cos(new Num (0)) ,new Cos(new Num (0)) ));
            value = e11.evaluate();
            if (value == -2)
            {
                gradePart1++;
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 13 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }


        //  	System.out.println("-----------test 14----------------------------------");

        try
        {
            // sin45 + cos 45
            // Accepted answer :
            // 1.414
            // ans is : 1.4 + epsilon
            Expression e12 = new Plus (new Cos(new Num (45)) , new Sin(new Num(45)));
            e12.toString().toLowerCase().replaceAll("\\s+","");
            value = e12.evaluate();
            value  = Double.parseDouble(new DecimalFormat("##.###").format(value));

            if (value >= 1.4 && value <= 1.5)
            {
                gradePart1++;
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 14 failed");
        }

        //    	System.out.println("-----------test 15----------------------------------");

        try
        {
            // sin(45)^2 + cos(45)^2 = 1 , trigonometric identity
            // ans is : 1.0
            Expression e13 = new Plus (new Pow(new Cos(new Num (45)) , new Num(2)) ,
                    new Pow(new Sin(new Num (45)) , new Num(2)));
            value = e13.evaluate();

            if (value == 1)
            {
                gradePart1++;
            }
        }

        catch(Exception exp)
        {
            System.out.println("Test 15 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }


        // 	System.out.println("-----------test 16----------------------------------");

        try
        {
            // -----(-2) plus -----(1) = 1
            // ans is : 1
            e14 = new Plus(
                    new Neg(new Neg(new Neg(new Neg(new Neg(new Num(-2)))))) ,
                    new Neg(new Neg(new Neg(new Neg(new Neg(new Num(1))))))
            );
            e14.toString().toLowerCase().replaceAll("\\s+","");
            value = e14.evaluate();

            if (value == 1)
            {
                gradePart1++;
            }


        }

        catch(Exception exp)
        {
            System.out.println("Test 16 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        //   	System.out.println("-----------test 17----------------------------------");

        try
        {
            // get the var of e14 ---> should return none
            // ans is "null" or "0"
            vars = e14.getVariables();

            if (vars == null || vars.size() == 0)
            {
                gradePart1++;
            }
        }


        catch(Exception exp)
        {
            System.out.println("Test 17 failed");
        }

        catch(StackOverflowError t)
        {
            System.out.println("Caught StackOverflowError");
        }

        System.out.println(gradePart1);
        // return gradePart1;

    }
}
