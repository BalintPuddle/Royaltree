package tree.team;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TeamHandler {
    public static List<Team> teams = new ArrayList<>();
    public static final int LIMIT = 10;

    public static void init() {
        createTeam("default", Color.BLUE);
        createTeam("yellow", Color.YELLOW);
        createTeam("green", Color.GREEN);
    }

    public static void createTeam(String name, Color color) {
        Team team = new Team(name, color);
        teams.add(team);
    }

    public static Team get(String name) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(name)) {
                return team;
            }
        }
        return null;
    }

    public static Team getdefault() {
        return teams.getFirst();
    }

    public static boolean insideLimit() {
        return teams.size() < LIMIT;
    }
}
