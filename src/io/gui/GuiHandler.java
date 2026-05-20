package io.gui;

import io.Renderer;
import tree.card.Card;

import javax.swing.*;
import java.awt.*;

public class GuiHandler {
    public static JLabel cordsLabel;
    private static JButton moveButton, editbutton;

    private static InteractionMode mode;

    public static void init() {
        GuiPanel cordspanel = new GuiPanel();
        cordspanel.setSize(io.Renderer.window.getWidth(), 30);
        cordspanel.setLocation(0, 0);
        cordspanel.setLayout(new BoxLayout(cordspanel, BoxLayout.X_AXIS));
        cordspanel.setBackground(Color.GRAY);
        cordspanel.setVisible(true);

        cordsLabel = new JLabel("0 0");
        cordsLabel.setSize(cordspanel.getWidth(), cordspanel.getHeight());
        cordsLabel.setForeground(Color.white);

        moveButton = new JButton("Move");
        moveButton.setFocusable(false);
        moveButton.setEnabled(false);
        moveButton.addActionListener(e -> {
            System.out.println("Move button pressed");
            setMode(InteractionMode.MOVE);
            moveButton.setEnabled(false);
            editbutton.setEnabled(true);
        });
        editbutton = new JButton("Edit");
        editbutton.setFocusable(false);
        editbutton.addActionListener(e -> {
            System.out.println("Edit button pressed");
            setMode(InteractionMode.EDIT);
            moveButton.setEnabled(true);
            editbutton.setEnabled(false);
        });

        cordspanel.add(Box.createRigidArea(new Dimension(io.Renderer.window.getWidth()/100, 0)));
        cordspanel.add(moveButton);
        cordspanel.add(editbutton);
        cordspanel.add(Box.createHorizontalGlue());
        cordspanel.add(cordsLabel);
        cordspanel.add(Box.createRigidArea(new Dimension(30, 0)));

        io.Renderer.window.refresh();
        Renderer.window.addPanel(cordspanel);

        mode = InteractionMode.MOVE;
    }

    public static void openCardEditor(Card card) {
        Renderer.window.createDialog("Card editor: " + card.getHeader());
    }

    public static InteractionMode getMode() {
        return mode;
    }

    public static void setMode(InteractionMode mode) {
        GuiHandler.mode = mode;
    }
}