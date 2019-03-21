import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * Classname: AbstractArtDrawing
 * we have 10 lines, drawn in black.
 * The middle point in each line is indicated in blue,
 * while the intersection points between the lines are indicated in red.
 *
 * @author Elad Israel
 * @version 1.0 01/04/2018
 */
public class AbstractArtDrawing {

    //here you can easily change the parameters for the drawing
    private static final int GUI_WIDTH = 400;
    private static final int GUI_HEIGHT = 300;
    private static final int NUMBER_OF_LINES = 10;
    private static final int CIRCLES_RADIUS = 3;

    /**
     * main method: creates an instance of AbstractArtDrawing and activates the drawing.
     *
     * @param args no arguments should be received.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }

    /**
     * draw the random lines on the GUI and DrawSurface it creates.
     */
    public void drawRandomLines() {
        // Create a window with the title "Random Lines"
        GUI gui = new GUI("Random Lines", GUI_WIDTH, GUI_HEIGHT);
        //the line are being drawed on a DrawSurface.
        DrawSurface d = gui.getDrawSurface();
        //an array that keeps all drawn lines.
        Line[] drawnLines = new Line[NUMBER_OF_LINES];
        //in each iteration(up to num of lines) creates the line, and draws it and it's middle point.
        for (int i = 0; i < NUMBER_OF_LINES; ++i) {
            //adds the line to an array that keeps drawn lines
            drawnLines[i] = generateRandomLine();
            //sets the color of the line
            d.setColor(Color.BLACK);
            //draw the line
            drawLine(drawnLines[i], d);
            //draw the middle
            drawMid(drawnLines[i], d);
        }
        //draws the intersection point.
        drawInter(drawnLines, d);
        //shows the drawing
        gui.show(d);
    }

    /**
     * generates a random line.
     *
     * @return random line
     */
    public Line generateRandomLine() {
        // create a random-number generator
        Random rand = new Random();
        //generates the line's coordinates within the gui limits.
        int x1 = rand.nextInt(GUI_WIDTH) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(GUI_HEIGHT) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(GUI_WIDTH) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(GUI_HEIGHT) + 1; // get integer in range 1-300
        //constructing and returning the new line
        return new Line(x1, y1, x2, y2);
    }

    /**
     * draws a given line on the given DrawSurface.
     *
     * @param l line
     * @param d drawSurface
     */
    public void drawLine(Line l, DrawSurface d) {
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
    }

    /**
     * draws a given line's middle point on the given DrawSurface.
     *
     * @param l line
     * @param d drawSurface
     */
    public void drawMid(Line l, DrawSurface d) {
        d.setColor(Color.blue);
        d.fillCircle((int) l.middle().getX(), (int) l.middle().getY(), CIRCLES_RADIUS);
    }

    /**
     * searches for intersections between lines in a given array and draws
     * the intersection point of the lines on the given DrawSurface.
     *
     * @param lArr lines array
     * @param d    drawSurface
     */
    public void drawInter(Line[] lArr, DrawSurface d) {
        //goes through each of the lines in the array
        for (int i = 0; i < lArr.length; i++) {
            //check if the line (i) is intersecting with any other line in the array(starting i+1)
            for (int j = i + 1; j < lArr.length; j++) {
                //found an intersection
                if (lArr[i].isIntersecting(lArr[j])) {
                    //sets the color of the intersection point
                    d.setColor(Color.red);
                    //draws the intersection point
                    d.fillCircle((int) lArr[i].intersectionWith(lArr[j]).getX(),
                            (int) lArr[i].intersectionWith(lArr[j]).getY(), CIRCLES_RADIUS);
                }
            }
        }
    }
}