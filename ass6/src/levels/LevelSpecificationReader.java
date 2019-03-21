package levels;

import game.*;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The type Level specification reader.
 *
 * @author Elad Israel
 * @version 4.0 17/06/2018
 */
public class LevelSpecificationReader {


    /**
     * get a file name and returns a list of LevelInformation objects.
     *
     * @param reader to the file
     * @return a list of LevelInformation objects
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<String> lines = new ArrayList<>();
        BufferedReader bufferReader = new BufferedReader(reader);
        try {
            String line = bufferReader.readLine();
            while (line != null) {
                if (line.equals("") || line.startsWith("#")) {
                    line = bufferReader.readLine();
                    continue;
                }
                lines.add(line);
                line = bufferReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("reading Blocks definition failed!");
        }
        List<List<String>> levels = separateLevels(lines);
        List<LevelInformation> levelInformationList = new ArrayList<>();
        for (List<String> level : levels) {
            levelInformationList.add(levelToLevelInformation(level));
        }
        return levelInformationList;
    }

    /**
     * receives all lines in a LevelSpecification file and separates them into list containing lists-
     * each internal list contains all the information of a specific level.
     *
     * @param lines the lines
     * @return list
     */
    public List<List<String>> separateLevels(List<String> lines) {
        List<List<String>> levels = new ArrayList<>();
        Boolean insideLevel = false;
        for (int i = 0, levelNum = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("START_LEVEL")) {
                if (insideLevel) {
                    throw new RuntimeException("START_LEVEL twice");
                }
                insideLevel = true;
                //add a new level
                levels.add(new ArrayList<>());
            } else if (lines.get(i).equals("END_LEVEL")) {
                if (!insideLevel) {
                    throw new RuntimeException("END_LEVEL twice");
                }
                insideLevel = false;
                levelNum++;
            } else if (insideLevel) {
                levels.get(levelNum).add(lines.get(i));
            }
        }
        return levels;
    }

    /**
     * Understanding the content of the level specification of a single level: this will go over the strings,
     * split and parse them, and map them to java objects, resulting in a LevelInformation object.
     *
     * @param stringsOfLevel the strings of level
     * @return LevelInformation object.
     */
    public LevelInformation levelToLevelInformation(List<String> stringsOfLevel) {
        LevelFactory level = new LevelFactory();

        //extract blocks rows
        List<String> blocksLayoutInLevel = extractBlocksLines(stringsOfLevel);
        //extract level properties
        extractAndSetLevelProperties(stringsOfLevel, level);
        //extract block properties: row_height, block_start_x, block_start_y.
        Map<String, Integer> blocksProperties = extractBlocksProperties(stringsOfLevel);
        //extract Block Definitions file
        String blocksDefFilePath = null;
        for (String line : stringsOfLevel) {
            if (line.startsWith("block_definitions:")) {
                line = line.substring("block_definitions:".length()).trim();
                blocksDefFilePath = line;
            }
        }
        if (blocksDefFilePath == null) {
            throw new RuntimeException("block_definitions path wasn't found in level spec");
        }
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = blocksDefToFactory(blocksDefFilePath);
        List<Block> blocks = getBlocks(blocksFromSymbolsFactory, blocksLayoutInLevel, blocksProperties);
        level.setBlocks(blocks);
        if (!level.isAllSet()) {
            throw new RuntimeException("level property missing");
        }
        return level;
    }

    /**
     * Extract blocks lines list.
     *
     * @param stringsOfLevel the strings of level
     * @return the list
     */
    public List<String> extractBlocksLines(List<String> stringsOfLevel) {
        List<String> blockLines = new ArrayList<>();
        Boolean insideBlocks = false;
        for (String line : stringsOfLevel) {
            if (line.equals("START_BLOCKS")) {
                if (insideBlocks) {
                    throw new RuntimeException("START_BLOCKS twice");
                }
                insideBlocks = true;
            } else if (line.equals("END_BLOCKS")) {
                if (!insideBlocks) {
                    throw new RuntimeException("END_BLOCKS twice");
                }
                insideBlocks = false;
            } else if (insideBlocks) {
                //add a new line of block
                blockLines.add(line);
            }
        }
        return blockLines;
    }


    /**
     * Extract and set level properties.
     *
     * @param stringsOfLevel the strings of level
     * @param level          the level
     */
    public void extractAndSetLevelProperties(List<String> stringsOfLevel, LevelFactory level) {
        for (String line : stringsOfLevel) {
            if (line.startsWith("level_name:")) {
                line = line.substring("level_name:".length()).trim();
                level.setLevelName(line);
            } else if (line.startsWith("ball_velocities:")) {
                line = line.substring("ball_velocities:".length()).trim();
                Map<String, String> angleSpeedPairs = stringLineToMap(line);
                List<Velocity> ballVelocities = new ArrayList<>();
                for (String angle : angleSpeedPairs.keySet()) {
                    try {
                        ballVelocities.add(new Velocity(Velocity.fromAngleAndSpeed(Integer.parseInt(angle),
                                Integer.parseInt(angleSpeedPairs.get(angle)))));
                    } catch (Exception e) {
                        throw new RuntimeException("invalid ball_velocities");
                    }
                }
                level.setInitialBallVelocities(ballVelocities);

            } else if (line.startsWith("background:")) {
                line = line.substring("background:".length()).trim();
                if (line.startsWith("image")) {
                    line = line.substring("image".length()).trim();
                    line = line.substring(1, line.length() - 1); // removes "(" ")"
                    level.setBackground(new BackgroundImage(line));
                } else if (line.startsWith("color")) {
                    line = line.substring("color".length()).trim();
                    line = line.substring(1, line.length() - 1); // removes "(" ")"
                    level.setBackground(new BackgroundColor(ColorsParser.colorFromString(line)));
                } else {
                    throw new RuntimeException("invalid background parameters");
                }
            } else if (line.startsWith("paddle_speed:")) {
                line = line.substring("paddle_speed:".length()).trim();
                try {
                    level.setPaddleSpeed(Integer.parseInt(line));
                    if (level.paddleSpeed() < 0) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    throw new RuntimeException("invalid paddle_speed");
                }
            } else if (line.startsWith("paddle_width:")) {
                line = line.substring("paddle_width:".length()).trim();
                try {
                    level.setPaddleWidth(Integer.parseInt(line));
                    if (level.paddleWidth() < 0) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    throw new RuntimeException("invalid paddle_width");
                }
            } else if (line.startsWith("num_blocks:")) {
                line = line.substring("num_blocks:".length()).trim();
                try {
                    level.setNumberOfBlocksToRemove(Integer.parseInt(line));
                    if (level.numberOfBlocksToRemove() < 0) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    throw new RuntimeException("invalid num_blocks");
                }
            }
        }
    }

    /**
     * String line to map map.
     *
     * @param line the line
     * @return the map
     */
    public Map<String, String> stringLineToMap(String line) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = line.split(" ");
        for (String pair : pairs) {
            String[] keyValuePair = pair.split(",");
            if (keyValuePair.length != 2) {
                throw new RuntimeException("converting string to map failed!");
            }
            map.put(keyValuePair[0], keyValuePair[1]);
        }
        return map;
    }


    /**
     * Extract blocks properties map.
     *
     * @param stringsOfLevel the strings of level
     * @return the map
     */
    public Map<String, Integer> extractBlocksProperties(List<String> stringsOfLevel) {
        Map<String, Integer> blocksProperties = new HashMap<>();
        for (String line : stringsOfLevel) {
            if (line.startsWith("blocks_start_x:")) {
                line = line.substring("blocks_start_x:".length()).trim();
                try {
                    blocksProperties.put("blocks_start_x", Integer.parseInt(line));
                } catch (Exception e) {
                    throw new RuntimeException("invalid blocks properties");
                }
            } else if (line.startsWith("blocks_start_y:")) {
                line = line.substring("blocks_start_y:".length()).trim();
                try {
                    blocksProperties.put("blocks_start_y", Integer.parseInt(line));
                } catch (Exception e) {
                    throw new RuntimeException("invalid blocks properties");
                }
            } else if (line.startsWith("row_height:")) {
                line = line.substring("row_height:".length()).trim();
                try {
                    blocksProperties.put("row_height", Integer.parseInt(line));
                } catch (Exception e) {
                    throw new RuntimeException("invalid blocks properties");
                }
            }
        }
        if (blocksProperties.size() != 3) {
            throw new RuntimeException("missing blocks properties");
        }
        return blocksProperties;
    }


    /**
     * Blocks definitions to BlocksFromSymbolsFactory.
     *
     * @param blocksDefFilePath the blocks def file path
     * @return the blocks from symbols factory
     */
    public BlocksFromSymbolsFactory blocksDefToFactory(String blocksDefFilePath) {
        Reader reader;
        try {
            InputStream is =
                    ClassLoader.getSystemClassLoader().getResourceAsStream(blocksDefFilePath);
            // Reading the blocks.
            reader = new BufferedReader(
                    new InputStreamReader(is));
        } catch (Exception e) {
            throw new RuntimeException("couldn't read blocks definitions file");
        }
        return BlocksDefinitionReader.fromReader(reader);

    }


    /**
     * Gets blocks.
     *
     * @param blocksFromSymbolsFactory the blocks from symbols factory
     * @param blocksLayoutInLevel      the blocks layout in level
     * @param blocksProperties         the blocks properties
     * @return the blocks
     */
    public List<Block> getBlocks(BlocksFromSymbolsFactory blocksFromSymbolsFactory, List<String> blocksLayoutInLevel,
                                 Map<String, Integer> blocksProperties) {
        List<Block> blocks = new ArrayList<>();
        //starting y of first row
        int yPos = blocksProperties.get("blocks_start_y");
        String symbol; //represent block or spacer
        for (String blocksRow : blocksLayoutInLevel) {
            //we cut the row with each iteration so its size is changing. so we need to keep the size beforehand.
            int rowLength = blocksRow.length();
            int xpos = blocksProperties.get("blocks_start_x");
            for (int i = 0; i < rowLength; i++) {
                //extract first symbol
                symbol = blocksRow.substring(0, 1);
                if (blocksFromSymbolsFactory.isBlockSymbol(symbol)) {
                    Block block = blocksFromSymbolsFactory.getBlock(symbol, xpos, yPos);
                    blocks.add(block);
                    xpos += block.getCollisionRectangle().getWidth();
                } else if (blocksFromSymbolsFactory.isSpaceSymbol(symbol)) {
                    xpos += blocksFromSymbolsFactory.getSpaceWidth(symbol);
                } else {
                    throw new RuntimeException("unknown symbol");
                }
                //remove first char
                blocksRow = blocksRow.substring(1);
            }
            yPos += blocksProperties.get("row_height");
        }
        return blocks;
    }
}