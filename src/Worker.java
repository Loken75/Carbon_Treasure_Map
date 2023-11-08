import entities.Adventurer;
import models.Model;
import tools.FileManager;
import views.View;

import javax.swing.*;
import java.util.List;

public class Worker extends SwingWorker<Void, String> {

    private final Model model;
    private final View view;

    public Worker(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    protected Void doInBackground() throws InterruptedException {
        loadMapAndUpdateView();

        Thread.sleep(500);

        for (Adventurer adventurer : model.getAdventurers()) {
            processAdventurerMovements(adventurer);
        }

        return null;
    }

    private void loadMapAndUpdateView() {
        FileManager.loadMap(model.getInputFile(), model.getMap(), model.getAdventurers());
        view.updateMap(model.getMap().getTiles());
    }

    private void processAdventurerMovements(Adventurer adventurer) throws InterruptedException {
        for (Adventurer.Movement movement : adventurer.getMovements()) {
            logAdventurerMovement(adventurer, movement);
            adventurer.runAction(movement, model.getMap());
            view.updateMap(model.getMap().getTiles());
            Thread.sleep(500);
        }
        logAdventurerTreasureCount(adventurer);
    }

    private void logAdventurerMovement(Adventurer adventurer, Adventurer.Movement movement) {
        String movementLog = adventurer.getName() + " (" + adventurer.getxPos() + "," + adventurer.getyPos() +
                "," + adventurer.getOrientation() + ") ";

        if (movement == Adventurer.Movement.FORWARD) {
            movementLog += "go " + movement;
        } else {
            movementLog += "turns " + movement;
        }

        publish(movementLog);
    }

    private void logAdventurerTreasureCount(Adventurer adventurer) {
        String treasureLog = adventurer.getName() + " found " + adventurer.getTreasureFoundCount() + " treasures!";
        publish(treasureLog);
    }

    @Override
    protected void process(List<String> chunks) {
        for (String chunk : chunks) {
            view.getLogTextArea().append("\n" + chunk);
        }
    }

    @Override
    protected void done() {
        FileManager.writeMapToFile(model.getOutputFile(), model.getMap(), model.getAdventurers());
        view.getLogTextArea().append("\nProcess done.");
    }
}
