package tree;

import io.LinePanel;
import io.Renderer;
import scene.Entity;
import utils.Vector2i;
import utils.Vector4i;

import java.awt.*;

public class Line extends Entity {
    public Line(Vector4i pos, Color color) {
        super(new Vector2i(pos.x, pos.y));
        panel = new LinePanel(pos, color);

        Renderer.scene.addEntity(this);
        Renderer.window.addPanel(panel);
        Renderer.window.refresh();
    }
}
