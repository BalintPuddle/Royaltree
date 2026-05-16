package tree;

import io.LinePanel;
import io.Renderer;
import tree.team.Team;
import utils.Vector2i;
import utils.Vector4i;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeHandler {
    private static List<Card> cards = new ArrayList<>();
    private static List<LinePanel> lines = new ArrayList<>();

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

    public static void addLine(Vector4i positions) {
        LinePanel line = new LinePanel(positions, Color.BLACK);
        line.setLocation(positions.x,positions.y);
        System.out.println(Math.abs(positions.x-positions.z));
        Renderer.window.addPanel(line);
        lines.add(line);
        Renderer.window.refresh();
    }

    public static void drawCardLine(Card from, Card to) {
        Vector2i startPoint, endPoint;
        startPoint = new Vector2i(
                from.getPosition().x + from.getWidth()/2,
                from.getPosition().y + from.getHeight());
        endPoint = new Vector2i(to.getPosition().x + to.getWidth() / 2, to.getPosition().y);
        System.out.println(startPoint.x + " " + startPoint.y);
        System.out.println(endPoint.x + " " + endPoint.y);
        addLine(new Vector4i(startPoint.x, startPoint.y, endPoint.x, endPoint.y));
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

    public static List<LinePanel> getLines() {
        return lines;
    }
}
