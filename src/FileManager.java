import entities.Adventurer;
import entities.Map;
import entities.Tile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static void loadMap(File file, Map map, List<Adventurer> adventurers) {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("C - ")) {
                    createMap(line, map);
                } else if (line.startsWith("M - ")) {
                    addMountain(line, map);
                } else if (line.startsWith("T - ")) {
                    addTreasure(line, map);
                } else if (line.startsWith("A - ")) {
                    addAdventurer(line, adventurers, map);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createMap(String line, Map map) {
        String[] parts = line.split(" - ");
        int width = Integer.parseInt(parts[1]);
        int height = Integer.parseInt(parts[2]);
        map.setWidth(width);
        map.setHeight(height);
        for (int heightIndex = 0; heightIndex < height; heightIndex++) {
            for (int widthIndex = 0; widthIndex < width; widthIndex++) {
                map.addTile(new Tile(widthIndex, heightIndex));
            }
        }
    }

    private static void addMountain(String line, Map map) {
        String[] parts = line.split(" - ");
        int xPos = Integer.parseInt(parts[1]);
        int yPos = Integer.parseInt(parts[2]);
        map.getTile(xPos, yPos).setTileType(Tile.TileType.MOUNTAIN);
    }

    private static void addTreasure(String line, Map map) {
        String[] parts = line.split(" - ");
        int xPos = Integer.parseInt(parts[1]);
        int yPos = Integer.parseInt(parts[2]);
        int treasureCount = Integer.parseInt(parts[3]);
        Tile tile = map.getTile(xPos, yPos);
        if (tile.getTileType() == Tile.TileType.PLAIN) {
            tile.setTreasureCount(treasureCount);
        }
    }

    private static void addAdventurer(String line, List<Adventurer> adventurers, Map map) {
        String[] parts = line.split(" - ");
        String name = parts[1];
        int xPos = Integer.parseInt(parts[2]);
        int yPos = Integer.parseInt(parts[3]);
        char orientation = switch (parts[4].charAt(0)) {
            case 'N' -> 'N';
            case 'S' -> 'S';
            case 'O' -> 'W';
            case 'E' -> 'E';
            default -> 0;
        };
        String movementSequence = parts[5];
        Adventurer adventurer = new Adventurer(name, xPos, yPos, orientation, movementSequence);
        adventurers.add(adventurer);
        Tile mapTile = map.getTile(xPos, yPos);
        mapTile.setAdventurer(true);
        mapTile.setAdventurerName(name);
    }

    public static void writeMapToFile(File file, Map map, List<Adventurer> adventurers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            List<String> outputText = new ArrayList<>();
            outputText.add("C - " + map.getWidth() + " - " + map.getHeight());
            for (Tile tile : map.getTiles()) {
                if (tile.getTileType() == Tile.TileType.MOUNTAIN) {
                    outputText.add("M - " + tile.getxPos() + " - " + tile.getyPos());
                } else if (tile.getTileType() == Tile.TileType.TREASURE && tile.getTreasureCount() > 0) {
                    outputText.add("T - " + tile.getxPos() + " - " + tile.getyPos() + " - " + tile.getTreasureCount());
                }
            }
            for (Adventurer adventurer : adventurers) {
                outputText.add("A - " + adventurer.getName() + " - " + adventurer.getxPos() + " - " + adventurer.getyPos() + " - " + adventurer.getOrientation() + " - " + adventurer.getTreasureFoundCount());
            }

            for (String line : outputText) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}