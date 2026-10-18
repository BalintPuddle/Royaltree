package balintpuddle.io.file;

import balintpuddle.tree.team.Team;
import balintpuddle.utils.Vector3i;

import java.awt.*;

public class SerializedTeam {
    private int id;
    private String name;
    private int color;

    public SerializedTeam() {}

    public SerializedTeam(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.color = team.getColor().getRGB();
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getColor() {
        return color;
    }
}
