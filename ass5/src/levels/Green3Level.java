package levels;

import game.Block;
import game.Sprite;
import game.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Classname: LevelInformation.
 * third level: "Green 3"
 * contains a few rows of blocks and a two balls, and a beacon tower.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class Green3Level implements LevelInformation {

    /**
     * Number of balls int.
     *
     * @return the int
     */
    public int numberOfBalls() {
        return 2;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list of velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<Velocity>(this.numberOfBalls());
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(40, 5)));
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(-40, 5)));
        return ballVelocities;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    public int paddleSpeed() {
        return 7;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    public int paddleWidth() {
        return 80;
    }

    /**
     * Level name string.
     * the level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    public String levelName() {
        return "Green 3";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new Green3Background();
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return Blocks list
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        //colors of each row(rainbow)
        final int numOfRows = 6;
        final int numOfColumns = 12;
        final int blockWidth = 50;
        final int blockHeight = 20;
        final int startingHeightOfBlocks = 150;
        final int startingWidthOfBlocks = 125;

        //colors of each row(rainbow)
        List<Color> rowsColors = new ArrayList<>();
        rowsColors.add(Color.RED);
        rowsColors.add(Color.ORANGE);
        rowsColors.add(Color.YELLOW);
        rowsColors.add(Color.GREEN);
        rowsColors.add(Color.BLUE);
        rowsColors.add(Color.magenta);
        /* for each row, starts from most-right block and adds them until reaches k
        (which goes up by 1 with each iteration- making one less block with each row.) */
        int k = 0;
        for (int i = 0; i < numOfRows; i++, k++) {
            for (int j = numOfColumns; j > k; j--) {
                if (i == 0) {
                    blocks.add(new Block(j * blockWidth + startingWidthOfBlocks, i * blockHeight
                            + startingHeightOfBlocks, blockWidth, blockHeight, rowsColors.get(i), Color.black, 2));
                } else {
                    blocks.add(new Block(j * blockWidth + startingWidthOfBlocks, i * blockHeight
                            + startingHeightOfBlocks, blockWidth, blockHeight, rowsColors.get(i), Color.black, 1));
                }
            }
        }
        return blocks;
    }

    /**
     * Number of levels that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the int
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
