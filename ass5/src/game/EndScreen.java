package game;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Classname: EndScreen.
 * Once the game is over (either the player run out of lives or managed to clear all the levels),
 * we will display the final score. If the game ended with the player losing all his lives,
 * the end screen should display the message "Game Over. Your score is X" (X being the final score).
 * If the game ended by clearing all the levels, the screen should display "You Win! Your score is X".
 * The "end screen" should persist until the space key is pressed.
 * After the space key is pressed, your program should terminate.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private Counter score;
    private boolean stop;
    private boolean won;

    /**
     * Instantiates a new End screen.
     *
     * @param k     the KeyboardSensor
     * @param score the score to display
     * @param won   did the player won or lost
     */
    public EndScreen(KeyboardSensor k, Counter score, boolean won) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.won = won;
    }

    /**
     * Do one frame of the animation.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        if (this.won) {
            d.setColor(Color.blue);
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 40);
        } else {
            d.setColor(Color.red);
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 40);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * Should the animation stop.
     *
     * @return boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
}