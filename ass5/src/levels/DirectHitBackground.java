package levels;

import biuoop.DrawSurface;
import game.Sprite;

import java.awt.Color;

/**
 * Classname: DirectHitBackground
 * background class for DirectHitLevel.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class DirectHitBackground implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d drawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.blue);
        d.drawCircle(400, 172, 50);
        d.drawCircle(400, 172, 80);
        d.drawCircle(400, 172, 110);
        d.drawLine(400, 192, 400, 312);
        d.drawLine(420, 172, 540, 172);
        d.drawLine(380, 172, 260, 172);
        d.drawLine(400, 152, 400, 32);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {

    }

}
