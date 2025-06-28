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
        TreeHandler.addCard(new Vector2i(5, 2), TeamHandler.teams.get(0), CardSizeTypes.MEDIUM);
        TreeHandler.addCard(new Vector2i(3, 5), TeamHandler.teams.get(0), CardSizeTypes.SMALL);

        Tick.start();
    }
}