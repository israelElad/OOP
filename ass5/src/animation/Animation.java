package animation;

import biuoop.DrawSurface;


/**
 * interface name: Animation
 * The Animation interface.
 * describes an animation object-
 * any animation should specify what to do in each frame, and notify when to stop the animation.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public interface Animation {
    /**
     * Do one frame of the animation.
     *
     * @param d the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should the animation stop.
     *
     * @return boolean
     */
    boolean shouldStop();
}