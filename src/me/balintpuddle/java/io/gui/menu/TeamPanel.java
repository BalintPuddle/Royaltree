package me.balintpuddle.java.io.gui.menu;

import me.balintpuddle.java.tree.team.Team;

import javax.swing.*;

public class TeamPanel extends JPanel {
    private final Team team;

    public TeamPanel(Team team) {
        super();
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }
}
