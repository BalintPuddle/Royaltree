package balintpuddle.utils;

public class Vector3i {
    public int x, y, z;

    public Vector3i() {}

    public Vector3i(Vector3i v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Vector3i value) {
        this.x += value.x;
        this.y += value.y;
        this.z += value.z;
    }

    public void add(int x, int y, int z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void subtract(Vector3i value) {
        this.x -= value.x;
        this.y -= value.y;
        this.z -= value.z;
    }

    public void set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
