package scene;

import utils.Vector2i;

import javax.swing.*;

public class Entity {
    protected JPanel panel;
    protected Vector2i position;
    public Entity(Vector2i position) {
        this.position = position;
    }

    public void transform(int dx, int dy) {
        panel.setLocation(panel.getLocation().x - dx, panel.getLocation().y - dy);
    }
}
