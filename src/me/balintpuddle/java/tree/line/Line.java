package me.balintpuddle.java.tree.line;

import me.balintpuddle.java.io.gui.scenegui.LinePanel;
import me.balintpuddle.java.io.renderer.Renderer;
import me.balintpuddle.java.scene.Entity;
import me.balintpuddle.java.tree.card.TreeHandler;
import me.balintpuddle.java.tree.card.Card;
import me.balintpuddle.java.utils.Vector2i;
import me.balintpuddle.java.utils.Vector4i;

import javax.swing.*;
import java.awt.*;

public class Line extends Entity {
    public Card from, to;
    public LineType type;
    private LinePanel linePanel;

    public Line(Card from, Card to, LineType type, Vector4i pos, Color color) {
        super(new Vector2i(pos.x, pos.y));
        panel = new LinePanel(pos, color);
        linePanel = (LinePanel) panel;
        panel.repaint();
        this.from = from;
        this.to = to;
        this.type = type;

        Renderer.scene.addEntity(this);
        Renderer.window.addPanel(panel);
        Renderer.window.refresh();
    }

    public void updatePositions(Vector4i pos) {
        linePanel.updatePositions(pos);
    }

    @Override
    public JPanel getPanel() {
        return linePanel;
    }

    public void destroy() {
        Renderer.window.removePanel(linePanel);
        TreeHandler.getLines().remove(this);
    }
}
