package com.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;

/**
* 
* @author 
* @author 
* @author Fidel Little 2123494
* @version 2.0
*/

public class Tile {
    public static final int TILE_MARGIN = 9;
    public static final int TILE_SIZE = 70;
    private char[] colors;
    private int x, y;

    private static ArrayList<Pair> allObjectsCoordinates = new ArrayList<>();
    private Item item;
    private Item bombArea;

    /**
    * 
    * 
    * @param colours used on the tile quarters
    * @param x_coordinates
    * @param y_coordinates
    */
    public Tile(String colors, int x, int y) {
        this.colors = colors.toCharArray();
        this.x = x;
        this.y = y;
    }

    /**
    * 
    * 
    * @param graphics context tool
    * @param x_coordinates
    * @param y_coordinates
    * @param size of full tile
    */
    public void drawTile(GraphicsContext gc, int x, int y, int cellSize) {
        double halfCellSize = (cellSize) / 2 - 0.4;

        gc.setStroke(Color.WHITE);

        gc.strokeRect(y * cellSize, x * cellSize, halfCellSize, halfCellSize);
        gc.setFill(getColorFromChar(colors[0]));
        gc.fillRect(y * cellSize, x * cellSize, halfCellSize, halfCellSize);

        gc.strokeRect(y * cellSize + halfCellSize, x * cellSize, halfCellSize, halfCellSize);
        gc.setFill(getColorFromChar(colors[1]));
        gc.fillRect(y * cellSize + halfCellSize, x * cellSize, halfCellSize, halfCellSize);

        gc.strokeRect(y * cellSize, x * cellSize + halfCellSize, halfCellSize, halfCellSize);
        gc.setFill(getColorFromChar(colors[2]));
        gc.fillRect(y * cellSize, x * cellSize + halfCellSize, halfCellSize, halfCellSize);

        gc.strokeRect(y * cellSize + halfCellSize, x * cellSize + halfCellSize, halfCellSize, halfCellSize);
        gc.setFill(getColorFromChar(colors[3]));
        gc.fillRect(y * cellSize + halfCellSize, x * cellSize + halfCellSize, halfCellSize, halfCellSize);

        drawItem(gc);
    }

    /**
    * 
    * 
    * @param colour reference character
    */
    public Color getColorFromChar(char C) {
        return switch (C) {
            case 'R' -> Color.valueOf("#ff6666");
            case 'G' -> Color.valueOf("#61cf6c");
            case 'B' -> Color.valueOf("#0080bd");
            case 'Y' -> Color.valueOf("#fff685");
            case 'C' -> Color.valueOf("#00e6e6");
            case 'M' -> Color.valueOf("#e600e6");
            default -> null;
        };
    }

    /**
    * 
    * 
    * @param tile
    * @param tile
    * @return true if two parameter tiles share any coloured quarters
    */
    public static boolean hasCommonColor(Tile tile1, Tile tile2) {
        for (int i = 0; i < tile1.getColors().length; i++) {
            for (int j = 0; j < tile2.getColors().length; j++) {
                if (tile1.getColors()[i] == tile2.getColors()[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
    * 
    * 
    * @param graphics context tool
    */
    private void drawItem(GraphicsContext gc) {
        if (hasItem()) {
            gc.drawImage(item.getImg(), item.getX() * TILE_SIZE + TILE_MARGIN,
                    item.getY() * TILE_SIZE + TILE_MARGIN);
        }
    }

    /**
    * @param item
    */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
    * @return item
    */
    public Item getItem() {
        return item;
    }

    /**
    * 
    * 
    */
    public void setItemToNull() {
        if (item instanceof Bomb) {
            if (!item.isActive) {
                item.getCollected();
            }
        } else if (item instanceof Gate || item instanceof Door) {
        } else {
            item = null;
        }
    }

    /**
    * @return true if the current tile has an item on it
    */
    public boolean hasItem() {
        return item != null;
    }

    /**
    * @return true if the current tile is a bomb area
    */
    public boolean isBombArea() {
        return bombArea != null;
    }
    
    /**
    * @return list of all object coordinates
    */
    public static ArrayList<Pair> getAllObjectsCoordinates() {
        return allObjectsCoordinates;
    }

    /**
    * @param coordinates of all objects
    */
    public static void setAllObjectsCoordinates(ArrayList<Pair> allObjectsCoordinates) {
        Tile.allObjectsCoordinates = allObjectsCoordinates;
    }

    /**
    * @return current tile colours
    */
    public char[] getColors() {
        return colors;
    }

    /**
    * @param colour for each quarter of the tile
    */
    public void setColors(char[] colors) {
        this.colors = colors;
    }

    /**
    * @return x coordinate
    */
    public int getX() {
        return x;
    }

    /**
    * @param x_coordinates
    */
    public void setX(int x) {
        this.x = x;
    }

    /**
    * @return y coordinates
    */
    public int getY() {
        return y;
    }

    /**
    * @param y_coordinates
    */
    public void setY(int y) {
        this.y = y;
    }

    /**
    * @return bomb area
    */
    public Item getBombArea() {
        return bombArea;
    }

    /**
    * @param bomb_area
    */
    public void setBombArea(Item bombArea) {
        this.bombArea = bombArea;
    }

}
