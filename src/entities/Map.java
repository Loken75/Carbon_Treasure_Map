package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * The Map class represents a grid-based map with tiles.
 */
public class Map {

    private int width;
    private int height;
    private List<Tile> tiles;

    public Map() {
        tiles = new ArrayList<>();
    }

    /**
     * Adds a tile to the map.
     *
     * @param tile The tile to add to the map.
     */
    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    /**
     * Retrieves a tile from the map based on its position.
     *
     * @param xPos The X-coordinate of the tile.
     * @param yPos The Y-coordinate of the tile.
     * @return The tile at the specified position, or null if it does not exist.
     */
    public Tile getTile(int xPos, int yPos) {
        int index = yPos * width + xPos;
        if (index >= 0 && index < tiles.size()) {
            return tiles.get(index);
        } else {
            return null;
        }
    }

    /**
     * Displays the map by printing its tiles to the console.
     */
    public void showMap() {
        for (int heightIndex = 0; heightIndex < this.height; heightIndex++) {
            for (int lineIndex = 0; lineIndex < this.width; lineIndex++) {
                System.out.print(getTile(lineIndex, heightIndex).toString() + " ");
            }
            System.out.println("\n");
        }
    }

    // Getters and Setters

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
