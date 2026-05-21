package io;

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

    private final int GRID_SIZE = 20;

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

        Input input = new Input();
        frame.addKeyListener(input);
        frame.addMouseListener(input);
        frame.setVisible(true);

        frame.setContentPane(new WindowPanel(getGridSize()));
        container = frame.getContentPane();
    }

    public JPanel createBoxPanel(int x, int y, Color color, Vector2i size, String label0, String label1) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        if (size != null) {
            panel.setSize(size.x, size.y);
        }
        else {
            panel.setSize(CardSizeTypes.SMALL.x, CardSizeTypes.SMALL.y);
        }

        panel.setLocation(x, y);
        panel.setBackground(color);
        panel.setVisible(true);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel header = new JLabel(label0, JLabel.CENTER);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setSize(panel.getWidth(), panel.getHeight());
        header.setForeground(Color.white);


        JLabel duration = new JLabel(label1, JLabel.CENTER);
        duration.setAlignmentX(Component.CENTER_ALIGNMENT);
        duration.setSize(panel.getWidth(), panel.getHeight());
        duration.setForeground(Color.white);

        panel.add(Box.createRigidArea(new Dimension(0, panel.getHeight()/4)));
        panel.add(header);
        panel.add(Box.createVerticalGlue());
        panel.add(duration);
        panel.add(Box.createRigidArea(new Dimension(0, panel.getHeight()/4)));

        refresh();
        return panel;
    }

    public <T> void createDialog(String title, int width, int height, iDialogInfo info, T modifiable) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(width, height);

        info.create(panel, modifiable);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            info.action(modifiable);
        }
    }

    public Vector2i PositionToGrid (Vector2i position) {
        Vector2i vector = new Vector2i(
                (int) Math.floor((double) position.x / ((double) WIDTH / GRID_SIZE)),
                (int) Math.floor((double) position.y / ((double) HEIGHT / GRID_SIZE))
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
                (int) Math.floor((double) position.x / ((double) WIDTH / GRID_SIZE)) * (WIDTH / GRID_SIZE),
                (int) Math.floor((double) position.y / ((double) HEIGHT / GRID_SIZE)) * (HEIGHT / GRID_SIZE)
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
}