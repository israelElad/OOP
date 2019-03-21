import java.util.Random;

/**
 * Classname: BouncingBallsHelper
 * A helper class for BouncingBallAnimation, MultipleBouncingBallsAnimation, MultipleFramesBouncingBallsAnimation.
 * keeps mutual methods and constants.
 *
 * @author Elad Israel
 * @version 1.0 01/04/2018
 */
public class BouncingBallsHelper {

    //mutual constant- waiting time between frames.
    public static final int SLEEP_INTERVAL = 50;
    //mutual constant- max degrees in degrees.
    public static final int MAX_DEGREES = 180;
    //private constant - max value of rgb intensity.
    private static final int RGB_INTENSITY = 255;

    /**
     * builds the balls' array- updates an array of type "Ball" from an array of sizes, and a frame.
     *
     * @param ballsArr   original empty balls array
     * @param ballsSize  an array of all balls' sizes
     * @param ballLimits the frame that limits the balls
     * @return updated balls array
     */
    public Ball[] ballsArrBuilder(Ball[] ballsArr, int[] ballsSize, Frame ballLimits) {
        //goes through the array of sizes(which determines number of balls
        for (int i = 0; i < ballsArr.length; i++) {
            // create a random-number generator
            Random rand = new Random();
            //keeps size of current ball
            int size = ballsSize[i];
            // x and y coordinates of the location that the ball will be spawned.
            int x = 0;
            int y = 0;
            /* throw IllegalArgumentException "bound must be positive" if (ballLimits.getWidth()-(size*2))<0 or
            (ballLimits.getHeight()-(size*2))<0 because then the value in random is invalid(negative).
            */
            try {
                // get x coordinate as integer in the range of the frame minus the diameter of the ball.
                x = rand.nextInt(ballLimits.getWidth() - (size * 2)) + ballLimits.getX() + size;
                // get y coordinate as integer in the range of the frame minus the diameter of the ball.
                y = rand.nextInt(ballLimits.getHeight() - (size * 2)) + ballLimits.getY() + size;
            } catch (IllegalArgumentException e) {
                System.out.println("A ball diameter is bigger then it's frame");
            }
            //creates a new ball with the given size, and a random color and coordinates
            Ball ball = new Ball(x, y, size, randomColor());
            //sets the velocity of the ball according to its size, and a random angle.
            ball.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(MAX_DEGREES), ball.speedAccToSize(size)));
            //sets the frame of the ball.
            ball.setFrame(ballLimits);
            //inserts the ball to the balls' array
            ballsArr[i] = ball;
        }
        return ballsArr;
    }

    /**
     * converts the given array of string to positive integers.
     *
     * @param args given string to convert.
     * @return converted string as int[].
     * @throws RuntimeException throws exception if has invalid(not positive int) values.
     */
    public int[] argsArrToIntArr(String[] args) throws RuntimeException {
        //new int array
        int[] intArr = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            //converts to int each string and throws exception if invalid
            intArr[i] = Integer.parseInt(args[i]);
            if (intArr[i] <= 0) {
                throw new RuntimeException();
            }
        }
        return intArr;
    }

    /**
     * Colors on the computer are made up of a red, green, blue triplet; typically called RGB.
     * each of the 3 pieces can be in the range from 0 to 255.
     * this method takes random integers in the range of 0 to 255
     * and represent the color(the intensity of each color- red,green,blue) using these random numbers.
     *
     * @return the random color
     */
    public java.awt.Color randomColor() {
        Random rand = new Random(); // create a random-number generator
        int r = rand.nextInt(RGB_INTENSITY);
        int g = rand.nextInt(RGB_INTENSITY);
        int b = rand.nextInt(RGB_INTENSITY);
        return new java.awt.Color(r, g, b);
    }
}