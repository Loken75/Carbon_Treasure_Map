import models.Model;
import views.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Controller class handles user interactions and coordinates between the Model and View.
 */
public class Controller implements ActionListener {

    private final View view;
    private final Model model;
    private final List<Worker> activeWorkers;

    /**
     * Constructs a new Controller instance.
     *
     * @param view  The View component of the application.
     * @param model The Model component of the application.
     */
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        activeWorkers = new ArrayList<>();
        view.addClickListener(this);

        view.initializeMap(model.getMap().getWidth(), model.getMap().getHeight(), model.getMap().getTiles());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getStartButton()) {
            view.getLogTextArea().setText("Start search:");
            Worker worker = new Worker(model, view);
            activeWorkers.add(worker);
            worker.execute();
        } else if (e.getSource() == view.getStopButton()) {
            stopActiveWorkers();
            view.getLogTextArea().append("\nProcess stopped by user!");
        } else if (e.getSource() == view.getInputFileMenuItem()) {
            openFile(model.getInputFile());
        } else if (e.getSource() == view.getOutputFileMenuItem()) {
            openFile(model.getOutputFile());
        }
    }

    /**
     * Open the specified file using the default application associated with the file type.
     *
     * @param file The file to be opened.
     */
    private void openFile(File file) {
        if (file != null && file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            System.out.println("File does not exist!");
        }
    }

    /**
     * Stops all active worker threads.
     */
    private void stopActiveWorkers() {
        for (Worker worker : activeWorkers) {
            if (!worker.isDone()) {
                worker.cancel(true);
            }
        }
        activeWorkers.clear();
    }
}
