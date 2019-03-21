package levels;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

/**
 * in charge of reading a block-definitions file and returning a BlocksFromSymbolsFactory object.
 * @author Elad Israel
 * @version 4.0 17/06/2018
 */
public class BlocksDefinitionReader {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;


    /**
     * Instantiates a new Blocks definition reader.
     */
    public BlocksDefinitionReader() {
        this.spacerWidths = new HashMap<>();
        this.blockCreators = new HashMap<>();
    }


    /**
     * get a symbol and create the desired block.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader bufferReader = new BufferedReader(reader);
        Map<String, String> defaultValues = new HashMap<>();
        BlocksDefinitionReader bdr = new BlocksDefinitionReader();
        String line;
        try {
            line = bufferReader.readLine();
        } catch (Exception e) {
            throw new RuntimeException("reading Blocks definition failed!");
        }
        while (line != null) {
            if (line.equals("") || line.startsWith("#")) {
                try {
                    line = bufferReader.readLine();
                    continue;
                } catch (Exception e) {
                    throw new RuntimeException("reading Blocks definition failed!");
                }
            }
            //default values
            if (line.startsWith("default")) {
                line = line.substring("default".length()).trim();
                defaultValues.putAll(stringLineToMap(line));
            } else if (line.startsWith("bdef")) { //block definitions
                line = line.substring("bdef".length()).trim();
                Map<String, String> blockDefMap = stringLineToMap(line);
                String symbol = blockDefMap.get("symbol");
                if (symbol.length() != 1) {
                    throw new RuntimeException("invalid symbol");
                }
                //assuming that if default values exists they will be above the bdef lines- add to each bdef.
                blockDefMap.putAll(defaultValues);

                BlockTemplateCreator blockTemplate = new BlockTemplateCreator();
                for (String key : blockDefMap.keySet()) {
                    if (key.equals("width")) {
                        try {
                            blockTemplate.setWidth(Integer.parseInt(blockDefMap.get(key)));
                        } catch (Exception e) {
                            throw new RuntimeException("parsing to int failed");
                        }
                    } else if (key.equals("height")) {
                        try {
                            blockTemplate.setHeight(Integer.parseInt(blockDefMap.get(key)));
                        } catch (Exception e) {
                            throw new RuntimeException("parsing to int failed");
                        }
                    } else if (key.equals("hit_points")) {
                        try {
                            blockTemplate.setHitPoints(Integer.parseInt(blockDefMap.get(key)));
                        } catch (Exception e) {
                            throw new RuntimeException("parsing to int failed");
                        }
                    } else if (key.startsWith("fill")) {
                        String fillKey = key.substring("fill".length());
                        String value = blockDefMap.get(key);
                        Background background;
                        if (value.startsWith("color")) {
                            value = value.substring("color".length()).trim();
                            value = value.substring(1, value.length() - 1); // removes "(" ")"
                            background = new BackgroundColor(ColorsParser.colorFromString(value));
                        } else if (value.startsWith("image")) {
                            value = value.substring("image".length()).trim();
                            value = value.substring(1, value.length() - 1); // removes "(" ")"
                            background = new BackgroundImage(value);
                        } else {
                            throw new RuntimeException("invalid fill parameter");
                        }
                        if (fillKey.startsWith("-")) {
                            int fillHP = Integer.parseInt(fillKey.substring(1));
                            blockTemplate.addHpToBackground(fillHP, background);
                        } else {
                            blockTemplate.setBackground(background);
                        }
                    } else if (key.equals("stroke")) {
                        String stroke = blockDefMap.get(key);
                        stroke = stroke.substring("color".length()).trim();
                        stroke = stroke.substring(1, stroke.length() - 1); // removes "(" ")"
                        blockTemplate.setStroke(ColorsParser.colorFromString(stroke));
                    }

                }

                //adds a block type(symbol and block template to create from that symbol) to BlockCreators list.
                bdr.blockCreators.put(symbol, blockTemplate);

            } else if (line.startsWith("sdef symbol:")) { //spacer definitions
                line = line.substring("sdef symbol:".length()).trim();
                String symbol = line.substring(0, 1);
                line = line.substring(1).trim();
                if (!line.startsWith("width:")) {
                    throw new RuntimeException("invalid spacer parameters");
                }
                int width;
                try {
                    width = Integer.parseInt(line.substring("width:".length()));
                } catch (Exception e) {
                    throw new RuntimeException("spacer parsing to int failed");
                }
                bdr.spacerWidths.put(symbol, width);
            } else {
                throw new RuntimeException("unknown characters in blocks definition");
            }
            try {
                line = bufferReader.readLine();
            } catch (Exception e) {
                throw new RuntimeException("reading Blocks definition failed!");
            }
        }
        return new BlocksFromSymbolsFactory(bdr.spacerWidths, bdr.blockCreators);
    }


    /**
     * String line to map.
     *
     * @param line the line
     * @return the map
     */
    public static Map<String, String> stringLineToMap(String line) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = line.split(" ");
        for (String pair : pairs) {
            String[] keyValuePair = pair.split(":");
            if (keyValuePair.length != 2) {
                throw new RuntimeException("converting string to map failed!");
            }
            map.put(keyValuePair[0], keyValuePair[1]);
        }
        return map;
    }

    /**
     * Get spacer widths map.
     *
     * @return the map
     */
    public Map<String, Integer> getSpacerWidths() {
        return this.spacerWidths;
    }

    /**
     * Get block creators map.
     *
     * @return the map
     */
    public Map<String, BlockCreator> getBlockCreators() {
        return this.blockCreators;
    }
}