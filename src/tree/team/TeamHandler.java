package tree.team;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TeamHandler {
    public static List<Team> teams = new ArrayList<>();

    public static void init() {
        createTeam("default", Color.BLUE);
    }

    public static void createTeam(String name, Color color) {
        Team team = new Team();
        team.name = name;
        team.color = color;
        teams.add(team);
    }

    public Team get(String name) {
        for (Team team : teams) {
            if (team.name.equals(name)) {
                return team;
            }
        }
        return null;
    }
}
