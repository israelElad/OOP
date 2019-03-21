package game;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.util.List;

/**
 * interface name: GameFlow
 * In charge of creating the different levels, and moving from one level to the next.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class GameFlow {

    private final int frameWidth;
    private final int frameHeight;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter numOfLives;


    /**
     * Constructor.
     *
     * @param ar          the AnimationRunner
     * @param ks          the KeyboardSensor
     * @param frameWidth  the frame width
     * @param frameHeight the frame height
     * @param numOfLives  the num of lives
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, final int frameWidth, final int frameHeight, int
            numOfLives) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
        this.numOfLives = new Counter();
        this.numOfLives.increase(numOfLives);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }


    /**
     * Run the specified levels on the list.
     *
     * @param levels the levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score, this
                    .numOfLives, this.frameWidth, this.frameHeight);

            level.initialize();

            while (level.getBlocksLeftToRemove().getValue() > 0 && level.getNumOfLives().getValue() > 0) {
                level.playOneTurn();
            }

            if (level.getNumOfLives().getValue() <= 0) {
                this.animationRunner.run(new EndScreen(this.keyboardSensor, this.score, false));
                return;
            }
        }
        this.animationRunner.run(new EndScreen(this.keyboardSensor, this.score, true));
    }
}