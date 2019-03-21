package levels;

import game.Block;
import game.Sprite;
import game.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * Classname: WideEasyLevel.
 * second level: "Wide Easy"
 * contains a single row of block and a lot of balls, and a very wide and slow paddle.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class WideEasyLevel implements LevelInformation {

    /**
     * Number of balls int.
     *
     * @return the int
     */
    public int numberOfBalls() {
        return 12;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list of velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<Velocity>(this.numberOfBalls());
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(-50, 5)));
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(-43, 5)));
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(-35, 5)));
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(-27, 5)));
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(-18, 5)));
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(-5, 5)));
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(5, 5)));
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(18, 5)));
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(27, 5)));
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(35, 5)));
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(43, 5)));
        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(50, 5)));
        return ballVelocities;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    public int paddleSpeed() {
        return 2;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    public int paddleWidth() {
        return 600;
    }

    /**
     * Level name string.
     * the level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return Blocks list
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        //colors of each row(rainbow)
        List<java.awt.Color> columnsColors = new ArrayList<>();
        columnsColors.add(Color.RED);
        columnsColors.add(Color.RED);
        columnsColors.add(Color.ORANGE);
        columnsColors.add(Color.ORANGE);
        columnsColors.add(Color.YELLOW);
        columnsColors.add(Color.YELLOW);
        columnsColors.add(Color.GREEN);
        columnsColors.add(Color.GREEN);
        columnsColors.add(Color.BLUE);
        columnsColors.add(Color.BLUE);
        columnsColors.add(Color.magenta);
        columnsColors.add(Color.magenta);
        for (int i = 0; i < 12; i++) {
            blocks.add(new Block(i * 62.5 + 25, 250, 62.5, 20, columnsColors.get(i), Color.black, 1));
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
