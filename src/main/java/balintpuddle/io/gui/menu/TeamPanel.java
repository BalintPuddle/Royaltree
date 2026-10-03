package balintpuddle.io.gui.menu;

import balintpuddle.tree.team.Team;

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
