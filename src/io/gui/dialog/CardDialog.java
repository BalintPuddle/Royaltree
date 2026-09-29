package io.gui.dialog;

import tree.card.Card;
import tree.card.Duration;
import tree.team.TeamHandler;

import javax.swing.*;
import java.awt.*;

public class CardDialog implements iDialogInfo {
    JTextField headerfield, reignstart, reignend;
    JComboBox<String> teamcombo;

    @Override
    public <T> void create(JPanel panel, T modifiable) {
        panel.setLayout(new GridLayout(5, 1));
        Card card = (Card) modifiable;
        headerfield = new JTextField(card.getHeader(), 15);
        reignstart = new JTextField(String.valueOf(card.getDuration().from),15);
        reignend = new JTextField(String.valueOf(card.getDuration().to),15);

        // [NAME] INPUT FIELD ----------------
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel1.add(new JLabel("Enter the name:"));
        panel1.add(headerfield);

        // REIGN DURATION INPUT FIELDS ------------------------------
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.add(new JLabel("Enter Start:"));
        panel2.add(reignstart);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.LIGHT_GRAY);
        panel3.add(new JLabel("Enter End:"));
        panel3.add(reignend);

        //TEAM BOX SELECTOR ---------------------------------------
        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel4.add(new JLabel("Team:"));
        teamcombo = new JComboBox<>(TeamHandler.getTeamNames());
        teamcombo.setSelectedItem(card.getTeam().getName());
        panel4.add(teamcombo);

        panel.add(panel1);
        panel.add(panel4);
        panel.add(new JLabel("Reign:"));
        panel.add(panel2);
        panel.add(panel3);
    }

    @Override
    public <T> void action(T modifiable) {
        Card card = (Card) modifiable;
        String name = headerfield.getText();
        String start = reignstart.getText();
        String end = reignend.getText();
        if (!name.isEmpty()) {
            card.setHeader(name);
            card.setDuration(new Duration(Integer.parseInt(start), Integer.parseInt(end)));
            card.setTeam(TeamHandler.get((String) teamcombo.getSelectedItem()));
            card.update();
        }
    }
}
