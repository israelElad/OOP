package levels;


import game.Block;
import levels.Background;
import levels.BlockCreator;
import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * each instance of this class generates a different type of block from the block definitions.
 *
 * @author Elad Israel
 * @version 4.0 17/06/2018
 */
public class BlockTemplateCreator implements BlockCreator {
    private int width;
    private int height;
    private int hitPoints;
    private Map<Integer, Background> hpToBackground;
    private Color stroke;
    private Background background;

    /**
     * Instantiates a new Block template creator.
     */
    public BlockTemplateCreator() {
        this.width = -1;
        this.height = -1;
        this.hitPoints = -1;
        this.hpToBackground = new HashMap<>();
        this.background = null;
        this.stroke = null;
    }

    /**
     * Sets background.
     *
     * @param backgroundToSet the background
     */
    public void setBackground(Background backgroundToSet) {
        this.background = backgroundToSet;
    }

    /**
     * Sets width.
     *
     * @param widthToSet the width
     */
    public void setWidth(int widthToSet) {
        this.width = widthToSet;
    }

    /**
     * Sets height.
     *
     * @param heightToSet the height
     */
    public void setHeight(int heightToSet) {
        this.height = heightToSet;
    }

    /**
     * Sets hit points.
     *
     * @param hitPointsToSet the hit points
     */
    public void setHitPoints(int hitPointsToSet) {
        this.hitPoints = hitPointsToSet;
    }

    /**
     * Add hp to background.
     *
     * @param hp         the hp
     * @param backgroundToSet the background
     */
    public void addHpToBackground(Integer hp, Background backgroundToSet) {
        this.hpToBackground.put(hp, backgroundToSet);
    }

    /**
     * Sets stroke.
     *
     * @param strokeToSet the stroke
     */
    public void setStroke(Color strokeToSet) {
        this.stroke = strokeToSet;
    }

    /**
     * Create a block at the specified location.
     *
     * @param xpos the x position of the block
     * @param ypos the y position of the block
     * @return block
     */
    public Block create(int xpos, int ypos) {
        Block block = new Block(new Rectangle(new Point(xpos, ypos), this.width, this.height));
        if (width < 0 || height < 0 || hitPoints < 0) {
            throw new RuntimeException("block wasn't initialized properly");
        }
        block.setHitPoints(this.hitPoints);
        block.setBackground(this.background);
        block.setHpToBackground(this.hpToBackground);
        if (this.stroke != null) {
            block.setStroke(this.stroke);
        }
        for (int hitPoint : this.hpToBackground.keySet()) {
            block.addHpBackground(hitPoint, this.hpToBackground.get(hitPoint));
        }
        return block;
    }
}
