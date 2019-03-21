import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Classname: Block
 * Blocks are obstacles on the screen.
 * a Block (actually, a Rectangle) has size (as a rectangle), color, and location (a Point).
 * Blocks also know how to draw themselves on a DrawSurface.
 * A block can also notify the object that we collided with it about the new velocity it should have after collision.
 *
 * @author Elad Israel
 * @version 1.0 20/04/2018
 */
public class Block implements Collidable, Sprite {

    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 800;
    private Rectangle collosionRectangle;
    private int hitPoints;

    /**
     * Constructor1
     * construct a Block using upper-left X coordinate, upper-left Y coordinate, width and height.
     *
     * @param upperLeftX upper-left corner X coordinate
     * @param upperLeftY upper-left corner Y coordinate
     * @param width      of the rectangle
     * @param height     of the rectangle
     */
    public Block(double upperLeftX, double upperLeftY, double width, double height) {
        this.collosionRectangle = new Rectangle(new Point(upperLeftX, upperLeftY), width, height);
    }

    /**
     * Constructor2
     * construct a Block using upper-left X coordinate, upper-left Y coordinate, width, height and color to fill.
     *
     * @param upperLeftX upper-left corner X coordinate
     * @param upperLeftY upper-left corner Y coordinate
     * @param width      of the rectangle
     * @param height     of the rectangle
     * @param fillColor  of the rectangle
     */
    public Block(double upperLeftX, double upperLeftY, double width, double height, java.awt.Color fillColor) {
        this.collosionRectangle = new Rectangle(new Point(upperLeftX, upperLeftY), width, height, fillColor);
    }

    /**
     * Constructor2
     * construct a Block using upper-left X coordinate, upper-left Y coordinate,
     * width, height and colors to fill and draw.
     *
     * @param upperLeftX upper-left corner X coordinate
     * @param upperLeftY upper-left corner Y coordinate
     * @param width      of the rectangle
     * @param height     of the rectangle
     * @param fillColor  of the rectangle
     * @param drawColor  of the rectangle
     */
    public Block(double upperLeftX, double upperLeftY, double width, double height, java.awt.Color fillColor, java.awt
            .Color drawColor) {
        this.collosionRectangle = new Rectangle(new Point(upperLeftX, upperLeftY), width, height, fillColor, drawColor);
    }

    /**
     * Constructor2
     * construct a Block using upper-left X coordinate, upper-left Y coordinate,
     * width, height and colors to fill and draw.
     *
     * @param upperLeftX upper-left corner X coordinate
     * @param upperLeftY upper-left corner Y coordinate
     * @param width      of the rectangle
     * @param height     of the rectangle
     * @param fillColor  of the rectangle
     * @param drawColor  of the rectangle
     * @param hitPoints  of the rectangle
     */
    public Block(double upperLeftX, double upperLeftY, double width, double height, java.awt.Color fillColor, java.awt
            .Color drawColor, int hitPoints) {
        this.collosionRectangle = new Rectangle(new Point(upperLeftX, upperLeftY), width, height, fillColor, drawColor);
        this.hitPoints = hitPoints;
    }

    /**
     * Return the "collision shape" of the object - the rectangle.
     *
     * @return collision shape- rectangle
     */
    public Rectangle getCollisionRectangle() {
        return collosionRectangle;
    }

    /**
     * draws this block on the given DrawSurface.
     * also, draws its hitPoints.
     *
     * @param surface drawSurface
     */
    public void drawOn(DrawSurface surface) {
        final int lettersSize = 15;
        double height = this.collosionRectangle.getHeight();
        double width = this.collosionRectangle.getWidth();
        Point upperLeft = this.collosionRectangle.getUpperLeft();
        this.collosionRectangle.drawOn(surface);
        surface.setColor(Color.white);
        //no hitPoints- draw an "X"
        if (this.hitPoints == 0) {
            //if its a frame block
            if (height == FRAME_HEIGHT || width == FRAME_WIDTH) {
                surface.drawText((int) (upperLeft.getX() + width / 2 - 5),
                        (int) (upperLeft.getY() + height / 2 + 5), "X", lettersSize);
            } else { //if its any other block
                surface.drawText((int) (upperLeft.getX() + width / 2.2),
                        (int) (upperLeft.getY() + height / 1.3), "X", lettersSize);
            }
        } else { //if its a frame block
            if (height == FRAME_HEIGHT || width == FRAME_WIDTH) {
                surface.drawText((int) (upperLeft.getX() + width / 2 - 5),
                        (int) (upperLeft.getY() + height / 2 + 5), Integer.toString(this.hitPoints), lettersSize);
            } else { //if its any other block
                surface.drawText((int) (upperLeft.getX() + width / 2.2),
                        (int) (upperLeft.getY() + height / 1.3), Integer.toString(this.hitPoints), lettersSize);
            }
        }
    }

    /**
     * Specify what the block does when time is passed. (currently- nothing).
     */
    public void timePassed() {

    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the point of collision
     * @param currentVelocity the velocity of the ball before impact.
     * @return the new velocity the ball should have after the collision.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        //lower the block's HP
        if (this.hitPoints > 0) {
            this.hitPoints--;
        }
        //checks on which edge the collision point is and returns the appropriate velocity accordingly.
        if (collosionRectangle.getUpperEdge().isPointOnTheLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        if (collosionRectangle.getLowerEdge().isPointOnTheLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        if (collosionRectangle.getLeftEdge().isPointOnTheLine(collisionPoint)) {
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (collosionRectangle.getRightEdge().isPointOnTheLine(collisionPoint)) {
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * adds the block to the game-as a sprite and as a Collidable.
     *
     * @param g game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
