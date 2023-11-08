package views;

import entities.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The View class represents the graphical user interface for the "La carte aux trésors" application.
 */
public class View {
    private final JFrame frame;
    private JPanel mapPanel;
    private List<JButton> mapButtons;
    private JButton startButton;
    private JButton stopButton;
    private JTextArea logTextArea;
    private final JMenuItem inputFileMenuItem;
    private final JMenuItem outputFileMenuItem;

    /**
     * Constructs a new View instance and sets up the graphical user interface.
     */
    public View() {
        frame = new JFrame("La carte aux trésors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size and location of the main frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int halfScreenWidth = (screenSize.width - frame.getWidth()) * 3 / 4;
        int halfScreenHeight = (screenSize.height - frame.getHeight()) * 3 / 4;
        frame.setSize(halfScreenWidth, halfScreenHeight);
        frame.setLocationRelativeTo(null);

        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();
        JSplitPane splitPane = createSplitPane(leftPanel, rightPanel);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Files");
        inputFileMenuItem = new JMenuItem("Input file");
        outputFileMenuItem = new JMenuItem("Output file");
        fileMenu.add(inputFileMenuItem);
        fileMenu.add(outputFileMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        frame.add(splitPane);
        frame.setVisible(true);
    }

    /**
     * Creates and configures the left panel of the GUI, which contains map display and control buttons.
     *
     * @return The configured left panel.
     */
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Map");
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(new LineBorder(Color.BLACK, 1));

        mapPanel = new JPanel();

        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Lancer");
        stopButton = new JButton("Stop");
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        leftPanel.add(titleLabel, BorderLayout.NORTH);
        leftPanel.add(mapPanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        return leftPanel;
    }

    /**
     * Creates and configures the right panel of the GUI, which displays logs and messages.
     *
     * @return The configured right panel.
     */
    private JPanel createRightPanel() {
        JPanel sidebar = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Logs");
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(new LineBorder(Color.BLACK, 1));
        logTextArea = new JTextArea("");
        logTextArea.setEditable(false);
        logTextArea.setLineWrap(true);
        logTextArea.setWrapStyleWord(true);
        DefaultCaret caret = (DefaultCaret) logTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        sidebar.add(titleLabel, BorderLayout.NORTH);
        sidebar.add(new JScrollPane(logTextArea), BorderLayout.CENTER);
        return sidebar;
    }

    /**
     * Creates a split pane to divide the main panel and the rightPanel panel.
     *
     * @param leftPanel  The main panel.
     * @param rightPanel The rightPanel panel.
     * @return The split pane with the specified components.
     */
    private JSplitPane createSplitPane(JPanel leftPanel, JPanel rightPanel) {
        leftPanel.setPreferredSize(new Dimension(frame.getWidth() * 2 / 3, 0));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);
        return splitPane;
    }

    /**
     * Adds an ActionListener to various components in the GUI, such as buttons and menu items.
     *
     * @param listener The ActionListener to be added.
     */
    public void addClickListener(ActionListener listener) {
        startButton.addActionListener(listener);
        stopButton.addActionListener(listener);
        inputFileMenuItem.addActionListener(listener);
        outputFileMenuItem.addActionListener(listener);
    }

    /**
     * Initializes the map display with the specified width, height, and map tiles.
     *
     * @param width      The width of the map.
     * @param height     The height of the map.
     * @param mapsTiles  The list of map tiles.
     */
    public void initializeMap(int width, int height, List<Tile> mapsTiles) {
        mapButtons = new ArrayList<>();

        if (width * height != mapsTiles.size()) {
            return;
        }

        mapPanel.setLayout(new GridLayout(height, width));
        for (Tile mapsTile : mapsTiles) {
            JButton button = new JButton(mapsTile.toString());
            button.setEnabled(false);
            mapButtons.add(button);
            mapPanel.add(button);
        }
        updateView();
    }

    /**
     * Updates the map display with the provided list of map tiles.
     *
     * @param mapTiles The list of map tiles to update the display with.
     */
    public void updateMap(List<Tile> mapTiles) {
        for (int i = 0; i < mapTiles.size(); i++) {
            mapButtons.get(i).setText(mapTiles.get(i).toString());
        }
        updateView();
    }

    /**
     * Updates the GUI view by revalidating and repainting the frame.
     */
    public void updateView() {
        frame.revalidate();
        frame.repaint();
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JTextArea getLogTextArea() {
        return logTextArea;
    }

    public JMenuItem getInputFileMenuItem() {
        return inputFileMenuItem;
    }

    public JMenuItem getOutputFileMenuItem() {
        return outputFileMenuItem;
    }
}
