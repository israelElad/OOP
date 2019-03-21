package levels;

import game.Block;

/**
 * Creates a block.
 *
 * @author Elad Israel
 * @version 4.0 17/06/2018
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     *
     * @param xpos the x position of the block
     * @param ypos the y position of the block
     * @return block
     */
    Block create(int xpos, int ypos);
}