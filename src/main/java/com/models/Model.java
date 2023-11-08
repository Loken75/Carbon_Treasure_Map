package main.java.com.models;

import main.java.com.entities.Adventurer;
import main.java.com.entities.Map;
import main.java.com.FileManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Model {
    public static File inputFile = new File("resources/input.txt");
    public static File outputFile = new File("resources/output.txt");
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

    public void setInputFile(File inputFile) {
        Model.inputFile = inputFile;
    }

}
