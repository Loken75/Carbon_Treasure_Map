package main.java.com.entities;

import java.util.List;

/**
 * Represents an adventurer in the adventure game. An adventurer can move around the map, collect treasures,
 * and change their orientation.
 */
public class Adventurer {
    private final String name;
    private int xPos;
    private int yPos;
    private int treasureFoundCount;
    private char orientation;
    private final Movement[] movements;

    public enum Movement {
        FORWARD, LEFT, RIGHT
    }

    private static final List<Character> ORIENTATIONS = List.of('N', 'E', 'S', 'W');

    /**
     * Constructs an Adventurer object with the given parameters.
     *
     * @param name            The name of the adventurer.
     * @param xPos            The initial X-coordinate of the adventurer's position on the map.
     * @param yPos            The initial Y-coordinate of the adventurer's position on the map.
     * @param orientation     The initial orientation of the adventurer ('N', 'E', 'S', or 'W').
     * @param movementSequence A string representing a sequence of movements for the adventurer.
     */
    public Adventurer(String name, int xPos, int yPos, char orientation, String movementSequence) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.orientation = orientation;
        this.movements = getMovementsFromString(movementSequence);
    }

    public static Movement[] getMovementsFromString(String txt) {
        Movement[] movements = new Movement[txt.length()];
        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == 'A') {
                movements[i] = Movement.FORWARD;
            } else if (txt.charAt(i) == 'G') {
                movements[i] = Movement.LEFT;
            } else if (txt.charAt(i) == 'D') {
                movements[i] = Movement.RIGHT;
            } else {
                System.out.println("Error: invalid movement sequence!");
                return null;
            }
        }
        return movements;
    }

    /**
     * Run a sequence of movements for the adventurer, updating their position and other properties accordingly.
     *
     * @param map The map on which the adventurer is moving.
     */
    public void runAction(Movement movement, Map map) {
        switch (movement) {
            case FORWARD -> forwardAction(map);
            case LEFT -> leftAction();
            case RIGHT -> rightAction();
        }
    }

    /**
     * Perform a forward movement action, updating the adventurer's position and collecting treasures if applicable.
     *
     * @param map The map on which the adventurer is moving.
     */
    private void forwardAction(Map map) {
        int destX = xPos;
        int destY = yPos;
        switch (orientation) {
            case 'N' -> destY = Math.max(destY - 1, 0);
            case 'S' -> destY = Math.min(destY + 1, map.getHeight());
            case 'W' -> destX = Math.max(destX - 1, 0);
            case 'E' -> destX = Math.min(destX + 1, map.getWidth());
        }

        Tile previousTile = map.getTile(xPos, yPos);
        Tile destTile = map.getTile(destX, destY);

        if (destTile.getTileType() != Tile.TileType.MOUNTAIN) {
            xPos = destX;
            yPos = destY;
            previousTile.setAdventurer(false);
            previousTile.setAdventurerName("");
            destTile.setAdventurer(true);
            destTile.setAdventurerName(name);
            if (destTile.getTileType() == Tile.TileType.TREASURE && destTile.getTreasureCount() > 0) {
                destTile.setTreasureCount(destTile.getTreasureCount() - 1);
                treasureFoundCount++;
            }
        }
    }

    /**
     * Perform a left turn action, changing the adventurer's orientation to the left.
     */
    private void leftAction() {
        int orientationIndex = ORIENTATIONS.indexOf(orientation);
        orientation = ORIENTATIONS.get((orientationIndex + 3) % 4);
    }

    /**
     * Perform a right turn action, changing the adventurer's orientation to the right.
     */
    private void rightAction() {
        int orientationIndex = ORIENTATIONS.indexOf(orientation);
        orientation = ORIENTATIONS.get((orientationIndex + 1) % 4);
    }

    /**
     * Returns a string representation of the adventurer's information, including their name, current position,
     * orientation, and movements.
     *
     * @return A string representation of the adventurer.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adventurer Name: ").append(name).append("\n");
        sb.append("Current Position: (").append(xPos).append(", ").append(yPos).append(")\n");
        sb.append("Orientation: ").append(orientation).append("\n");
        sb.append("Movements: ");

        for (Movement action : movements) {
            switch (action) {
                case FORWARD -> sb.append("A");
                case LEFT -> sb.append("G");
                case RIGHT -> sb.append("D");
            }
        }

        return sb.toString();
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getTreasureFoundCount() {
        return treasureFoundCount;
    }

    public char getOrientation() {
        return orientation;
    }

    public Movement[] getMovements() {
        return movements;
    }
}
