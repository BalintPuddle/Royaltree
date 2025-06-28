package io;

import tree.CardSizeTypes;
import utils.Vector2i;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame frame;

    private final String TITLE = "Royaltree 0.1";
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
    }

    public JPanel createBoxPanel(int x, int y, Color color, Vector2i size) {
        JPanel panel = new JPanel();

        if (size != null) {
            panel.setSize(size.x, size.y);
        }
        else {
            panel.setSize(CardSizeTypes.SMALL.x, CardSizeTypes.SMALL.y);
        }

        panel.setLocation(x, y);
        panel.setBackground(color);

        return panel;
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
        Vector2i vector = new Vector2i(
                (int) Math.floor((double) position.x / ((double) WIDTH / GRID_SIZE)) * (WIDTH / GRID_SIZE),
                (int) Math.floor((double) position.y / ((double) HEIGHT / GRID_SIZE)) * (HEIGHT / GRID_SIZE)
        );
        return vector;
    }

    public Component getComponentAt(int x, int y) {
        return frame.findComponentAt(x, y);
    }

    public void addPanel(JPanel panel) {
        frame.add(panel);
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
}