package utils;

public class Vector2i {
    public int x, y;

    public Vector2i(Vector2i v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2i value) {
        this.x += value.x;
        this.y += value.y;
    }

    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void subtract(Vector2i value) {
        this.x -= value.x;
        this.y -= value.y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
