package tree;

import io.Renderer;
import tree.team.Team;
import utils.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class TreeHandler {
    private static List<Card> objects = new ArrayList<>();

    public static void addCard(Vector2i gridPosition, Team team) {
        Card card = new Card(Renderer.window.GridToPosition(gridPosition), team);
        objects.add(card);
    }

    public static void addCard(Vector2i gridPosition, Team team, Vector2i size) {
        Card card = new Card(Renderer.window.GridToPosition(gridPosition), team, size);
        objects.add(card);
    }

    public static List<Card> getObjects() {
        return objects;
    }
}
