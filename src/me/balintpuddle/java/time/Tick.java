package me.balintpuddle.java.time;

import me.balintpuddle.java.io.Camera;
import me.balintpuddle.java.io.gui.GuiHandler;
import me.balintpuddle.java.io.input.Input;

import java.util.Timer;
import java.util.TimerTask;

public class Tick {
    private static int count;

    public static void start() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                everyTick();
            }
        };

        timer.schedule(task, 0, 1);
    }

    public static void everyTick() {
        if (Input.dragging) {
            Input.moveComponentToMouse();
        }
        GuiHandler.cordsLabel.setText(Camera.x + " " + Camera.y);
    }
}
