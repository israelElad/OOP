/**
 * Classname: Factorial
 * Calculating the factorial function twice:
 * once using a recursive definition, and once using an iterative procedure,
 * and print out the result.
 *
 * @author Elad Israel
 * @version 1.0 11/03/2018
 */
public class Factorial {
    /**
     * main function-receives the number whose factorial needs to be calculated.
     *
     * @param args receives number as argument(string) via command line.
     */
    public static void main(String[] args) {
        //converts the first argument from String to long and stores it.
        long n = Long.parseLong(args[0]);
        //keeps the result returned from the factorial calculations function.
        long factorialResult;
        //calls the recursive function to calculate the factorial and prints it.
        factorialResult = factorialRecursive(n);
        System.out.println("recursive: " + factorialResult);
        //calls the iterative function to calculate the factorial and prints it.
        factorialResult = factorialIter(n);
        System.out.println("iterative: " + factorialResult);
    }

    /**
     * factorialIter calculate the factorial of the number received iteratively.
     *
     * @param n is the number received whose factorial need to be calculated.
     * @return sum of multiplying 1 to n = the factorial.
     */
    public static long factorialIter(long n) {
        //keeps the sum of multiplying 1 to n
        long sum = 1;
        //goes from 1 to n and multiplies by i in each iteration.
        for (int i = 1; i <= n; i++) {
            sum = sum * i;
        }
        return sum;
    }

    /**
     * factorialRecursive calculates the factorial
     * of the number received recursively.
     *
     * @param n is the number received whose factorial need to be calculated.
     * @return factorial which is being build recursevly as the function
     * calls itself until n reaches 0.
     */
    public static long factorialRecursive(long n) {
        // stop sign
        if (n == 0) {
            return 1;
        }
        /* returns a recursive call to the function multiplied by n,
        which builds up the factorial. */
        return n * factorialRecursive(n - 1);
    }
}