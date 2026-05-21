package io.gui;

import tree.card.Card;
import tree.card.Duration;

import javax.swing.*;

public class CardDialog implements iDialogInfo {
    JTextField headerfield, reignstart, reignend;

    @Override
    public <T> void create(JPanel panel, T modifiable) {
        Card card = (Card) modifiable;
        headerfield = new JTextField(card.getHeader(), 15);
        reignstart = new JTextField(String.valueOf(card.getDuration().from),15);
        reignend = new JTextField(String.valueOf(card.getDuration().to),15);

        panel.add(new JLabel("Enter the name:"));
        panel.add(headerfield);
        panel.add(new JLabel("Enter the start of their reign:"));
        panel.add(reignstart);
        panel.add(new JLabel("Enter the end of their reign:"));
        panel.add(reignend);
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
        }
    }
}
