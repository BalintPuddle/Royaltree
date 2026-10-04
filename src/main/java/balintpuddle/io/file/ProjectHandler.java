package balintpuddle.io.file;

import balintpuddle.io.gui.GuiHandler;
import balintpuddle.io.renderer.Renderer;
import balintpuddle.scene.Scene;
import balintpuddle.tree.card.Card;
import balintpuddle.tree.card.CardSizeTypes;
import balintpuddle.tree.card.TreeHandler;
import balintpuddle.tree.team.TeamHandler;
import balintpuddle.utils.Vector2i;

import java.io.IOException;
import java.nio.file.Path;

public class ProjectHandler {
    public static void save() {
        try {
            ProjectSerializer.save(Path.of("Myproject.rtree"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void load() {
        try {
            Project project = ProjectSerializer.load(Path.of("Myproject.rtree"));
            constructScene(project);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void constructScene(Project project) {
        Renderer.scene = new Scene(Renderer.window);

        switch (ProjectSerializer.getFormatVersion()) {
            case 1:
                //Implementation for the loading the teams

                //Create the cards in the scene from the deserialized project data
                for (SerializedCard serial : project.getCards()) {
                    Card card = TreeHandler.createCard(
                            Renderer.window.PositionToGrid(Renderer.scene.worldToScreenSpace(serial.getX(), serial.getY())),
                            TeamHandler.get(serial.getTeamid()),
                            CardSizeTypes.SMALL);
                    card.setHeader(serial.getHeader());
                    card.setDuration(serial.getDuration());
                }

                //Loop through every deserialized project card again and connect them (add children)
                for (SerializedCard serialcard : project.getCards()) {
                    Card card = TreeHandler.getCard(serialcard.getId()); //Get the corresponding newly created scene card object
                    if (card != null) {
                        //Looping through the project card's children and adding them to the scene card
                        for (int id : serialcard.getChildren()) {
                            card.addChild(TreeHandler.getCard(id));
                        }
                    }
                }

                break;
        }
    }
}
