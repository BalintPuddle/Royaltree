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

        TeamHandler.createTeam("solleno", Color.RED);

        Card albert1 = TreeHandler.createCard("albert1", new Vector2i(3, 5), TeamHandler.get("solleno"), CardSizeTypes.SMALL);
        albert1.setHeader("Albert I");
        albert1.setDuration(new Duration(1100, 1147));

        Card albert2 = TreeHandler.createCard("albert2", new Vector2i(6, 10), TeamHandler.get("solleno"), CardSizeTypes.SMALL);
        albert2.setHeader("Albert II");
        albert2.setDuration(new Duration(1147, 1165));

        Card alonso = TreeHandler.createCard("alonso", new Vector2i(0, 10), TeamHandler.get("solleno"), CardSizeTypes.SMALL);
        alonso.setHeader("Prince Alonso");
        alonso.setDuration(new Duration(1147, 1165));

        Card alonso1 = TreeHandler.createCard("alonso1", new Vector2i(0, 15), TeamHandler.get("solleno"), CardSizeTypes.SMALL);
        alonso1.setHeader("Alonso I.");
        alonso1.setDuration(new Duration(1147, 1165));

        albert1.addChild(albert2);
        albert1.addChild(alonso);
        alonso.addChild(alonso1);

        Tick.start();
    }
}