package game;

import animation.Animation;
import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Class name: GameLevel
 * A class that will hold the sprites and the collidables, and will be in charge of the animation.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class GameLevel implements Animation {

    private static final int UP_AND_DOWN_FRAMES_HEIGHT = 25;
    private static final int LEFT_AND_RIGHT_FRAMES_WIDTH = 25;
    private static final java.awt.Color FRAMES_COLOR = Color.gray;
    private final int frameHeight;
    private final int frameWidth;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private KeyboardSensor keyboardSensor;
    private Counter numOfBlocks;
    private BlockRemover blockRemover;
    private Counter numOfBalls;
    private BallRemover ballRemover;
    private Counter score;
    private Counter numOfLives;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;
    private Counter blocksLeftToRemove;


    /**
     * Constructor- creates the sprite collection, environment, and keyboard sensor of the game.
     *
     * @param levelInformation the level information
     * @param keyboardSensor   the keyboard sensor
     * @param animationRunner  the animation runner
     * @param score            the score
     * @param numOfLives       the num of lives
     * @param frameWidth       the frame width
     * @param frameHeight      the frame height
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     Counter score, Counter numOfLives, final int frameWidth, final int frameHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.runner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(new ArrayList<>());
        this.numOfBlocks = new Counter();
        this.numOfBalls = new Counter();
        this.ballRemover = new BallRemover(this, this.numOfBalls);
        this.levelInformation = levelInformation;
        this.blocksLeftToRemove = new Counter();
        this.blocksLeftToRemove.increase(this.levelInformation.numberOfBlocksToRemove());
        this.blockRemover = new BlockRemover(this, this.numOfBlocks, this.blocksLeftToRemove);
        this.score = score;
        this.numOfLives = numOfLives;
    }

    /**
     * add the given collidable to the collidables collection in the environment.
     *
     * @param c given collidable.
     */
    public void addCollidable(Collidable c) {
        try {
            environment.addCollidable(c);
        } catch (RuntimeException nullPointer) {
            throw new RuntimeException("Collidable field wasn't initialized!");
        }
    }

    /**
     * removes the given collidable from the collidables collection in the environment.
     *
     * @param c given collidable.
     */
    public void removeCollidable(Collidable c) {
        try {
            environment.removeCollidable(c);
        } catch (RuntimeException nullPointer) {
            throw new RuntimeException("Collidable field wasn't initialized!");
        }
    }

    /**
     * add the given sprite to the sprite collection.
     *
     * @param s given sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * removes the given sprite from the sprite collection.
     *
     * @param s given sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }


    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        addSprite(this.levelInformation.getBackground());
        initializeFrames();
        initializeCenterBlocks();
    }


    /**
     * creates frames from all sides to prevent the balls of leaving the screen.
     */
    public void initializeFrames() {
        Block right = new Block(frameWidth - LEFT_AND_RIGHT_FRAMES_WIDTH, 0, LEFT_AND_RIGHT_FRAMES_WIDTH,
                frameHeight, FRAMES_COLOR, FRAMES_COLOR, 1);
        right.addToGame(this);
        Block left = new Block(0, 0, LEFT_AND_RIGHT_FRAMES_WIDTH, frameHeight, FRAMES_COLOR, FRAMES_COLOR, 1);
        left.addToGame(this);
        Block up = new Block(0, UP_AND_DOWN_FRAMES_HEIGHT, frameWidth, UP_AND_DOWN_FRAMES_HEIGHT, FRAMES_COLOR,
                FRAMES_COLOR, 1);
        up.addToGame(this);
        //"death region". lowered beneath the gui so that the balls will disappear after leaving the screen.
        Block down = new Block(-frameWidth, frameHeight + UP_AND_DOWN_FRAMES_HEIGHT, frameWidth * 3,
                UP_AND_DOWN_FRAMES_HEIGHT, FRAMES_COLOR, FRAMES_COLOR, 1);
        down.addToGame(this);
        down.addHitListener(this.ballRemover);

        this.getNumOfBlocks().decrease(4);

        //initialize score sprite
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Rectangle(new Point(0, 0), frameWidth,
                UP_AND_DOWN_FRAMES_HEIGHT, Color.white, Color.white), this.score);
        scoreIndicator.addToGame(this);

        LivesIndicator livesIndicator = new LivesIndicator(this.numOfLives);
        livesIndicator.addToGame(this);

        NameOfLevelIndicator nameOfLevelIndicator = new NameOfLevelIndicator(this.levelInformation.levelName());
        nameOfLevelIndicator.addToGame(this);
    }

    /**
     * Creates the blocks in the center of the screen- the ones the ball will collide with and destroy.
     */
    public void initializeCenterBlocks() {
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);

        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
    }


    /**
     * Creates two balls that will bounce around the screen, and the paddle that will try to prevent them from
     * falling down(by the user).
     *
     * @return paddle to remove by playOneTurn
     */
    public Paddle initializeBallsAndPaddle() {
        final int paddleHeight = 15;
        final int paddleWidth = this.levelInformation.paddleWidth();
        //Paddle
        this.paddle = new Paddle(new Point(frameWidth / 2 - paddleWidth / 2,
                frameHeight - UP_AND_DOWN_FRAMES_HEIGHT), paddleWidth, paddleHeight,
                this.levelInformation.paddleSpeed(), Color.yellow, java.awt.Color.black, keyboardSensor);
        this.paddle.addToGame(this);

        //balls
        for (Velocity velocityOfBall : this.levelInformation.initialBallVelocities()) {
            double ballX = this.paddle.getCollisionRectangle().getUpperLeft().getX()
                    + this.levelInformation.paddleWidth() / 2;
            double ballY = this.paddle.getCollisionRectangle().getUpperLeft().getY() - 15;
            Ball ball = new Ball((int) ballX, (int) ballY, 5, Color.white, Color.black);
            ball.setGameEnvironment(this.environment);
            ball.setVelocity(velocityOfBall);
            ball.addToGame(this);
        }

        return paddle;
    }

    /**
     * Should the animation stop.
     *
     * @return boolean
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Do one frame of the animation.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboardSensor));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.numOfBlocks.getValue() == 0 || this.blocksLeftToRemove.getValue() <= 0) {
            paddle.removeFromGame(this);
            this.score.increase(100);
            this.running = false;
        }
        if (this.numOfBalls.getValue() == 0) {
            paddle.removeFromGame(this);
            this.numOfLives.decrease(1);
            this.running = false;
        }
    }

    /**
     * playing one turn.
     * playOneTurn starts by creating balls and putting the paddle at the bottom of the screen.
     */
    public void playOneTurn() {
        this.paddle = initializeBallsAndPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * Gets num of blocks.
     *
     * @return the num of blocks
     */
    public Counter getNumOfBlocks() {
        return this.numOfBlocks;
    }

    /**
     * Gets blocks left to remove.
     *
     * @return the blocks left to remove
     */
    public Counter getBlocksLeftToRemove() {
        return this.blocksLeftToRemove;
    }

    /**
     * Gets num of balls.
     *
     * @return the num of balls
     */
    public Counter getNumOfBalls() {
        return this.numOfBalls;
    }

    /**
     * Gets num of lives.
     *
     * @return the num of lives
     */
    public Counter getNumOfLives() {
        return this.numOfLives;
    }
}