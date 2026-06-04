package io.gui;

import io.Renderer;
import tree.card.Card;

import javax.swing.*;
import java.awt.*;

public class GuiHandler {
    public static JLabel cordsLabel;
    private static JButton moveButton, editbutton, connectiontool, settings;

    private static InteractionMode mode;

    public static void init() {
        GuiPanel toolpanel = new GuiPanel();
        toolpanel.setSize(io.Renderer.window.getWidth(), 30);
        toolpanel.setLocation(0, 0);
        toolpanel.setLayout(new BoxLayout(toolpanel, BoxLayout.X_AXIS));
        toolpanel.setBackground(Color.GRAY);
        toolpanel.setVisible(true);

        cordsLabel = new JLabel("0 0");
        cordsLabel.setSize(toolpanel.getWidth(), toolpanel.getHeight());
        cordsLabel.setForeground(Color.white);

        moveButton = new JButton("Move");
        moveButton.setFocusable(false);
        moveButton.setEnabled(false);
        moveButton.addActionListener(e -> {
            System.out.println("Move button pressed");
            setMode(InteractionMode.MOVE);
            moveButton.setEnabled(false);
            editbutton.setEnabled(true);
            connectiontool.setEnabled(true);
            connectiontool.setEnabled(true);
        });
        editbutton = new JButton("Edit");
        editbutton.setFocusable(false);
        editbutton.addActionListener(e -> {
            System.out.println("Edit button pressed");
            setMode(InteractionMode.EDIT);
            moveButton.setEnabled(true);
            editbutton.setEnabled(false);
            connectiontool.setEnabled(true);
        });
        connectiontool = new JButton("Connection");
        connectiontool.setFocusable(false);
        connectiontool.addActionListener(e -> {
            System.out.println("Connection button pressed");
            setMode(InteractionMode.CONNECT);
            moveButton.setEnabled(true);
            editbutton.setEnabled(true);
            connectiontool.setEnabled(false);
        });
        settings = new JButton("Settings");
        settings.setFocusable(false);
        settings.addActionListener(e -> {
            System.out.println("Settings button pressed");
            Renderer.window.createDialog("Global Settings", Renderer.window.getWidthPercent(45), Renderer.window.getHeightPercent(40f), new SettingsDialog(), null);
        });

        toolpanel.add(Box.createRigidArea(new Dimension(io.Renderer.window.getWidthPercent(1f), 0)));
        toolpanel.add(moveButton);
        toolpanel.add(Box.createRigidArea(new Dimension(io.Renderer.window.getWidthPercent(0.1f), 0)));
        toolpanel.add(editbutton);
        toolpanel.add(Box.createRigidArea(new Dimension(io.Renderer.window.getWidthPercent(0.1f), 0)));
        toolpanel.add(connectiontool);
        toolpanel.add(Box.createRigidArea(new Dimension(io.Renderer.window.getWidthPercent(0.1f), 0)));
        toolpanel.add(settings);
        toolpanel.add(Box.createHorizontalGlue());
        toolpanel.add(cordsLabel);
        toolpanel.add(Box.createRigidArea(new Dimension(30, 0)));

        io.Renderer.window.refresh();
        Renderer.window.addPanel(toolpanel);

        mode = InteractionMode.MOVE;
    }

    public static void openCardEditor(Card card) {
        Renderer.window.createDialog("Card editor: " + card.getHeader(), 300, Renderer.window.getHeightPercent(13f), new CardDialog(), card);
    }

    public static InteractionMode getMode() {
        return mode;
    }

    public static void setMode(InteractionMode mode) {
        GuiHandler.mode = mode;
    }
}