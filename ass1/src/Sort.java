/**
 * Classname: Sort
 * Receives sorting order followed by a list of numbers
 * from the commandline and print out the sorted list of numbers,
 * according to the sorting method received.
 *
 * @author Elad Israel
 * @version 1.0 11/03/2018
 */
public class Sort {
    /**
     * main function- receives the numbers that needs to be sorted.
     *
     * @param args receives the numbers as argument(strings)
     *             via command line.
     */
    public static void main(String[] args) {
        /* calls stringsToInts to convert the numbers received
        as Strings to int, and stores them. */
        int[] argsAsInts = stringsToInts(args);
        //user want to sort in ascending order
        if (args[0].equals("asc")) {
            sortArrAsc(argsAsInts);
            //user want to sort in descending order
        } else if (args[0].equals("desc")) {
            sortArrDesc(argsAsInts);
        } else {
            return;
        }
        //new line between each run
        System.out.println("");
    }

    /**
     * stringsToInts converts the numbers received
     * as args to an int array.
     *
     * @param numbers is the numbers received as Strings
     *                which needs to be converted.
     * @return an array of int containing the numbers.
     */
    public static int[] stringsToInts(String[] numbers) {
        /* creates a new array in the length of the number of args
        (minus 1 as the first arg is asc/desc).*/
        int[] argsAsInts = new int[numbers.length - 1];
        /* goes through the arguments and converts them one by one
        to int and keeps them in the array. */
        for (int i = 1; i < numbers.length; i++) {
            argsAsInts[i - 1] = Integer.parseInt(numbers[i]);
        }
        return argsAsInts;
    }

    /**
     * sortArrAsc sorts the numbers received in ascending order and
     * prints them.
     *
     * @param arr is the numbers received which needs to be sorted.
     */
    public static void sortArrAsc(int[] arr) {
        //keeps the value of the cell that is being switched
        int temp;
        /*
         * goes through the array and in each iteration of the
         * first loop takes the biggest number(using the second loop)
         * to the right (of those who aren't sorted).
         */
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //current cell bigger then the next - switch
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * sortArrDesc sorts the numbers received in descending order and
     * prints them.
     *
     * @param arr is the numbers received which needs to be sorted.
     */
    public static void sortArrDesc(int[] arr) {
        //keeps the value of the cell that is being switched
        int temp;
        /*
         * goes through the array and in each iteration of the
         * first loop takes the smallest number(using the second loop)
         * to the right (of those who aren't sorted).
         */
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //current cell smaller then the next - switch
                if (arr[j] < arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}