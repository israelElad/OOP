import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * Classname: BouncingBallAnimation.
 * Creates a ball that bounces on the screen within its limits.
 *
 * @author Elad Israel
 * @version 1.0 01/04/2018
 */
public class BouncingBallAnimation {

    private static final int GUI_WIDTH = 200;
    private static final int GUI_HEIGHT = 200;
    private static final int FRAME_X = 0;
    private static final int FRAME_Y = 0;
    private static final int BALL_STARTING_POINT_X = 100;
    private static final int BALL_STARTING_POINT_Y = 100;
    private static final int BALL_RADIUS = 30;
    private static final int BALL_SPEED = 5;

    /**
     * the main method that creates a ball that bounces on the screen.
     *
     * @param args not in use.
     */
    public static void main(String[] args) {
        //creates the graphical user interface.
        GUI gui = new GUI("BouncingBall", GUI_WIDTH, GUI_HEIGHT);
        //creates the frame of the ball
        Frame frame = new Frame(FRAME_X, FRAME_Y, GUI_WIDTH, GUI_HEIGHT);
        // creates the sleeper so that show can be delayed.
        Sleeper sleeper = new Sleeper();
        //creates the ball at its starting point.
        Ball ball = new Ball(BALL_STARTING_POINT_X, BALL_STARTING_POINT_Y, BALL_RADIUS, java.awt.Color.BLACK);
        //creates and sets the ball velocity.
        Velocity v = Velocity.fromAngleAndSpeed(BouncingBallsHelper.MAX_DEGREES, BALL_SPEED);
        ball.setVelocity(v);
        //sets the ball's frame
        ball.setFrame(frame);
        //do the animation
        while (true) {
            //moves the ball to the next position
            ball.moveOneStep();
            //creates the surface for the ball
            DrawSurface d = gui.getDrawSurface();
            //draws the ball on the surface
            ball.drawOn(d);
            //shows the drawing
            gui.show(d);
            sleeper.sleepFor(BouncingBallsHelper.SLEEP_INTERVAL);  // wait for 50 milliseconds.
        }
    }
}

