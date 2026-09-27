package io.gui;

import io.renderer.Renderer;
import io.renderer.WindowPanel;
import tree.card.TreeHandler;
import tree.line.LineMode;
import tree.team.Team;
import tree.team.TeamHandler;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog implements iDialogInfo {
    private int page;
    private int teamselector;
    private JTextField gridfield, namefield;
    private JCheckBox showgrid;
    private JPanel colorvisual;
    private Color currentColor;
    private JSlider R_Slider, G_Slider, B_Slider;
    private JComboBox lineModesBox;

    @Override
    public <T> void create(JPanel panel, T modifiable) {
        panel.setLayout(new BorderLayout(10, 10));
        JPanel navbar = new JPanel();
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel content = new JPanel();
        content.setBackground(Color.LIGHT_GRAY);
        content.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton generalbtn = new JButton("General");
        generalbtn.addActionListener(_ -> {
            System.out.println("General button pressed");
            page = 0;
            buildPage(0, content);
        });

        JButton teamsbtn = new JButton("Teams");
        teamsbtn.addActionListener(_ -> {
            System.out.println("Teams button pressed");
            page = 1;
            buildPage(1, content);
        });
        JButton linesbtn = new JButton("Lines");
        linesbtn.addActionListener(_ -> {
            System.out.println("Lines button pressed");
            page = 2;
            buildPage(2, content);
        });
        navbar.add(generalbtn);
        navbar.add(teamsbtn);
        navbar.add(linesbtn);

        panel.add(navbar, BorderLayout.PAGE_START);
        panel.add(content, BorderLayout.CENTER);

        teamselector = 0;

        buildPage(page, content);
    }

    @Override
    public <T> void action(T modifiable) {
        switch (page) {
            case 0:
                Renderer.window.setGridSize(Integer.parseInt(gridfield.getText()));
                WindowPanel windowPanel = (WindowPanel) Renderer.window.getContainer();
                windowPanel.updateGrid(Renderer.window.getWidth(), Integer.parseInt(gridfield.getText()));

                Renderer.window.setShowGrid(showgrid.isSelected());
                break;
            case 1:
                TreeHandler.updateAllCards();
                break;
            case 2:
                switch (lineModesBox.getSelectedItem().toString()) {
                    case "HIGH CENTER":
                        TreeHandler.setMode(LineMode.HIGH_CENTER);
                        break;
                    case "LOW CENTER":
                        TreeHandler.setMode(LineMode.LOW_CENTER);
                        break;
                    case "CENTER":
                        TreeHandler.setMode(LineMode.CENTER);
                        break;
                }
                break;
        }
    }

    private void buildPage(int page, JPanel panel) {
        panel.removeAll();
        switch (page) {
            case 0:
                panel.setLayout(new GridLayout(1,2));
                OptionPanel options = new OptionPanel(8);
                panel.add(options);

                options.get(0).add(new JLabel("Grid Size:"));
                gridfield = new JTextField(String.valueOf(Renderer.window.getGridSize()), 15);
                options.get(0).add(gridfield);
                showgrid = new JCheckBox("Show grid", Renderer.window.isGridShowing());
                options.get(1).add(showgrid);

                break;
            case 1:
                panel.setLayout(new GridLayout(0, 2));

                panel.add(buildTeamSection(0, panel));
                panel.add(buildTeamSection(1, panel));
                break;
            case 2:
                panel.setLayout(new GridLayout(1,2));
                OptionPanel lineoptions = new OptionPanel(8);
                panel.add(lineoptions);

                String[] lineModes = {"HIGH CENTER", "LOW CENTER", "CENTER"};
                JLabel label = new JLabel("Line Mode:");
                lineoptions.get(0).add(label);
                lineModesBox = new JComboBox<>(lineModes);
                switch (TreeHandler.getMode()) {
                    case HIGH_CENTER -> lineModesBox.setSelectedIndex(0);
                    case LOW_CENTER -> lineModesBox.setSelectedIndex(1);
                    case CENTER -> lineModesBox.setSelectedIndex(2);
                }
                lineoptions.get(0).add(lineModesBox);

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
                TeamPanel teampanel = new TeamPanel(team);
                teampanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                teampanel.setBackground(Color.GRAY);
                teampanel.setMaximumSize(new Dimension(500, 50));
                teampanel.add(new JLabel(team.getName()));

                JPanel coloricon = new JPanel();
                coloricon.setPreferredSize(new Dimension(30, 30));
                coloricon.setBackground(team.getColor());
                teampanel.add(coloricon);

                JButton editbutton = new JButton("Edit");
                editbutton.setFocusable(false);
                editbutton.addActionListener(_ -> {
                    teamselector = TeamHandler.teams.indexOf(teampanel.getTeam());
                    namefield.setText(team.getName());
                    currentColor = team.getColor();
                    colorvisual.setBackground(currentColor);
                    R_Slider.setValue(currentColor.getRed());
                    G_Slider.setValue(currentColor.getGreen());
                    B_Slider.setValue(currentColor.getBlue());
                });

                JButton removebutton = new JButton("Remove");
                removebutton.setFocusable(false);

                teampanel.add(editbutton);
                teampanel.add(removebutton);
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
            createteambtn.addActionListener(_ -> {
                if (TeamHandler.insideLimit()) {
                    Team team = new Team();
                    TeamHandler.teams.add(team);
                }
                buildPage(1, root);
            });
            JButton applybutton = new JButton("Apply changes");
            applybutton.addActionListener(_ -> {
                TeamHandler.teams.get(teamselector).setName(namefield.getText());
                TeamHandler.teams.get(teamselector).setColor(currentColor);
                System.out.println(TeamHandler.teams.get(teamselector));
                buildPage(1, root);
            });
            edittop.add(createteambtn);
            edittop.add(applybutton);
            editpane.add(edittop, BorderLayout.PAGE_START);
            editpane.add(new JPanel(), BorderLayout.CENTER);

            JPanel editbottom =  new JPanel();
            editbottom.setBackground(Color.DARK_GRAY);
            editbottom.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // margins
            editbottom.setLayout(new BoxLayout(editbottom, BoxLayout.Y_AXIS));

            namefield = new JTextField(TeamHandler.teams.get(teamselector).getName(), 15);

            //COLOR PANEL ---------------------------------------
            JPanel colorpanel = new JPanel();

            colorpanel.setLayout(new BorderLayout(5, 5));

            colorvisual = new JPanel();
            colorvisual.setPreferredSize(new Dimension(50, 50));
            currentColor = TeamHandler.teams.get(teamselector).getColor();
            colorvisual.setBackground(currentColor);

            JPanel slidercontainer = new JPanel(new GridLayout(3, 2, 10, 0));
            JLabel RLabel = new JLabel("R: " + currentColor.getRed());
            JLabel GLabel = new JLabel("G: " + currentColor.getGreen());
            JLabel BLabel = new JLabel("B: " + currentColor.getBlue());


            R_Slider = new JSlider(0, 255, currentColor.getRed());
            R_Slider.addChangeListener(_ -> {
                currentColor = new Color(R_Slider.getValue(), currentColor.getGreen(), currentColor.getBlue());
                colorvisual.setBackground(currentColor);
                RLabel.setText("R: " + currentColor.getRed());
            });
            G_Slider = new JSlider(0, 255, currentColor.getGreen());
            G_Slider.addChangeListener(_ -> {
                currentColor  = new Color(currentColor.getRed(), G_Slider.getValue(), currentColor.getBlue());
                colorvisual.setBackground(currentColor);
                GLabel.setText("R: " + currentColor.getGreen());
            });
            B_Slider = new JSlider(0, 255, currentColor.getBlue());
            B_Slider.addChangeListener(_ -> {
                currentColor = new Color(currentColor.getRed(), currentColor.getGreen(), B_Slider.getValue());
                colorvisual.setBackground(currentColor);
                BLabel.setText("R: " + currentColor.getBlue());
            });
            slidercontainer.add(R_Slider);
            slidercontainer.add(RLabel);
            slidercontainer.add(G_Slider);
            slidercontainer.add(GLabel);
            slidercontainer.add(B_Slider);
            slidercontainer.add(BLabel);

            colorpanel.add(slidercontainer, BorderLayout.CENTER);
            colorpanel.add(colorvisual, BorderLayout.EAST);

            editbottom.add(namefield);
            editbottom.add(Box.createVerticalStrut(15));
            editbottom.add(colorpanel);

            editpane.add(editbottom, BorderLayout.PAGE_END);

            return editpane;
        }
    }
}
