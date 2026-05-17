package io;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {
    int GRID_SIZE;
    public WindowPanel(int gridsize) {
        super(null, true);
        this.GRID_SIZE = gridsize;
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.getHSBColor(0f, 0f, 0.85f));

        int offsetX = Camera.x % GRID_SIZE;
        int offsetY = Camera.y % GRID_SIZE;

        // Vertical lines
        for (int x = -GRID_SIZE; x < getWidth() + GRID_SIZE; x += GRID_SIZE) {
            g.drawLine(x + offsetX, 0, x + offsetX, getHeight());
        }

        // Horizontal lines
        for (int y = -GRID_SIZE; y < getHeight() + GRID_SIZE; y += GRID_SIZE) {
            g.drawLine(0, y + offsetY, getWidth(), y + offsetY);
        }
    }
}