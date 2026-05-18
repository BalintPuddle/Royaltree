package tree;

import io.LinePanel;
import io.Renderer;
import tree.team.Team;
import utils.Vector2i;
import utils.Vector4i;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeHandler {
    private static List<Card> cards = new ArrayList<>();
    private static List<Line> lines = new ArrayList<>();

    public static void addCard(String id, Vector2i gridPosition, Team team) {
        Card card = new Card(id, Renderer.window.GridToPosition(gridPosition), team);
        cards.add(card);
    }

    public static void addCard(String id, Vector2i gridPosition, Team team, Vector2i size) {
        Card card = new Card(id, Renderer.window.GridToPosition(gridPosition), team, size);
        cards.add(card);
        Renderer.window.refresh();
    }

    public static Card createCard(String id, Vector2i gridPosition, Team team, Vector2i size) { //Same as add card but this one returns the card
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

    public static Card getCardByPanel(JPanel panel) {
        for (Card card : cards) {
            if (card.getPanel() == panel) {
                return card;
            }
        }
        return null;
    }

    //LINES SECTION ----------------------------------------------------------------------------------

    public static void addLine(Card from, Card to, LineType type, Vector4i positions) {
        Line line = new Line(from, to, type, positions, Color.BLACK);
        lines.add(line);
    }

    public static void drawCardLine(Card from, Card to) {
        Vector2i startPoint, endPoint;
        int width, height, side = 1;
        startPoint = new Vector2i(
                from.getPosition().x + from.getWidth()/2,
                from.getPosition().y + from.getHeight());
        endPoint = new Vector2i(to.getPosition().x + to.getWidth() / 2, to.getPosition().y);
        width = Math.abs(startPoint.x - endPoint.x);
        height = Math.abs(startPoint.y - endPoint.y);

        if (startPoint.x > endPoint.x) {
            side = -1;  //Determining whether the "to" card is to the left/right, and setting a value to compensate for that
        }

        addLine(from, to, LineType.START, new Vector4i(
                startPoint.x,   startPoint.y,  //START X, START Y
                startPoint.x, startPoint.y + height/2));  // END X, END Y

        addLine(from, to, LineType.MIDDLE, new Vector4i(startPoint.x, startPoint.y + height/2, //START X, START Y
                startPoint.x + width * side, startPoint.y + height/2)); // END X, END Y

        addLine(from, to, LineType.END, new Vector4i(startPoint.x + width * side, startPoint.y + height/2, //START X, START Y
                startPoint.x + width * side, startPoint.y + height)); // END X, END Y
    }

    public static void updateCardLine(Card from, Card to) {
        Vector2i startPoint, endPoint;
        int width, height, side = 1;
        startPoint = new Vector2i(
                from.getPosition().x + from.getWidth()/2,
                from.getPosition().y + from.getHeight());
        endPoint = new Vector2i(to.getPosition().x + to.getWidth() / 2, to.getPosition().y);
        width = Math.abs(startPoint.x - endPoint.x);
        height = Math.abs(startPoint.y - endPoint.y);

        if (startPoint.x > endPoint.x) {
            side = -1;  //Determining whether the "to" card is to the left/right, and setting a value to compensate for that
        }

        getLine(from, to, LineType.START).updatePositions(new Vector4i(startPoint.x, startPoint.y, //START X, START Y
                startPoint.x, startPoint.y + height/2));
        getLine(from, to, LineType.MIDDLE).updatePositions(new Vector4i(startPoint.x, startPoint.y + height/2, //START X, START Y
                startPoint.x + width * side, startPoint.y + height/2));
        getLine(from, to, LineType.END).updatePositions(new Vector4i(startPoint.x + width * side, startPoint.y + height/2, //START X, START Y
                startPoint.x + width * side, startPoint.y + height));
    }

    public static List<Line> getLines() {
        return lines;
    }

    public static Line getLine(Card from, Card to, LineType type) {
        for (Line line : lines) {
            if (line.from == from && line.to == to && line.type == type) {
                //System.out.println(from.getId() + " " + to.getId() + " " + type);
                return line;
            }
        }
        System.out.println("end");
        return null;
    }
}
