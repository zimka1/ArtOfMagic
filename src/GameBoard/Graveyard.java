package GameBoard;

import Cards.Card;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a graveyard in a card game, where cards that are no longer in play are stored.
 * This class manages the collection of discarded or used cards.
 */
public class Graveyard {
    private List<Card> atTheGraveyard;

    /**
     * Constructs a new graveyard to hold cards.
     */
    public Graveyard() {
        this.atTheGraveyard = new ArrayList<>();
    }

    /**
     * Retrieves the list of cards currently in the graveyard.
     *
     * @return A list of cards that have been moved to the graveyard.
     */
    public List<Card> getCards() {
        return atTheGraveyard;
    }

    /**
     * Adds a card to the graveyard.
     *
     * @param card The card to add to the graveyard.
     */
    public void sendCardToGraveyard(Card card) {
        atTheGraveyard.add(card);
    }
}
