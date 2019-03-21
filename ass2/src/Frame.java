/**
 * Classname: Frame
 * a frame contains the limits in which the objects are allowed to move.
 * a frame could also have a color.
 *
 * @author Elad Israel
 * @version 1.0 01/04/2018
 */
public class Frame {

    //members
    private int x;
    private int y;
    private int width;
    private int height;
    private java.awt.Color fillColor;

    /**
     * Constructor 1.
     * Constructs a frame using starting point's coordinates, width, and height.
     *
     * @param x      coordinate x of the starting point of the frame.
     * @param y      coordinate y of the starting point of the frame.
     * @param width  width of the frame.
     * @param height height of the frame.
     */
    public Frame(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor 2.
     * Constructs a frame using starting point's coordinates, width, height, and color.
     *
     * @param x         coordinate x of the starting point of the frame.
     * @param y         coordinate y of the starting point of the frame.
     * @param width     width of the frame.
     * @param height    height of the frame.
     * @param fillColor the color of the filling.
     */
    public Frame(int x, int y, int width, int height, java.awt.Color fillColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
    }

    /**
     * Access method- Return the x value of the starting point of the frame.
     *
     * @return x coordinate of the starting point of the frame.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Access method- Return the y value of the starting point of the frame.
     *
     * @return y coordinate of the starting point of the frame.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Access method- Return the width of the frame.
     *
     * @return width of the frame.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Access method- Return the height of the frame.
     *
     * @return height of the frame.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Access method- Return the Color of the frame.
     *
     * @return Color of the frame.
     */
    public java.awt.Color getFillColor() {
        return this.fillColor;
    }
}