import entities.Adventurer;
import entities.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final File inputFile = new File("resources/input.txt");
    private static final File outputFile = new File("resources/output.txt");

    public Model() {
        Map map = new Map();
        List<Adventurer> adventurers = new ArrayList<>();
        FileManager.loadMap(inputFile, map, adventurers);

        map.showMap();

        for (Adventurer adventurer : adventurers) {
            adventurer.runActions(map);
        }

        System.out.println("\n\n");

        map.showMap();

        FileManager.writeMapToFile(outputFile, map, adventurers);
    }
}
