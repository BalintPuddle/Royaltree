package balintpuddle.io.file;

import balintpuddle.tree.card.Card;
import balintpuddle.tree.team.Team;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int formatVersion;
    private List<SerializedCard> cards;
    private List<SerializedTeam> teams;

    public Project() {

    }
    public Project(int formatVerison, List<Card> cards, List<Team> teams) {
        this.formatVersion = formatVerison;
        this.cards = new ArrayList<>();
        this.teams = new ArrayList<>();

        for (Card card : cards) {
            SerializedCard serializedCard = new SerializedCard(card);
            this.cards.add(serializedCard);
        }

        for (Team team : teams) {
            SerializedTeam serializedTeam = new SerializedTeam(team);
            this.teams.add(serializedTeam);
        }
    }

    public int getFormatVersion() {
        return formatVersion;
    }

    public List<SerializedCard> getCards() {
        return cards;
    }

    public List<SerializedTeam> getTeams() {
        return teams;
    }
}
