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
        TreeHandler.addCard(new Vector2i(3, 5), TeamHandler.teams.getFirst(), CardSizeTypes.SMALL);
        TreeHandler.addCard(new Vector2i(5, 2), TeamHandler.teams.getFirst(), CardSizeTypes.MEDIUM);
        TreeHandler.addCard(new Vector2i(6, 6), TeamHandler.teams.getFirst(), CardSizeTypes.MEDIUM);

        Tick.start();
    }
}