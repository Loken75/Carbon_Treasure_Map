package main.java.com.entities;

import java.util.HashMap;

public class Tile {

    public enum TileType {
        PLAIN, MOUNTAIN, TREASURE
    }

    private static final HashMap<TileType, String> typeStringMap = new HashMap<>();

    static {
        typeStringMap.put(TileType.PLAIN, ".");
        typeStringMap.put(TileType.MOUNTAIN, "M");
        typeStringMap.put(TileType.TREASURE, "T");
    }

    private TileType tileType;
    private final int xPos, yPos;
    private int treasureCount;
    private boolean adventurer;
    private String adventurerName;

    public Tile(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.tileType = TileType.PLAIN;
        this.treasureCount = 0;
    }

    @Override
    public String toString() {
        if (adventurer) {
            return "A(" + adventurerName + ")";
        } else {
            if (this.tileType == TileType.TREASURE) {
                return "T(" + this.treasureCount + ")";
            } else {
                return typeStringMap.get(this.tileType);
            }
        }
    }

    // Getters and Setters


    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getTreasureCount() {
        return treasureCount;
    }

    public void setAdventurer(boolean adventurer) {
        this.adventurer = adventurer;
    }

    public void setAdventurerName(String adventurerName) {
        this.adventurerName = adventurerName;
    }

    public boolean isAdventurer() {
        return adventurer;
    }

    public void setTreasureCount(int treasureCount) {
        this.treasureCount = treasureCount;
    }

    public TileType getTileType() {
        return tileType;
    }

    public String getAdventurerName() {
        return adventurerName;
    }
}
