package tree.card;

import io.Renderer;
import scene.Entity;
import tree.line.LineMode;
import tree.TreeHandler;
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
    private String title;

    public Card(String id, Vector2i position, Team team) {
        super(position);
        this.id = id;
        this.team = team;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();

        panel = Renderer.window.createBoxPanel(
                position.x,
                position.y,
                team.color,
                null,
                "Default Header",
                "0AD - 50 AD"
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

        panel = Renderer.window.createBoxPanel(
                position.x,
                position.y,
                team.color,
                size,
                "Default Header",
                "0AD - 50 AD"
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
    }

    public void addChild(Card child) {
        children.add(child);
        child.addParent(this);
        TreeHandler.drawCardLine(this, child);
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

    public String getId() {
        return id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Card> getParents() {
        return parents;
    }

    public List<Card> getChildren() {
        return children;
    }
}
