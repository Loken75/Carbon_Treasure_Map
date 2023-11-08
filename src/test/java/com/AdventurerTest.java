package test.java.com;

import main.java.com.FileManager;
import main.java.com.entities.Adventurer;
import main.java.com.entities.Map;
import main.java.com.models.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AdventurerTest {
    private Adventurer adventurer;
    private Map map;
    private List<Adventurer> adventurers;

    @Before
    public void setUp() {
        map = new Map();
        adventurers = new ArrayList<>();
        adventurer = new Adventurer("Lara", 1, 1, 'S', "AADADA");
        FileManager.loadMap(Model.inputFile, map, adventurers);
    }

    @Test
    public void testGetMovementsFromString() {
        assertEquals(Adventurer.getMovementsFromString("AADADA"), adventurer.getMovements());
        assertNull(Adventurer.getMovementsFromString("aadada"));
        assertNull(Adventurer.getMovementsFromString(String.valueOf(0)));
    }

    @Test
    public void testAdventurerConstruction() {
        assertEquals("Lara", adventurer.getName());
        assertEquals(1, adventurer.getxPos());
        assertEquals(1, adventurer.getyPos());
        assertEquals('S', adventurer.getOrientation());
        assertEquals(Adventurer.getMovementsFromString("AADADA"), adventurer.getMovements());
    }

    @Test
    public void testRunAction() {

        adventurer.runAction(Adventurer.Movement.FORWARD, map);
        assertEquals(1, adventurer.getxPos());
        assertEquals(2, adventurer.getyPos());
        adventurer.runAction(Adventurer.Movement.LEFT, map);
        assertEquals('E', adventurer.getOrientation());
        adventurer.runAction(Adventurer.Movement.RIGHT, map);
        assertEquals('S', adventurer.getOrientation());
    }

    @Test
    public void testToString() {
        String expectedString = "Adventurer Name: Lara\n" +
                "Current Position: (1, 1)\n" +
                "Orientation: S\n" +
                "Movements: AADADA";
        assertEquals(expectedString, adventurer.toString());
    }

    @After
    public void tearDown() {
        adventurer = null;
        adventurers = null;
        map = null;
    }
}
