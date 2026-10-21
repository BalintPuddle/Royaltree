package balintpuddle.io.gui.menu;

import balintpuddle.tree.team.Team;

import javax.swing.*;
import java.awt.*;

public class TeamPanel extends JPanel {
    private final Team team;

    public TeamPanel(Team team) {
        super();
        this.team = team;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.GRAY);
        setMaximumSize(new Dimension(500, 50));
    }

    public Team getTeam() {
        return team;
    }
}
