package io.gui.scenegui;

import tree.card.CardSizeTypes;
import utils.Vector2i;

import javax.swing.*;
import java.awt.*;

public class BoxPanel extends InteractablePanel {
    public BoxPanel(int x, int y, Color color, Vector2i size, String label0, String label1) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        if (size != null) {
            setSize(size.x, size.y);
        }
        else {
            setSize(CardSizeTypes.SMALL.x, CardSizeTypes.SMALL.y);
        }

        setLocation(x, y);
        setBackground(color);
        setVisible(true);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel header = new JLabel(label0, JLabel.CENTER);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setSize(getWidth(), getHeight());
        header.setForeground(Color.white);


        JLabel duration = new JLabel(label1, JLabel.CENTER);
        duration.setAlignmentX(Component.CENTER_ALIGNMENT);
        duration.setSize(getWidth(), getHeight());
        duration.setForeground(Color.white);

        add(Box.createRigidArea(new Dimension(0, getHeight()/4)));
        add(header);
        add(Box.createVerticalGlue());
        add(duration);
        add(Box.createRigidArea(new Dimension(0, getHeight()/4)));
    }

}
