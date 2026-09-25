package io.gui;

import tree.team.Team;
import utils.Vector4i;

import javax.swing.*;
import java.awt.*;

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
