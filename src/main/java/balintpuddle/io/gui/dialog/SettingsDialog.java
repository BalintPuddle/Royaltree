package balintpuddle.io.gui.dialog;

import balintpuddle.io.gui.menu.OptionPanel;
import balintpuddle.io.gui.menu.TeamPanel;
import balintpuddle.io.renderer.Renderer;
import balintpuddle.io.renderer.WindowPanel;
import balintpuddle.tree.card.TreeHandler;
import balintpuddle.tree.line.LineMode;
import balintpuddle.tree.team.Team;
import balintpuddle.tree.team.TeamHandler;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog implements iDialogInfo {
    private int page;
    private int teamselector;
    private JTextField gridfield, namefield, timesuffixfield;
    private JCheckBox showgrid;
    private JPanel colorvisual;
    private Color currentColor;
    private JComboBox lineModesBox;

    @Override
    public <T> void create(JPanel panel, T modifiable) {
        panel.setLayout(new BorderLayout(10, 10));
        JPanel navbar = new JPanel();
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel content = new JPanel();
        content.setLayout(new FlowLayout(FlowLayout.LEFT));

        if (modifiable != null) {
            page = (int) modifiable;
        }

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
                TreeHandler.setYearFormat(timesuffixfield.getText());
                break;
            case 1:
                TreeHandler.updateAllCards();
                break;
            case 2:
                switch (lineModesBox.getSelectedItem().toString()) {
                    case "HIGHEST CENTER":
                        TreeHandler.setMode(LineMode.HIGH_CENTER);
                        break;
                    case "INDIVIDUAL CENTER":
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

                String[] newCardTeamBehaviourList = {"Default", "Last used"};
                JComboBox<String> newCardTeamBehaviour = new JComboBox<>(newCardTeamBehaviourList);
                options.get(2).add(new JLabel("New card team assignment:"));
                options.get(2).add(newCardTeamBehaviour);

                options.get(3).add(new JLabel("Year format:"));
                timesuffixfield = new JTextField(TreeHandler.getYearFormat(), 15);
                options.get(3).add(timesuffixfield);


                break;
            case 1:
                panel.setLayout(new GridLayout(0, 2, 10, 0));

                panel.add(buildTeamSection(0, panel));
                panel.add(buildTeamSection(1, panel));
                break;
            case 2:
                panel.setLayout(new GridLayout(1,2));
                OptionPanel lineoptions = new OptionPanel(8);
                panel.add(lineoptions);

                String[] lineModes = {"HIGHEST CENTER", "INDIVIDUAL CENTER"};
                JLabel label = new JLabel("Line Mode:");
                lineoptions.get(0).add(label);
                lineModesBox = new JComboBox<>(lineModes);
                switch (TreeHandler.getMode()) {
                    case HIGH_CENTER -> lineModesBox.setSelectedIndex(0);
                    case CENTER -> lineModesBox.setSelectedIndex(1);
                }
                lineoptions.get(0).add(lineModesBox);

                break;
        }

        panel.revalidate();
        panel.repaint();
    }

    private JPanel buildTeamSection(int side, JPanel root) {
        //LEFT HALF OF THE PAGE <-------
        if (side == 0) {
            JPanel leftContainer = new JPanel(new BorderLayout(0, 0));

            //TOP SECTION -------------------------------------------
            JPanel topbar = new JPanel(new GridLayout(1, 2, 5, 5));
            topbar.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

            JButton createteambtn = new JButton("Create Team");
            createteambtn.addActionListener(_ -> {
                if (TeamHandler.insideLimit()) {
                    Team team = new Team();
                    TeamHandler.teams.add(team);
                }
                buildPage(1, root);
            });

            JButton clearallbtn = new JButton("Clear All");
            clearallbtn.addActionListener(_ -> {
                TeamHandler.reset();
                buildPage(1, root);
            });
            
            topbar.add(createteambtn);
            topbar.add(clearallbtn);
            topbar.setPreferredSize(new Dimension(100, 40));
            
            JPanel listpane = new JPanel();

            listpane.setLayout(new BoxLayout(listpane, BoxLayout.Y_AXIS));
            listpane.setBackground(Color.LIGHT_GRAY);
            listpane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            for (Team team : TeamHandler.teams) {
                TeamPanel teampanel = new TeamPanel(team);

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
                });

                JButton removebutton = new JButton("Remove");
                removebutton.setFocusable(false);
                removebutton.addActionListener(_ -> {
                    if (TeamHandler.teams.indexOf(teampanel.getTeam()) > 0) {
                        if (TeamHandler.teams.indexOf(teampanel.getTeam()) == teamselector) {
                            teamselector = 0;
                        }
                        TeamHandler.remove(TeamHandler.teams.indexOf(teampanel.getTeam()));
                        buildPage(1, root);
                    }
                });


                teampanel.add(editbutton);
                teampanel.add(removebutton);
                listpane.add(teampanel, Component.LEFT_ALIGNMENT);
                listpane.add(Box.createRigidArea(new Dimension(0, 10)));
            }

            leftContainer.add(topbar, BorderLayout.NORTH);
            leftContainer.add(listpane, BorderLayout.CENTER);
            
            return leftContainer;
        }
        //RIGHT HALF OF THE PAGE ------------>
        else {
            JPanel rightContainer = new JPanel(new GridLayout(4, 1, 5, 5));
            JPanel properties = new JPanel(new BorderLayout(0, 0));
            JPanel style = new JPanel(new BorderLayout(0, 0));
            JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));

            rightContainer.add(properties);
            rightContainer.add(style);
            rightContainer.add(new JPanel());
            rightContainer.add(actions);

            // PROPERTIES --------------------------------------------
            JPanel propTopbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
            propTopbar.setPreferredSize(new Dimension(100, 40));
            propTopbar.add(new JLabel("Properties"));
            properties.add(propTopbar, BorderLayout.NORTH);

            JPanel propName = new JPanel(new FlowLayout(FlowLayout.LEFT));
            propName.add(new JLabel("Name:"));
            namefield = new JTextField(20);
            namefield.setText(TeamHandler.get(teamselector).getName());
            propName.add(namefield);
            properties.add(propName, BorderLayout.CENTER);

            // STYLE -----------------------------------------------------------------
            JPanel styleTopbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
            styleTopbar.setPreferredSize(new Dimension(100, 40));
            styleTopbar.add(new JLabel("Style"));
            style.add(styleTopbar, BorderLayout.NORTH);

            JPanel styleColor = new JPanel(new FlowLayout(FlowLayout.LEFT));
            styleColor.add(new JLabel("Color:"));
            colorvisual = new JPanel();
            colorvisual.setPreferredSize(new Dimension(30, 30));
            colorvisual.setBackground(TeamHandler.get(teamselector).getColor());
            colorvisual.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            styleColor.add(colorvisual);
            currentColor  = TeamHandler.get(teamselector).getColor();

            JButton pickcolor = new JButton("Pick Color");
            pickcolor.addActionListener(_ -> {
               JColorChooser colorchooser = new JColorChooser();
               currentColor = JColorChooser.showDialog(null, "Pick Color", colorchooser.getColor());
               colorvisual.setBackground(currentColor);
            });

            styleColor.add(pickcolor);

            style.add(styleColor, BorderLayout.CENTER);

            JButton actionApply = new JButton("Apply Changes");
            actions.add(actionApply);
            actionApply.addActionListener(_ -> {
               Team team = TeamHandler.teams.get(teamselector);
               team.setName(namefield.getText());
               team.setColor(currentColor);
               buildPage(1, root);
            });

            return rightContainer;
        }
    }
}
