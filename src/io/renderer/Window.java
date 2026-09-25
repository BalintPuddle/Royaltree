package io.renderer;

import io.gui.SettingsDialog;
import io.input.Input;
import io.gui.InteractablePanel;
import io.gui.iDialogInfo;
import tree.card.CardSizeTypes;
import utils.Vector2i;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame frame;
    private Container container;

    private final String TITLE = "Royaltree Beta";
    private final int WIDTH = 2000;
    private final int HEIGHT = 1000;

    private int GRID_SIZE = 20;
    private boolean showGrid;

    public Window() {
        createWindow();
    }

    public void createWindow() {
        frame = new JFrame();
        frame.setTitle(TITLE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setFocusable(true);

        Input input = new Input();
        frame.addKeyListener(input);
        frame.addMouseListener(input);
        frame.setJMenuBar(createMenubar());
        frame.setVisible(true);

        showGrid = true;
        frame.setContentPane(new WindowPanel(getGridSize()));
        container = frame.getContentPane();
        frame.requestFocus();
    }

    private static JMenuBar createMenubar() {
        //Sets up the top of the screen menu bar and its submenus
        JMenuBar bar = new JMenuBar();
        JMenu filemenu = new JMenu("File");
        JMenu editmenu = new JMenu("Edit");
        JMenu aboutmenu = new JMenu("About");
        JMenu helpmenu = new JMenu("Help");

        // [FILE] --> MENU ITEMS

            JMenuItem newproject = new JMenuItem("New Project");
            JMenuItem open = new JMenuItem("Open Project");
            JMenuItem save = new JMenuItem("Save Project");
            JMenuItem exit = new JMenuItem("Exit");

            exit.addActionListener(_ -> System.exit(0));

            filemenu.add(newproject);
            filemenu.add(open);
            filemenu.add(save);
            filemenu.add(exit);

        // [EDIT] --> MENU ITEMS

            JMenuItem settings = new JMenuItem("Settings");

            settings.addActionListener(_ ->
                    Renderer.window.createDialog("Global Settings", Renderer.window.getWidthPercent(45), Renderer.window.getHeightPercent(40f), new SettingsDialog(), null));

            editmenu.add(settings);

        //====================================================
        //Adding all the menus  to the bar
        bar.add(filemenu);
        bar.add(editmenu);
        bar.add(aboutmenu);
        bar.add(helpmenu);
        return bar;
    }

    public <T> void createDialog(String title, int width, int height, iDialogInfo info, T modifiable) {
        JPanel panel = new JPanel();   //Creating a panel that will be the parent to every component and the actual visual part
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(width, height));

        info.create(panel, modifiable);

        int result = JOptionPane.showConfirmDialog(
                frame,
                panel,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            info.action(modifiable);
        }
    }

    public <T> iDialogInfo showDialog(String title, int width, int height, iDialogInfo info, T modifiable) {
        JPanel panel = new JPanel();   //Creating a panel that will be the parent to every component and the actual visual part
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(width, height));

        info.create(panel, modifiable);

        int result = JOptionPane.showConfirmDialog(
                frame,
                panel,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            info.action(modifiable);
        }

        return info;
    }

    public Vector2i PositionToGrid (Vector2i position) {
        Vector2i vector = new Vector2i(
                position.x / (WIDTH / GRID_SIZE),
                position.y / (HEIGHT / GRID_SIZE)
        );
        return vector;
    }

    public Vector2i GridToPosition (Vector2i gridPosition) {
        Vector2i vector = new Vector2i(
                gridPosition.x * (WIDTH / GRID_SIZE),
                gridPosition.y * (HEIGHT / GRID_SIZE)
        );
        return vector;
    }

    public Vector2i PositionToGridPosition(Vector2i position) {
        return Renderer.scene.worldToScreenSpace(
                position.x / (WIDTH / GRID_SIZE) * (WIDTH / GRID_SIZE),
                position.y / (HEIGHT / GRID_SIZE) * (HEIGHT / GRID_SIZE)
        );
    }

    public Component getComponentAt(int x, int y) {
        return frame.findComponentAt(x, y);
    }

    public void refresh() {
        frame.setVisible(true);
        frame.getContentPane().setVisible(true);
    }

    public void addPanel(JPanel panel) {
        container.add(panel);
    }

    public void removePanel(JPanel panel) {
        container.remove(panel);
    }

    public String getTitle() {
        return TITLE;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getGridSize() {
        return GRID_SIZE;
    }

    public Container getContainer() {
        return container;
    }

    public int getWidthPercent(float percent) {
        return (int) (WIDTH * (percent/100));
    }

    public int getHeightPercent(float percent) {
        return (int) (HEIGHT * (percent/100));
    }

    public void setGridSize(int size) {
        this.GRID_SIZE = size;
    }

    public boolean isGridShowing() {
        return showGrid;
    }

    public void setShowGrid(boolean showGrid) {
        this.showGrid = showGrid;
    }
}