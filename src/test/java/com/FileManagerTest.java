package test.java.com;

import main.java.com.FileManager;
import main.java.com.entities.Adventurer;
import main.java.com.entities.Map;
import main.java.com.entities.Tile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileManagerTest {
    private Map map;
    private List<Adventurer> adventurers;
    private File inputFile;
    private File outputFile;

    @Before
    public void setUp() {
        map = new Map();
        adventurers = new ArrayList<>();
        inputFile = new File("resources/input.txt");
        outputFile = new File("resources/tests/outputTest.txt");
    }

    @Test
    public void testLoadMap() {
        FileManager.loadMap(inputFile, map, adventurers);

        assertEquals(3, map.getWidth());
        assertEquals(4, map.getHeight());

        assertEquals(Tile.TileType.PLAIN, map.getTile(0, 1).getTileType());
        assertEquals(Tile.TileType.MOUNTAIN, map.getTile(1, 0).getTileType());
        assertEquals(Tile.TileType.TREASURE, map.getTile(0, 3).getTileType());

        Adventurer adventurer = adventurers.get(0);
        assertNotNull(adventurer);
        assertEquals("Lara", adventurer.getName());
        assertEquals(1, adventurer.getxPos());
        assertEquals(1, adventurer.getyPos());
        assertEquals('S', adventurer.getOrientation());
        Adventurer.Movement[] movements = Adventurer.getMovementsFromString("AADADAGGA");
        assertArrayEquals(movements, adventurer.getMovements());
    }

    @Test
    public void testWriteMapToFile() {
        map.setWidth(3);
        map.setHeight(4);
        map.addTile(new Tile(1, 1));
        adventurers.add(new Adventurer("Lara", 2, 2, 'N', "AADADA"));
        FileManager.writeMapToFile(outputFile, map, adventurers);
    }

    @After
    public void tearDown() {
        map = null;
        adventurers = null;
        inputFile = null;
        outputFile = null;
    }
}
