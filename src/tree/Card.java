package tree;

import io.Renderer;
import tree.team.Team;
import utils.Vector2i;

import javax.swing.*;

public class Card {
    private final String id;
    private Card[] parent;
    private Card[] Children;
    private Team team;

    private String header;
    private Duration duration;
    private String title;

    private JPanel panel;

    public Card(String id, Vector2i position, Team team) {
        this.id = id;
        this.team = team;

        JPanel panel = Renderer.window.createBoxPanel(
                position.x,
                position.y,
                team.color,
                null,
                "Default Header",
                "0AD - 50 AD"
        );
        Renderer.window.addPanel(panel);
        Renderer.window.refresh();
        this.panel = panel;

        System.out.println("Created new card at " + panel.getX() + "," + panel.getY());
    }

    public Card(String id, Vector2i position, Team team, Vector2i size) {
        this.id = id;
        this.team = team;

        JPanel panel = Renderer.window.createBoxPanel(
                position.x,
                position.y,
                team.color,
                size,
                "Default Header",
                "0AD - 50 AD"
        );
        Renderer.window.addPanel(panel);
        this.panel = panel;

        System.out.println("Created new card at " + panel.getX() + "," + panel.getY());
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
}
