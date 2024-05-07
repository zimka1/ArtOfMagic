package GameBoard;

import Cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic deck of elements in a game, managing operations such as adding,
 * removing, and dealing elements.
 *
 * @param <T> the type of elements this deck will hold, must be a subtype of Card
 */
public class Deck<T extends Card> {
    private List<T> cards;

    /**
     * Constructs a new empty deck.
     */
    public Deck() {
        this.cards = new ArrayList<>();
    }

    /**
     * Adds a card to the deck.
     *
     * @param card The card to be added to the deck.
     */
    public void addCard(T card) {
        cards.add(card);
    }

    /**
     * Retrieves the list of cards currently in the deck.
     *
     * @return A list of cards in the deck.
     */
    public List<T> getCards(){
        return cards;
    }

    /**
     * Deals the last card from the deck, removing it in the process.
     * Returns null if the deck is empty.
     *
     * @return The card that was dealt from the deck, or null if the deck is empty.
     */
    public T giveCard(){
        if (!cards.isEmpty()) {
            return cards.remove(cards.size() - 1);
        }
        return null;
    }
}
