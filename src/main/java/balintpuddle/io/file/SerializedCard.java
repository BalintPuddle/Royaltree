package balintpuddle.io.file;

import balintpuddle.io.renderer.Renderer;
import balintpuddle.scene.Scene;
import balintpuddle.tree.card.Card;
import balintpuddle.tree.card.Duration;

public class SerializedCard {
    private int id;
    private int x, y;
    private int teamid;
    private String header;
    private Duration duration;
    private int[] parents, children;

    public SerializedCard() {

    }
    public SerializedCard(Card card) {
        this.id = card.getId();
        this.x = Renderer.scene.screenToWorldSpace(card.getPosition()).x;
        this.y = Renderer.scene.screenToWorldSpace(card.getPosition()).y;

        this.teamid = card.getTeam().getId();
        this.header = card.getHeader();
        this.duration = card.getDuration();

        this.parents = new int[card.getParents().size()];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = card.getParents().get(i).getId();
        }

        this.children = new int[card.getChildren().size()];
        for (int i = 0; i < children.length; i++) {
            children[i] = card.getChildren().get(i).getId();
        }
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTeamid() {
        return teamid;
    }

    public String getHeader() {
        return header;
    }

    public Duration getDuration() {
        return duration;
    }

    public int[] getParents() {
        return parents;
    }

    public int[] getChildren() {
        return children;
    }
}
