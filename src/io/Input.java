package io;

import io.gui.GuiHandler;
import io.gui.InteractionMode;
import tree.*;
import tree.card.Card;
import tree.card.CardSizeTypes;
import tree.team.TeamHandler;
import utils.Vector2i;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements KeyListener, MouseListener {
    private static final int MOVE_SPEED = 15;

    public static boolean dragging;
    public static Card[] connectionPair;
    public static Component selectedComponent;
    public static int selectedWidth, selectedHeight;

    public Input() {
        dragging = false;
        connectionPair = new Card[2];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            Renderer.scene.transformAll(0, -MOVE_SPEED);
        }
        if (e.getKeyChar() == 's') {
            Renderer.scene.transformAll(0, MOVE_SPEED);
        }
        if (e.getKeyChar() == 'a') {
            Renderer.scene.transformAll(-MOVE_SPEED, 0);
        }
        if (e.getKeyChar() == 'd') {
            Renderer.scene.transformAll(MOVE_SPEED, 0);
        }

        if (e.getKeyChar() == 'e') {
            Point point = MouseInfo.getPointerInfo().getLocation();

            TreeHandler.addCard(
                    "card_" + (TreeHandler.getCards().size() - 1),
                    Renderer.window.PositionToGrid(new Vector2i(point.x, point.y)),
                    TeamHandler.getdefault(),
                    CardSizeTypes.SMALL
            );
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //MOUSE EVENTS ------------------------------------------------

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (GuiHandler.getMode() == InteractionMode.MOVE) {
            if (e.getButton() == 1) {
                Component component = Renderer.window.getComponentAt(e.getX(), e.getY());
                if (component instanceof InteractablePanel) {
                    selectedComponent = component;
                    selectedWidth = component.getWidth();
                    selectedHeight = component.getHeight();
                    dragging = true;
                }
            }
        }
        else if (GuiHandler.getMode() == InteractionMode.EDIT) {
            if (e.getButton() == 1) {
                Component component = Renderer.window.getComponentAt(e.getX(), e.getY());
                if (component instanceof InteractablePanel) {
                    selectedComponent = component;
                    GuiHandler.openCardEditor(TreeHandler.getCardByPanel((JPanel) selectedComponent));
                }
            }
        }
        else if (GuiHandler.getMode() == InteractionMode.CONNECT) {
            if (e.getButton() == 1) {
                Component component = Renderer.window.getComponentAt(e.getX(), e.getY());
                if (component instanceof InteractablePanel) {
                    selectedComponent = component;
                    connectionPair[0] = TreeHandler.getCardByPanel((JPanel) selectedComponent);
                }
            }
            else if (e.getButton() == 3) {
                Component component = Renderer.window.getComponentAt(e.getX(), e.getY());
                if (component instanceof InteractablePanel) {
                    selectedComponent = component;
                    connectionPair[1] = TreeHandler.getCardByPanel((JPanel) selectedComponent);
                    TreeHandler.connect(connectionPair[0], connectionPair[1]);
                    connectionPair[0] = null;
                    connectionPair[1] = null;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        selectedComponent = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void moveComponentToMouse() {
        try {
            Vector2i newPos = Renderer.window.PositionToGridPosition(Renderer.scene.screenToWorldSpace(
                    MouseInfo.getPointerInfo().getLocation().x - selectedWidth/2,
                    MouseInfo.getPointerInfo().getLocation().y - selectedHeight/2)
            );
            if (selectedComponent != null) {
                selectedComponent.setLocation(newPos.x, newPos.y);
                Card card = TreeHandler.getCardByPanel((JPanel) selectedComponent);
                card.update();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}