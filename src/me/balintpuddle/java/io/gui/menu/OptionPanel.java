package me.balintpuddle.java.io.gui.menu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OptionPanel extends JPanel {
    private JPanel left, right;
    private List<JPanel> options;

    public OptionPanel(int rows) {
        setLayout(new GridLayout(1, 2));
        options = new ArrayList<>();
        left = new JPanel(new GridLayout(rows, 1, 2, 2));
        right = new JPanel(new GridLayout(rows, 1, 2, 2));

        add(left);
        add(right);

        for (int i = 0; i < rows * 2; i++) {
            if (i < rows) {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                left.add(panel);
                options.add(panel);
            }
            else {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                right.add(panel);
                options.add(panel);
            }
        }
    }

    public JPanel get(int index) {
        return options.get(index);
    }
}
