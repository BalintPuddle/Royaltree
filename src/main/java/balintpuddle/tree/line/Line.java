package balintpuddle.tree.line;

import balintpuddle.io.gui.scenegui.LinePanel;
import balintpuddle.io.renderer.Renderer;
import balintpuddle.scene.Entity;
import balintpuddle.tree.card.TreeHandler;
import balintpuddle.tree.card.Card;
import balintpuddle.utils.Vector2i;
import balintpuddle.utils.Vector4i;

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
