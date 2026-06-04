import io.gui.GuiHandler;
import io.Renderer;
import time.Tick;
import tree.card.Card;
import tree.card.CardSizeTypes;
import tree.card.Duration;
import tree.team.TeamHandler;
import tree.TreeHandler;
import utils.Vector2i;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        init();
        System.out.println("Started successfully!");
    }

    public static void init() {
        Renderer.render();
        TeamHandler.init();
        GuiHandler.init();
        Tick.start();
    }
}