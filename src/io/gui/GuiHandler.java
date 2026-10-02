package io.gui;

import io.gui.dialog.CardDialog;
import io.input.InteractionMode;
import io.renderer.Renderer;
import tree.card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;
import java.util.ArrayList;

public class GuiHandler {
    public static JLabel cordsLabel;
    private static ArrayList<JButton> buttons;
    private static GuiPanel sidebar;
    private static JButton moveButton, addButton, editButton, connectionButton, deleteButton;

    private static InteractionMode mode;

    public static void init() {
        sidebar = new GuiPanel();
        sidebar.setSize(Renderer.window.getHeight()/15, Renderer.window.getHeight()/3);
        sidebar.setLocation(10, 10);
        sidebar.setLayout(new GridLayout(5, 1, 0, 1));
        sidebar.setBackground(Color.GRAY);
        sidebar.setVisible(true);

        buttons = new ArrayList<>();
        addButtons(sidebar);

        cordsLabel = new JLabel("0 0");
        cordsLabel.setSize(100, 100);
        cordsLabel.setForeground(Color.BLACK);
        cordsLabel.setLocation(Renderer.window.getWidth()/51, (int) (Renderer.window.getHeight()/1.2));

        Renderer.window.addPanel(sidebar);
        Renderer.window.getContainer().add(cordsLabel);
        Renderer.refresh();

        mode = InteractionMode.MOVE;
    }

    public static void onWindowResize(int newWidth, int newHeight) {
        sidebar.setSize(newHeight/15, newHeight/3);
    }

    private static void addButtons(GuiPanel toolpanel) {
        moveButton = createButton("/resources/moveicon.png");
        addButton = createButton("/resources/addicon.png");
        editButton = createButton("/resources/editicon.png");
        connectionButton = createButton("/resources/connecticon.png");
        deleteButton = createButton("/resources/deleteicon.png");

        toggleButtonVisuals(0);

        //CLICK ACTION LISTENERS -------------------------------------------

        moveButton.addActionListener(_ -> {
            System.out.println("Move button pressed");
            setMode(InteractionMode.MOVE);
            toggleButtonVisuals(0);
        });

        addButton.addActionListener(_ -> {
            System.out.println("Add button pressed");
            setMode(InteractionMode.ADD);
            toggleButtonVisuals(1);
        });

        editButton.addActionListener(_ -> {
            System.out.println("Edit button pressed");
            setMode(InteractionMode.EDIT);
            toggleButtonVisuals(2);
        });

        connectionButton.addActionListener(_ -> {
            System.out.println("Connection button pressed");
            setMode(InteractionMode.CONNECT);
            toggleButtonVisuals(3);
        });

        deleteButton.addActionListener(_ -> {
            System.out.println("Delete button pressed");
            setMode(InteractionMode.DELETE);
            toggleButtonVisuals(4);
        });

        toolpanel.add(moveButton);
        toolpanel.add(addButton);
        toolpanel.add(editButton);
        toolpanel.add(connectionButton);
        toolpanel.add(deleteButton);
        toolpanel.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private static JButton createButton(String iconpath) {
        JButton button = new JButton();
        button.setFocusable(false);
        button.setEnabled(false);
        button.setBackground(Color.WHITE);

        URL buttonIconURL = GuiHandler.class.getResource(iconpath);

        if (buttonIconURL != null) {
            ImageIcon moveicon = new ImageIcon(buttonIconURL);
            Image scaled = moveicon.getImage().getScaledInstance(Renderer.window.getHeight()/20, Renderer.window.getHeight()/20, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaled));
        }
        buttons.add(button);

        button.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ImageIcon moveicon = new ImageIcon(buttonIconURL);
                Image scaled = moveicon.getImage().getScaledInstance(Renderer.window.getHeight()/20, Renderer.window.getHeight()/20, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaled));
            }
        });

        return button;
    }

    public static void openCardEditor(Card card) {
        Renderer.window.createDialog("Card editor: " + card.getHeader(), 300, 200, new CardDialog(), card);
    }

    public static InteractionMode getMode() {
        return mode;
    }

    public static void setMode(InteractionMode mode) {
        GuiHandler.mode = mode;
    }

    public static void toggleButtonVisuals(int index) {
        for (JButton button : buttons) {
            if (button == buttons.get(index)) {
                button.setEnabled(false);
            }
            else {
                button.setEnabled(true);
            }
        }
    }
}