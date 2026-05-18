package io;

import scene.Scene;

public class Renderer {
    public static Window window;
    public static Scene scene;

    public static void render() {
        window = new Window();
        scene = new Scene(window);
    }
}
