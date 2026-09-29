package tree.card;

import io.renderer.Renderer;
import tree.line.Line;
import tree.line.LineMode;
import tree.line.LineType;
import tree.team.Team;
import utils.Vector2i;
import utils.Vector4i;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class TreeHandler {
    private static List<Card> cards = new ArrayList<>();
    private static List<Line> lines = new ArrayList<>();
    private static LineMode mode = LineMode.HIGH_CENTER;
    private static String yearFormat;

    public static void init() {
        yearFormat = "AD";
    }

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

    public static void removeCard(Card card) {
        cards.remove(card);
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

    public static void updateAllCards() {
        for (Card card : cards) {
            card.update();
        }
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

        if (startPoint.y > endPoint.y) return;

        width = Math.abs(startPoint.x - endPoint.x);
        height = Math.abs(startPoint.y - endPoint.y);

        if (startPoint.x > endPoint.x) {
            side = -1;  //Determining whether the "to" card is to the left/right, and setting a value to compensate for that
        }

        addLine(from, to, LineType.START, getTypePosition(LineType.START, startPoint, endPoint, width, height, side, from));
        addLine(from, to, LineType.MIDDLE, getTypePosition(LineType.MIDDLE, startPoint, endPoint, width, height, side, from));
        addLine(from, to, LineType.END, getTypePosition(LineType.END, startPoint, endPoint, width, height, side, from));
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

        if (startPoint.y > endPoint.y) return;

        if (startPoint.x > endPoint.x) {
            side = -1;  //Determining whether the "to" card is to the left/right, and setting a value to compensate for that
        }

        getLine(from, to, LineType.START).updatePositions(getTypePosition(LineType.START, startPoint, endPoint, width, height, side, from));
        getLine(from, to, LineType.MIDDLE).updatePositions(getTypePosition(LineType.MIDDLE, startPoint, endPoint, width, height, side, from));
        getLine(from, to, LineType.END).updatePositions(getTypePosition(LineType.END, startPoint, endPoint, width, height, side, from));
    }

    public static Vector4i getTypePosition(LineType type, Vector2i startPoint, Vector2i endPoint, int width, int height, int side, Card from) {
        switch (mode) {
            case CENTER:
                switch (type) {
                    case START ->
                    {
                        return new Vector4i(
                                startPoint.x,   startPoint.y,  //START X, START Y
                                startPoint.x, startPoint.y + height/2);  // END X, END Y
                    }
                    case MIDDLE ->
                    {
                        return new Vector4i(startPoint.x, startPoint.y + height/2, //START X, START Y
                                startPoint.x + width * side, startPoint.y + height/2); // END X, END Y
                    }
                    case END ->
                    {
                        return new Vector4i(startPoint.x + width * side, startPoint.y + height/2, //START X, START Y
                                startPoint.x + width * side, startPoint.y + height); // END X, END Y
                    }
                    case null, default ->
                    {
                        return new Vector4i();
                    }
                }
            case HIGH_CENTER:
                Card highest = from.getHighestChild();
                int difference = Math.abs(highest.getPosition().y - endPoint.y);

                switch (type) {
                    case START ->
                    {
                        return new Vector4i(
                                startPoint.x,   startPoint.y,  //START X, START Y
                                startPoint.x, startPoint.y + height/2 - difference/2);  // END X, END Y
                    }
                    case MIDDLE ->
                    {
                        return new Vector4i(startPoint.x, startPoint.y + height/2 - difference/2, //START X, START Y
                                startPoint.x + width * side, startPoint.y + height/2 - difference/2); // END X, END Y
                    }
                    case END ->
                    {
                        return new Vector4i(startPoint.x + width * side, startPoint.y + height/2 - difference/2, //START X, START Y
                                startPoint.x + width * side, startPoint.y + height); // END X, END Y
                    }
                    case null, default ->
                    {
                        return new Vector4i();
                    }
                }
            case null, default:
                return new Vector4i();
        }
    }

    public static void removeCardLine(Card from, Card to) {
        getLine(from, to, LineType.START).destroy();
        getLine(from, to, LineType.MIDDLE).destroy();
        getLine(from, to, LineType.END).destroy();
    }

    public static void connect(Card from, Card to) {
        if (from != null && to != null) {
            if (from.isParentOf(to)) {
                disconnect(from, to);
            }
            else {
                from.addChild(to);
            }
        }
        else {
            System.out.println("ERROR: Failed to connect cards from " + (from==null ? "NULL" : from.getId()) + " to " + (to==null ? "NULL" : to.getId()));
        }
    }

    public static void disconnect(Card from, Card to) {
        from.removeChild(to);
        removeCardLine(from, to);
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
        throw new NoSuchElementException("No such line!");
    }

    public static LineMode getMode() {
        return mode;
    }

    public static void setMode(LineMode mode) {
        TreeHandler.mode = mode;
    }

    public static String getYearFormat() {
        return yearFormat;
    }

    public static void setYearFormat(String yearFormat) {
        TreeHandler.yearFormat = yearFormat;
    }
}
