package test.java.com;

import main.java.com.models.Model;
import main.java.com.views.View;
import main.java.com.Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.io.File;

import static org.junit.Assert.*;

public class ControllerTest {
    private View view;
    private Model model;
    private Controller controller;

    @Before
    public void setUp() {
        model = new Model();
        view = new View();
        controller = new Controller(view, model);
    }

    @Test
    public void testActionPerformed_StartButton() {
        view.getStartButton().doClick();
        assertEquals(1, controller.getActiveWorkers().size());
    }

    @Test
    public void testActionPerformed_StopButton() {
        view.getStartButton().doClick();
        view.getStopButton().doClick();
        assertTrue(controller.getActiveWorkers().isEmpty());
    }

    @Test
    public void testOpenFile_ExistingFile() {
        view.getInputFileMenuItem().doClick();
    }

    @Test
    public void testOpenFile_NonExistingFile() {
        model.setInputFile(new File("resources/NonExistingFile.txt"));
        view.getInputFileMenuItem().doClick();
    }

    @After
    public void tearDown() {
        model = null;
        view = null;
        controller = null;
    }
}
