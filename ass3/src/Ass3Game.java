/**
 * Classname: Ass3Game
 * Creates a new arkanoid game, initialize and runs the game.
 *
 * @author Elad Israel
 * @version 1.0 20/04/2018
 */
public class Ass3Game {
    /**
     * When runs, it creates a new game, initialize its components and runs the game.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}