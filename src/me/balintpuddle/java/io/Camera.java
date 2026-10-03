package me.balintpuddle.java.io;

public class Camera {
    public static int x, y;

    public Camera() {
        x = 0;
        y = 0;
    }

    public static void moveRelative(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
