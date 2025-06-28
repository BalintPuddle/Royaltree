package tree;

import io.Renderer;
import tree.team.Team;
import utils.Vector2i;

import javax.swing.*;

public class Card {
    private Card[] parent;
    private Card[] Children;
    private Team team;

    private JPanel panel;

    public Card(Vector2i position, Team team) {
        this.team = team;

        JPanel panel = Renderer.window.createBoxPanel(
                position.x,
                position.y,
                team.color,
                null
        );
        Renderer.window.addPanel(panel);
        this.panel = panel;

        System.out.println("Created new card at " + panel.getX() + "," + panel.getY());
    }

    public Card(Vector2i position, Team team, Vector2i size) {
        this.team = team;

        JPanel panel = Renderer.window.createBoxPanel(
                position.x,
                position.y,
                team.color,
                size
        );
        Renderer.window.addPanel(panel);
        this.panel = panel;

        System.out.println("Created new card at " + panel.getX() + "," + panel.getY());
    }

    public void setPosition(Vector2i position) {
        panel.setLocation(position.x, position.y);
    }

    public Vector2i getPosition() {
        return new Vector2i(panel.getX(), panel.getY());
    }
}
