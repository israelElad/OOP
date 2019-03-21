package game;

import shapes.Ball;

/**
 * Class name: ScoreTrackingListener
 * updates the score counter when blocks are being hit and removed.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the object that is being hit.
     * @param hitter   the object that hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() > 1) {
            this.currentScore.increase(5);
        } else {
            this.currentScore.increase(10);
        }
    }
}