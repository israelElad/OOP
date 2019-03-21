package levels;

import game.Block;
import game.Velocity;

import java.util.List;


/**
 * produces a level.
 *
 * @author Elad Israel
 * @version 4.0 17/06/2018
 */
public class LevelFactory implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Background getBackground;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * Instantiates a new Level factory.
     */
    public LevelFactory() {
        this.numberOfBalls = -1;
        this.initialBallVelocities = null;
        this.paddleSpeed = -1;
        this.paddleWidth = -1;
        this.levelName = null;
        this.getBackground = null;
        this.blocks = null;
        this.numberOfBlocksToRemove = -1;
    }

    /**
     * Is all set boolean.
     *
     * @return the boolean
     */
    public Boolean isAllSet() {
        if (this.numberOfBalls != -1
                && this.initialBallVelocities != null
                && this.paddleSpeed != -1
                && this.paddleWidth != -1
                && this.levelName != null
                && this.getBackground != null
                && this.blocks != null
                && this.numberOfBlocksToRemove != -1) {
            return true;
        }
        return false;
    }

    /**
     * Sets number of balls.
     *
     * @param numberOfBallsToSet the number of balls
     */
    public void setNumberOfBalls(int numberOfBallsToSet) {
        this.numberOfBalls = numberOfBallsToSet;
    }

    /**
     * Sets initial ball velocities.
     *
     * @param initialBallVelocitiesToSet the initial ball velocities
     */
    public void setInitialBallVelocities(List<Velocity> initialBallVelocitiesToSet) {
        this.initialBallVelocities = initialBallVelocitiesToSet;
        setNumberOfBalls(initialBallVelocities.size());
    }

    /**
     * Sets paddle speed.
     *
     * @param paddleSpeedToSet the paddle speed
     */
    public void setPaddleSpeed(int paddleSpeedToSet) {
        this.paddleSpeed = paddleSpeedToSet;
    }

    /**
     * Sets paddle width.
     *
     * @param paddleWidthToSet the paddle width
     */
    public void setPaddleWidth(int paddleWidthToSet) {
        this.paddleWidth = paddleWidthToSet;
    }

    /**
     * Sets level name.
     *
     * @param levelNameToSet the level name
     */
    public void setLevelName(String levelNameToSet) {
        this.levelName = levelNameToSet;
    }

    /**
     * Sets blocks.
     *
     * @param blocksToSet the blocks
     */
    public void setBlocks(List<Block> blocksToSet) {
        this.blocks = blocksToSet;
    }

    /**
     * Sets number of blocks to remove.
     *
     * @param numOfBlocksToRemove the number of blocks to remove
     */
    public void setNumberOfBlocksToRemove(int numOfBlocksToRemove) {
        this.numberOfBlocksToRemove = numOfBlocksToRemove;
    }

    /**
     * Number of balls int.
     *
     * @return the int
     */
    public int numberOfBalls() {
        return numberOfBalls;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list of velocities
     */
    public List<Velocity> initialBallVelocities() {
        return initialBallVelocities;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    public int paddleSpeed() {
        return paddleSpeed;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    public int paddleWidth() {
        return paddleWidth;
    }

    /**
     * Level name string.
     * the level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    public String levelName() {
        return levelName;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    public Background getBackground() {
        return getBackground;
    }

    /**
     * Sets background.
     *
     * @param backgroundToSet the get background
     */
    public void setBackground(Background backgroundToSet) {
        this.getBackground = backgroundToSet;
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return Blocks list
     */
    public List<Block> blocks() {
        return blocks;
    }

    /**
     * Number of levels that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the int
     */
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }
}
