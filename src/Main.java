import io.Camera;
import io.GuiHandler;
import io.Renderer;
import time.Tick;
import tree.CardSizeTypes;
import tree.team.TeamHandler;
import tree.TreeHandler;
import utils.Vector2i;

public class Main {
    public static void main(String[] args) {
        init();
        System.out.println("Started successfully!");
    }

    public static void init() {
        Renderer renderer = new Renderer();
        TeamHandler.init();
        GuiHandler.init();

        TreeHandler.addCard(new Vector2i(3, 5), TeamHandler.getdefault(), CardSizeTypes.SMALL);
        TreeHandler.addCard(new Vector2i(5, 2), TeamHandler.getdefault(), CardSizeTypes.MEDIUM);
        TreeHandler.addCard(new Vector2i(6, 6), TeamHandler.getdefault(), CardSizeTypes.MEDIUM);

        Tick.start();
    }
}