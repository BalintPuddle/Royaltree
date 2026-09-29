package tree.card;

import io.gui.scenegui.BoxPanel;
import io.renderer.Renderer;
import scene.Entity;
import tree.line.LineMode;
import tree.team.Team;
import utils.Vector2i;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Card extends Entity {
    private final String id;
    private List<Card> parents;
    private List<Card> children;
    private Team team;

    private String header;
    private Duration duration;

    public Card(String id, Vector2i position, Team team) {
        super(position);
        this.id = id;
        this.team = team;
        this.header = "New Card";
        this.duration = new Duration(0, 0);
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();

        panel = new BoxPanel(
                position.x,
                position.y,
                team.getColor(),
                null,
                header,
                "0-0 " + TreeHandler.getYearFormat()
        );
        Renderer.scene.addEntity(this);
        Renderer.window.addPanel(panel);
        Renderer.window.refresh();

        System.out.println("Created new card at " + panel.getX() + "," + panel.getY());
    }

    public Card(String id, Vector2i position, Team team, Vector2i size) {
        super(position);
        this.id = id;
        this.team = team;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
        this.header = "New Card";
        this.duration = new Duration(0, 0);

        panel = new BoxPanel(
                position.x,
                position.y,
                team.getColor(),
                size,
                header,
                "0-0 "  + TreeHandler.getYearFormat()
        );
        Renderer.scene.addEntity(this);
        Renderer.window.addPanel(panel);
        Renderer.window.refresh();

        System.out.println("Created new card at " + panel.getX() + "," + panel.getY());
    }

    @Override
    public void update() {
        for (Card child : children) {
            TreeHandler.updateCardLine(this, child);
        }
        for (Card parent : parents) {
            TreeHandler.updateCardLine(parent, this);

            if (TreeHandler.getMode() == LineMode.HIGH_CENTER) {
                for (Card sibling : parent.getChildren()) {
                    TreeHandler.updateCardLine(parent, sibling);
                }
            }
        }
        panel.setBackground(team.getColor());
    }

    public String getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void addChild(Card child) {
        children.add(child);
        child.addParent(this);
        TreeHandler.drawCardLine(this, child);
        Renderer.window.getContainer().repaint();
    }

    public void removeChild(Card child) {
        children.remove(child);
        child.parents.clear();
        TreeHandler.removeCardLine(this, child);
        Renderer.window.getContainer().repaint();
    }

    public void removeChildren() {
        final ArrayList<Card> childrenBuffer = new ArrayList<>(children);
        for (Card child : childrenBuffer) {
            removeChild(child);
        }
        children.clear();
    }

    public void addParent(Card parent) {
        parents.add(parent);
    }

    public Card getHighestChild() {
        int y = children.getFirst().getPosition().y;
        Card returncard = null;
        for (Card child : children) {
            if (child.getPosition().y <= y) {
                y = child.getPosition().y;
                returncard = child;
            }
        }
        return returncard;
    }

    public void updateChildren() {
        for (Card child : children) {
            child.update();
        }
    }

    public boolean isChildOf(Card parent) {
        return parents.contains(parent);
    }

    public boolean isParentOf(Card child) {
        return children.contains(child);
    }

    public boolean isSiblingOf(Card sibling) {
        return getSiblings().contains(sibling);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
        JLabel headercomp = (JLabel) panel.getComponent(1);
        headercomp.setText(header);
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
        JLabel durationcomp = (JLabel) panel.getComponent(3);
        durationcomp.setText(duration.get());
    }

    public void setDuration(int from, int to) {
        this.duration = new Duration(from, to);
        JLabel durationcomp = (JLabel) panel.getComponent(3);
        durationcomp.setText(duration.get());
    }

    public void setPosition(Vector2i position) {
        panel.setLocation(position.x, position.y);
    }

    public Vector2i getPosition() {
        return new Vector2i(panel.getX(), panel.getY());
    }

    public int getWidth() {
        return panel.getWidth();
    }

    public int getHeight() {
        return panel.getHeight();
    }

    public boolean hasParents() {
        return !parents.isEmpty();
    }

    public List<Card> getParents() {
        return parents;
    }

    public List<Card> getChildren() {
        return children;
    }

    public List<Card> getSiblings() {
        List<Card> siblings = new ArrayList<>();
        for (Card other : getParents().getFirst().getChildren()) {
            if (other != this) {
                siblings.add(other);
            }
        }
        return siblings;
    }
}
