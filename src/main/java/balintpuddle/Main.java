package main.java.balintpuddle;


import balintpuddle.io.file.ProjectSerializer;
import balintpuddle.io.gui.GuiHandler;
import balintpuddle.io.renderer.Renderer;
import balintpuddle.time.Tick;
import balintpuddle.tree.card.TreeHandler;
import balintpuddle.tree.team.TeamHandler;

public class Main {
    public static void main(String[] args) {
        init();
        System.out.println("Started successfully!");
    }

    public static void init() {
        Renderer.render();
        TeamHandler.init();
        TreeHandler.init();
        ProjectSerializer.init();
        Tick.start();
    }
}