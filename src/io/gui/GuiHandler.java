package io.gui;

import io.gui.dialog.CardDialog;
import io.input.InteractionMode;
import io.renderer.Renderer;
import tree.card.Card;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class GuiHandler {
    public static JLabel cordsLabel;
    private static JButton moveButton, addButton, editButton, connectionButton, deleteButton;

    private static InteractionMode mode;

    public static void init() {
        GuiPanel sidebar = new GuiPanel();
        sidebar.setSize(Renderer.window.getWidth()/30, Renderer.window.getHeight()/3);
        sidebar.setLocation(10, 10);
        sidebar.setLayout(new GridLayout(5, 1, 0, 1));
        sidebar.setBackground(Color.GRAY);
        sidebar.setVisible(true);

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

    private static void addButtons(GuiPanel toolpanel) {
        moveButton = new JButton();
        moveButton.setFocusable(false);
        moveButton.setEnabled(false);
        moveButton.setBackground(Color.WHITE);

        URL moveIconURL = GuiHandler.class.getResource("/resources/moveicon.png");

        if (moveIconURL != null) {
            ImageIcon moveicon = new ImageIcon(moveIconURL);
            moveButton.setIcon(moveicon);
        }

        addButton = new JButton();
        addButton.setFocusable(false);
        addButton.setBackground(Color.WHITE);

        URL addIconURL = GuiHandler.class.getResource("/resources/addicon.png");

        if (addIconURL != null) {
            ImageIcon addicon = new ImageIcon(addIconURL);
            addButton.setIcon(addicon);
        }

        editButton = new JButton();
        editButton.setFocusable(false);
        editButton.setBackground(Color.WHITE);

        URL editIconURL = GuiHandler.class.getResource("/resources/editicon.png");

        if (editIconURL != null) {
            ImageIcon editicon = new ImageIcon(editIconURL);
            editButton.setIcon(editicon);
        }

        connectionButton = new JButton();
        connectionButton.setFocusable(false);
        connectionButton.setBackground(Color.WHITE);

        URL connectIconURL = GuiHandler.class.getResource("/resources/connecticon.png");

        if (connectIconURL != null) {
            ImageIcon connecticon = new ImageIcon(connectIconURL);
            connectionButton.setIcon(connecticon);
        }

        deleteButton = new JButton();
        deleteButton.setFocusable(false);
        deleteButton.setBackground(Color.WHITE);

        URL deleteIconURL = GuiHandler.class.getResource("/resources/deleteicon.png");

        if (deleteIconURL != null) {
            ImageIcon delete = new ImageIcon(deleteIconURL);
            deleteButton.setIcon(delete);
        }

        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(moveButton);
        buttons.add(addButton);
        buttons.add(editButton);
        buttons.add(connectionButton);
        buttons.add(deleteButton);


        //CLICK ACTION LISTENERS -------------------------------------------

        moveButton.addActionListener(e -> {
            System.out.println("Move button pressed");
            setMode(InteractionMode.MOVE);
            toggleButtonVisuals(buttons, 0);
        });

        addButton.addActionListener(e -> {
            System.out.println("Add button pressed");
            setMode(InteractionMode.ADD);
            toggleButtonVisuals(buttons, 1);
        });

        editButton.addActionListener(e -> {
            System.out.println("Edit button pressed");
            setMode(InteractionMode.EDIT);
            toggleButtonVisuals(buttons, 2);
        });

        connectionButton.addActionListener(e -> {
            System.out.println("Connection button pressed");
            setMode(InteractionMode.CONNECT);
            toggleButtonVisuals(buttons, 3);
        });

        deleteButton.addActionListener(e -> {
            System.out.println("Delete button pressed");
            setMode(InteractionMode.DELETE);
            toggleButtonVisuals(buttons, 4);
        });

        toolpanel.add(moveButton);
        toolpanel.add(addButton);
        toolpanel.add(editButton);
        toolpanel.add(connectionButton);
        toolpanel.add(deleteButton);
        toolpanel.setBorder(BorderFactory.createLineBorder(Color.black));
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

    public static void toggleButtonVisuals(ArrayList<JButton> buttons, int index) {
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