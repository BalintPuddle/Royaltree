package utils;

public class Vector2i {
    public int x, y;

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2i value) {
        this.x = this.x + value.x;
        this.y = this.y + value.y;
    }

    public void subtract(Vector2i value) {
        this.x = this.x - value.x;
        this.y = this.y - value.y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
