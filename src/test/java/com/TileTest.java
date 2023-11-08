package test.java.com;

import main.java.com.entities.Tile;
import main.java.com.entities.Tile.TileType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TileTest {
    private Tile tile;

    @Before
    public void setUp() {
        tile = new Tile(1, 2);
    }

    @Test
    public void testSetUp() {
        assertEquals(1, tile.getxPos());
        assertEquals(2, tile.getyPos());
        assertEquals(TileType.PLAIN, tile.getTileType());
        assertEquals(0, tile.getTreasureCount());
    }

    @Test
    public void testToString() {
        assertEquals(".", tile.toString());
        tile.setTileType(TileType.MOUNTAIN);
        assertEquals("M", tile.toString());
        tile.setTileType(TileType.TREASURE);
        tile.setTreasureCount(3);
        assertEquals("T(3)", tile.toString());
        tile.setAdventurer(true);
        tile.setAdventurerName("Lara");
        assertEquals("A(Lara)", tile.toString());
    }

    @After
    public void tearDown() {
        tile = null;
    }
}
