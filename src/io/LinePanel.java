package io;

import utils.Vector4i;

import javax.swing.*;
import java.awt.*;

public class LinePanel extends JPanel {
    private final int LINEWIDTH = 5;
    private final int CORRECTIVE_WIDTH = 2;
    private Vector4i positons;
    private Color color;
    public LinePanel(Vector4i pos, Color color) {
        super(new FlowLayout());
        if (Math.abs(pos.x-pos.z) < LINEWIDTH) { //If the 2 positon's x coordinates are less than the width default them to a straight line
            setSize(LINEWIDTH, Math.abs(pos.y-pos.w));
            this.positons = new Vector4i(CORRECTIVE_WIDTH, 0, CORRECTIVE_WIDTH, getHeight());
        }
        else if (Math.abs(pos.y-pos.w) < LINEWIDTH) { //If the 2 positon's y coordinates are less than the width default them to a straight line
            setSize(Math.abs(pos.x-pos.z), LINEWIDTH);
            this.positons = new Vector4i(0, CORRECTIVE_WIDTH, getWidth(), CORRECTIVE_WIDTH);
        }
        else {
            setSize(Math.abs(pos.x-pos.z), Math.abs(pos.y-pos.w)); //Setting the size to equal a rectangle drawn by start point and end point
            this.positons = new Vector4i(0, 0, getWidth(), getHeight()); //Drawing a line between it's left corner and right corner
        }

        this.color = color;
        setVisible(true);
    }

    public void setPositons(Vector4i pos) {
        this.positons = pos;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(4));
        g.drawLine(positons.x, positons.y, positons.z, positons.w);
    }

    @Override
    public Component add(Component comp) {
        return super.add(comp);
    }
}
