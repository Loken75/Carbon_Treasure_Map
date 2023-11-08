package models;

import entities.Adventurer;
import entities.Map;
import tools.FileManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Model {
    private final File inputFile = new File("resources/input.txt");
    private final File outputFile = new File("resources/output.txt");
    private final Map map;
    private final List<Adventurer> adventurers;

    public Model() {
        map = new Map();
        adventurers = new ArrayList<>();
        FileManager.loadMap(inputFile, map, adventurers);

    }

    public Map getMap() {
        return map;
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }
}
