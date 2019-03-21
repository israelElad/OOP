package levels;

import game.Block;
import game.Sprite;
import game.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Classname: DirectHitLevel.
 * first level: "Direct Hit"
 * contains a single block and a single ball that goes straight at it.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class DirectHitLevel implements LevelInformation {

    /**
     * Number of balls int.
     *
     * @return the int
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list of velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<Velocity>(this.numberOfBalls());
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(0, 5)));
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
        return "Direct Hit";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new DirectHitBackground();
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return Blocks list
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        blocks.add(new Block(388, 160, 25, 25, Color.red, Color.red, 1));
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
