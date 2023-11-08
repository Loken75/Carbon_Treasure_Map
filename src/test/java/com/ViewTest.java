package test.java.com;

import main.java.com.entities.Map;
import main.java.com.entities.Tile;
import main.java.com.views.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.JButton;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ViewTest {
    private View view;

    @Before
    public void setUp() {
        view = new View();
    }

    @Test
    public void testViewInitialization() {
        assertNotNull(view.getStartButton());
        assertNotNull(view.getStopButton());
        assertNotNull(view.getLogTextArea());
        assertNotNull(view.getInputFileMenuItem());
        assertNotNull(view.getOutputFileMenuItem());
    }

    @Test
    public void testInitializeMap() {
        List<Tile> mapTiles = List.of(new Tile(0,1), new Tile(0,2), new Tile(0,3));
        view.initializeMap(3, 1, mapTiles);

        List<JButton> mapButtons = view.getMapButtons();
        assertNotNull(mapButtons);
        assertEquals(3, mapButtons.size());
    }

    @Test
    public void testUpdateMap() {
        Map map = new Map();
        map.setWidth(3);
        map.setHeight(0);
        map.setTiles(List.of(new Tile(0,1), new Tile(0,2), new Tile(0,3)));

        map.getTile(0,1).setTileType(Tile.TileType.MOUNTAIN);
        map.getTile(0,2).setTileType(Tile.TileType.TREASURE);
        map.getTile(0,2).setTreasureCount(2);
        view.initializeMap(map.getWidth(), map.getHeight(), map.getTiles());

        List<JButton> mapButtons = view.getMapButtons();
        assertEquals("M", mapButtons.get(0).getText());
        assertEquals("T(2)", mapButtons.get(1).getText());
        assertEquals(".", mapButtons.get(2).getText());

        map.getTile(0,1).setTileType(Tile.TileType.TREASURE);
        map.getTile(0,2).setTileType(Tile.TileType.PLAIN);
        map.getTile(0,3).setTileType(Tile.TileType.MOUNTAIN);
        view.updateMap(map.getTiles());

        assertEquals("T(0)", mapButtons.get(0).getText());
        assertEquals(".", mapButtons.get(1).getText());
        assertEquals("M", mapButtons.get(2).getText());
    }

    @After
    public void tearDown() {
        view = null;
    }
}
