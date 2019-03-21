import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import levels.FinalFourLevel;
import levels.Green3Level;
import levels.LevelInformation;
import levels.WideEasyLevel;
import levels.DirectHitLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Classname: Ass5Game
 * Creates a new arkanoid game, initialize and runs the game.
 *
 * @author Elad Israel
 * @version 3.0 20/05/2018
 */
public class Ass5Game {
    /**
     * When runs, it creates a new game and runs the game.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        final int frameWidth = 800;
        final int frameHeight = 600;
        List<LevelInformation> levels = new ArrayList<>();
        GUI gui = new GUI("Arkanoid Game", frameWidth, frameHeight);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor(), frameWidth, frameHeight, 7);

        //no arguments were added
        if (args.length == 0) {
            levels.add(new DirectHitLevel());
            levels.add(new WideEasyLevel());
            levels.add(new Green3Level());
            levels.add(new FinalFourLevel());
        } else { //there are arguments
            for (String levelNumberString : args) {
                int levelNumber;
                try { //validity check
                    levelNumber = Integer.parseInt(levelNumberString);
                } catch (NumberFormatException e) {
                    continue;
                }
                //adding levels according to the valid arguments
                switch (levelNumber) {
                    case 1:
                        levels.add(new DirectHitLevel());
                        break;
                    case 2:
                        levels.add(new WideEasyLevel());
                        break;
                    case 3:
                        levels.add(new Green3Level());
                        break;
                    case 4:
                        levels.add(new FinalFourLevel());
                        break;
                    default:
                        break;
                }
            }
        }
        gameFlow.runLevels(levels);
        gui.close();
    }
}