package game;

import animation.GameLevel;
import biuoop.DrawSurface;
import levels.Background;
import listeners.HitListener;
import listeners.HitNotifier;
import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classname: Block
 * Blocks are obstacles on the screen.
 * a Block (actually, a Rectangle) has size (as a rectangle), color, and location (a Point).
 * Blocks also know how to draw themselves on a DrawSurface.
 * A block can also notify the object that we collided with it about the new velocity it should have after collision.
 *
 * @author Elad Israel
 * @version 4.0 17/06/2018
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle collosionRectangle;
    private int hitPoints;
    private List<HitListener> hitListeners;
    private Map<Integer, Background> hpToBackground;
    private Background background;


    /**
     * Instantiates a new Block.
     *
     * @param rectangle the rectangle
     */
    public Block(Rectangle rectangle) {
        this.background = null;
        this.hpToBackground = new HashMap<>();
        this.collosionRectangle = rectangle;
    }

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
        this(new Rectangle(new Point(upperLeftX, upperLeftY), width, height));
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
        this(new Rectangle(new Point(upperLeftX, upperLeftY), width, height, fillColor));
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
        this(new Rectangle(new Point(upperLeftX, upperLeftY), width, height, fillColor, drawColor));
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
        this(new Rectangle(new Point(upperLeftX, upperLeftY), width, height, fillColor, drawColor));
        this.hitPoints = hitPoints;
    }

    /**
     * Sets collosion rectangle.
     *
     * @param rectangle the collosion rectangle
     */
    public void setCollosionRectangle(Rectangle rectangle) {
        this.collosionRectangle = rectangle;
    }

    /**
     * Sets background.
     *
     * @param backgroundToSet the background
     */
    public void setBackground(Background backgroundToSet) {
        this.background = backgroundToSet;
    }

    /**
     * Sets hp to background.
     *
     * @param hpToBackgroundToSet the hp to background
     */
    public void setHpToBackground(Map<Integer, Background> hpToBackgroundToSet) {
        this.hpToBackground = hpToBackgroundToSet;
    }

    /**
     * Sets stroke.
     *
     * @param stroke the stroke
     */
    public void setStroke(Color stroke) {
        this.getCollisionRectangle().setDrawColor(stroke);
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(double width) {
        this.collosionRectangle.setWidth(width);
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(double height) {
        this.collosionRectangle.setHeight(height);
    }

    /**
     * Add hp background.
     *
     * @param hp  the hit points
     * @param backgroundToSet the background
     */
    public void addHpBackground(int hp, Background backgroundToSet) {
        this.hpToBackground.put(hp, backgroundToSet);
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

        if (this.hpToBackground.containsKey(this.hitPoints)) {
            Background backgroundFromHp = this.hpToBackground.get(this.hitPoints);
            backgroundFromHp.drawOn(surface, collosionRectangle);
            if (this.collosionRectangle.getDrawColor() != null) {
                surface.setColor(this.collosionRectangle.getDrawColor());
                surface.drawRectangle((int) this.collosionRectangle.getUpperLeft().getX(), (int) this
                                .collosionRectangle.getUpperLeft().getY(),
                        (int) this.collosionRectangle.getWidth(), (int) this.collosionRectangle.getHeight());
            }
        } else {
            if (this.background == null) {
                this.collosionRectangle.drawOn(surface);
            } else { //background!=null
                this.background.drawOn(surface, collosionRectangle);
                if (this.collosionRectangle.getDrawColor() != null) {
                    surface.setColor(this.collosionRectangle.getDrawColor());
                    surface.drawRectangle((int) this.collosionRectangle.getUpperLeft().getX(), (int) this
                                    .collosionRectangle.getUpperLeft().getY(),
                            (int) this.collosionRectangle.getWidth(), (int) this.collosionRectangle.getHeight());
                }
            }
        }
    }

    /**
     * Gets hit points.
     *
     * @return the hit points
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * Sets hit points.
     *
     * @param hp the hit points
     */
    public void setHitPoints(int hp) {
        this.hitPoints = hp;
    }

    /**
     * Specify what the block does when time is passed. (currently- nothing).
     * @param dt amount of seconds passed since the last call
     */
    public void timePassed(double dt) {

    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the point of collision
     * @param currentVelocity the velocity of the ball before impact.
     * @param hitter          the ball that hits the block.
     * @return the new velocity the ball should have after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
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
     * will be called whenever a hit() occurs,
     * and notifiers all of the registered HitListener objects by calling their hitEvent method.
     *
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        if (this.hitListeners == null) {
            this.hitListeners = new ArrayList<>();
        }
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * adds the block to the game-as a sprite and as a Collidable.
     * also, increases the number of blocks in the game.
     *
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
        g.getNumOfBlocks().increase(1);
    }

    /**
     * removes the block from the gameLevel-as a sprite and as a Collidable.
     * Decrease in numOfBalls is executed in BallsRemover.
     *
     * @param gameLevel gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl HitListener to remove
     */
    public void addHitListener(HitListener hl) {
        if (this.hitListeners == null) {
            this.hitListeners = new ArrayList<>();
        }
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl HitListener to remove
     */
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners == null) {
            throw new RuntimeException("hitListeners List wasn't initialized."
                    + "cannot remove listener if no listeners were added");
        }
        this.hitListeners.remove(hl);
    }
}
