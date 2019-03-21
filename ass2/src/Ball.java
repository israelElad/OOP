import biuoop.DrawSurface;

/**
 * Classname: Ball
 * a Ball (actually, a circle) has size (radius), color, and location (a Point).
 * Balls also know how to draw themselves on a DrawSurface.
 *
 * @author Elad Israel
 * @version 1.0 01/04/2018
 */
public class Ball {
    //members
    private int size;
    private Point point;
    private java.awt.Color color;
    private Velocity velocity;
    private Frame frame;

    /**
     * Constructor 1.
     * Constructs a Ball using center point, radius, and color.
     *
     * @param center center point of this ball.
     * @param r      radius of this ball.
     * @param color  color of this ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.size = r;
        this.point = center;
        this.color = color;
    }

    /**
     * Constructor 2.
     * Constructs a Ball using x and y coordinates of the center point, radius, and color.
     *
     * @param x     x coordinate of the center point of this ball.
     * @param y     y coordinate of the center point of this ball.
     * @param r     radius of this ball.
     * @param color color of this ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.size = r;
        this.point = new Point(x, y);
        this.color = color;
    }

    /**
     * Access method- Return the x value of this ball.
     *
     * @return x coordinate of the center point of this ball.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * Access method- Return the y value of this ball.
     *
     * @return y coordinate of the center point of this ball.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * Access method- Return the size(radius) of this ball.
     *
     * @return the size(radius) of this ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Access method- Return the color of this ball.
     *
     * @return the color of this ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draws this ball on the given DrawSurface.
     *
     * @param surface drawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.point.getX(), (int) this.point.getY(), this.size);
    }

    /**
     * sets the Velocity of the ball using dx and dy.
     *
     * @param dx dx value to set to this ball's velocity
     * @param dy dy value to set to this ball's velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * gets the Velocity of the ball.
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * sets the Velocity of the ball using Velocity.
     *
     * @param v Velocity value to set to this ball's velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * calculates the ball speed according to its size(bigger==slower).
     *
     * @param ballSize the ball's size
     * @return speed
     */
    public int speedAccToSize(int ballSize) {
        if (ballSize > 50) {
            return 1;
        }
        //51 so that ball size of 50 won't move in 0 speed.
        return 51 - ballSize;
    }

    /**
     * gets the frame of the ball.
     *
     * @return frame
     */
    public Frame getFrame() {
        return this.frame;
    }

    /**
     * sets the frame of the ball.
     *
     * @param newFrame the frame to set to the ball.
     */
    public void setFrame(Frame newFrame) {
        this.frame = newFrame;
    }

    /**
     * calculates where the ball should advance to next:
     * makes sure the ball does not go outside of the screen
     * when it hits the border to the left or to the right, it changes its horizontal direction,
     * and when it hits the border on the top or the bottom, it changes its vertical direction.
     * and then calls applyToPoint that actually moves the ball.
     */
    public void moveOneStep() {
        //default values of velocity if the velocity wasn't set before trying to move the ball.
        double dx = 1;
        double dy = 1;
        try {
            dx = this.velocity.getDx();
            dy = this.velocity.getDy();
            //if the velocity wasn't set before trying to move the ball, prints a message and sets the default values.
        } catch (NullPointerException e) {
            System.out.println("Ball's velocity wasn't defined. Velocity is now default values: dx=1, dy=1.");
            this.setVelocity(dx, dy);
        }
        //hit left wall or beyond it already
        if ((this.point.getX() - this.size + dx < this.frame.getX()) && (dx < 0)) {
            this.velocity.setDx(-dx);
        }
        //hit right wall or beyond it already
        if ((this.point.getX() + this.size + dx > this.frame.getX() + this.frame.getWidth()) && (dx > 0)) {
            this.velocity.setDx(-dx);
        }
        //hit upper wall or beyond it already
        if ((this.point.getY() - this.size + dy < this.frame.getY()) && (dy < 0)) {
            this.velocity.setDy(-dy);
        }
        //hit lower wall or beyond it already
        if ((this.point.getY() + this.size + dy > this.frame.getY() + this.frame.getHeight()) && (dy > 0)) {
            this.velocity.setDy(-dy);
        }
        //move the ball to its next position
        this.point = this.getVelocity().applyToPoint(this.point);
    }
}