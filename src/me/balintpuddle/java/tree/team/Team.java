package me.balintpuddle.java.tree.team;

import java.awt.*;

public class Team {
    private final int id;
    private String name;
    private Color color;

    public Team(String name, Color color) {
        this.id = TeamHandler.getCount();
        this.name = name;
        this.color = color;
    }

    public Team() {
        this.id = TeamHandler.getCount();
        this.name = "Team " + (TeamHandler.teams.size() + 1);
        this.color = Color.BLACK;
    }

    public int getId() {
        return id;
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
