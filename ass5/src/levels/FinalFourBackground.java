package levels;

import biuoop.DrawSurface;
import game.Sprite;

import java.awt.Color;

/**
 * Classname: FinalFourBackground
 * background class for FinalFourLevel.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class FinalFourBackground implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d drawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.decode("#00B5DC"));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            d.drawLine(160 + i * 10, 390, 130 + i * 10, d.getHeight());
        }
        for (int i = 0; i < 10; i++) {
            d.drawLine(560 + i * 10, 440, 520 + i * 10, d.getHeight());
        }
        d.setColor(Color.decode("#B8BDBC"));
        d.fillCircle(150, 390, 25);
        d.setColor(Color.decode("#B3B8B7"));
        d.fillCircle(170, 420, 30);
        d.setColor(Color.decode("#ACB1B0"));
        d.fillCircle(200, 390, 30);
        d.setColor(Color.decode("#A4A9A8"));
        d.fillCircle(210, 420, 25);
        d.setColor(Color.decode("#9BA09F"));
        d.fillCircle(240, 405, 30);

        d.setColor(Color.decode("#B8BDBC"));
        d.fillCircle(550, 440, 25);
        d.setColor(Color.decode("#B3B8B7"));
        d.fillCircle(570, 470, 30);
        d.setColor(Color.decode("#ACB1B0"));
        d.fillCircle(600, 440, 30);
        d.setColor(Color.decode("#A4A9A8"));
        d.fillCircle(610, 470, 25);
        d.setColor(Color.decode("#9BA09F"));
        d.fillCircle(640, 455, 30);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {

    }
}

