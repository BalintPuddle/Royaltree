package balintpuddle.io.renderer;

import balintpuddle.io.renderer.Window;
import balintpuddle.scene.Scene;

public class Renderer {
    public static Window window;
    public static Scene scene;

    public static void render() {
        window = new Window();
        scene = new Scene(window);
    }

    public static void refresh() {
        window.refresh();
    }
}
