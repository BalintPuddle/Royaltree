package time;

import io.Camera;
import io.GuiHandler;
import io.Input;
import io.Renderer;

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
