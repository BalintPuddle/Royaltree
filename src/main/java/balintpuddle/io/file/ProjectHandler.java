package balintpuddle.io.file;

import balintpuddle.global.MutableFlags;
import balintpuddle.io.gui.GuiHandler;
import balintpuddle.io.renderer.Renderer;
import balintpuddle.scene.Scene;
import balintpuddle.tree.card.Card;
import balintpuddle.tree.card.CardSizeTypes;
import balintpuddle.tree.card.TreeHandler;
import balintpuddle.tree.team.Team;
import balintpuddle.tree.team.TeamHandler;
import balintpuddle.utils.Vector2i;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ProjectHandler {
    //TODO: Atomic saving implementation

    public static void save() {
        if (MutableFlags.currentProjectPath == null) {
            saveAs();
        }
        else {
            try {
                ProjectSerializer.save(MutableFlags.currentProjectPath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Failed to save project:\n" + e.getMessage(),
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public static void saveAs() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Project");
        chooser.setSelectedFile(new File("project.rtree"));

        // Filter for .rtree
        chooser.setFileFilter(
                new FileNameExtensionFilter(
                        "Royaltree Project (*.rtree)",
                        "rtree"
                )
        );

        int result = chooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile  = chooser.getSelectedFile();

            if (!selectedFile.getName().toLowerCase().endsWith(".rtree")) {
                selectedFile = new File(selectedFile.getParentFile(),  selectedFile.getName() + ".rtree");
            }

            if (selectedFile.exists()) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "This file already exists. Overwrite it?",
                        "Confirm Overwrite",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            try {
                ProjectSerializer.save(selectedFile.toPath());
                MutableFlags.currentProjectPath = selectedFile.toPath();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Failed to save project:\n" + e.getMessage(),
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public static void load() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Open Project");

        // Filter for .rtree
        chooser.setFileFilter(
                new FileNameExtensionFilter(
                        "Royaltree Project (*.rtree)",
                        "rtree"
                )
        );

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            if (selectedFile.getName().endsWith(".rtree")) {
                try {
                    Project project = ProjectSerializer.load(selectedFile.toPath());
                    constructScene(project);
                    MutableFlags.currentProjectPath = selectedFile.toPath();
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(
                            null,
                            "Failed to load project:\n" + e.getMessage(),
                            "Load Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }

    private static void constructScene(Project project) {
        Renderer.scene = new Scene(Renderer.window);
        TreeHandler.clear();
        TeamHandler.clear();

        switch (ProjectSerializer.getFormatVersion()) {
            case 1:
                //Create the teams
                for (SerializedTeam serialteam : project.getTeams()) {
                    if (!project.getTeams().isEmpty()) {
                        TeamHandler.createTeam(serialteam.getName(), new Color(serialteam.getColor()));
                    }
                    else {
                        TeamHandler.init();
                    }
                }

                //Create the cards in the scene from the deserialized project data
                for (SerializedCard serial : project.getCards()) {
                    Card card = TreeHandler.createCardAtExact(
                            Renderer.scene.worldToScreenSpace(serial.getX(), serial.getY()),
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
