import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Class name: SpriteCollection
 * a SpriteCollection will hold a collection of sprites.
 *
 * @author Elad Israel
 * @version 1.0 20/04/2018
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<>();


    /**
     * add the given sprite to the collection.
     *
     * @param s given sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d drawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}