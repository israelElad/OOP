package levels;

import biuoop.DrawSurface;
import levels.Background;
import shapes.Rectangle;

import java.awt.Color;

/**
 * Background with Color.
 * describes a Background of a level or block with color.
 *
 * @author Elad Israel
 * @version 4.0 17/06/2018
 */
public class BackgroundColor implements Background {
    private Color color;

    /**
     * Instantiates a new Background color.
     *
     * @param color the color
     */
    public BackgroundColor(Color color) {
        this.color = color;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d drawSurface
     */
    public void drawOn(DrawSurface d) {
        drawOn(d, null);
    }

    /**
     * Draw Background on drawsurface.
     *
     * @param d the drawsurface
     * @param rectangle the rectangle
     */
    public void drawOn(DrawSurface d, Rectangle rectangle) {
        d.setColor(color);
        if (rectangle == null) {
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        } else {
            d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                    (int) rectangle.getWidth(), (int) rectangle.getHeight());
        }
    }


    /**
     * notify the sprite that time has passed.
     * @param dt amount of seconds passed since the last call
     */
    public void timePassed(double dt) {

    }
}
