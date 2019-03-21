import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import game.HighScoresTable;
import animation.KeyPressStoppableAnimation;
import animation.Task;
import animation.MenuAnimation;
import animation.Menu;
import animation.HighScoresAnimation;
import levels.LevelSpecificationReader;
import game.ShowHiScoresTask;
import levels.LevelInformation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Classname: Ass6Game
 * Creates a new arkanoid game, initialize and runs the game.
 *
 * @author Elad Israel
 * @version 4.0 16/06/2018
 */
public class Ass6Game {
    /**
     * When runs, it creates a new game and runs the game.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        final int frameWidth = 800;
        final int frameHeight = 600;
        GUI gui = new GUI("Arkanoid Game", frameWidth, frameHeight);
        AnimationRunner animationRunner = new AnimationRunner(gui);

        //loading or creating a highscore file.
        File highscoresFile = new File("highscores");
        HighScoresTable highScoresTable = new HighScoresTable(5);
        if (!highscoresFile.exists()) {
            try {
                highScoresTable.save(highscoresFile);
            } catch (IOException e) {
                System.err.println("Failed saving file");
            }
        } else {
            try {
                highScoresTable.load(highscoresFile);
            } catch (IOException e) {
                System.err.println("Failed loading file");
            }
        }

        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", gui.getKeyboardSensor(), animationRunner);
        KeyPressStoppableAnimation highscoresAnimation = new KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                "space", new HighScoresAnimation(highScoresTable, gui.getKeyboardSensor()));

        Menu<Task<Void>> subMenu = new MenuAnimation<>("Arkanoid", gui.getKeyboardSensor(), animationRunner);
        String levelSetsPath = "level_sets.txt";
        BufferedReader levelSetsReader;
        try {
            levelSetsReader = new BufferedReader(new
                    InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetsPath)));

        } catch (Exception e) {
            throw new RuntimeException("couldn't load level sets file");
        }
        String line;
        String levelKey;
        String levelMessage;
        try {
            do {
                line = levelSetsReader.readLine();
                if (line == null) {
                    break;
                }
                levelKey = line.substring(0, 1);
                if (!line.substring(1, 2).equals(":")) {
                    throw new Exception("invalid levelSet format");
                }
                levelMessage = line.substring(2);

                line = levelSetsReader.readLine();
                if (line == null) {
                    throw new Exception("level name without level path");
                }
                final String levelDefPath = line;
                subMenu.addSelection(levelKey, levelMessage, new Task<Void>() {
                    @Override
                    public Void run() {
                        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor(), frameWidth,
                                frameHeight, 7, highScoresTable);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader
                                .getSystemClassLoader().getResourceAsStream(levelDefPath)));

                        LevelSpecificationReader levelSpecReader = new LevelSpecificationReader();
                        List<LevelInformation> levelsInformation = levelSpecReader.fromReader(reader);
                        gameFlow.runLevels(levelsInformation);
                        return null;
                    }
                });
            } while (true);
        } catch (Exception e) {
            throw new RuntimeException("reading Blocks definition failed!");
        }

        menu.addSubMenu("s", "Game", subMenu);

        menu.addSelection("h", "Hi scores", new ShowHiScoresTask(animationRunner, highscoresAnimation));

        menu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        });


        while (true) {
            animationRunner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
}