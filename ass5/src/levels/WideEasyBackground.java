package levels;

import biuoop.DrawSurface;
import game.Sprite;

import java.awt.Color;

/**
 * Classname: WideEasyBackground
 * background class for WideEasyLevel.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class WideEasyBackground implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d drawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        int raysCount = 100;
        d.setColor(Color.decode("#FDFF88"));
        for (int i = 1; i <= raysCount; i++) {
            d.drawLine(150, 140, 7 * i, 250);
        }
        d.setColor(Color.decode("#EFE9AB"));
        d.fillCircle(150, 140, 60);
        d.setColor(Color.decode("#979314").brighter());
        d.fillCircle(150, 140, 50);
        d.setColor(Color.decode("#FFFB21"));
        d.fillCircle(150, 140, 40);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {

    }

}

