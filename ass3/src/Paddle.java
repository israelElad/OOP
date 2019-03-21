import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * ClassName: Paddle
 * The Paddle is the player in the game. It is a rectangle that is controlled by the arrow keys,
 * and moves according to the player key presses.
 * It implements the Sprite and the Collidable interfaces.
 * It  also knows how to move to the left and to the right.
 *
 * @author Elad Israel
 * @version 1.0 20/04/2018
 */
public class Paddle implements Sprite, Collidable {

    private static final int SIDE_FRAMES_WIDTH = 25;
    private static final int FRAME_WIDTH = 800;
    private static final int PADDLE_SPEED = 7;
    private Rectangle paddle;
    private KeyboardSensor keyboardSensor;


    /**
     * Construct the Paddle using position point, width, height, fill color, draw color.
     * also receives the keyboardSensor and sets it.
     *
     * @param upperLeft      point(position)
     * @param width          of the paddle
     * @param height         of the paddle
     * @param fillColor      of the paddle
     * @param drawColor      of the paddle
     * @param keyboardSensor passed in order to identify the movements of the Paddle.
     */
    public Paddle(Point upperLeft, double width, double height, java.awt.Color fillColor, java.awt.Color drawColor,
                  KeyboardSensor keyboardSensor) {
        this.paddle = new Rectangle(upperLeft, width, height, fillColor, drawColor);
        this.keyboardSensor = keyboardSensor;
    }

    /**
     * moves the paddle to the left.
     */
    public void moveLeft() {
        //reached left edge(block)
        if (paddle.getUpperLeft().getX() <= SIDE_FRAMES_WIDTH) {
            return;
        }
        paddle.changePosition(new Point(paddle.getUpperLeft().getX() - PADDLE_SPEED, paddle.getUpperLeft().getY()));
    }

    /**
     * moves the paddle to the right.
     */
    public void moveRight() {
        //reached right edge(block)
        if (paddle.getUpperLeft().getX() + paddle.getWidth() >= FRAME_WIDTH - SIDE_FRAMES_WIDTH) {
            return;
        }
        paddle.changePosition(new Point(paddle.getUpperLeft().getX() + PADDLE_SPEED, paddle.getUpperLeft().getY()));
    }

    /**
     * Specify what the paddle does when time is passed - moves left or right if pressed.
     */
    public void timePassed() {
        if (keyboardSensor.isPressed(keyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboardSensor.isPressed(keyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draws the paddle on the surface.
     *
     * @param d draw surface
     */
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }

    /**
     * Return the "collision shape" of the object.
     *
     * @return collision shape- rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * <p>
     * The paddle have 5 equally-spaced regions. The behavior of the ball's bounce depends on where it hits the paddle.
     * Let's denote the left-most region as 1 and the rightmost as 5 (so the middle region is 3).
     * If the ball hits the middle region (region 3), it keeps its horizontal direction and only change its vertical
     * one (like when hitting a block).
     * However, if we hit region 1, the ball should bounce back with an angle of 300 degrees (-60),
     * regardless of where it came from. Remember, angle 0 = 360 is "up", so 300 means "a lot to the left".
     * Similarly, for region 2 it bounces back 330 degrees (a little to the left),
     * for region 4 it  bounces in 30 degrees, and for region 5 in 60 degrees.
     *
     * @param collisionPoint  the point of collision
     * @param currentVelocity the velocity of the ball before impact.
     * @return the new velocity the ball should have after the collision.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Line upperEdge = this.paddle.getUpperEdge();
        // divides the paddle's upper edge to 5 equally-spaced regions
        double upperEdgeRegionLength = upperEdge.length() / 5;
        //calculates the speed using Pitagoras (sqrt(dx^2+dy^2))=speed.
        double currentSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));

        // calculates the 5 regions
        Line leftMostRegion = new Line(upperEdge.start(), new Point(upperEdge.start().getX() + upperEdgeRegionLength,
                upperEdge.start().getY()));
        Line leftMiddleRegion = new Line(new Point(upperEdge.start().getX() + upperEdgeRegionLength,
                upperEdge.start().getY()), new Point(upperEdge.start().getX() + 2 * upperEdgeRegionLength,
                upperEdge.start().getY()));
        Line middleRegion = new Line(new Point(upperEdge.start().getX() + 2 * upperEdgeRegionLength,
                upperEdge.start().getY()), new Point(upperEdge.start().getX() + 3 * upperEdgeRegionLength,
                upperEdge.start().getY()));
        Line rightMiddleRegion = new Line(new Point(upperEdge.start().getX() + 3 * upperEdgeRegionLength,
                upperEdge.start().getY()), new Point(upperEdge.start().getX() + 4 * upperEdgeRegionLength,
                upperEdge.start().getY()));
        Line rightMostRegion = new Line(new Point(upperEdge.start().getX() + 4 * upperEdgeRegionLength,
                upperEdge.start().getY()), new Point(upperEdge.start().getX() + 5 * upperEdgeRegionLength,
                upperEdge.start().getY()));

        //deals with a collision accourding to the region(detailed explanation above)
        if (leftMostRegion.isPointOnTheLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, currentSpeed);
        }
        if (leftMiddleRegion.isPointOnTheLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, currentSpeed);
        }
        if (middleRegion.isPointOnTheLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        if (rightMiddleRegion.isPointOnTheLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, currentSpeed);
        }
        if (rightMostRegion.isPointOnTheLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, currentSpeed);
        }
        return currentVelocity;
    }

    /**
     * adds the Paddle to the game-as a sprite and as a Collidable.
     *
     * @param g game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}