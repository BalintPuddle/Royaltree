package me.balintpuddle.java.scene;

import me.balintpuddle.java.io.Camera;
import me.balintpuddle.java.io.renderer.Renderer;
import me.balintpuddle.java.io.renderer.Window;
import me.balintpuddle.java.utils.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    public List<Entity> entities;
    private Window window;

    public Scene(Window window) {
        this.window = window;
        entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void transformAll(int dx, int dy) {
        for (Entity entity : entities) {
            entity.transform(dx, dy);
        }
        Camera.moveRelative(dx, dy);
        Renderer.window.getContainer().repaint();
    }

    public Vector2i screenToWorldSpace(Vector2i space) {
        return new Vector2i(space.x + Camera.x, space.y + Camera.y);
    }

    public Vector2i screenToWorldSpace(int x, int y) {
        return new Vector2i(x + Camera.x, y + Camera.y);
    }

    public Vector2i worldToScreenSpace(Vector2i space) {
        return new Vector2i(space.x - Camera.x, space.y - Camera.y);
    }

    public Vector2i worldToScreenSpace(int x, int y) {
        return new Vector2i(x - Camera.x, y - Camera.y);
    }
}
