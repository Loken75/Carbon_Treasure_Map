package test.java.com;

import main.java.com.FileManager;
import main.java.com.entities.Adventurer;
import main.java.com.models.Model;
import main.java.com.views.View;
import main.java.com.Worker;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

public class WorkerTest {
    private Model model;
    private View view;
    private Worker worker;

    @Before
    public void setUp() {
        model = new Model();
        view = new View();
        worker = new Worker(model, view);
        FileManager.loadMap(model.getInputFile(), model.getMap(), model.getAdventurers());
        view.initializeMap(model.getMap().getWidth(), model.getMap().getHeight(), model.getMap().getTiles());
    }

    @Test
    public void testProcessAdventurerMovements() {
        Adventurer adventurer = model.getAdventurers().get(0);
        try {
            worker.processAdventurerMovements(adventurer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(0, adventurer.getxPos());
        assertEquals(3, adventurer.getyPos());
        assertEquals('S', adventurer.getOrientation());
        int tileIndex = model.getMap().getTileIndex(0,3);
        assertTrue(view.getMapButtons().get(tileIndex).getText().contains("A"));
        assertTrue(model.getMap().getTiles().get(tileIndex).isAdventurer());
    }

    @After
    public void tearDown() {
    }
}
