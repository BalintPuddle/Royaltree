import io.GuiHandler;
import io.LinePanel;
import io.Renderer;
import time.Tick;
import tree.Card;
import tree.CardSizeTypes;
import tree.Duration;
import tree.team.TeamHandler;
import tree.TreeHandler;
import utils.Vector2i;
import utils.Vector4i;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        init();
        System.out.println("Started successfully!");
    }

    public static void init() {
        Renderer renderer = new Renderer();
        TeamHandler.init();
        GuiHandler.init();

        TeamHandler.createTeam("solleno", Color.ORANGE);

        Card albert1 = TreeHandler.createCard("albert1", new Vector2i(3, 5), TeamHandler.get("solleno"), CardSizeTypes.SMALL);
        albert1.setHeader("Albert I");
        albert1.setDuration(new Duration(1100, 1147));

        Card albert2 = TreeHandler.createCard("albert2", new Vector2i(6, 10), TeamHandler.get("solleno"), CardSizeTypes.SMALL);
        albert2.setHeader("Albert II");
        albert2.setDuration(new Duration(1147, 1165));

        TreeHandler.drawCardLine(albert1, albert2);

        Tick.start();
    }
}