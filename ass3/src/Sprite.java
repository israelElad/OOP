import biuoop.DrawSurface;

/**
 * interface name: Sprite
 * A Sprite is a game object that can be drawn to the screen (and which is not just a background image).
 * Sprites can be drawn on the screen, and can be notified that time has passed
 * (so that they know to change their position / shape / appearance / etc)
 *
 * @author Elad Israel
 * @version 1.0 20/04/2018
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d drawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}