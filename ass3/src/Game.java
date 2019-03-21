import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class name: Game
 * A class that will hold the sprites and the collidables, and will be in charge of the animation.
 *
 * @author Elad Israel
 * @version 1.0 20/04/2018
 */
public class Game {

    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 800;
    private static final int UP_AND_DOWN_FRAMES_HEIGHT = 25;
    private static final int LEFT_AND_RIGHT_FRAMES_WIDTH = 25;
    private static final java.awt.Color FRAMES_COLOR = java.awt.Color.red;
    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboardSensor;

    /**
     * Constructor- creates the sprite collection, environment, gui and keyboard sensor of the game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(new ArrayList<>());
        this.gui = new GUI("Arkanoid Game", FRAME_WIDTH, FRAME_HEIGHT);
        keyboardSensor = gui.getKeyboardSensor();
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
     * add the given sprite to the sprite collection.
     *
     * @param s given sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        initializeBallsAndPaddle();
        initializeFrames();
        initializeCenterBlocks();
    }

    /**
     * Creates two balls that will bounce around the screen, and the paddel that will try to prevent them from
     * falling down(by the user).
     */
    public void initializeBallsAndPaddle() {
        final int paddleHeight = 15;
        final int paddleWidth = 80;
        final int ball1Radius = 10;
        final int ball2Radius = 5;
        final int ball1Angle = -66;
        final int ball2Angle = 66;

        //Paddle
        Paddle paddle = new Paddle(new Point(FRAME_WIDTH / 2 - paddleWidth / 2, FRAME_HEIGHT - UP_AND_DOWN_FRAMES_HEIGHT
                - paddleHeight * 2), paddleWidth, paddleHeight, java.awt.Color.black, java.awt.Color.blue,
                keyboardSensor);
        paddle.addToGame(this);
        //balls
        Ball ball1 = new Ball(FRAME_WIDTH / 2, FRAME_HEIGHT - UP_AND_DOWN_FRAMES_HEIGHT
                - paddleHeight * 2 - ball1Radius, ball1Radius, Color.BLACK);
        Ball ball2 = new Ball(FRAME_WIDTH / 2, FRAME_HEIGHT - UP_AND_DOWN_FRAMES_HEIGHT
                - paddleHeight * 2 - ball2Radius, ball2Radius, java.awt.Color.BLUE);
        ball1.setGameEnvironment(this.environment);
        ball1.setVelocity(new Velocity(Velocity.fromAngleAndSpeed(ball1Angle, ball1.speedAccToSize(ball1.getSize()))));
        ball1.addToGame(this);
        ball2.setGameEnvironment(this.environment);
        ball2.setVelocity(new Velocity(Velocity.fromAngleAndSpeed(ball2Angle, ball2.speedAccToSize(ball2.getSize()))));
        ball2.addToGame(this);
    }

    /**
     * creates frames from all sides to prevent the balls of leaving the screen.
     */
    public void initializeFrames() {
        Block right = new Block(FRAME_WIDTH - LEFT_AND_RIGHT_FRAMES_WIDTH, 0, LEFT_AND_RIGHT_FRAMES_WIDTH,
                FRAME_HEIGHT, FRAMES_COLOR, FRAMES_COLOR, 1);
        right.addToGame(this);
        Block left = new Block(0, 0, LEFT_AND_RIGHT_FRAMES_WIDTH, FRAME_HEIGHT, FRAMES_COLOR, FRAMES_COLOR, 1);
        left.addToGame(this);
        Block up = new Block(0, 0, FRAME_WIDTH, UP_AND_DOWN_FRAMES_HEIGHT, FRAMES_COLOR, FRAMES_COLOR, 1);
        up.addToGame(this);
        Block down = new Block(0, FRAME_HEIGHT - UP_AND_DOWN_FRAMES_HEIGHT, FRAME_WIDTH, UP_AND_DOWN_FRAMES_HEIGHT,
                FRAMES_COLOR, FRAMES_COLOR, 1);
        down.addToGame(this);
    }

    /**
     * Creates the blocks in the center of the screen- the ones the ball will collide with and destroy.
     */
    public void initializeCenterBlocks() {
        final int numOfRows = 6;
        final int numOfColumns = 12;
        final int blockWidth = 50;
        final int blockHeight = 20;
        final int startingHeightOfBlocks = 100;
        final int startingWidthOfBlocks = 125;
        //colors of each row(rainbow)
        List<java.awt.Color> rowsColors = new ArrayList<>();
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
                    Block block = new Block(j * blockWidth + startingWidthOfBlocks, i * blockHeight
                            + startingHeightOfBlocks, blockWidth, blockHeight, rowsColors.get(i), Color.black, 2);
                    block.addToGame(this);
                } else {
                    Block block = new Block(j * blockWidth + startingWidthOfBlocks, i * blockHeight
                            + startingHeightOfBlocks, blockWidth, blockHeight, rowsColors.get(i), Color.black, 1);
                    block.addToGame(this);
                }
            }
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        final int framesPerSecond = 60;
        final int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            /*the time it takes to perform each loop may be non-negligible.
             We therefor subtract the time it takes to do the work from
             the sleep time of millisecondsPerFrame milliseconds.
             */
            long startTime = System.currentTimeMillis(); //time at the beginning
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime; //the time it took
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime; //time left to sleep after the iteration.
            if (milliSecondLeftToSleep > 0) { // there is still time to sleep
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}