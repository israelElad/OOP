package listeners;

import animation.GameLevel;
import game.Block;
import game.Counter;
import listeners.HitListener;
import shapes.Ball;

/**
 * Classname: BlockRemover.
 * a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    private Counter blocksLeftToRemove;


    /**
     * Constructor.
     *
     * @param gameLevel          the game level
     * @param remainingBlocks    the remaining blocks
     * @param blocksLeftToRemove the blocks left to remove
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks, Counter blocksLeftToRemove) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
        this.blocksLeftToRemove = blocksLeftToRemove;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the gameLevel.
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball that hit.
     **/
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 1) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.gameLevel);
            remainingBlocks.decrease(1);
            blocksLeftToRemove.decrease(1);
        }
    }
}