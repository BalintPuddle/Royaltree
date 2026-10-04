package balintpuddle.io.file;

import balintpuddle.tree.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int formatVersion;
    private List<SerializedCard> cards;

    public Project() {

    }
    public Project(int formatVerison, List<Card> cards) {
        this.formatVersion = formatVerison;
        this.cards = new ArrayList<>();

        for (Card card : cards) {
            SerializedCard serializedCard = new SerializedCard(card);
            this.cards.add(serializedCard);
        }
    }

    public int getFormatVersion() {
        return formatVersion;
    }

    public List<SerializedCard> getCards() {
        return cards;
    }
}
