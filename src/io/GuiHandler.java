package io;

import javax.swing.*;
import java.awt.*;

public class GuiHandler {
    public static JLabel cordsLabel;
    public static void init() {
        JPanel cordspanel = new JPanel();
        cordspanel.setSize(100, 30);
        cordspanel.setLocation(0, 0);
        cordspanel.setBackground(Color.BLACK);
        cordspanel.setVisible(true);

        cordsLabel = new JLabel("0 0", JLabel.CENTER);
        cordsLabel.setSize(cordspanel.getWidth(), cordspanel.getHeight());
        cordsLabel.setForeground(Color.white);

        cordspanel.add(cordsLabel);

        Renderer.window.refresh();
        Renderer.window.addPanel(cordspanel);
    }
}
