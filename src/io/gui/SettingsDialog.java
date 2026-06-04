package io.gui;

import io.Renderer;
import tree.team.Team;
import tree.team.TeamHandler;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog implements iDialogInfo {
    private int page;
    private int teamselector;

    @Override
    public <T> void create(JPanel panel, T modifiable) {
        panel.setLayout(new BorderLayout(10, 10));
        JPanel navbar = new JPanel();
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel content = new JPanel();
        content.setBackground(Color.LIGHT_GRAY);
        content.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton generalbtn = new JButton("General");
        generalbtn.addActionListener(e -> {
            System.out.println("General button pressed");
            page = 0;
            buildContents(0, content);
        });

        JButton teamsbtn = new JButton("Teams");
        teamsbtn.addActionListener(e -> {
            System.out.println("Teams button pressed");
            page = 1;
            buildContents(1, content);
        });
        JButton linesbtn = new JButton("Lines");
        linesbtn.addActionListener(e -> {
            System.out.println("Lines button pressed");
            page = 2;
            buildContents(2, content);
        });
        navbar.add(generalbtn);
        navbar.add(teamsbtn);
        navbar.add(linesbtn);

        panel.add(navbar, BorderLayout.PAGE_START);
        panel.add(content, BorderLayout.CENTER);

        teamselector = 0;

        buildContents(page, content);
    }

    @Override
    public <T> void action(T modifiable) {

    }

    private void buildContents(int page, JPanel panel) {
        panel.removeAll();
        switch (page) {
            case 0:
                panel.setLayout(new FlowLayout(FlowLayout.LEFT));
                panel.add(new JLabel("Grid Size:"));
                JTextField gridfield = new JTextField(String.valueOf(Renderer.window.getGridSize()), 15);
                panel.add(gridfield);
                panel.add(new JLabel("Show grid:"));
                break;
            case 1:
                panel.setLayout(new GridLayout(0, 2));

                panel.add(buildTeamSection(0, panel));
                panel.add(buildTeamSection(1, panel));
                break;
            case 2:
                break;
        }

        panel.revalidate();
        panel.repaint();
    }

    private JPanel buildTeamSection(int side, JPanel root) {
        if (side == 0) {
            JPanel listpane = new JPanel();
            listpane.setLayout(new BoxLayout(listpane, BoxLayout.Y_AXIS));
            listpane.setBackground(Color.LIGHT_GRAY);

            for (Team team : TeamHandler.teams) {
                JPanel teampanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                teampanel.setBackground(Color.GRAY);
                teampanel.setMaximumSize(new Dimension(500, 50));
                teampanel.add(new JLabel(team.getName()));
                JPanel coloricon = new JPanel();
                coloricon.setPreferredSize(new Dimension(30, 30));
                coloricon.setBackground(team.getColor());
                teampanel.add(coloricon);
                listpane.add(teampanel, Component.LEFT_ALIGNMENT);
                listpane.add(Box.createRigidArea(new Dimension(0, 10)));
            }
            return listpane;
        }
        else {
            JPanel editpane = new JPanel();
            editpane.setLayout(new BorderLayout(5, 5));
            JPanel edittop = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JButton createteambtn = new JButton("Create Team");
            createteambtn.addActionListener(e -> {
                if (TeamHandler.insideLimit()) {
                    Team team = new Team();
                    TeamHandler.teams.add(team);
                }
                buildContents(1, root);
            });
            edittop.add(createteambtn);
            editpane.add(edittop, BorderLayout.PAGE_START);
            return editpane;
        }
    }
}
