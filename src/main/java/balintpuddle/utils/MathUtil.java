package balintpuddle.utils;

public class MathUtil {
    public static int getCenterX(int width, int widthObj) {
        return (width / 2 - widthObj / 2);
    }

    public static int getCenterY(int height, int heightObj) {
        return (height / 2 - heightObj / 2);
    }

    public static Vector2i getCenter(int objectWidth, int objectHeight) {
        Vector2i vector = new Vector2i(
                objectWidth / 2,
                objectHeight /2);
        return vector;
    }
}
