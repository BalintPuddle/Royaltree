package me.balintpuddle.java;

import me.balintpuddle.java.io.gui.GuiHandler;
import me.balintpuddle.java.io.renderer.Renderer;
import me.balintpuddle.java.time.Tick;
import me.balintpuddle.java.tree.card.TreeHandler;
import me.balintpuddle.java.tree.team.TeamHandler;

public class Main {
    public static void main(String[] args) {
        init();
        System.out.println("Started successfully!");
    }

    public static void init() {
        Renderer.render();
        TeamHandler.init();
        TreeHandler.init();
        GuiHandler.init();
        Tick.start();
    }
}