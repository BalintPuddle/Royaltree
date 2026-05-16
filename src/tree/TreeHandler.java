package tree;

import io.Renderer;
import tree.team.Team;
import utils.Vector2i;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeHandler {
    private static List<Card> cards = new ArrayList<>();

    public static void addCard(String id, Vector2i gridPosition, Team team) {
        Card card = new Card(id, Renderer.window.GridToPosition(gridPosition), team);
        cards.add(card);
    }

    public static void addCard(String id, Vector2i gridPosition, Team team, Vector2i size) {
        Card card = new Card(id, Renderer.window.GridToPosition(gridPosition), team, size);
        cards.add(card);
        Renderer.window.refresh();
    }

    public static Card createCard(String id, Vector2i gridPosition, Team team, Vector2i size) {
        Card card = new Card(id, Renderer.window.GridToPosition(gridPosition), team, size);
        cards.add(card);
        Renderer.window.refresh();
        return card;
    }

    public static List<Card> getCards() {
        return cards;
    }

    public static Card getCard(String id) {
        for (Card card : cards) {
            if (Objects.equals(card.getId(), id)) {
                return card;
            }
        }
        return null;
    }
}
