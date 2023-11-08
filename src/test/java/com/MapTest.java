package test.java.com;

import main.java.com.FileManager;
import main.java.com.entities.Adventurer;
import main.java.com.entities.Map;
import main.java.com.entities.Tile;
import main.java.com.models.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class MapTest {

    private Map map;

    @Before
    public void setUp() {
        map = new Map();
        List<Adventurer> adventurers = new ArrayList<>();
        FileManager.loadMap(Model.inputFile, map, adventurers);
    }

    @Test
    public void testGetTile() {
        assertEquals(3, map.getTile(1,3).getTreasureCount());
    }

    @Test
    public void testGetSize() {
        map.setWidth(3);
        map.setHeight(4);
        assertEquals(12, map.getSize());
    }

    @After
    public void tearDown() {
        map = null;
    }
}
