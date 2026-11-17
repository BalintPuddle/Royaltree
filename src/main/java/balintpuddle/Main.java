package balintpuddle;


import balintpuddle.global.ImmutableFlags;
import balintpuddle.io.file.ProjectSerializer;
import balintpuddle.io.renderer.Renderer;
import balintpuddle.time.Tick;
import balintpuddle.tree.card.TreeHandler;
import balintpuddle.tree.team.TeamHandler;
import balintpuddle.utils.Log;

public class Main {
    public static void main(String[] args) {
        Log.info("STARTING initialization");
        init();
        Log.info("Successfully FINISHED initialization!");
        Log.info("Running version: " + ImmutableFlags.VERSION);
        Log.info("Resolution: " + Renderer.window.getFrame().getWidth() + "x" + Renderer.window.getFrame().getHeight());
    }

    public static void init() {
        Renderer.render();
        Log.info("Renderer initialized!");
        TeamHandler.init();
        Log.info("TeamHandler initialized!");
        TreeHandler.init();
        Log.info("TreeHandler initialized!");
        ProjectSerializer.init();
        Log.info("ProjectSerializer initialized!");
        Tick.start();
        Log.info("Tick started!");
    }
}