import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Classname: MultipleBouncingBallsAnimation.
 * Creates multiple balls that bounces on the screen within their limits.
 * @author Elad Israel
 * @version 1.0 01/04/2018
 */
public class MultipleBouncingBallsAnimation {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int FRAME_X = 0;
    private static final int FRAME_Y = 0;

    /**
     * the main method that creates the balls and then activates the animation.     * expects to receive their sizes.
     * @param args contain the balls sizes.
     */
    public static void main(String[] args) {
        //no sizes were received
        if (args.length == 0) {
            System.out.println("Empty array, no arguments.");
            return;
        }
        //creates a new instance of the required classes
        MultipleBouncingBallsAnimation multipleBouncingBalls = new MultipleBouncingBallsAnimation();
        BouncingBallsHelper helper = new BouncingBallsHelper();
        //keeps the sizes of the balls as integers.
        int[] ballsSize;
        //tries to convert the received sizes from strings to ints
        try {
            ballsSize = helper.argsArrToIntArr(args);
            //invalid arguments were entered
        } catch (RuntimeException e) {
            System.out.println("Invalid arguments. Please only enter positive numbers.");
            return;
        }
        //creates the array that keeps the balls.
        Ball[] ballsArr = new Ball[ballsSize.length];
        //creates the frame
        Frame frame = new Frame(FRAME_X, FRAME_Y, GUI_WIDTH, GUI_HEIGHT);
        //builds the array of the balls.
        ballsArr = helper.ballsArrBuilder(ballsArr, ballsSize, frame);
        //activates the animation
        multipleBouncingBalls.animateMultipleBouncingBalls(ballsArr);
    }

    /**
     * Do the animation of the bouncing balls.
     * @param ballsArr the array of balls that will be draw on the screen
     */
    public void animateMultipleBouncingBalls(Ball[] ballsArr) {
        //creates the graphical user interface.
        GUI gui = new GUI("MultipleBouncingBalls", GUI_WIDTH, GUI_HEIGHT);
        // creates the sleeper so that show can be delayed.
        Sleeper sleeper = new Sleeper();
        //do the animation
        while (true) {
            //creates the surface for the ball
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < ballsArr.length; i++) {
                //moves the ball to the next position
                ballsArr[i].moveOneStep();
                //draws the ball on the surface
                ballsArr[i].drawOn(d);
            }
            //shows the drawing
            gui.show(d);
            sleeper.sleepFor(BouncingBallsHelper.SLEEP_INTERVAL);  // wait for 50 milliseconds.
        }
    }
}
