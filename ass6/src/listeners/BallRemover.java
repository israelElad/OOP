package listeners;

import animation.GameLevel;
import game.Block;
import game.Counter;
import listeners.HitListener;
import shapes.Ball;


/**
 * class name: BallRemover
 * BallRemover is in charge of removing balls from the gameLevel, as well as keeping count
 * of the number of balls that remain.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param gameLevel      the game level
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * whenever a special block that will sit at (or slightly below) the bottom of the screen is hit,
     * it will function as a "death region".
     * the BallRemover is registered as a listener of the death-region block, so that BallRemover will be
     * notified whenever a ball hits the death-region. Whenever this happens, the BallRemover will remove the ball
     * from the gameLevel and update the balls counter.
     *
     * @param beingHit the death region block
     * @param hitter   the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);
    }
}