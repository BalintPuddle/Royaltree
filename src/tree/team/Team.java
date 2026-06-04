package tree.team;

import java.awt.*;

public class Team {
    private String name;
    private Color color;

    public Team(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public Team() {
        this.name = "Team " + (TeamHandler.teams.size() + 1);
        this.color = Color.BLACK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
