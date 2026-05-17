package io;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {
    int GRID_SIZE;
    public WindowPanel(int gridsize) {
        super(null);
        this.GRID_SIZE = gridsize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.lightGray);
        for (int i = 0; i< (int) Math.floor(getWidth()/GRID_SIZE); i++) {
            g.drawLine(GRID_SIZE*i, 0, GRID_SIZE*i, getHeight());
        }
        for (int i = 0; i< (int) Math.floor(getHeight()/GRID_SIZE); i++) {
            g.drawLine(0, GRID_SIZE*i, getWidth(), GRID_SIZE*i);
        }
    }

    @Override
    public Component add(Component comp) {
        return super.add(comp);
    }
}
