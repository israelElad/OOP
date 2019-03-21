package levels;

import game.Block;
import levels.BlockCreator;

import java.util.Map;

/**
 * Creates Blocks from symbols.
 * (has a method which gets a symbol and create the desired block).
 * The block definition files define a mapping from symbols to spaces and blocks. These symbols are then used in the
 * level specification files to define the blocks that need to be created.
 * @author Elad Israel
 * @version 4.0 17/06/2018
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Instantiates a new Blocks from symbols factory.
     *
     * @param spacerWidths  the spacer widths
     * @param blockCreators the block creators
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }


    /**
     * returns true if 's' is a valid space symbol.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }

    /**
     * returns true if 's' is a valid block symbol.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s the s
     * @return the int
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (x, y).
     *
     * @param symbol the symbol
     * @param x      the x
     * @param y      the y
     * @return the block
     */
    public Block getBlock(String symbol, int x, int y) {
        return this.blockCreators.get(symbol).create(x, y);
    }
}