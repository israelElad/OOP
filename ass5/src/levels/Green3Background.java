package levels;

import biuoop.DrawSurface;
import game.Sprite;

import java.awt.Color;

/**
 * Classname: Green3Background
 * background class for Green3Level.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class Green3Background implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d drawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.green.darker());
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //tower
        d.setColor(Color.decode("#393939").brighter().brighter());
        d.fillRectangle(115, 160, 10, 230);
        d.setColor(Color.decode("#393939").brighter());
        d.fillRectangle(100, 380, 40, 100);
        d.setColor(Color.decode("#393939"));
        d.fillRectangle(70, 440, 100, 200);
        d.setColor(Color.WHITE);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle((j * 20) + 75, (i * 30) + 450, 11, 20);
            }
        }
        //flashing beacon
        if ((System.currentTimeMillis() / 1000) % 3 == 0) {
            d.setColor(Color.decode("#D8A95E"));
            d.fillCircle(120, 150, 15);
            d.setColor(Color.decode("#F63D33"));
            d.fillCircle(120, 150, 10);
            d.setColor(Color.WHITE);
            d.fillCircle(120, 150, 5);
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {

    }
}
