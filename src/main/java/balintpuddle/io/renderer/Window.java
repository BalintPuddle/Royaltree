package balintpuddle.io.renderer;

import balintpuddle.io.file.ProjectHandler;
import balintpuddle.io.file.ProjectSerializer;
import balintpuddle.io.gui.GuiHandler;
import balintpuddle.io.gui.dialog.SettingsDialog;
import balintpuddle.io.gui.dialog.iDialogInfo;
import balintpuddle.io.input.Input;
import balintpuddle.scene.Scene;
import balintpuddle.utils.Vector2i;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.nio.file.Path;

public class Window {
    private JFrame frame;
    private Container container;

    private final String VERSION = "0.3.0";
    private final String TITLE = "Royaltree Beta " + VERSION;

    private int grid_size = 50;
    private boolean showGrid;

    public Window() {
        createWindow();
    }

    public void createWindow() {
        frame = new JFrame();
        frame.setTitle(TITLE);
        frame.setSize(2560, 1440);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setFocusable(true);

        Input input = new Input();
        frame.addKeyListener(input);
        frame.addMouseListener(input);
        frame.setJMenuBar(createMenubar());
        frame.setVisible(true);

        showGrid = true;
        frame.setContentPane(new WindowPanel(grid_size, getWidth()));
        container = frame.getContentPane();
        frame.requestFocus();

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                GuiHandler.onWindowResize(frame.getWidth(), frame.getHeight());
            }
        });

        frame.addWindowStateListener(e -> {
            if ((e.getNewState() & Frame.MAXIMIZED_BOTH) != 0) {
                GuiHandler.onWindowResize(frame.getWidth(), frame.getHeight());
            }
            else {
                GuiHandler.onWindowResize(frame.getWidth(), frame.getHeight());
            }
        });
    }

    private JMenuBar createMenubar() {
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

            newproject.addActionListener(_ -> {
                Renderer.scene = new Scene(this);
            });

            save.addActionListener(_ -> {
                ProjectHandler.save();
            });

            open.addActionListener(_ -> {
                ProjectHandler.load();
            });

            exit.addActionListener(_ -> System.exit(0));

            filemenu.add(newproject);
            filemenu.add(open);
            filemenu.add(save);
            filemenu.add(exit);

        // [EDIT] --> MENU ITEMS

            JMenuItem settings = new JMenuItem("Settings");

            settings.addActionListener(_ ->
                    Renderer.window.createDialog("Project Settings", 900, 400, new SettingsDialog(), null));

            JMenuItem teams = new JMenuItem("Teams");
            teams.addActionListener(_ ->
                    Renderer.window.createDialog("Project Settings", 900, 400, new SettingsDialog(), 1));

            editmenu.add(settings);
            editmenu.add(teams);

        // [ABOUT] --> MENU ITEMS
            JMenuItem project  = new JMenuItem("Project");
            JMenuItem credits = new JMenuItem("Credits");

            aboutmenu.add(project);
            aboutmenu.add(credits);

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
                position.x / grid_size,
                position.y / grid_size
        );
        return vector;
    }

    public Vector2i GridToPosition (Vector2i gridPosition) {
        Vector2i vector = new Vector2i(
                gridPosition.x * grid_size,
                gridPosition.y * grid_size
        );
        return vector;
    }

    public Vector2i PositionToGridPosition(Vector2i position) {
        //IMPORTANT TO DIVIDE IT AND MULTIPLE IT AS INTEGER DIVISION FLOORS THE VALUE
        return Renderer.scene.worldToScreenSpace(
                position.x / grid_size * grid_size,
                position.y / grid_size * grid_size
        );
    }

    public Component getComponentAt(int x, int y) {
        return frame.findComponentAt(x, y);
    }

    public void refresh() {
        frame.setVisible(true);
        frame.getContentPane().setVisible(true);
        frame.repaint();
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
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }

    public int getGridSize() {
        return grid_size;
    }

    public Container getContainer() {
        return container;
    }

    public int getWidthPercent(float percent) {
        return (int) (getWidth() * (percent/100));
    }

    public int getHeightPercent(float percent) {
        return (int) (getHeight() * (percent/100));
    }

    public void setGridSize(int size) {
        this.grid_size = size;
    }

    public boolean isGridShowing() {
        return showGrid;
    }

    public void setShowGrid(boolean showGrid) {
        this.showGrid = showGrid;
    }

    public Point getWindowScrenLocation() {
        return frame.getLocationOnScreen();
    }

    public JFrame getFrame() {
        return frame;
    }
}