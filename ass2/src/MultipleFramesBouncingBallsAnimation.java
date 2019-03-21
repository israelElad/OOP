import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Classname: MultipleFramesBouncingBallsAnimation.
 * Creates multiple balls on multiple frames that bounces on these frames within their limits.
 *
 * @author Elad Israel
 * @version 1.0 01/04/2018
 */
public class MultipleFramesBouncingBallsAnimation {

    private static final int FRAME1_X = 50;
    private static final int FRAME1_Y = 50;
    private static final int FRAME1_WIDTH = 450;
    private static final int FRAME1_HEIGHT = 450;

    private static final int FRAME2_X = 450;
    private static final int FRAME2_Y = 450;
    private static final int FRAME2_WIDTH = 150;
    private static final int FRAME2_HEIGHT = 150;

    /**
     * the main method that creates the balls and then activates the animation.
     * expects to receive their sizes.
     *
     * @param args contain the balls sizes.
     */
    public static void main(String[] args) {
        //no sizes were received
        if (args.length == 0) {
            System.out.println("Empty array, no arguments.");
            return;
        }
        //odd number of balls sizes were received
        if (args.length % 2 == 1) {
            System.out.println("Odd number of balls.");
            return;
        }
        //creates a new instance of the required classes
        MultipleFramesBouncingBallsAnimation mFBB = new MultipleFramesBouncingBallsAnimation();
        BouncingBallsHelper helper = new BouncingBallsHelper();
        //keeps the sizes of the balls as integers.
        int[] ballsSizeToInt;
        //tries to convert the received sizes from strings to ints
        try {
            ballsSizeToInt = helper.argsArrToIntArr(args);
            //invalid arguments were entered
        } catch (RuntimeException e) {
            System.out.println("Invalid arguments. Please only enter positive numbers.");
            return;
        }
        //creates the first array that keeps the first frame balls' sizes as integers.
        int[] ballsSizeInt1 = mFBB.splitArrAccToIndexes(ballsSizeToInt, 0, ballsSizeToInt.length / 2 - 1);
        //creates the second array that keeps the second frame balls' sizes as integers.
        int[] ballsSizeInt2 = mFBB.splitArrAccToIndexes(ballsSizeToInt, ballsSizeToInt.length / 2,
                ballsSizeToInt.length - 1);
        //creates the first balls array that keeps the first frame balls.
        Ball[] ballsArr1 = new Ball[ballsSizeToInt.length / 2];
        //creates the second balls array that keeps the second frame balls.
        Ball[] ballsArr2 = new Ball[ballsSizeToInt.length / 2];
        //creates the frame of the first balls.
        Frame frame1 = new Frame(FRAME1_X, FRAME1_Y, FRAME1_WIDTH, FRAME1_HEIGHT, java.awt.Color.gray);
        //creates the frame of the second balls.
        Frame frame2 = new Frame(FRAME2_X, FRAME2_Y, FRAME2_WIDTH, FRAME2_HEIGHT, java.awt.Color.yellow);
        //builds the array of the first balls.
        ballsArr1 = helper.ballsArrBuilder(ballsArr1, ballsSizeInt1, frame1);
        //builds the array of the second balls.
        ballsArr2 = helper.ballsArrBuilder(ballsArr2, ballsSizeInt2, frame2);
        //activates the animation
        mFBB.animateMultipleBouncingBalls(ballsArr1, ballsArr2);
    }

    /**
     * returns an array that contains only the cells from the start index received to the end index.
     *
     * @param arr        the array that needs splitting
     * @param startIndex start take values from here
     * @param endIndex   take the values up to here.
     * @return the required portion of the array.
     */
    public int[] splitArrAccToIndexes(int[] arr, int startIndex, int endIndex) {
        int[] splitArr = new int[(endIndex - startIndex) + 1];
        for (int i = 0, j = startIndex; i < splitArr.length; i++, j++) {
            splitArr[i] = arr[j];
        }
        return splitArr;
    }

    /**
     * Do the animation of the bouncing balls.
     *
     * @param ballsArr1 the first array of balls that will be draw on the first frame.
     * @param ballsArr2 the second array of balls that will be draw on the second frame.
     */
    public void animateMultipleBouncingBalls(Ball[] ballsArr1, Ball[] ballsArr2) {
        //creates the graphical user interface and determine its size according to the frames sizes.
        GUI gui = new GUI("MultipleFramesBouncingBalls",
                Math.max(ballsArr1[0].getFrame().getX() + ballsArr1[0].getFrame().getWidth(),
                        ballsArr2[0].getFrame().getX() + ballsArr2[0].getFrame().getWidth()),
                Math.max(ballsArr1[0].getFrame().getY() + ballsArr1[0].getFrame().getHeight(),
                        ballsArr2[0].getFrame().getY() + ballsArr2[0].getFrame().getHeight()));
        // creates the sleeper so that show can be delayed.
        Sleeper sleeper = new Sleeper();
        //do the animation
        while (true) {
            //creates the surface for the ball
            DrawSurface surface = gui.getDrawSurface();
            //sets the color of the first frame.
            surface.setColor(ballsArr1[0].getFrame().getFillColor());
            //draws the first frame.
            surface.fillRectangle(ballsArr1[0].getFrame().getX(), ballsArr1[0].getFrame().getY(), ballsArr1[0]
                    .getFrame().getWidth(), ballsArr1[0].getFrame().getHeight());
            //sets the color of the second frame.
            surface.setColor(ballsArr2[0].getFrame().getFillColor());
            //draws the second frame.
            surface.fillRectangle(ballsArr2[0].getFrame().getX(), ballsArr2[0].getFrame().getY(), ballsArr2[0]
                    .getFrame().getWidth(), ballsArr2[0].getFrame().getHeight());
            //for each ball of the first group move and draw.
            for (int i = 0; i < ballsArr1.length; i++) {
                ballsArr1[i].moveOneStep();
                ballsArr1[i].drawOn(surface);
            }
            //for each ball of the second group move and draw.
            for (int i = 0; i < ballsArr2.length; i++) {
                ballsArr2[i].moveOneStep();
                ballsArr2[i].drawOn(surface);
            }
            //shows the drawing
            gui.show(surface);
            sleeper.sleepFor(BouncingBallsHelper.SLEEP_INTERVAL);  // wait for 50 milliseconds.
        }
    }
}