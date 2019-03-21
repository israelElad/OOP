/**
 * Classname: DescribeNumbers
 * Gets a list of numbers in the commandline,
 * and prints their minimum, maximum, and average values.
 *
 * @author Elad Israel
 * @version 1.0 11/03/2018
 */
public class DescribeNumbers {
    /**
     * main function- receives the numbers that needs to be described.
     *
     * @param args receives the numbers as argument(strings) via command line.
     */
    public static void main(String[] args) {
        //calls stringsToInts to convert the numbers received as Strings to int.
        int[] argsAsInts = stringsToInts(args);
        /* calls the functions that to the appropriate calculations
        (min, max, avg) and prints the results. */
        System.out.println("min: " + min(argsAsInts));
        System.out.println("max: " + max(argsAsInts));
        System.out.println("avg: " + avg(argsAsInts));
    }

    /**
     * stringsToInts converts the numbers received as args to an int array.
     *
     * @param numbers the numbers received as Strings that need to be converted.
     * @return an array of int containing the numbers.
     */
    public static int[] stringsToInts(String[] numbers) {
        //creates a new array in the length of the number of arguments.
        int[] argsAsInts = new int[numbers.length];
        // goes through the arguments and converts them one by one to int and
        // keeps them in the array.
        for (int i = 0; i < numbers.length; i++) {
            argsAsInts[i] = Integer.parseInt(numbers[i]);
        }
        return argsAsInts;
    }

    /**
     * min calculates the minimum of the numbers received in the int array.
     *
     * @param numbers is the array that stores the numbers received.
     * @return the minimum of all numbers in the array.
     */
    public static int min(int[] numbers) {
        //minimum value is initialized as the first number in the array.
        int minimum = numbers[0];
        /* goes through the array starting with the second place-
           and compares them to the minimum value. */
        for (int i = 1; i < numbers.length; i++) {
            //found a value smaller then the current minimum- the new minimum.
            if (numbers[i] < minimum) {
                minimum = numbers[i];
            }
        }
        return minimum;
    }

    /**
     * max calculates the maximum of the numbers received in the int array.
     *
     * @param numbers is the array that stores the numbers received.
     * @return the maximum of all numbers in the array.
     */
    public static int max(int[] numbers) {
        //maximum value is initialized as the first number in the array.
        int maximum = numbers[0];
        /* goes through the array starting with the second place-
           and compares them to the maximum value.*/
        for (int i = 1; i < numbers.length; i++) {
            //found a value bigger then the current maximum- the new maximum.
            if (numbers[i] > maximum) {
                maximum = numbers[i];
            }
        }
        return maximum;
    }

    /**
     * max calculates the average of the numbers received in the int array.
     *
     * @param numbers is the array that stores the numbers received.
     * @return the average of all numbers in the array.
     */
    public static float avg(int[] numbers) {
        /*keeps the sum of all numbers in the array, initialized as the first
         number in the array. */
        int sum = numbers[0];
        // goes through array starting with second place- and adds to the sum.
        for (int i = 1; i < numbers.length; i++) {
            sum += numbers[i];
        }
        // average is the sum divided by the amount of numbers.
        return ((float) sum / (float) numbers.length);
    }
}