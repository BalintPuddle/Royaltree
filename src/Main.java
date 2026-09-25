import io.gui.GuiHandler;
import io.renderer.Renderer;
import time.Tick;
import tree.team.TeamHandler;

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