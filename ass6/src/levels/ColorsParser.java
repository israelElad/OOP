package levels;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * parse color definition and return the specified color.
 * parse from string of the structure: "colorname" or "RGB(x,y,z)"
 * @author Elad Israel
 * @version 4.0 17/06/2018
 */
public class ColorsParser {

    /**
     * parse color definition and return the specified color.
     * parse from string of the structure: "colorname" or "RGB(x,y,z)"
     *
     * @param s the s
     * @return Color color
     */
    public static java.awt.Color colorFromString(String s) {
        if (s.startsWith("RGB")) {
            int x, y, z;
            s = s.substring("RGB".length()).trim();
            s = s.substring(1, s.length() - 1); // removes "(" ")"
            String[] rgbVals = s.split(",");
            try {
                x = Integer.parseInt(rgbVals[0]);
                y = Integer.parseInt(rgbVals[1]);
                z = Integer.parseInt(rgbVals[2]);
            } catch (Exception e) {
                throw new RuntimeException("invalid Color RGB");
            }
            return new Color(x, y, z);
        } else {
            Map<String, Color> colorsMap = new HashMap<String, Color>();
            colorsMap.put("yellow", Color.yellow);
            colorsMap.put("red", Color.red);
            colorsMap.put("black", Color.black);
            colorsMap.put("blue", Color.blue);
            colorsMap.put("cyan", Color.cyan);
            colorsMap.put("gray", Color.gray);
            colorsMap.put("lightGray", Color.lightGray);
            colorsMap.put("green", Color.green);
            colorsMap.put("orange", Color.orange);
            colorsMap.put("pink", Color.pink);
            colorsMap.put("white", Color.white);
            if (colorsMap.get(s) == null) {
                throw new RuntimeException("invalid Color name");
            }
            return colorsMap.get(s);
        }
    }
}