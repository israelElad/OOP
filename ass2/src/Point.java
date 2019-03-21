/**
 * Classname: Point
 * A point has an x and a y value, and can measure the distance to other points,
 * and if its is equal to another point.
 *
 * @author Elad Israel
 * @version 1.0 01/04/2018
 */
public class Point {

    // Members
    private double x;
    private double y;

    /**
     * Constructor.
     * Constructs a Point using x coordinate and y coordinate.
     *
     * @param x X coordinate of this point.
     * @param y Y coordinate of this point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Access method- Return the x value of this point.
     *
     * @return x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Access method- Return the y value of this point.
     *
     * @return y value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * distance - return the distance of this point to the other point.
     *
     * @param other other point
     * @return distance
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * equals - return true if the points are equal, false otherwise.
     *
     * @param other other point
     * @return are equals or not(boolean)
     */
    public boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()));
    }
}